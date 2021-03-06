
package it.polito.dp2.RNS.sol2.database.model;

import java.util.ArrayList;
import java.util.List;
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
 *       &lt;sequence>
 *         &lt;element name="nodes" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="node" type="{}NodeType" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="relationships" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="relation" type="{}RelationshipType" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="shortestPathRequest" type="{}ShortestPathRequestType" minOccurs="0"/>
 *         &lt;element name="shortestPathResponse" type="{}ShortestPathResponseType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RnsType", propOrder = {
    "nodes",
    "relationships",
    "shortestPathRequest",
    "shortestPathResponse"
})
public class RnsType {

    protected RnsType.Nodes nodes;
    protected RnsType.Relationships relationships;
    protected ShortestPathRequestType shortestPathRequest;
    protected ShortestPathResponseType shortestPathResponse;

    /**
     * Gets the value of the nodes property.
     * 
     * @return
     *     possible object is
     *     {@link RnsType.Nodes }
     *     
     */
    public RnsType.Nodes getNodes() {
        return nodes;
    }

    /**
     * Sets the value of the nodes property.
     * 
     * @param value
     *     allowed object is
     *     {@link RnsType.Nodes }
     *     
     */
    public void setNodes(RnsType.Nodes value) {
        this.nodes = value;
    }

    public boolean isSetNodes() {
        return (this.nodes!= null);
    }

    /**
     * Gets the value of the relationships property.
     * 
     * @return
     *     possible object is
     *     {@link RnsType.Relationships }
     *     
     */
    public RnsType.Relationships getRelationships() {
        return relationships;
    }

    /**
     * Sets the value of the relationships property.
     * 
     * @param value
     *     allowed object is
     *     {@link RnsType.Relationships }
     *     
     */
    public void setRelationships(RnsType.Relationships value) {
        this.relationships = value;
    }

    public boolean isSetRelationships() {
        return (this.relationships!= null);
    }

    /**
     * Gets the value of the shortestPathRequest property.
     * 
     * @return
     *     possible object is
     *     {@link ShortestPathRequestType }
     *     
     */
    public ShortestPathRequestType getShortestPathRequest() {
        return shortestPathRequest;
    }

    /**
     * Sets the value of the shortestPathRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link ShortestPathRequestType }
     *     
     */
    public void setShortestPathRequest(ShortestPathRequestType value) {
        this.shortestPathRequest = value;
    }

    public boolean isSetShortestPathRequest() {
        return (this.shortestPathRequest!= null);
    }

    /**
     * Gets the value of the shortestPathResponse property.
     * 
     * @return
     *     possible object is
     *     {@link ShortestPathResponseType }
     *     
     */
    public ShortestPathResponseType getShortestPathResponse() {
        return shortestPathResponse;
    }

    /**
     * Sets the value of the shortestPathResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link ShortestPathResponseType }
     *     
     */
    public void setShortestPathResponse(ShortestPathResponseType value) {
        this.shortestPathResponse = value;
    }

    public boolean isSetShortestPathResponse() {
        return (this.shortestPathResponse!= null);
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="node" type="{}NodeType" maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "node"
    })
    public static class Nodes {

        protected List<NodeType> node;

        /**
         * Gets the value of the node property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the node property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getNode().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link NodeType }
         * 
         * 
         */
        public List<NodeType> getNode() {
            if (node == null) {
                node = new ArrayList<NodeType>();
            }
            return this.node;
        }

        public boolean isSetNode() {
            return ((this.node!= null)&&(!this.node.isEmpty()));
        }

        public void unsetNode() {
            this.node = null;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="relation" type="{}RelationshipType" maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "relation"
    })
    public static class Relationships {

        protected List<RelationshipType> relation;

        /**
         * Gets the value of the relation property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the relation property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getRelation().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link RelationshipType }
         * 
         * 
         */
        public List<RelationshipType> getRelation() {
            if (relation == null) {
                relation = new ArrayList<RelationshipType>();
            }
            return this.relation;
        }

        public boolean isSetRelation() {
            return ((this.relation!= null)&&(!this.relation.isEmpty()));
        }

        public void unsetRelation() {
            this.relation = null;
        }

    }

}
