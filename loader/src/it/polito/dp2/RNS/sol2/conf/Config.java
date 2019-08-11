package it.polito.dp2.RNS.sol2.conf;

/**
 * Configuration class.
 */
public class Config {

    /**
     * Where the schema RNS file is located.
     */
    public static final String schemaFile = "custom/rnsDb.xsd";
    public static final String jaxbClassesPackage = "it.polito.dp2.RNS.sol2.jaxb";

    /**
     * Makes this configuration class non-instantiable.
     */
    private Config() {
    }
}
