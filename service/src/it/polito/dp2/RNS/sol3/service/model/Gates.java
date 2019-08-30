
package it.polito.dp2.RNS.sol3.service.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Gates complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Gates">
 *   &lt;complexContent>
 *     &lt;extension base="{}pagedResponse">
 *       &lt;sequence>
 *         &lt;element name="gate" type="{}Gate" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Gates", propOrder = {
    "gate"
})
public class Gates
    extends PagedResponse
{

    @XmlElement(required = true)
    protected List<Gate> gate;

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
     * {@link Gate }
     * 
     * 
     */
    public List<Gate> getGate() {
        if (gate == null) {
            gate = new ArrayList<Gate>();
        }
        return this.gate;
    }

}
