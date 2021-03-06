
package it.polito.dp2.RNS.sol1.jaxb;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PlaceType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PlaceType">
 *   &lt;complexContent>
 *     &lt;extension base="{}IdentifiedEntity">
 *       &lt;sequence>
 *         &lt;element name="nextPlace" type="{}NextPlaceType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="capacity" use="required" type="{http://www.w3.org/2001/XMLSchema}unsignedShort" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PlaceType", propOrder = {
    "nextPlace"
})
@XmlSeeAlso({
    RoadSegmentType.class,
    GateType.class,
    ParkingAreaType.class
})
public class PlaceType
    extends IdentifiedEntity
{

    protected List<NextPlaceType> nextPlace;
    @XmlAttribute(name = "capacity", required = true)
    @XmlSchemaType(name = "unsignedShort")
    protected int capacity;

    /**
     * Gets the value of the nextPlace property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the nextPlace property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNextPlace().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link NextPlaceType }
     * 
     * 
     */
    public List<NextPlaceType> getNextPlace() {
        if (nextPlace == null) {
            nextPlace = new ArrayList<NextPlaceType>();
        }
        return this.nextPlace;
    }

    public boolean isSetNextPlace() {
        return ((this.nextPlace!= null)&&(!this.nextPlace.isEmpty()));
    }

    public void unsetNextPlace() {
        this.nextPlace = null;
    }

    /**
     * Gets the value of the capacity property.
     * 
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Sets the value of the capacity property.
     * 
     */
    public void setCapacity(int value) {
        this.capacity = value;
    }

    public boolean isSetCapacity() {
        return true;
    }

}
