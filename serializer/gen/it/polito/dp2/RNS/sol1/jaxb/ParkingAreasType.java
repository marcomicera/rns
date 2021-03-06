
package it.polito.dp2.RNS.sol1.jaxb;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ParkingAreasType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ParkingAreasType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="parkingArea" type="{}ParkingAreaType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ParkingAreasType", propOrder = {
    "parkingArea"
})
public class ParkingAreasType {

    @XmlElement(required = true)
    protected List<ParkingAreaType> parkingArea;

    /**
     * Gets the value of the parkingArea property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the parkingArea property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getParkingArea().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ParkingAreaType }
     * 
     * 
     */
    public List<ParkingAreaType> getParkingArea() {
        if (parkingArea == null) {
            parkingArea = new ArrayList<ParkingAreaType>();
        }
        return this.parkingArea;
    }

    public boolean isSetParkingArea() {
        return ((this.parkingArea!= null)&&(!this.parkingArea.isEmpty()));
    }

    public void unsetParkingArea() {
        this.parkingArea = null;
    }

}
