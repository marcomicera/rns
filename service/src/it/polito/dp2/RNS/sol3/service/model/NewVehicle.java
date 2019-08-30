
package it.polito.dp2.RNS.sol3.service.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for NewVehicle complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="NewVehicle">
 *   &lt;complexContent>
 *     &lt;extension base="{}IdentifiedEntity">
 *       &lt;sequence>
 *         &lt;element name="suggestedPath" type="{}SuggestedPath"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NewVehicle", propOrder = {
    "suggestedPath"
})
public class NewVehicle
    extends IdentifiedEntity
{

    @XmlElement(required = true)
    protected SuggestedPath suggestedPath;

    /**
     * Gets the value of the suggestedPath property.
     * 
     * @return
     *     possible object is
     *     {@link SuggestedPath }
     *     
     */
    public SuggestedPath getSuggestedPath() {
        return suggestedPath;
    }

    /**
     * Sets the value of the suggestedPath property.
     * 
     * @param value
     *     allowed object is
     *     {@link SuggestedPath }
     *     
     */
    public void setSuggestedPath(SuggestedPath value) {
        this.suggestedPath = value;
    }

}
