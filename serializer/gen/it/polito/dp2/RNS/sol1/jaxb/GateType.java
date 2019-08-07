
package it.polito.dp2.RNS.sol1.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GateType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GateType">
 *   &lt;complexContent>
 *     &lt;extension base="{}PlaceType">
 *       &lt;attribute name="type" use="required" type="{}GateTypeType" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GateType")
public class GateType
    extends PlaceType
{

    @XmlAttribute(name = "type", required = true)
    protected GateTypeType type;

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link GateTypeType }
     *     
     */
    public GateTypeType getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link GateTypeType }
     *     
     */
    public void setType(GateTypeType value) {
        this.type = value;
    }

    public boolean isSetType() {
        return (this.type!= null);
    }

}
