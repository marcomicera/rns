package it.polito.dp2.RNS.sol3.service.database.neo4j;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import it.polito.dp2.RNS.ConnectionReader;
import it.polito.dp2.RNS.PlaceReader;
import it.polito.dp2.RNS.lab3.ServiceException;
import it.polito.dp2.RNS.lab3.UnknownPlaceException;
import it.polito.dp2.RNS.sol2.database.model.*;
import it.polito.dp2.RNS.sol3.service.conf.Config;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Neo4JManager {

    /**
     * When set to true, sanity checks are enabled.
     */
    private static final boolean SANITY_CHECKS = false;

    /**
     * Web service interaction fields.
     */
    private static Client client;
    private static WebTarget baseTarget;

    /**
     * Local data fields.
     */
    private static ObjectFactory objectFactory = new ObjectFactory();
    private static BiMap<String, URI> nodes = HashBiMap.create();
    private static Set<URI> relationships = new HashSet<>();

    /**
     * This class is a singleton.
     */
    private Neo4JManager() {

    }

    static {

        // Web service interaction fields initialization
        client = ClientBuilder.newClient();
        baseTarget = client.target(getBaseURI());
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
     * Returns the base URI of the Neo4JSimpleXML web service.
     *
     * @return base Neo4JSimpleXML's URI.
     */
    private static URI getBaseURI() {
        String baseURI;
        try {
            baseURI = System.getProperty(Config.Neo4J.serviceUrl);
        } catch (SecurityException | NullPointerException e) {
            System.err.println("Couldn't retrieve the web-service base URI: " + e.getMessage());
            baseURI = Config.Neo4J.serviceDefaultUrl;
        }

        return UriBuilder.fromUri(baseURI + Config.Neo4J.serviceDataRelativePath).build();
    }

    /**
     * Posts a single place object to the web service and keeps track of its URI.
     *
     * @param place the place to be stored.
     * @throws ServiceException in case it was not possible to upload this place.
     */
    public static void postPlace(PlaceReader place) throws ServiceException {

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

    /**
     * Deletes a single place object from the web service.
     *
     * @param placeURI the place URI to be deleted.
     * @throws ServiceException in case it was not possible to delete this place.
     */
    public static void deletePlace(URI placeURI) throws ServiceException {

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
     * @param connection connection to be uploaded.
     * @throws ServiceException in case it was not possible to upload this connection.
     */
    public static void postConnection(ConnectionReader connection) throws ServiceException {

        PlaceReader src = connection.getFrom();
        PlaceReader dst = connection.getTo();

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
    public static void deleteConnection(URI connectionURI) throws ServiceException {

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
     * @throws UnknownPlaceException if source or destination is not a known place identifier
     * @throws ServiceException      if the operation cannot be completed because the remote service is not available or fails
     */
    public static Set<List<String>> findShortestPaths(String source, String destination, int maxlength)
            throws UnknownPlaceException, ServiceException {

        // Arguments checking
        if (!nodes.containsKey(source)) {
            throw new UnknownPlaceException("Unknown source node ID " + source + ".");
        }
        if (!nodes.containsKey(destination)) {
            throw new UnknownPlaceException("Unknown destination node ID " + source + ".");
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
