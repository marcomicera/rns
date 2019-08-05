package it.polito.dp2.RNS.sol1.readers;

import it.polito.dp2.RNS.PlaceReader;
import it.polito.dp2.RNS.VehicleState;
import it.polito.dp2.RNS.VehicleType;

import javax.xml.datatype.XMLGregorianCalendar;
import java.lang.ref.WeakReference;
import java.util.Calendar;
import java.util.Map;

public class VehicleReader extends IdentifiedEntityReader implements it.polito.dp2.RNS.VehicleReader {

    Map<String, WeakReference<it.polito.dp2.RNS.sol1.readers.PlaceReader>> places;
    private VehicleType type;
    private XMLGregorianCalendar entryTime;
    private PlaceReader origin;
    private PlaceReader position;
    private PlaceReader destination;
    private VehicleState state;
    
    public VehicleReader(it.polito.dp2.RNS.sol1.jaxb.VehicleType vehicle,
                         Map<String, WeakReference<it.polito.dp2.RNS.sol1.readers.PlaceReader>> places // TODO improve
    ) {
        super(vehicle.getId());
        this.places = places;
        type = VehicleType.fromValue(vehicle.getType().value());
        entryTime = vehicle.getEntryTime();
        origin = places.getOrDefault(vehicle.getOrigin(), new WeakReference<>(null)).get();
        position = places.getOrDefault(vehicle.getPosition(), new WeakReference<>(null)).get();
        destination = places.getOrDefault(vehicle.getDestination(), new WeakReference<>(null)).get();
        state = VehicleState.fromValue(vehicle.getState().value());
    }

    @Override
    public VehicleType getType() {
        return type;
    }

    @Override
    public Calendar getEntryTime() {
        return entryTime.toGregorianCalendar()  ;
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
