package it.polito.dp2.RNS.sol2;

import it.polito.dp2.RNS.PlaceReader;
import it.polito.dp2.RNS.RnsReader;
import it.polito.dp2.RNS.RnsReaderException;
import it.polito.dp2.RNS.RnsReaderFactory;
import it.polito.dp2.RNS.lab2.BadStateException;
import it.polito.dp2.RNS.lab2.ModelException;
import it.polito.dp2.RNS.lab2.ServiceException;
import it.polito.dp2.RNS.lab2.UnknownIdException;
import it.polito.dp2.RNS.sol2.jaxb.*;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.List;
import java.util.Set;

public class PathFinder implements it.polito.dp2.RNS.lab2.PathFinder {

    /**
     * Web service interaction fields.
     */
    private RnsReader rnsReader;
    private Client client;
    private WebTarget target;

    /**
     * Local data fields.
     */
    private ObjectFactory objectFactory = new ObjectFactory();
    private RnsType data;

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
            rnsReader = RnsReaderFactory.newInstance().newRnsReader();
        } catch (RnsReaderException e) {
            System.err.println("An implementation of RnsReader cannot be created.");
            e.printStackTrace();
            System.exit(1);
        }
        try {
            client = ClientBuilder.newClient();
            target = client.target(getBaseURI());
        }
        catch (RuntimeException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }

        // Local data fields initialization
        data = objectFactory.createRnsType();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        client.close();
    }

    /**
     * Returns the base URI of the Neo4JSimpleXML web service.
     * @return	base Neo4JSimpleXML's URI.
     */
    private static URI getBaseURI() {
        String baseURI;
        try {
            baseURI = System.getProperty("it.polito.dp2.RNS.lab2.URL");
        } catch(SecurityException | NullPointerException e) {
            baseURI = "http://localhost:7474/db";
        }

        return UriBuilder.fromUri(baseURI + "/data/").build();
    }

    @Override
    public boolean isModelLoaded() {
        return false;
    }

    @Override
    public void reloadModel() throws ServiceException, ModelException {

        // Data retriever
        try {
            rnsReader = RnsReaderFactory.newInstance().newRnsReader();
        } catch (RnsReaderException e) {
            throw new ModelException("Couldn't create an RNS reader instance: " + e.getMessage(), e.getCause());
        }
        readPlaces();
    }

    /**
     * Stores locally the minimum information needed about all RNS places.
     */
    private void readPlaces() {

        Set<PlaceReader> allPlaces = rnsReader.getPlaces(null);
        if (allPlaces != null && !allPlaces.isEmpty()) {
            NodesType nodes = objectFactory.createNodesType();
            RelationshipsType relationships = null;
            for (PlaceReader place : rnsReader.getPlaces(null)) {
                NodeType node = objectFactory.createNodeType();
                node.setId(place.getId());
                if (place.getNextPlaces() != null && !place.getNextPlaces().isEmpty()) {
                    if (relationships == null) {
                        relationships = objectFactory.createRelationshipsType();
                    }
                    place.getNextPlaces().forEach((PlaceReader nextPlace) -> {
                        // TODO implement
                    });
                }
                nodes.getNode().add(node);
            }
        }
    }

    @Override
    public Set<List<String>> findShortestPaths(String source, String destination, int maxlength) throws UnknownIdException, BadStateException, ServiceException {
        return null;
    }
}
