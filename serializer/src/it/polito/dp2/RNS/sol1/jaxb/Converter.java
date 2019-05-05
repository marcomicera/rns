package it.polito.dp2.RNS.sol1.jaxb;

import it.polito.dp2.RNS.RnsReader;

import javax.xml.bind.JAXBElement;

public class Converter {

    /**
     * Reader from which data has to be retrieved
     */
    private RnsReader monitor;

    /**
     * Default constructor creating a converter with a given monitor.
     *
     * @param monitor   RNS reader object from which data can be read
     */
    public Converter(RnsReader monitor) {
        this.monitor = monitor;
    }

    /**
     * Converts the RNS object obtainable by RnsReader into a JAXB equivalent class.
     *
     * @return the RNS JAXB-compatible object
     */
//    public JAXBElement<RnsType> getRnsInfo() {
//        // Retrieving objects...
//        // ...
//
//        RnsType rnsInfo = new RnsType();
//        // rnsInfo.set...
//        // rnsInfo.set...
//        // ...
//
//        return new ObjectFactory().createRnsInfo(rnsInfo);
//    }
}
