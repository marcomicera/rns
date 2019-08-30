package it.polito.dp2.RNS.sol3.service;

import it.polito.dp2.RNS.sol3.service.conf.Config;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.ReaderInterceptor;
import javax.ws.rs.ext.ReaderInterceptorContext;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.util.JAXBSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

import static javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI;


@Provider
@Consumes({"application/json", "text/json"})
public class JsonValidationInterceptor implements ReaderInterceptor {
    final String jaxbPackage = Config.Service.jaxbClassesPackage;
    Schema schema;
    JAXBContext jc;
    Logger logger;
    String responseBodyTemplate;

    public JsonValidationInterceptor() {
        logger = Logger.getLogger(XmlValidationProvider.class.getName());

        try {
            InputStream schemaStream = XmlValidationProvider.class.getResourceAsStream(Config.Service.schemaFile);
            if (schemaStream == null) {
                String errorMessage = "XML schema file not found: " + Config.Service.schemaFile;
                logger.log(Level.SEVERE, errorMessage);
                throw new IOException(errorMessage);
            }
            SchemaFactory sf = SchemaFactory.newInstance(W3C_XML_SCHEMA_NS_URI);
            schema = sf.newSchema(new StreamSource(schemaStream));

            jc = JAXBContext.newInstance(jaxbPackage);

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

            logger.log(Level.INFO, "JsonValidationInterceptor initialized successfully");
        } catch (Exception se) {
            logger.log(Level.SEVERE, "Error initializing JsonValidationInterceptor. Service will not work properly.", se);
        }
    }

    public void validate(Object item) {
        try {
            JAXBSource source = new JAXBSource(jc, item);
            Validator v = schema.newValidator();
            v.setErrorHandler(new MyErrorHandler());
            v.validate(source);
        } catch (SAXException e) {
            logger.log(Level.WARNING, "Request body validation error.", e);
            String validationErrorMessage = "Request body validation error";
            if (e.getMessage() != null)
                validationErrorMessage += ": " + e.getMessage();
            Throwable linked = e.getCause();
            while (linked != null) {
                if (linked instanceof SAXParseException && linked.getMessage() != null)
                    validationErrorMessage += ": " + linked.getMessage();
                linked = linked.getCause();
            }
            BadRequestException bre = new BadRequestException("Request body validation error");
            String responseBody = responseBodyTemplate.replaceFirst("___TO_BE_REPLACED___", validationErrorMessage);
            Response response = Response.fromResponse(bre.getResponse()).entity(responseBody).type("text/html").build();
            throw new BadRequestException("Request body validation error", response);
        } catch (Exception e) {
            throw new InternalServerErrorException();
        }
    }

    @Override
    public Object aroundReadFrom(ReaderInterceptorContext context) throws IOException, WebApplicationException {
        Object ret = context.proceed();
        validate(ret);
        return ret;
    }

}
