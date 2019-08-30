package it.polito.dp2.RNS.sol3.service.conf;

/**
 * Configuration class.
 */
public class Config {

    public static final String fileNotFoundPage = "/html/BadRequestBodyTemplate.html";

    /**
     * Makes this configuration class non-instantiable.
     */
    private Config() {
    }

    public static class Neo4J {

        public static final String serviceUrl = "it.polito.dp2.RNS.lab3.Neo4JURL";
        public static final String serviceDefaultUrl = "http://localhost:7474/db";
        public static final String serviceDataRelativePath = "/data/";

        public static final String schemaFile = "/xsd/RnsDb.xsd";
        public static final String jaxbClassesPackage = "it.polito.dp2.RNS.sol2.database.model";
    }

    public static class Service {

        public static final String schemaFile = "/xsd/RnsSystem.xsd";
        public static final String jaxbClassesPackage = "it.polito.dp2.RNS.sol3.service.model";
    }
}
