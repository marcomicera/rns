
package it.polito.dp2.RNS.sol3.service.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RoadSegments complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RoadSegments">
 *   &lt;complexContent>
 *     &lt;extension base="{}pagedResponse">
 *       &lt;sequence>
 *         &lt;element name="roadSegment" type="{}RoadSegment" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RoadSegments", propOrder = {
    "roadSegment"
})
public class RoadSegments
    extends PagedResponse
{

    @XmlElement(required = true)
    protected List<RoadSegment> roadSegment;

    /**
     * Gets the value of the roadSegment property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the roadSegment property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRoadSegment().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RoadSegment }
     * 
     * 
     */
    public List<RoadSegment> getRoadSegment() {
        if (roadSegment == null) {
            roadSegment = new ArrayList<RoadSegment>();
        }
        return this.roadSegment;
    }

}
