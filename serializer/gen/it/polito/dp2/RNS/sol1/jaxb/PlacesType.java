
package it.polito.dp2.RNS.sol1.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PlacesType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PlacesType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="gates" type="{}GatesType" minOccurs="0"/>
 *         &lt;element name="parkingAreas" type="{}ParkingAreasType" minOccurs="0"/>
 *         &lt;element name="roadSegments" type="{}RoadSegmentsType" minOccurs="0"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PlacesType", propOrder = {

})
public class PlacesType {

    protected GatesType gates;
    protected ParkingAreasType parkingAreas;
    protected RoadSegmentsType roadSegments;

    /**
     * Gets the value of the gates property.
     * 
     * @return
     *     possible object is
     *     {@link GatesType }
     *     
     */
    public GatesType getGates() {
        return gates;
    }

    /**
     * Sets the value of the gates property.
     * 
     * @param value
     *     allowed object is
     *     {@link GatesType }
     *     
     */
    public void setGates(GatesType value) {
        this.gates = value;
    }

    public boolean isSetGates() {
        return (this.gates!= null);
    }

    /**
     * Gets the value of the parkingAreas property.
     * 
     * @return
     *     possible object is
     *     {@link ParkingAreasType }
     *     
     */
    public ParkingAreasType getParkingAreas() {
        return parkingAreas;
    }

    /**
     * Sets the value of the parkingAreas property.
     * 
     * @param value
     *     allowed object is
     *     {@link ParkingAreasType }
     *     
     */
    public void setParkingAreas(ParkingAreasType value) {
        this.parkingAreas = value;
    }

    public boolean isSetParkingAreas() {
        return (this.parkingAreas!= null);
    }

    /**
     * Gets the value of the roadSegments property.
     * 
     * @return
     *     possible object is
     *     {@link RoadSegmentsType }
     *     
     */
    public RoadSegmentsType getRoadSegments() {
        return roadSegments;
    }

    /**
     * Sets the value of the roadSegments property.
     * 
     * @param value
     *     allowed object is
     *     {@link RoadSegmentsType }
     *     
     */
    public void setRoadSegments(RoadSegmentsType value) {
        this.roadSegments = value;
    }

    public boolean isSetRoadSegments() {
        return (this.roadSegments!= null);
    }

}
