package it.polito.dp2.RNS.sol1.jaxb;

import it.polito.dp2.RNS.RnsReader;
import it.polito.dp2.RNS.VehicleReader;

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
//        PlacesType places = getPlaces();

        // Retrieving connections
//        ConnectionsType connections = getConnections();

        // Retrieving roads
//        RoadsType roads = getRoads();

        // Retrieving vehicles
        VehiclesType vehicles = getVehicles();

        // Building the RNS object
        RnsType rnsInfo = new RnsType();
        /*rnsInfo.setPlaces(places);
        rnsInfo.setConnections(connections);
        rnsInfo.setRoads(roads);*/
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

        return places;
    }

    private GatesType getGates() {
        throw new UnsupportedOperationException("Not implemented, yet.");
    }

    private ParkingAreasType getParkingAreas() {
        throw new UnsupportedOperationException("Not implemented, yet.");
    }

    private RoadSegmentsType getRoadSegments() {
        throw new UnsupportedOperationException("Not implemented, yet.");
    }

    private ConnectionsType getConnections() {
        throw new UnsupportedOperationException("Not implemented, yet.");
    }

    private RoadsType getRoads() {
        RoadsType roads = new RoadsType();
        List<RoadType> roadsList = roads.getRoad();

        return roads;
    }

    private VehiclesType getVehicles() {
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
            tmpVehicle.setPosition(vehicle.getPosition().toString());
            tmpVehicle.setOrigin(vehicle.getOrigin().toString());
            tmpVehicle.setDestination(vehicle.getDestination().toString());
            vehiclesList.add(tmpVehicle);
        }

        return vehicles;
    }
}
