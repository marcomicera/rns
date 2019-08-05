package it.polito.dp2.RNS.sol1.readers;

import it.polito.dp2.RNS.PlaceReader;
import it.polito.dp2.RNS.VehicleState;
import it.polito.dp2.RNS.VehicleType;

import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Calendar;

public class VehicleReader extends IdentifiedEntityReader implements it.polito.dp2.RNS.VehicleReader {

    private VehicleType type;
    private XMLGregorianCalendar entryTime;
    private PlaceReader origin;
    private PlaceReader position;
    private PlaceReader destination;
    private VehicleState state;

    public VehicleReader(it.polito.dp2.RNS.sol1.jaxb.VehicleType vehicle,
                         PlaceReader origin,
                         PlaceReader position,
                         PlaceReader destination) {
        super(vehicle.getId());
        type = VehicleType.fromValue(vehicle.getType().value());
        entryTime = vehicle.getEntryTime();
        this.origin = origin;
        this.position = position;
        this.destination = destination;
        state = VehicleState.fromValue(vehicle.getState().value());
    }

    @Override
    public VehicleType getType() {
        return type;
    }

    @Override
    public Calendar getEntryTime() {
        return entryTime.toGregorianCalendar();
    }

    @Override
    public PlaceReader getDestination() {
        return destination;
    }

    @Override
    public PlaceReader getOrigin() {
        return origin;
    }

    @Override
    public PlaceReader getPosition() {
        return position;
    }

    @Override
    public VehicleState getState() {
        return state;
    }
}
