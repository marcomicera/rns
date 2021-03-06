package it.polito.dp2.RNS.sol1;

import it.polito.dp2.RNS.FactoryConfigurationError;
import it.polito.dp2.RNS.RnsReader;
import it.polito.dp2.RNS.RnsReaderException;
import it.polito.dp2.RNS.RnsReaderFactory;
import it.polito.dp2.RNS.sol1.conf.Config;
import it.polito.dp2.RNS.sol1.jaxb.Converter;
import it.polito.dp2.RNS.sol1.jaxb.RnsType;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.*;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.*;

public class RnsInfoSerializer {

    /**
     * Converts data retrieved by reader interfaces into JAXB-compatible objects.
     */
    private Converter converter;

    /**
     * Default constructor.
     *
     * @throws RnsReaderException        thrown if an implementation of RnsReader cannot be created
     * @throws FactoryConfigurationError thrown if the implementation of the concrete factory is not
     *                                   available or cannot be instantiated.
     */
    private RnsInfoSerializer() throws RnsReaderException, FactoryConfigurationError {
        this(RnsReaderFactory.newInstance().newRnsReader());
    }

    /**
     * Creates a RnsInfoSerializer with a given monitor.
     *
     * @param monitor monitor to be used
     * @throws RnsReaderException thrown if an implementation of RnsReader cannot be created
     */
    public RnsInfoSerializer(RnsReader monitor) throws RnsReaderException {

        if (monitor == null) {
            throw new RnsReaderException("Invalid monitor");
        }

        converter = new Converter(monitor);
    }

    /**
     * Main application serializing data about the DP2-RNS system.
     *
     * @param args args[0]  represents the output file name
     */
    public static void main(String[] args) {

        // Arguments checking
        if (args.length != 1) {
            System.err.println("Usage: java RnsInfoSerializer filename");
            System.exit(1);
        }
        String fileName = args[0];

        // Creating the output file
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(new File(fileName), false);
        } catch (FileNotFoundException | SecurityException e) {
            System.err.println("Could not create output file");
            e.printStackTrace();
            System.exit(1);
        }

        // Retrieving data and serializing
        try {
            RnsInfoSerializer serializer = new RnsInfoSerializer();
            JAXBElement<RnsType> rns = serializer.toJAXB(); // Conversion
            serializer.marshal(rns, fos);                   // Marshalling
        } catch (RnsReaderException e) {
            System.err.println("Could not instantiate data generator");
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Converts the RNS object obtainable by RnsReader into a JAXB equivalent class.
     *
     * @return the RNS JAXB-compatible object
     */
    private JAXBElement<RnsType> toJAXB() {
        return converter.getRnsInfo();
    }

    /**
     * Marshals object obj to the OutputStream os using a schema.
     *
     * @param obj object to be marshalled
     * @param os  OutputStream used during marshalling
     */
    private void marshal(Object obj, OutputStream os) {

        try {
            // Data will be validated against this XML schema
            Schema schema = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI).newSchema(new File(Config.schemaFile));

            // Creating a Marshaller
            Marshaller m = JAXBContext.newInstance(Config.jaxbClassesPackage).createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            m.setSchema(schema);
            m.setEventHandler((ValidationEvent ve) -> {
                if (ve.getSeverity() != ValidationEvent.WARNING) { // ignoring warnings
                    ValidationEventLocator vel = ve.getLocator();
                    System.out.println("Line:Col[" + vel.getLineNumber() +
                            ":" + vel.getColumnNumber() +
                            "]: " + ve.getMessage());
                }
                return true; // the JAXB Provider continues validating also in case of a [fatal] error
            });

            // Marshalling to the specified file
            m.marshal(obj, os == null ? System.out : os);

        } catch (SAXException e) {
            System.err.println("Error while parsing RNS data.");
            e.printStackTrace();
            System.exit(1);
        } catch (JAXBException e1) {
            System.err.println("Error while creating a JAXB context class or marshalling");
            e1.printStackTrace();
            System.exit(1);
        } catch (IllegalArgumentException e) {
            if (obj == null)
                System.err.println("Cannot marshal null object");
            else if (os == null)
                System.err.println("Invalid output stream");
            e.printStackTrace();
            System.exit(1);
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    System.err.println("An I/O exception occurred");
                    e.printStackTrace();
                    System.exit(1);
                }
            }
        }
    }
}
