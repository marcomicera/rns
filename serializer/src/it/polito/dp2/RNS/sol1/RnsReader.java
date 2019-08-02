package it.polito.dp2.RNS.sol1;

import com.google.common.collect.Iterables;
import it.polito.dp2.RNS.*;
import it.polito.dp2.RNS.sol1.conf.Config;
import it.polito.dp2.RNS.sol1.jaxb.NextPlaceType;
import it.polito.dp2.RNS.sol1.jaxb.PlaceType;
import it.polito.dp2.RNS.sol1.jaxb.RnsType;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class RnsReader implements it.polito.dp2.RNS.RnsReader {

    /**
     * Info about the RNS system.
     */
    private RnsType rnsInfo;

    /**
     * Cached data for serving clients.
     */
    private Map<String, it.polito.dp2.RNS.sol1.readers.PlaceReader> places = new HashMap<>();
    private Set<it.polito.dp2.RNS.sol1.readers.ConnectionReader> connections = new HashSet<>();
    private Map<String, it.polito.dp2.RNS.sol1.readers.VehicleReader> vehicles = new HashMap<>();

    /**
     * The XML input file to be loaded and validated.
     */
    private String inputFile;

    /**
     * Default constructor creating a RNS reader
     *
     * @throws RnsReaderException if the input file name is invalid
     */
    public RnsReader() throws RnsReaderException {

        // Retrieving the input file name
        System.setProperty(Config.inputFileProperty, "/home/marcomicera/git/rns/serializer/out1.xml"); // TODO delete
        inputFile = System.getProperty(Config.inputFileProperty);

        try {
            // Retrieving the RNS info from the XML file
            JAXBContext jc = JAXBContext.newInstance(Config.jaxbClassesPackage);
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            XMLStreamReader reader = XMLInputFactory.newInstance().createXMLStreamReader(new FileInputStream(inputFile));
            rnsInfo = unmarshaller.unmarshal(reader, RnsType.class).getValue();

            // Converting data into readers that will then serve client requests
            readPlaces();
            readConnections();
            readVehicles();
        } catch (JAXBException e) {
            throw new RnsReaderException(e, "Error while creating a JAXB context class.");
        } catch (XMLStreamException | FileNotFoundException e) {
            throw new RnsReaderException(e, "Could not find the input XML file.");
        }
    }

    private void readPlaces() {
        // Gates
        rnsInfo.getPlaces().getGates().getGate().forEach(g ->
                places.put(g.getId(), new it.polito.dp2.RNS.sol1.readers.GateReader(g)));
        // Parking areas
        rnsInfo.getPlaces().getParkingAreas().getParkingArea().forEach(pa ->
                places.put(pa.getId(), new it.polito.dp2.RNS.sol1.readers.ParkingAreaReader(pa)));
        // Segments
        rnsInfo.getPlaces().getRoadSegments().getRoadSegment().forEach(rs ->
                places.put(rs.getId(), new it.polito.dp2.RNS.sol1.readers.RoadSegmentReader(rs)));
        // Connecting places
        Iterables.concat(
                rnsInfo.getPlaces().getGates().getGate(),
                rnsInfo.getPlaces().getParkingAreas().getParkingArea(),
                rnsInfo.getPlaces().getRoadSegments().getRoadSegment()
        ).forEach((PlaceType place) -> {
            place.getNextPlace().forEach((NextPlaceType nextPlace) -> {
                places.get(place.getId()).addNextPlace(places.get(nextPlace.getName()));
            });
        });
    }

    private void readConnections() {
        connections = new HashSet<>();
        rnsInfo.getConnections().getConnection().forEach(c -> connections.add(new it.polito.dp2.RNS.sol1.readers.ConnectionReader(c)));
    }

    private void readVehicles() {

    }

    /**
     * Testing TODO delete
     * @param args
     */
    public static void main(String[] args) {

        try {
            RnsReader reader = new RnsReader();
            System.out.println(reader.getConnections()); // TODO delete
        } catch (RnsReaderException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Set<PlaceReader> getPlaces(String s) {
        return null;
    }

    @Override
    public PlaceReader getPlace(String s) {
        return null;
    }

    @Override
    public Set<GateReader> getGates(GateType gateType) {
        return null;
    }

    @Override
    public Set<RoadSegmentReader> getRoadSegments(String s) {
        return null;
    }

    @Override
    public Set<ParkingAreaReader> getParkingAreas(Set<String> set) {
        return null;
    }

    @Override
    public Set<ConnectionReader> getConnections() {
        return null;
    }

    @Override
    public Set<VehicleReader> getVehicles(Calendar since, Set<VehicleType> types, VehicleState state) {
        return null;
    }

    @Override
    public VehicleReader getVehicle(String id) {
        return null;
    }
}
