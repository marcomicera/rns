//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.06.16 at 02:53:14 AM CEST 
//


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

}
