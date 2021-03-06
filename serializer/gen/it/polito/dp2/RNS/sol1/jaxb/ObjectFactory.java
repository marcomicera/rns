
package it.polito.dp2.RNS.sol1.jaxb;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the it.polito.dp2.RNS.sol1.jaxb package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _RnsInfo_QNAME = new QName("", "rnsInfo");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: it.polito.dp2.RNS.sol1.jaxb
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link RnsType }
     * 
     */
    public RnsType createRnsType() {
        return new RnsType();
    }

    /**
     * Create an instance of {@link PlaceType }
     * 
     */
    public PlaceType createPlaceType() {
        return new PlaceType();
    }

    /**
     * Create an instance of {@link ParkingAreasType }
     * 
     */
    public ParkingAreasType createParkingAreasType() {
        return new ParkingAreasType();
    }

    /**
     * Create an instance of {@link ConnectionType }
     * 
     */
    public ConnectionType createConnectionType() {
        return new ConnectionType();
    }

    /**
     * Create an instance of {@link PlacesType }
     * 
     */
    public PlacesType createPlacesType() {
        return new PlacesType();
    }

    /**
     * Create an instance of {@link VehicleType }
     * 
     */
    public VehicleType createVehicleType() {
        return new VehicleType();
    }

    /**
     * Create an instance of {@link NextPlaceType }
     * 
     */
    public NextPlaceType createNextPlaceType() {
        return new NextPlaceType();
    }

    /**
     * Create an instance of {@link ConnectionsType }
     * 
     */
    public ConnectionsType createConnectionsType() {
        return new ConnectionsType();
    }

    /**
     * Create an instance of {@link RoadSegmentsType }
     * 
     */
    public RoadSegmentsType createRoadSegmentsType() {
        return new RoadSegmentsType();
    }

    /**
     * Create an instance of {@link RoadSegmentType }
     * 
     */
    public RoadSegmentType createRoadSegmentType() {
        return new RoadSegmentType();
    }

    /**
     * Create an instance of {@link ServiceType }
     * 
     */
    public ServiceType createServiceType() {
        return new ServiceType();
    }

    /**
     * Create an instance of {@link GatesType }
     * 
     */
    public GatesType createGatesType() {
        return new GatesType();
    }

    /**
     * Create an instance of {@link IdentifiedEntity }
     * 
     */
    public IdentifiedEntity createIdentifiedEntity() {
        return new IdentifiedEntity();
    }

    /**
     * Create an instance of {@link VehiclesType }
     * 
     */
    public VehiclesType createVehiclesType() {
        return new VehiclesType();
    }

    /**
     * Create an instance of {@link GateType }
     * 
     */
    public GateType createGateType() {
        return new GateType();
    }

    /**
     * Create an instance of {@link ParkingAreaType }
     * 
     */
    public ParkingAreaType createParkingAreaType() {
        return new ParkingAreaType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RnsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "rnsInfo")
    public JAXBElement<RnsType> createRnsInfo(RnsType value) {
        return new JAXBElement<RnsType>(_RnsInfo_QNAME, RnsType.class, null, value);
    }

}
