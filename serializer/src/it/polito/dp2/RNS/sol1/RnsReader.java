package it.polito.dp2.RNS.sol1;

import it.polito.dp2.RNS.*;
import it.polito.dp2.RNS.sol1.conf.Config;
import it.polito.dp2.RNS.sol1.jaxb.RnsType;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class RnsReader implements it.polito.dp2.RNS.RnsReader {

    /**
     * Info about the RNS system.
     */
    private RnsType rnsInfo;

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
        } catch (JAXBException e) {
            throw new RnsReaderException(e, "Error while creating a JAXB context class.");
        } catch (XMLStreamException | FileNotFoundException e) {
            throw new RnsReaderException(e, "Could not find the input XML file.");
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
