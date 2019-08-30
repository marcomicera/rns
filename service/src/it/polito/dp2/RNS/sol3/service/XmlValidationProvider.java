package it.polito.dp2.RNS.sol3.service;

// This validator performs JAXB unmarshalling with validation
// against the schema
import static javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import it.polito.dp2.RNS.sol3.service.conf.Config;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

@Provider
@Consumes({"application/xml","text/xml"})
public class XmlValidationProvider<T> implements MessageBodyReader<T> {
    final String jaxbPackage = Config.Service.jaxbClassesPackage;
    Unmarshaller unmarshaller;
    Logger logger;
    String responseBodyTemplate;


    public XmlValidationProvider() {
        logger = Logger.getLogger(XmlValidationProvider.class.getName());

        try {
            InputStream schemaStream = XmlValidationProvider.class.getResourceAsStream(Config.Service.schemaFile);
            if (schemaStream == null) {
                String errorMessage = "XML schema file not found: " + Config.Service.schemaFile;
                logger.log(Level.SEVERE, errorMessage);
                throw new IOException(errorMessage);
            }
            JAXBContext jc = JAXBContext.newInstance( jaxbPackage );
            unmarshaller = jc.createUnmarshaller();
            SchemaFactory sf = SchemaFactory.newInstance(W3C_XML_SCHEMA_NS_URI);
            Schema schema = sf.newSchema(new StreamSource(schemaStream));
            unmarshaller.setSchema(schema);

            InputStream templateStream = XmlValidationProvider.class.getResourceAsStream(Config.fileNotFoundPage);
            if (templateStream == null) {
                String errorMessage = "\"File not found\" HTML template not found: " + Config.fileNotFoundPage;
                logger.log(Level.SEVERE, errorMessage);
                throw new IOException(errorMessage);
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(templateStream));
            StringBuilder out = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                out.append(line);
            }
            responseBodyTemplate = out.toString();

            logger.log(Level.INFO, "XmlProvider initialized successfully");
        } catch (SAXException | JAXBException | IOException se) {
            logger.log(Level.SEVERE, "Error parsing xml directory file. Service will not work properly.", se);
        }
    }

    @Override
    public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return jaxbPackage.equals(type.getPackage().getName());
    }

    @Override
    public T readFrom(Class<T> type, Type genericType, Annotation[] annotations, MediaType mediaType,
                      MultivaluedMap<String, String> httpHeaders, InputStream entityStream)
            throws IOException, WebApplicationException {
        try {
            return (T) unmarshaller.unmarshal(entityStream);
        } catch (JAXBException ex) {
            logger.log(Level.WARNING, "Request body validation error.", ex);
            Throwable linked = ex.getLinkedException();
            String validationErrorMessage = "Request body validation error";
            if (linked != null && linked instanceof SAXParseException)
                validationErrorMessage += ": " + linked.getMessage();
            BadRequestException bre = new BadRequestException("Request body validation error");
            String responseBody = responseBodyTemplate.replaceFirst("___TO_BE_REPLACED___", validationErrorMessage);
            Response response = Response.fromResponse(bre.getResponse()).entity(responseBody).build();
            throw new BadRequestException("Request body validation error", response);
        } catch (ClassCastException ex) {
            logger.log(Level.WARNING, "Request body validation error. Wrong Type.", ex);
            BadRequestException bre = new BadRequestException("Request body validation error. Wrong Type.");
            String responseBody = responseBodyTemplate.replaceFirst("___TO_BE_REPLACED___", "Request body validation error. Wrong Type.");
            Response response = Response.fromResponse(bre.getResponse()).entity(responseBody).build();
            throw new BadRequestException("Request body validation error", response);
        }
    }

}
