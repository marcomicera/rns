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
import it.polito.dp2.RNS.sol2.jaxb.*;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.xml.ws.Service;
import java.math.BigInteger;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

public class PathFinder implements it.polito.dp2.RNS.lab2.PathFinder {

    /**
     * When set to true, sanity checks will be enabled.
     */
    private static final boolean SANITY_CHECKS = true;

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

    // TODO testing
    public static void main(String[] args) throws ServiceException {
        System.setProperty("it.polito.dp2.RNS.RnsReaderFactory", "it.polito.dp2.RNS.Random.RnsReaderFactoryImpl");
        System.setProperty("it.polito.dp2.RNS.lab2.URL", "http://localhost:7474/db");
        new PathFinder().test();
    }

    // TODO testing
    private void test() throws ServiceException {
        PlaceReader newPlace = new PlaceReader() {
            @Override
            public int getCapacity() {
                return 2;
            }

            @Override
            public Set<PlaceReader> getNextPlaces() {
                return null;
            }

            @Override
            public String getId() {
                return "newPlaceId";
            }
        };
        postNode(newPlace);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        client.close();
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

    @Override
    public boolean isModelLoaded() {
        return loaded;
    }

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
        // TODO validation?

        // Reloading the model
        readPlaces();
        readConnections();
        loaded = true;
    }

    private void reloadReader() throws ModelException {
        try {
            rnsReader = RnsReaderFactory.newInstance().newRnsReader();
        } catch (RnsReaderException e) {
            throw new ModelException("Couldn't create an RNS reader instance: " + e.getMessage(), e.getCause());
        }
    }

    private void deleteModel() throws ServiceException {

        // Delete relationships
        for (URI relationship : relationships) {
            deleteRelationship(relationship);
        }

        // Delete nodes
        for (URI node : nodes.values()) {
            deleteNode(node);
        }

        if (SANITY_CHECKS) {
            assert nodes.isEmpty();
            assert relationships.isEmpty();
        }
    }

    /**
     * Stores locally the minimum information needed about all RNS places.
     */
    private void readPlaces() throws ServiceException {

        for (PlaceReader place : rnsReader.getPlaces(null)) {
            postNode(place);
        }
    }

    private void readConnections() throws ServiceException {

        for (PlaceReader place : rnsReader.getPlaces(null)) {
            for (PlaceReader nextPlace : place.getNextPlaces()) {
                postRelationship(place, nextPlace);
            }
        }
    }

    private void postNode(PlaceReader place) throws ServiceException {

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

        } catch (RuntimeException e) {
            throw new ServiceException("Error while posting node " + place.getId() + ": " + e.getMessage(),
                    e.getCause());
        }
    }

    private void deleteNode(URI nodeURI) throws ServiceException {

        try {
            Response response = client.target(nodeURI).request(MediaType.APPLICATION_JSON).delete();

            if (response.getStatus() == 204) {
                System.out.println("Node " + nodeURI + " successfully deleted.");
                nodes.inverse().remove(nodeURI);
            } else {
                System.out.println("Node " + nodeURI + " deletion error: error " + response.getStatus());
            }
        } catch (RuntimeException e) {
            throw new ServiceException("Error while deleting node " + nodeURI + ": " + e.getMessage(), e.getCause());
        }
    }

    private void postRelationship(PlaceReader src, PlaceReader dst) throws ServiceException {

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
        } catch (RuntimeException e) {
            throw e;
            /*throw new ServiceException("Error while creating a relationship between " + src.getId() +
                    " and " + dst.getId() + ": " + e.getMessage(),
                    e.getCause());*/
        }
    }

    private void deleteRelationship(URI relationshipURI) throws ServiceException {

        try {
            Response response = client.target(relationshipURI).request(MediaType.APPLICATION_JSON).delete();

            if (response.getStatus() == 204) {
                System.out.println("Relationship " + relationshipURI + " successfully deleted.");
                relationships.remove(relationshipURI);
            } else {
                System.out.println("Relationship " + relationshipURI + " deletion error: error " +
                        response.getStatus());
            }
        } catch (RuntimeException e) {
            throw new ServiceException("Error while deleting relationship " + relationshipURI + ": " + e.getMessage(),
                    e.getCause());
        }
    }

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
        if (SANITY_CHECKS) {
            assert nodes.size() - 1 > 0;
        }
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
