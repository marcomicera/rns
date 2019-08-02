package it.polito.dp2.RNS.sol1.readers;

import java.util.Set;

// TODO abstract class? interface?
public class PlaceReader extends IdentifiedEntityReader implements it.polito.dp2.RNS.PlaceReader {

    protected int capacity;
    protected Set<it.polito.dp2.RNS.PlaceReader> nextPlaces;

    public PlaceReader(String id, int capacity) {
        super(id);
        this.capacity = capacity;
    }

    public boolean addNextPlace(PlaceReader nextPlace) {
        return nextPlaces.add(nextPlace);
    }

    @Override
    public int getCapacity() {
        return 0;
    }

    @Override
    public Set<it.polito.dp2.RNS.PlaceReader> getNextPlaces() {
        return nextPlaces;
    }
}
