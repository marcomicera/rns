package it.polito.dp2.RNS.sol1.readers;

import it.polito.dp2.RNS.PlaceReader;

public class ConnectionReader implements it.polito.dp2.RNS.ConnectionReader {

    private PlaceReader from;
    private PlaceReader to;

    public ConnectionReader(PlaceReader from, PlaceReader to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public PlaceReader getFrom() {
        return from;
    }

    @Override
    public PlaceReader getTo() {
        return to;
    }
}
