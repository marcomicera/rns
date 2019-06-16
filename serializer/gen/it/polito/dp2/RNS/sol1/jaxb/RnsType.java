//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.06.16 at 02:30:59 AM CEST 
//


package it.polito.dp2.RNS.sol1.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RnsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RnsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="places" type="{}PlacesType" minOccurs="0"/>
 *         &lt;element name="connections" type="{}ConnectionsType" minOccurs="0"/>
 *         &lt;element name="vehicles" type="{}VehiclesType" minOccurs="0"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RnsType", propOrder = {

})
public class RnsType {

    protected PlacesType places;
    protected ConnectionsType connections;
    protected VehiclesType vehicles;

    /**
     * Gets the value of the places property.
     * 
     * @return
     *     possible object is
     *     {@link PlacesType }
     *     
     */
    public PlacesType getPlaces() {
        return places;
    }

    /**
     * Sets the value of the places property.
     * 
     * @param value
     *     allowed object is
     *     {@link PlacesType }
     *     
     */
    public void setPlaces(PlacesType value) {
        this.places = value;
    }

    /**
     * Gets the value of the connections property.
     * 
     * @return
     *     possible object is
     *     {@link ConnectionsType }
     *     
     */
    public ConnectionsType getConnections() {
        return connections;
    }

    /**
     * Sets the value of the connections property.
     * 
     * @param value
     *     allowed object is
     *     {@link ConnectionsType }
     *     
     */
    public void setConnections(ConnectionsType value) {
        this.connections = value;
    }

    /**
     * Gets the value of the vehicles property.
     * 
     * @return
     *     possible object is
     *     {@link VehiclesType }
     *     
     */
    public VehiclesType getVehicles() {
        return vehicles;
    }

    /**
     * Sets the value of the vehicles property.
     * 
     * @param value
     *     allowed object is
     *     {@link VehiclesType }
     *     
     */
    public void setVehicles(VehiclesType value) {
        this.vehicles = value;
    }

}
