package it.polito.dp2.RNS.sol1;

import it.polito.dp2.RNS.*;
import it.polito.dp2.RNS.sol1.conf.Config;

import java.util.Calendar;
import java.util.Set;

public class RnsReader implements it.polito.dp2.RNS.RnsReader {
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

        // If the input file name is invalid
        if(inputFile == null || inputFile.isEmpty()) {
            // Throw an exception
            throw new RnsReaderException("Could not find the input XML file");
        }
        // The the input file name is valid
        else {

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
    public Set<VehicleReader> getVehicles(Calendar calendar, Set<VehicleType> set, VehicleState vehicleState) {
        return null;
    }

    @Override
    public VehicleReader getVehicle(String s) {
        return null;
    }
}
