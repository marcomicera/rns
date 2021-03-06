
package it.polito.dp2.RNS.sol3.service.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for Vehicle complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Vehicle">
 *   &lt;complexContent>
 *     &lt;extension base="{}IdentifiedEntity">
 *       &lt;sequence>
 *         &lt;element name="entryTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *       &lt;/sequence>
 *       &lt;attribute name="type" use="required" type="{}VehicleType" />
 *       &lt;attribute name="state" use="required" type="{}VehicleStatus" />
 *       &lt;attribute name="position" use="required" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *       &lt;attribute name="origin" use="required" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *       &lt;attribute name="destination" use="required" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Vehicle", propOrder = {
    "entryTime"
})
public class Vehicle
    extends IdentifiedEntity
{

    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar entryTime;
    @XmlAttribute(name = "type", required = true)
    protected VehicleType type;
    @XmlAttribute(name = "state", required = true)
    protected VehicleStatus state;
    @XmlAttribute(name = "position", required = true)
    @XmlSchemaType(name = "anyURI")
    protected String position;
    @XmlAttribute(name = "origin", required = true)
    @XmlSchemaType(name = "anyURI")
    protected String origin;
    @XmlAttribute(name = "destination", required = true)
    @XmlSchemaType(name = "anyURI")
    protected String destination;

    /**
     * Gets the value of the entryTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEntryTime() {
        return entryTime;
    }

    /**
     * Sets the value of the entryTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEntryTime(XMLGregorianCalendar value) {
        this.entryTime = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link VehicleType }
     *     
     */
    public VehicleType getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link VehicleType }
     *     
     */
    public void setType(VehicleType value) {
        this.type = value;
    }

    /**
     * Gets the value of the state property.
     * 
     * @return
     *     possible object is
     *     {@link VehicleStatus }
     *     
     */
    public VehicleStatus getState() {
        return state;
    }

    /**
     * Sets the value of the state property.
     * 
     * @param value
     *     allowed object is
     *     {@link VehicleStatus }
     *     
     */
    public void setState(VehicleStatus value) {
        this.state = value;
    }

    /**
     * Gets the value of the position property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPosition() {
        return position;
    }

    /**
     * Sets the value of the position property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPosition(String value) {
        this.position = value;
    }

    /**
     * Gets the value of the origin property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrigin() {
        return origin;
    }

    /**
     * Sets the value of the origin property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrigin(String value) {
        this.origin = value;
    }

    /**
     * Gets the value of the destination property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDestination() {
        return destination;
    }

    /**
     * Sets the value of the destination property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDestination(String value) {
        this.destination = value;
    }

}
