package it.polito.dp2.RNS.sol3.service.database.local;

import it.polito.dp2.RNS.*;
import it.polito.dp2.RNS.lab3.ServiceException;
import it.polito.dp2.RNS.sol3.service.database.neo4j.Neo4JManager;
import it.polito.dp2.RNS.sol3.service.model.GateType;
import it.polito.dp2.RNS.sol3.service.model.*;

import java.lang.ref.WeakReference;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class PlacesManager {

    /**
     * Places data
     */
    private static ConcurrentHashMap<String, WeakReference<Place>> places = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<String, Gate> gates = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<String, RoadSegment> roadSegments = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<String, ParkingArea> parkingAreas = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<String, Set<Vehicle>> vehiclesInPlace = new ConcurrentHashMap<>(); // TODO fill this up
    private static ObjectFactory objectFactory = new ObjectFactory();

    /**
     * This class is a singleton.
     */
    private PlacesManager() {

    }

    /**
     * Retrieves RNS places info.
     *
     * @param monitor RnsReader interface through which data must be retrieved.
     */
    protected synchronized static void download(RnsReader monitor) throws ServiceException {

        // Retrieving gates
        for (GateReader retrievedGate : monitor.getGates(null)) {
            Gate newGate = objectFactory.createGate();
            newGate.setId(retrievedGate.getId());
            newGate.setCapacity(retrievedGate.getCapacity());
            newGate.setType(GateType.fromValue(retrievedGate.getType().value()));
            //newGate.setSelf(uriInfo.getAbsolutePath().toASCIIString()); // TODO check this
            gates.put(retrievedGate.getId(), newGate);
            places.put(retrievedGate.getId(), new WeakReference<>(newGate));
            Neo4JManager.postPlace(retrievedGate); // post it to Neo4J
        }

        // Retrieving road segments
        for (RoadSegmentReader retrievedRoadSegment : monitor.getRoadSegments(null)) {
            RoadSegment newRoadSegment = objectFactory.createRoadSegment();
            newRoadSegment.setId(retrievedRoadSegment.getId());
            newRoadSegment.setCapacity(retrievedRoadSegment.getCapacity());
            newRoadSegment.setName(retrievedRoadSegment.getName());
            newRoadSegment.setRoad(retrievedRoadSegment.getRoadName());
            //newRoadSegment.setSelf(uriInfo.getAbsolutePath().toASCIIString()); // TODO check this
            roadSegments.put(retrievedRoadSegment.getId(), newRoadSegment);
            places.put(retrievedRoadSegment.getId(), new WeakReference<>(newRoadSegment));
            Neo4JManager.postPlace(retrievedRoadSegment); // post it to Neo4J
        }

        // Retrieving parking areas
        for (ParkingAreaReader retrievedParkingArea : monitor.getParkingAreas(null)) {
            ParkingArea newParkingArea = objectFactory.createParkingArea();
            newParkingArea.setId(retrievedParkingArea.getId());
            newParkingArea.setCapacity(retrievedParkingArea.getCapacity());
            for (String retrievedService : retrievedParkingArea.getServices()) {
                newParkingArea.getService().add(retrievedService);
            }
            //newParkingArea.setSelf(uriInfo.getAbsolutePath().toASCIIString()); // TODO check this
            parkingAreas.put(retrievedParkingArea.getId(), newParkingArea);
            places.put(retrievedParkingArea.getId(), new WeakReference<>(newParkingArea));
            Neo4JManager.postPlace(retrievedParkingArea); // post it to Neo4J
        }

        // TODO Configuring 'self' links

        // Connecting places
        for (ConnectionReader retrievedConnection : monitor.getConnections()) {
            Place source = places.getOrDefault(retrievedConnection.getFrom().getId(), new WeakReference<>(null)).get();
            if (source != null) {
                Place destination = places.getOrDefault(retrievedConnection.getTo().getId(), new WeakReference<>(null)).get();
                if (destination != null) {
                    source.getNextPlace().add(destination.getSelf());
                    Neo4JManager.postConnection(retrievedConnection); // post it to Neo4J
                }
            }
        }
    }

    public synchronized static Gates getGates(/* TODO add params */) {
        Gates result = objectFactory.createGates();
        result.setTotalPages(1);
        result.setNext(null);
        result.setPage(0);
        result.getGate().addAll(gates.values());
        return result;
    }

    public synchronized static ParkingAreas getParkingAreas(/* TODO add params */) {
        ParkingAreas result = objectFactory.createParkingAreas();
        result.setTotalPages(1);
        result.setNext(null);
        result.setPage(0);
        result.getParkingArea().addAll(parkingAreas.values());
        return result;
    }

    public synchronized static RoadSegments getRoadSegments(/* TODO add params */) {
        RoadSegments result = objectFactory.createRoadSegments();
        result.setTotalPages(1);
        result.setNext(null);
        result.setPage(0);
        result.getRoadSegment().addAll(roadSegments.values());
        return result;
    }
}
