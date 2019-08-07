
package it.polito.dp2.RNS.sol1.jaxb;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for VehicleTypeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="VehicleTypeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="CAR"/>
 *     &lt;enumeration value="TRUCK"/>
 *     &lt;enumeration value="SHUTTLE"/>
 *     &lt;enumeration value="CARAVAN"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "VehicleTypeType")
@XmlEnum
public enum VehicleTypeType {

    CAR,
    TRUCK,
    SHUTTLE,
    CARAVAN;

    public String value() {
        return name();
    }

    public static VehicleTypeType fromValue(String v) {
        return valueOf(v);
    }

}
