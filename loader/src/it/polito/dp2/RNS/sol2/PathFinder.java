package it.polito.dp2.RNS.sol2;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import it.polito.dp2.RNS.PlaceReader;
import it.polito.dp2.RNS.RnsReader;
import it.polito.dp2.RNS.RnsReaderException;
import it.polito.dp2.RNS.RnsReaderFactory;
import it.polito.dp2.RNS.lab2.BadStateException;
import it.polito.dp2.RNS.lab2.ModelException;
import it.polito.dp2.RNS.lab2.ServiceException;
import it.polito.dp2.RNS.lab2.UnknownIdException;
import it.polito.dp2.RNS.sol2.conf.Config;
import it.polito.dp2.RNS.sol2.jaxb.*;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.util.JAXBSource;
import javax.xml.stream.XMLStreamException;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI;

/**
 * PathFinder is an interface that has to be implemented in DP2 Assignment 2.
 * A PathFinder lets users find shortest paths in a set of interconnected places (the model).
 * A PathFinder exploits a remote service, capable of finding shortest paths in
 * a directed graph (in Assignment 2 this service is NEO4J).
 * A PathFinder must know how to get the current version of the model,
 * which may change from time to time.
 * Only when requested by the user, through the reloadModel operation, a PathFinder gets
 * (and loads) the current version of the model, which replaces any previously loaded model.
 * During this operation, the PathFinder also uploads the graph of this model to the remote
 * service. After this operation, the loaded model can be used to compute shortest paths,
 * by means of the findShortestPaths operation, until the next reloadModel, which may
 * cause the loaded model to change.
 * A PathFinder has 2 states:
 * 1. initial state: no model loaded (and no graph yet uploaded to remote service).
 * 2. operating state: model loaded (and graph uploaded to remote service and service ready
 * to respond to queries).
 * The current state can be checked by means of the isModelLoaded operation.
 */
public class PathFinder implements it.polito.dp2.RNS.lab2.PathFinder {

    /**
     * When set to true, sanity checks are enabled.
     */
    private static final boolean SANITY_CHECKS = false;

    /**
     * Web service interaction fields.
     */
    private RnsReader rnsReader;
    private Client client;
    private WebTarget baseTarget;

    /**
     * Local data fields.
     */
    private ObjectFactory objectFactory = new ObjectFactory();
    private BiMap<String, URI> nodes = HashBiMap.create();
    private Set<URI> relationships = new HashSet<>();

    /**
     * True when the data model has been loaded.
     */
    private boolean loaded = false;

    /**
     * Default constructor
     */
    public PathFinder() throws ServiceException {

        // Web service interaction fields initialization
        try {
            client = ClientBuilder.newClient();
            baseTarget = client.target(getBaseURI());
        } catch (RuntimeException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    /**
     * Returns the base URI of the Neo4JSimpleXML web service.
     *
     * @return base Neo4JSimpleXML's URI.
     */
    private static URI getBaseURI() throws ServiceException {
        String baseURI;
        try {
            baseURI = System.getProperty("it.polito.dp2.RNS.lab2.URL");
        } catch (SecurityException | NullPointerException e) {
            throw new ServiceException("Couldn't retrieve the web-service base URI: " + e.getMessage(), e.getCause());
        }

        return UriBuilder.fromUri(baseURI + "/data/").build();
    }

    /**
     * Class destructor.
     *
     * @throws Throwable
     */
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        client.close();
    }

    /**
     * Checks the current state
     *
     * @return true if the current state is the operating state (model loaded)
     */
    @Override
    public boolean isModelLoaded() {
        return loaded;
    }

    /**
     * Loads the current version of the model so that, if the operation is successful,
     * after the operation the PathFinder is in the operating state (model loaded) and
     * it can compute shortest paths on the loaded model.
     *
     * @throws ServiceException if the operation cannot be completed because the remote service is not available or fails
     * @throws ModelException   if the operation cannot be completed because the current model cannot be read or is wrong (the problem is not related to the remote service)
     */
    @Override
    public void reloadModel() throws ServiceException, ModelException {

        // If the model has previously been already downloaded
        if (loaded) {
            // Deleting the current model
            deleteModel();
        }
        loaded = false;

        // Data retriever
        reloadReader();

        // Reloading the model
        RnsType rnsValidationObject = objectFactory.createRnsType();
        readPlaces(rnsValidationObject);
        readConnections(rnsValidationObject);

        // Validating
        try {
            Schema schema = SchemaFactory.newInstance(W3C_XML_SCHEMA_NS_URI).newSchema(new File(Config.schemaFile));
            Validator validator = schema.newValidator();
            validator.setErrorHandler(new ErrorHandler() {

                @Override
                public void warning(SAXParseException e) throws SAXException {
                    // Printing additional info in case of a warning
                    System.out.println("Line:Col[" + e.getLineNumber() +
                            ":" + e.getColumnNumber() +
                            "]: " + e.getMessage());
                }

                @Override
                public void error(SAXParseException e) throws SAXException {
                    throw e;
                }

                @Override
                public void fatalError(SAXParseException e) throws SAXException {
                    throw e;
                }
            });
            JAXBContext jaxbContext = JAXBContext.newInstance(Config.jaxbClassesPackage);
            validator.validate(new JAXBSource(jaxbContext, objectFactory.createRnsInfo(rnsValidationObject)));
        } catch (FileNotFoundException e) {
            throw new ModelException("Could not find the input XML file: " + e.getMessage(), e.getCause());
        } catch (SAXException | IOException e) {
            throw new ModelException("Error while parsing RNS data: " + e.getMessage(), e.getCause());
        } catch (JAXBException e) {
            throw new ModelException("Error while creating a JAXB context class or unmarshalling: " + e.getMessage(), e.getCause());
        }

        // Model has been loaded correctly
        loaded = true;
    }

    /**
     * Reloads the data retriever, using the corresponding {@code RNSReaderFactory}.
     *
     * @throws ModelException in case it was not possible to create an RNS reader instance.
     */
    private void reloadReader() throws ModelException {
        try {
            rnsReader = RnsReaderFactory.newInstance().newRnsReader();
        } catch (RnsReaderException e) {
            throw new ModelException("Couldn't create an RNS reader instance: " + e.getMessage(), e.getCause());
        }
    }

    /**
     * Deletes all the data locally and on the web service.
     *
     * @throws ServiceException in case it was not possible to delete something on the web service.
     */
    private void deleteModel() throws ServiceException {

        // Delete relationships
        for (URI relationship : relationships) {
            deleteConnection(relationship);
        }

        // Delete nodes
        for (URI node : nodes.values()) {
            deletePlace(node);
        }

        if (SANITY_CHECKS) {
            assert nodes.isEmpty();
            assert relationships.isEmpty();
        }
    }

    /**
     * Sends information about RNS places on the web services and keeps track of these object URIs.
     *
     * @param rnsValidationObject the RNS XML object used for validation purposes.
     * @throws ServiceException in case it was not possible to upload a place to the web service.
     */
    private void readPlaces(RnsType rnsValidationObject) throws ServiceException {

        rnsValidationObject.setNodes(objectFactory.createRnsTypeNodes());
        for (PlaceReader place : rnsReader.getPlaces(null)) {
            NodeType newNode = postPlace(place);
            rnsValidationObject.getNodes().getNode().add(newNode);
        }
    }

    /**
     * Sends information about RNS connections on the web services and keeps track of these object URIs.
     *
     * @param rnsValidationObject the RNS XML object used for validation purposes.
     * @throws ServiceException in case it was not possible to upload a connection to the web service.
     */
    private void readConnections(RnsType rnsValidationObject) throws ServiceException {

        rnsValidationObject.setRelationships(objectFactory.createRnsTypeRelationships());
        for (PlaceReader place : rnsReader.getPlaces(null)) {
            for (PlaceReader nextPlace : place.getNextPlaces()) {
                RelationshipType newRelationship = postConnection(place, nextPlace);
                rnsValidationObject.getRelationships().getRelation().add(newRelationship);
            }
        }
    }

    /**
     * Posts a single place object to the web service and keeps track of its URI.
     *
     * @param place the place to be stored.
     * @return the DB node object just uploaded.
     * @throws ServiceException in case it was not possible to upload this place.
     */
    private NodeType postPlace(PlaceReader place) throws ServiceException {

        try {
            NodeType node = objectFactory.createNodeType();
            node.setId(place.getId());
            Response response = baseTarget.path("node").request(MediaType.APPLICATION_JSON).post(
                    Entity.entity(node, MediaType.APPLICATION_JSON));

            if (response.getStatus() == 201) {
                URI location = response.getLocation();
                System.out.println("Node " + place.getId() + " posted successfully at " + location);
                nodes.put(node.getId(), location);
            } else {
                System.out.println("Node " + place.getId() + " post failed: error " + response.getStatus());
            }

            return node;
        } catch (RuntimeException e) {
            throw new ServiceException("Error while posting node " + place.getId() + ": " + e.getMessage(),
                    e.getCause());
        }
    }

    /**
     * Deletes a single place object from the web service.
     *
     * @param placeURI the place URI to be deleted.
     * @throws ServiceException in case it was not possible to delete this place.
     */
    private void deletePlace(URI placeURI) throws ServiceException {

        try {
            Response response = client.target(placeURI).request(MediaType.APPLICATION_JSON).delete();

            if (response.getStatus() == 204) {
                System.out.println("Node " + placeURI + " successfully deleted.");
                nodes.inverse().remove(placeURI);
            } else {
                System.out.println("Node " + placeURI + " deletion error: error " + response.getStatus());
            }
        } catch (RuntimeException e) {
            throw new ServiceException("Error while deleting node " + placeURI + ": " + e.getMessage(), e.getCause());
        }
    }

    /**
     * Posts a single connection object to the web service and keeps track of its URI.
     *
     * @param src the source place from which this connection starts.
     * @param dst the destination place to which this connection ends.
     * @return the DB relationship object just uploaded.
     * @throws ServiceException in case it was not possible to upload this connection.
     */
    private RelationshipType postConnection(PlaceReader src, PlaceReader dst) throws ServiceException {

        try {
            RelationshipType relationship = objectFactory.createRelationshipType();
            relationship.setTo(nodes.get(dst.getId()).toASCIIString());
            relationship.setType("ConnectedTo");
            Response response = client.target(nodes.get(src.getId())).path("relationships")
                    .request(MediaType.APPLICATION_JSON).post(Entity.entity(relationship, MediaType.APPLICATION_JSON));

            if (response.getStatus() == 201) {
                URI location = response.getLocation();
                System.out.println("Relationship between " + src.getId() + " and " + dst.getId() +
                        " successfully posted at " + location);
                relationships.add(location);
            } else {
                System.out.println("Relationship between " + src.getId() + " and " + dst.getId() +
                        " post failed: error " + response.getStatus());
            }

            return relationship;
        } catch (RuntimeException e) {
            throw new ServiceException("Error while creating a relationship between " + src.getId() +
                    " and " + dst.getId() + ": " + e.getMessage(),
                    e.getCause());
        }
    }

    /**
     * Deletes a single connection object from the web service.
     *
     * @param connectionURI the connection URI to be deleted.
     * @throws ServiceException in case it was not possible to delete this connection.
     */
    private void deleteConnection(URI connectionURI) throws ServiceException {

        try {
            Response response = client.target(connectionURI).request(MediaType.APPLICATION_JSON).delete();

            if (response.getStatus() == 204) {
                System.out.println("Relationship " + connectionURI + " successfully deleted.");
                relationships.remove(connectionURI);
            } else {
                System.out.println("Relationship " + connectionURI + " deletion error: error " +
                        response.getStatus());
            }
        } catch (RuntimeException e) {
            throw new ServiceException("Error while deleting relationship " + connectionURI + ": " + e.getMessage(),
                    e.getCause());
        }
    }

    /**
     * Looks for the shortest paths connecting a source place to a destination place
     * Each path is returned as a list of place identifiers, where the first place in the list is the source
     * and the last place is the destination.
     *
     * @param source      The id of the source of the paths to be found
     * @param destination The id of the destination of the paths to be found
     * @param maxlength   The maximum length of the paths to be found (0 or negative means no bound on the length)
     * @return the set of the shortest paths connecting source to destination
     * @throws UnknownIdException if source or destination is not a known place identifier
     * @throws BadStateException  if the operation is called when in the initial state (no model loaded)
     * @throws ServiceException   if the operation cannot be completed because the remote service is not available or fails
     */
    @Override
    public Set<List<String>> findShortestPaths(String source, String destination, int maxlength)
            throws UnknownIdException, BadStateException, ServiceException {

        // Arguments checking
        if (!this.loaded) {
            throw new BadStateException("Model is not loaded: cannot find shortest paths.");
        }
        if (!this.nodes.containsKey(source)) {
            throw new UnknownIdException("Unknown source node ID " + source + ".");
        }
        if (!this.nodes.containsKey(destination)) {
            throw new UnknownIdException("Unknown destination node ID " + source + ".");
        }

        // Forming the paths request (https://neo4j.com/docs/pdf/neo4j-manual-2.3.12.pdf paragraph 2.18)
        ShortestPathRequestType spRequest = objectFactory.createShortestPathRequestType();
        spRequest.setTo(nodes.get(destination).toASCIIString());
        ShortestPathRequestType.Relationships spRelationships = objectFactory.createShortestPathRequestTypeRelationships();
        spRequest.setRelationships(spRelationships);
        spRequest.getRelationships().setType("ConnectedTo");
        spRequest.getRelationships().setDirection("out");
        assert !SANITY_CHECKS || nodes.size() - 1 > 0;
        spRequest.setMaxDepth(maxlength > 0 ? maxlength : nodes.size() - 1);
        spRequest.setAlgorithm("shortestPath");

        // Performing the request
        try {
            List<ShortestPathResponseType> shortestPaths = client.target(nodes.get(source)).path("paths")
                    .request(MediaType.APPLICATION_JSON).post(
                            Entity.entity(spRequest, MediaType.APPLICATION_JSON),
                            new GenericType<List<ShortestPathResponseType>>() {
                            });

            if (shortestPaths == null) {
                throw new ServiceException("Error while retrieving shortest paths between " + source +
                        " and " + destination + ".");
            }

            if (shortestPaths.isEmpty()) {
                System.out.println("There are no shortest paths between " + source + " and " + destination + ".");
            }

            Set<List<String>> result = new HashSet<>();
            for (ShortestPathResponseType shortestPath : shortestPaths) {
                List<String> successors = new ArrayList<>();
                for (String nextNode : shortestPath.getNodes()) {
                    successors.add(nodes.inverse().get(new URI(nextNode)));
                }
                result.add(successors);
            }
            return result;
        } catch (RuntimeException | URISyntaxException e) {
            throw new ServiceException("Error while asking for the shortest path between " + source +
                    " and " + destination + ": " + e.getMessage(), e.getCause());
        }
    }
}
