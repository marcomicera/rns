package it.polito.dp2.RNS.sol3.service.database;

import it.polito.dp2.RNS.FactoryConfigurationError;
import it.polito.dp2.RNS.RnsReader;
import it.polito.dp2.RNS.RnsReaderException;
import it.polito.dp2.RNS.RnsReaderFactory;
import it.polito.dp2.RNS.lab1.RnsInfo;

import javax.xml.datatype.DatatypeConfigurationException;

public class RnsDatabase {

    /**
     * Indicates whether the RNS database has been already downloaded or not.
     */
    private static boolean loaded = false;

    /**
     * Monitor through which RNS info will be retrieved.
     */
    private static RnsReader monitor;

    /**
     * This class is a singleton.
     */
    private RnsDatabase() {

    }

    /**
     * Initializes this database.
     *
     * @throws FactoryConfigurationError if there is some error while retrieving an implementation of the
     *                                   RnsReader interface.
     * @throws RnsReaderException        if an implementation of {@code RnsReader} cannot be created.
     */
    public synchronized static void init() throws RnsReaderException, FactoryConfigurationError {

        // If the database has not been downloaded yet
        if (!loaded) {

            // TODO debugging
            System.out.println("Downloading RNS info...");

            // Using the RnsReader interface to retrieve data
            monitor = RnsReaderFactory.newInstance().newRnsReader();

            // TODO debugging
            System.out.println("Input data:");
            new RnsInfo(monitor).printAll();

            // TODO Downloading places

            // Downloading vehicles
            VehiclesManager.download(monitor);

            // Setting the database download status flag
            loaded = true;
        }
    }
}
