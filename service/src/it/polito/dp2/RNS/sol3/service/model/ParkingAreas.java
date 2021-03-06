
package it.polito.dp2.RNS.sol3.service.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ParkingAreas complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ParkingAreas">
 *   &lt;complexContent>
 *     &lt;extension base="{}pagedResponse">
 *       &lt;sequence>
 *         &lt;element name="parkingArea" type="{}ParkingArea" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ParkingAreas", propOrder = {
    "parkingArea"
})
public class ParkingAreas
    extends PagedResponse
{

    @XmlElement(required = true)
    protected List<ParkingArea> parkingArea;

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
     * {@link ParkingArea }
     * 
     * 
     */
    public List<ParkingArea> getParkingArea() {
        if (parkingArea == null) {
            parkingArea = new ArrayList<ParkingArea>();
        }
        return this.parkingArea;
    }

}
