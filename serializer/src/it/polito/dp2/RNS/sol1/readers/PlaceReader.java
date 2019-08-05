package it.polito.dp2.RNS.sol1.readers;

import java.util.HashSet;
import java.util.Set;

// TODO abstract class? interface?
public class PlaceReader extends IdentifiedEntityReader implements it.polito.dp2.RNS.PlaceReader {

    protected int capacity;
    protected PlaceType placeType;
    protected Set<it.polito.dp2.RNS.PlaceReader> nextPlaces = new HashSet<>();

    public PlaceReader(String id, int capacity, PlaceType type) {
        super(id);
        this.capacity = capacity;
        this.placeType = type;
    }

    public boolean addNextPlace(PlaceReader nextPlace) {
        return nextPlaces.add(nextPlace);
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    public Set<it.polito.dp2.RNS.PlaceReader> getNextPlaces() {
        return nextPlaces;
    }

    public PlaceType getPlaceType() { return placeType; }
}
