
package it.polito.dp2.RNS.sol2.jaxb;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the it.polito.dp2.RNS.sol2.jaxb package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _RnsInfo_QNAME = new QName("", "rnsInfo");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: it.polito.dp2.RNS.sol2.jaxb
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link RnsType }
     * 
     */
    public RnsType createRnsType() {
        return new RnsType();
    }

    /**
     * Create an instance of {@link RelationshipsType }
     * 
     */
    public RelationshipsType createRelationshipsType() {
        return new RelationshipsType();
    }

    /**
     * Create an instance of {@link NodeType }
     * 
     */
    public NodeType createNodeType() {
        return new NodeType();
    }

    /**
     * Create an instance of {@link RelationType }
     * 
     */
    public RelationType createRelationType() {
        return new RelationType();
    }

    /**
     * Create an instance of {@link NodesType }
     * 
     */
    public NodesType createNodesType() {
        return new NodesType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RnsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "rnsInfo")
    public JAXBElement<RnsType> createRnsInfo(RnsType value) {
        return new JAXBElement<RnsType>(_RnsInfo_QNAME, RnsType.class, null, value);
    }

}