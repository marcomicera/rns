package it.polito.dp2.RNS.sol1;

import it.polito.dp2.RNS.*;
import it.polito.dp2.RNS.GateType;
import it.polito.dp2.RNS.VehicleState;
import it.polito.dp2.RNS.VehicleType;
import it.polito.dp2.RNS.sol1.conf.Config;
import it.polito.dp2.RNS.sol1.jaxb.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.ref.WeakReference;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RnsReader implements it.polito.dp2.RNS.RnsReader {

    /**
     * Info about the RNS system.
     */
    private RnsType rnsInfo;

    /**
     * Cached data for serving clients.
     */
    private Map<String, GateReader> gates = new ConcurrentHashMap<>();
    private Map<String, ParkingAreaReader> parkingAreas = new ConcurrentHashMap<>();
    private Map<String, RoadSegmentReader> roadSegments = new ConcurrentHashMap<>();
    private Map<String, WeakReference<it.polito.dp2.RNS.sol1.readers.PlaceReader>> places = new ConcurrentHashMap<>();
    private Set<ConnectionReader> connections = new HashSet<>();
    private Map<String, VehicleReader> vehicles = new ConcurrentHashMap<>();

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
        inputFile = System.getProperty(Config.inputFileProperty);

        try {
            // Retrieving the RNS info from the XML file
            JAXBContext jc = JAXBContext.newInstance(Config.jaxbClassesPackage);
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            XMLStreamReader reader = XMLInputFactory.newInstance().createXMLStreamReader(new FileInputStream(inputFile));
            rnsInfo = unmarshaller.unmarshal(reader, RnsType.class).getValue();

            // Converting data into readers that will then serve client requests
            readPlacesAndConnections();
            readVehicles();
        } catch (JAXBException e) {
            throw new RnsReaderException(e, "Error while creating a JAXB context class.");
        } catch (XMLStreamException | FileNotFoundException e) {
            throw new RnsReaderException(e, "Could not find the input XML file.");
        }
    }

    private void readPlacesAndConnections() {

        if (rnsInfo.getPlaces() != null) {

            // Gates
            if (rnsInfo.getPlaces().getGates() != null) {
                for (it.polito.dp2.RNS.sol1.jaxb.GateType g : rnsInfo.getPlaces().getGates().getGate()) {
                    it.polito.dp2.RNS.sol1.readers.GateReader newGate = new it.polito.dp2.RNS.sol1.readers.GateReader(g);
                    gates.put(g.getId(), newGate);
                    places.put(g.getId(), new WeakReference<>(newGate));
                }
            }

            // Parking areas
            if (rnsInfo.getPlaces().getParkingAreas() != null) {
                for (ParkingAreaType pa : rnsInfo.getPlaces().getParkingAreas().getParkingArea()) {
                    it.polito.dp2.RNS.sol1.readers.ParkingAreaReader newParkingArea = new it.polito.dp2.RNS.sol1.readers.ParkingAreaReader(pa);
                    parkingAreas.put(pa.getId(), newParkingArea);
                    places.put(pa.getId(), new WeakReference<>(newParkingArea));
                }
            }

            // Road segments
            if (rnsInfo.getPlaces().getRoadSegments() != null) {
                for (RoadSegmentType rs : rnsInfo.getPlaces().getRoadSegments().getRoadSegment()) {
                    it.polito.dp2.RNS.sol1.readers.RoadSegmentReader newRoadSegment = new it.polito.dp2.RNS.sol1.readers.RoadSegmentReader(rs);
                    roadSegments.put(rs.getId(), newRoadSegment);
                    places.put(rs.getId(), new WeakReference<>(newRoadSegment));
                }
            }
        }

        // Connecting places
        if (rnsInfo.getConnections() != null) {
            Stream.concat(
                    Stream.concat(
                            rnsInfo.getPlaces().getGates().getGate().stream(),
                            rnsInfo.getPlaces().getParkingAreas().getParkingArea().stream()),
                    rnsInfo.getPlaces().getRoadSegments().getRoadSegment().stream()
            ).forEach((PlaceType place) -> {
                for (NextPlaceType nextPlace : place.getNextPlace()) {
                    it.polito.dp2.RNS.sol1.readers.PlaceReader from = places.get(place.getId()).get();
                    it.polito.dp2.RNS.sol1.readers.PlaceReader to = places.get(nextPlace.getName()).get();
                    connections.add(new it.polito.dp2.RNS.sol1.readers.ConnectionReader(from, to));
                    assert from != null;
                    assert to != null;
                    from.addNextPlace(to);
                }
            });
        }
    }

    private void readVehicles() {
        if (rnsInfo.getVehicles() != null) {
            for (it.polito.dp2.RNS.sol1.jaxb.VehicleType v : rnsInfo.getVehicles().getVehicle()) {
                vehicles.put(v.getId(), new it.polito.dp2.RNS.sol1.readers.VehicleReader(v, places));
            }
        }
    }

    @Override
    public Set<PlaceReader> getPlaces(String idPrefix) {
        return places.entrySet().stream().filter(e -> idPrefix == null || e.getKey().startsWith(idPrefix))
                .map(e -> e.getValue().get()).collect(Collectors.toSet());
    }

    @Override
    public PlaceReader getPlace(String id) {
        return (id == null) ? null : places.getOrDefault(id, new WeakReference<>(null)).get();
    }

    @Override
    public Set<GateReader> getGates(GateType type) {
        return gates.values().stream().filter(g -> type == null || g.getType() == type).collect(Collectors.toSet());
    }

    @Override
    public Set<RoadSegmentReader> getRoadSegments(String roadName) {
        return roadSegments.values().stream().filter(rs -> roadName == null || rs.getRoadName().equals(roadName))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<ParkingAreaReader> getParkingAreas(Set<String> services) {
        return parkingAreas.values().stream().filter(pa -> services == null || pa.getServices().containsAll(services))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<ConnectionReader> getConnections() {
        return connections;
    }

    @Override
    public Set<VehicleReader> getVehicles(Calendar since, Set<VehicleType> types, VehicleState state) {
        return vehicles.values().stream().filter(v ->
                (since == null || v.getEntryTime().after(since)) &&
                (types == null || types.contains(v.getType())) &&
                (state == null || state == v.getState())
        ).collect(Collectors.toSet());
    }

    @Override
    public VehicleReader getVehicle(String id) {
        return (id == null) ? null : vehicles.getOrDefault(id, null);
    }
}
