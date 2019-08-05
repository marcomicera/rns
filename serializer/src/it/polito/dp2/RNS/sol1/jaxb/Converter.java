package it.polito.dp2.RNS.sol1.jaxb;

import it.polito.dp2.RNS.*;

import javax.xml.bind.JAXBElement;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Converts data retrieved by reader interfaces into JAXB-compatible objects.
 */
public class Converter {

    /**
     * Reader from which data is retrieved.
     */
    private RnsReader monitor;

    private Map<String, PlaceType> placesMap;

    /**
     * Default constructor creating a converter with a given monitor.
     *
     * @param monitor RNS reader object from which data can be read
     */
    public Converter(RnsReader monitor) {
        this.monitor = monitor;
        this.placesMap = new ConcurrentHashMap<>();
    }

    /**
     * Converts the RNS object obtainable by RnsReader into a JAXB equivalent class.
     *
     * @return the RNS JAXB-compatible object
     */
    public JAXBElement<RnsType> getRnsInfo() {
        // Retrieving places
        PlacesType places = getPlaces();

        // Retrieving connections
        ConnectionsType connections = getConnections();

        // Retrieving vehicles
        VehiclesType vehicles = getVehicles();

        // Building the RNS object
        RnsType rnsInfo = new RnsType();
        rnsInfo.setPlaces(places);
        rnsInfo.setConnections(connections);
        rnsInfo.setVehicles(vehicles);

        // Return a JAXB-compatible object
        return new ObjectFactory().createRnsInfo(rnsInfo);
    }

    /**
     * Retrieves RNS places.
     *
     * @return the RNS places, including gates, parking areas and road segments.
     */
    private PlacesType getPlaces() {
        // Places root object
        PlacesType places = new PlacesType();

        // Retrieving all different kind of places
        GatesType gates = getGates();
        ParkingAreasType parkingAreas = getParkingAreas();
        RoadSegmentsType roadSegments = getRoadSegments();

        // Adding places to the places root object
        places.setGates(gates);
        places.setParkingAreas(parkingAreas);
        places.setRoadSegments(roadSegments);

        return places;
    }

    /**
     * Retrieves RNS gates.
     *
     * @return a list of RNS gates, from which vehicles can get in, get out, or both.
     */
    private GatesType getGates() {
        // If the RNS reader retrieves no gate objects
        if (monitor.getGates(null).isEmpty()) {
            // Do not create the XML `gates` element
            return null;
        }
        // If there is at least one gate object
        else {
            GatesType gates = new GatesType();
            List<GateType> gatesList = gates.getGate();

            // Convert all retrieved gate objects
            for (GateReader gate : monitor.getGates(null)) {
                GateType tmpGate = new GateType();
                tmpGate.setId(gate.getId());
                tmpGate.setType(GateTypeType.fromValue(gate.getType().value()));
                tmpGate.setCapacity(gate.getCapacity());
                gatesList.add(tmpGate);
                placesMap.put(tmpGate.getId(), tmpGate);
            }

            return gates;
        }
    }

    /**
     * Retrieves RNS parking areas.
     *
     * @return a list of RNS parking areas having different services.
     */
    private ParkingAreasType getParkingAreas() {
        // If the RNS reader retrieves no parking area objects
        if (monitor.getParkingAreas(null).isEmpty()) {
            // Do not create the XML `parkingAreas` element
            return null;
        }
        // If there is at least one parking area object
        else {
            ParkingAreasType parkingAreas = new ParkingAreasType();
            List<ParkingAreaType> parkingAreasList = parkingAreas.getParkingArea();

            // Convert all retrieved parking area objects
            for (ParkingAreaReader parkingArea : monitor.getParkingAreas(null)) {
                ParkingAreaType tmpParkingArea = new ParkingAreaType();
                tmpParkingArea.setId(parkingArea.getId());
                tmpParkingArea.setCapacity(parkingArea.getCapacity());
                for (String service : parkingArea.getServices()) {
                    ServiceType tmpService = new ServiceType();
                    tmpService.setName(service);
                    tmpParkingArea.getService().add(tmpService);
                }
                parkingAreasList.add(tmpParkingArea);
                placesMap.put(tmpParkingArea.getId(), tmpParkingArea);
            }

            return parkingAreas;
        }
    }

    /**
     * Retrieves RNS road segments.
     *
     * @return a list of RNS road segments, belonging to a specific road.
     */
    private RoadSegmentsType getRoadSegments() {
        // If the RNS reader retrieves no road segment objects
        if (monitor.getRoadSegments(null).isEmpty()) {
            // Do not create the XML `roadSegments` element
            return null;
        }
        // If there is at least one road segment object
        else {
            RoadSegmentsType roadSegments = new RoadSegmentsType();
            List<RoadSegmentType> roadSegmentsList = roadSegments.getRoadSegment();

            // Convert all retrieved road segment objects
            for (RoadSegmentReader roadSegment : monitor.getRoadSegments(null)) {
                RoadSegmentType tmpRoadSegment = new RoadSegmentType();
                tmpRoadSegment.setId(roadSegment.getId());
                tmpRoadSegment.setName(roadSegment.getName());
                tmpRoadSegment.setRoad(roadSegment.getRoadName());
                tmpRoadSegment.setCapacity(roadSegment.getCapacity());
                roadSegmentsList.add(tmpRoadSegment);
                placesMap.put(tmpRoadSegment.getId(), tmpRoadSegment);
            }

            return roadSegments;
        }
    }

    /**
     * Retrieves RNS connections.
     *
     * @return a list of RNS place connections.
     */
    private ConnectionsType getConnections() {
        // If the RNS reader retrieves no connection objects
        if (monitor.getConnections().isEmpty()) {
            // Do not create the XML `connections` element
            return null;
        }
        // If there is at least one connection object
        else {
            ConnectionsType connections = new ConnectionsType();
            List<ConnectionType> connectionsList = connections.getConnection();

            // Convert all retrieved connection objects
            for (ConnectionReader connection : monitor.getConnections()) {
                ConnectionType tmpConnection = new ConnectionType();
                tmpConnection.setFrom(connection.getFrom().getId());
                tmpConnection.setTo(connection.getTo().getId());
                connectionsList.add(tmpConnection);

                // Adding the 'nextPlace' element
                if (placesMap.containsKey(tmpConnection.getFrom())) {
                    NextPlaceType nextPlace = new NextPlaceType();
                    nextPlace.setName(tmpConnection.getTo());
                    placesMap.get(tmpConnection.getFrom()).getNextPlace().add(nextPlace);
                }
            }

            return connections;
        }
    }

    /**
     * Retrieves RNS vehicles.
     *
     * @return a list of RNS vehicles.
     */
    private VehiclesType getVehicles() {
        // If the RNS reader retrieves no vehicles objects
        if (monitor.getVehicles(null, null, null).isEmpty()) {
            // Do not create the XML `vehicles` element
            return null;
        }
        // If there is at least one vehicle object
        else {
            VehiclesType vehicles = new VehiclesType();
            List<VehicleType> vehiclesList = vehicles.getVehicle();

            // Convert all retrieved vehicle objects
            for (VehicleReader vehicle : monitor.getVehicles(null, null, null)) {
                VehicleType tmpVehicle = new VehicleType();
                tmpVehicle.setId(vehicle.getId());

                // Converting the entry time
                GregorianCalendar entryTime = new GregorianCalendar();
                entryTime.setTime(vehicle.getEntryTime().getTime());
                try {
                    tmpVehicle.setEntryTime(DatatypeFactory.newInstance().newXMLGregorianCalendar(entryTime));
                } catch (DatatypeConfigurationException e) {
                    System.err.println("Invalid entry type for vehicle " + vehicle.getId() + ".");
                    e.printStackTrace();
                }

                tmpVehicle.setType(VehicleTypeType.fromValue(vehicle.getType().value()));
                tmpVehicle.setState(VehicleState.fromValue(vehicle.getState().value()));
                tmpVehicle.setPosition(vehicle.getPosition().getId());
                tmpVehicle.setOrigin(vehicle.getOrigin().getId());
                tmpVehicle.setDestination(vehicle.getDestination().getId());
                vehiclesList.add(tmpVehicle);
            }

            return vehicles;
        }
    }
}
