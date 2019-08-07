
package it.polito.dp2.RNS.sol1.jaxb;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GateTypeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="GateTypeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="IN"/>
 *     &lt;enumeration value="OUT"/>
 *     &lt;enumeration value="INOUT"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "GateTypeType")
@XmlEnum
public enum GateTypeType {

    IN,
    OUT,
    INOUT;

    public String value() {
        return name();
    }

    public static GateTypeType fromValue(String v) {
        return valueOf(v);
    }

}
