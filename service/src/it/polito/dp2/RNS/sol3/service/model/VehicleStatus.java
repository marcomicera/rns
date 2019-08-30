
package it.polito.dp2.RNS.sol3.service.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for VehicleStatus.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="VehicleStatus">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="PARKED"/>
 *     &lt;enumeration value="IN_TRANSIT"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "VehicleStatus")
@XmlEnum
public enum VehicleStatus {

    PARKED,
    IN_TRANSIT;

    public String value() {
        return name();
    }

    public static VehicleStatus fromValue(String v) {
        return valueOf(v);
    }

}
