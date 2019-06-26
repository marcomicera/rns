package it.polito.dp2.RNS.sol1.conf;

/**
 * Configuration class.
 */
public class Config {

    /**
     * Where the schema RNS file is located.
     */
    public static final String schemaFile = "xsd/rnsInfo.xsd";
    public static final String inputFileProperty = "it.polito.dp2.RNS.sol1.RnsInfo.file";
    public static final String jaxbClassesPackage = "it.polito.dp2.RNS.sol1.jaxb";

    /**
     * Makes this configuration class non-instantiable.
     */
    private Config() {
    }
}
