package it.polito.dp2.RNS.sol1.readers;

import it.polito.dp2.RNS.PlaceReader;
import it.polito.dp2.RNS.sol1.jaxb.ConnectionType;

public class ConnectionReader implements it.polito.dp2.RNS.ConnectionReader {

    private PlaceReader from;
    private PlaceReader to;

    public ConnectionReader(ConnectionType connection) {

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
