package it.polito.dp2.RNS.sol1.readers;

import it.polito.dp2.RNS.PlaceReader;
import it.polito.dp2.RNS.VehicleState;
import it.polito.dp2.RNS.VehicleType;

import java.util.Calendar;

public class VehicleReader extends IdentifiedEntityReader implements it.polito.dp2.RNS.VehicleReader {

    public VehicleReader(it.polito.dp2.RNS.sol1.jaxb.VehicleType info) {
        super(info.getId());
        // TODO implement
    }

    @Override
    public VehicleType getType() {
        return null; // TODO implement
    }

    @Override
    public Calendar getEntryTime() {
        return null; // TODO implement
    }

    @Override
    public PlaceReader getDestination() {
        return null; // TODO implement
    }

    @Override
    public PlaceReader getOrigin() {
        return null; // TODO implement
    }

    @Override
    public PlaceReader getPosition() {
        return null; // TODO implement
    }

    @Override
    public VehicleState getState() {
        return null; // TODO implement
    }
}
