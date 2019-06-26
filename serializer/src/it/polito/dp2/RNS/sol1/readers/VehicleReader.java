package it.polito.dp2.RNS.sol1.readers;

import it.polito.dp2.RNS.PlaceReader;
import it.polito.dp2.RNS.VehicleState;
import it.polito.dp2.RNS.VehicleType;

import java.util.Calendar;

public class VehicleReader extends IdentifiedEntityReader implements it.polito.dp2.RNS.VehicleReader {

    private it.polito.dp2.RNS.sol1.jaxb.VehicleType info;

    public VehicleReader(it.polito.dp2.RNS.sol1.jaxb.VehicleType info) {
        super(info.getId());
        this.info = info;
    }

    @Override
    public VehicleType getType() {
        return VehicleType.fromValue(info.getType().value());
    }

    @Override
    public Calendar getEntryTime() {
        return info.getEntryTime().toGregorianCalendar();
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
        return VehicleState.fromValue(info.getState().value());
    }

    @Override
    public String getId() {
        return info.getId();
    }
}
