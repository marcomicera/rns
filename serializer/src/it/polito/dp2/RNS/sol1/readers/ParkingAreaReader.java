package it.polito.dp2.RNS.sol1.readers;

import it.polito.dp2.RNS.sol1.jaxb.ParkingAreaType;

import java.util.HashSet;
import java.util.Set;

public class ParkingAreaReader extends it.polito.dp2.RNS.sol1.readers.PlaceReader
        implements it.polito.dp2.RNS.ParkingAreaReader {

    private Set<String> services = new HashSet<>();

    public ParkingAreaReader(ParkingAreaType parkingArea) {

        super(parkingArea.getId(), parkingArea.getCapacity(), PlaceType.PARKING_AREA);

        if (!parkingArea.getService().isEmpty()) {
            parkingArea.getService().forEach(s -> services.add(s.getName()));
        }
    }

    @Override
    public Set<String> getServices() {
        return services;
    }
}
