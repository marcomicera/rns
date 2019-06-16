package it.polito.dp2.RNS.sol1.readers;

import it.polito.dp2.RNS.PlaceReader;

import java.util.Set;

public class ParkingAreaReader implements it.polito.dp2.RNS.ParkingAreaReader {
    @Override
    public Set<String> getServices() {
        return null;
    }

    @Override
    public int getCapacity() {
        return 0;
    }

    @Override
    public Set<PlaceReader> getNextPlaces() {
        return null;
    }

    @Override
    public String getId() {
        return null;
    }
}
