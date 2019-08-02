package it.polito.dp2.RNS.sol1.readers;

import it.polito.dp2.RNS.sol1.jaxb.ParkingAreaType;

import java.util.Set;

public class ParkingAreaReader extends it.polito.dp2.RNS.sol1.readers.PlaceReader
        implements it.polito.dp2.RNS.ParkingAreaReader {

    private Set<String> services;

    public ParkingAreaReader(ParkingAreaType parkingArea) {
        super(parkingArea.getId(), parkingArea.getCapacity());
        parkingArea.getService().forEach(s -> services.add(s.getName()));
    }

    @Override
    public Set<String> getServices() {
        return services;
    }
}
