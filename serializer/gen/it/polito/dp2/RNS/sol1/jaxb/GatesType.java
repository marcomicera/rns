
package it.polito.dp2.RNS.sol1.jaxb;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GatesType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GatesType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="gate" type="{}GateType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GatesType", propOrder = {
    "gate"
})
public class GatesType {

    @XmlElement(required = true)
    protected List<GateType> gate;

    /**
     * Gets the value of the gate property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the gate property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGate().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link GateType }
     * 
     * 
     */
    public List<GateType> getGate() {
        if (gate == null) {
            gate = new ArrayList<GateType>();
        }
        return this.gate;
    }

    public boolean isSetGate() {
        return ((this.gate!= null)&&(!this.gate.isEmpty()));
    }

    public void unsetGate() {
        this.gate = null;
    }

}
