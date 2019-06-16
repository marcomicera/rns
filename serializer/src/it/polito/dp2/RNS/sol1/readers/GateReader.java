package it.polito.dp2.RNS.sol1.readers;

import it.polito.dp2.RNS.GateType;
import it.polito.dp2.RNS.PlaceReader;

import java.util.Set;

public class GateReader implements it.polito.dp2.RNS.GateReader {
    @Override
    public GateType getType() {
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
