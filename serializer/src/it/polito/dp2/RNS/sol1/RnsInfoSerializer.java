package it.polito.dp2.RNS.sol1;

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
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class RnsInfoSerializer {

    private Converter converter;
    private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");

    /**
     * Default constructor.
     *
     * @throws RnsReaderException thrown if an implementation of RnsReader cannot be created
     */
    private RnsInfoSerializer() throws RnsReaderException {
        this(RnsReaderFactory.newInstance().newRnsReader());
    }

    /**
     * Creates a RnsInfoSerializer with a given monitor.
     *
     * @param monitor monitor to be used
     * @throws RnsReaderException thrown if an implementation of RnsReader cannot be created
     */
    public RnsInfoSerializer(RnsReader monitor) throws RnsReaderException {
        if (monitor == null)
            throw new RnsReaderException("Invalid monitor");

        converter = new Converter(monitor);
    }

    /**
     * Main application serializing data about the DP2-RNS system.
     *
     * @param args args[0]  represents the output file name
     */
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java RnsInfoSerializer filename");
            System.exit(1);
        }

        String filename = args[0];

        // Creating the output file
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(new File(filename), false);
        } catch (FileNotFoundException e) {
            System.err.println("Could not create output file");
            e.printStackTrace();
            System.exit(1);
        }

        // Retrieving data and serializing
//        JAXBElement<RnsType> rns = null;
        try {
            RnsInfoSerializer serializer = new RnsInfoSerializer();
//            rns = serializer.toJAXB();      // Conversion
//            serializer.marshal(rns, fos);   // Marshalling
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
        // Validation
        SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = null;
        try {
            schema = sf.newSchema(new File(Config.schemaFile));
        } catch (SAXException e) {
            System.err.println("Could not read the schema file");
            e.printStackTrace();
        }

        try {
            // JAXBContext capable of handling JAXB-compatible generated classes
            JAXBContext jc = JAXBContext.newInstance(Config.jaxbClassesPackage);

            // Creating a Marshaller
            Marshaller m = jc.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            m.setSchema(schema);
            // allows unmarshalling to continue even if there are errors
            m.setEventHandler(ve -> {
                // ignore warnings
                if (ve.getSeverity() != ValidationEvent.WARNING) {
                    ValidationEventLocator vel = ve.getLocator();
                    System.out.println("Line:Col[" + vel.getLineNumber() +
                            ":" + vel.getColumnNumber() +
                            "]:" + ve.getMessage()
                    );
                }
                return true;
            });

            // Marshalling to the specified file
            m.marshal(obj, os == null ? System.out : os);
            if (os != null)
                os.close();
        } catch (JAXBException e1) {
            System.err.println("A JAXB exception occurred");
            e1.printStackTrace();
            System.exit(1);
        } catch (IOException e) {
            System.err.println("An I/O exception occurred");
            e.printStackTrace();
            System.exit(1);
        } catch (IllegalArgumentException e) {
            if (obj == null)
                System.err.println("Cannot marshal null object");
            else if (os == null)
                System.err.println("Invalid output stream");
            e.printStackTrace();
            System.exit(1);
        }
    }
}
