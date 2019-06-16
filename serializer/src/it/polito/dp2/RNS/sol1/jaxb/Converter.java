package it.polito.dp2.RNS.sol1.jaxb;

import it.polito.dp2.RNS.*;

import javax.xml.bind.JAXBElement;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import java.util.GregorianCalendar;
import java.util.List;

public class Converter {

    /**
     * Reader from which data has to be retrieved
     */
    private RnsReader monitor;

    /**
     * Default constructor creating a converter with a given monitor.
     *
     * @param monitor RNS reader object from which data can be read
     */
    public Converter(RnsReader monitor) {
        this.monitor = monitor;
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

        return new ObjectFactory().createRnsInfo(rnsInfo);
    }

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

        // TODO add next places for each place, based on existing connections

        return places;
    }

    private GatesType getGates() {
        if (monitor.getGates(null).isEmpty()) {
            return null;
        }
        else {
            GatesType gates = new GatesType();
            List<GateType> gatesList = gates.getGate();
            for (GateReader gate : monitor.getGates(null)) {
                GateType tmpGate = new GateType();
                tmpGate.setId(gate.getId());
                tmpGate.setType(GateTypeType.fromValue(gate.getType().value()));
                tmpGate.setCapacity(gate.getCapacity());
                gatesList.add(tmpGate);
            }

            return gates;
        }
    }

    private ParkingAreasType getParkingAreas() {
        if (monitor.getParkingAreas(null).isEmpty()) {
            return null;
        }
        else {
            ParkingAreasType parkingAreas = new ParkingAreasType();
            List<ParkingAreaType> parkingAreasList = parkingAreas.getParkingArea();
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
            }

            return parkingAreas;
        }
    }

    private RoadSegmentsType getRoadSegments() {
        if (monitor.getRoadSegments(null).isEmpty()) {
            return null;
        }
        else {
            RoadSegmentsType roadSegments = new RoadSegmentsType();
            List<RoadSegmentType> roadSegmentsList = roadSegments.getRoadSegment();
            for (RoadSegmentReader roadSegment : monitor.getRoadSegments(null)) {
                RoadSegmentType tmpRoadSegment = new RoadSegmentType();
                tmpRoadSegment.setId(roadSegment.getId());
                tmpRoadSegment.setName(roadSegment.getName());
                tmpRoadSegment.setRoad(roadSegment.getRoadName());
                tmpRoadSegment.setCapacity(roadSegment.getCapacity());
                roadSegmentsList.add(tmpRoadSegment);
            }

            return roadSegments;
        }
    }

    private ConnectionsType getConnections() {
        if (monitor.getConnections().isEmpty()) {
            return null;
        }
        else {
            ConnectionsType connections = new ConnectionsType();
            List<ConnectionType> connectionsList = connections.getConnection();
            for (ConnectionReader connection : monitor.getConnections()) {
                ConnectionType tmpConnection = new ConnectionType();
                tmpConnection.setFrom(connection.getFrom().getId());
                tmpConnection.setTo(connection.getTo().getId());
                connectionsList.add(tmpConnection);
            }

            return connections;
        }
    }

    private VehiclesType getVehicles() {
        if (monitor.getVehicles(null, null, null).isEmpty()) {
            return null;
        }
        else {
            VehiclesType vehicles = new VehiclesType();
            List<VehicleType> vehiclesList = vehicles.getVehicle();
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
