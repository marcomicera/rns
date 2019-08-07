
package it.polito.dp2.RNS.sol1.jaxb;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RoadSegmentsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RoadSegmentsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="roadSegment" type="{}RoadSegmentType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RoadSegmentsType", propOrder = {
    "roadSegment"
})
public class RoadSegmentsType {

    @XmlElement(required = true)
    protected List<RoadSegmentType> roadSegment;

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
     * {@link RoadSegmentType }
     * 
     * 
     */
    public List<RoadSegmentType> getRoadSegment() {
        if (roadSegment == null) {
            roadSegment = new ArrayList<RoadSegmentType>();
        }
        return this.roadSegment;
    }

    public boolean isSetRoadSegment() {
        return ((this.roadSegment!= null)&&(!this.roadSegment.isEmpty()));
    }

    public void unsetRoadSegment() {
        this.roadSegment = null;
    }

}
