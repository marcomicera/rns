package it.polito.dp2.RNS.sol3.service.database.local;

import it.polito.dp2.RNS.FactoryConfigurationError;
import it.polito.dp2.RNS.RnsReader;
import it.polito.dp2.RNS.RnsReaderException;
import it.polito.dp2.RNS.RnsReaderFactory;
import it.polito.dp2.RNS.lab3.ServiceException;

public class RnsDatabase {

    /**
     * Monitor through which RNS info will be retrieved.
     */
    /*private*/ /* FIXME */ public static RnsReader monitor;
    /**
     * Indicates whether the RNS database has been already downloaded or not.
     */
    private static boolean loaded = false;

    static {


        //URI serviceUri = uriInfo.getAbsolutePathBuilder().build();
    }

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
     * @throws ServiceException          TODO
     */
    public synchronized static void init() {

        // If the database has not been downloaded yet
        if (!loaded) {

            // Using the RnsReader interface to retrieve data
            try {
                monitor = RnsReaderFactory.newInstance().newRnsReader();
            } catch (RnsReaderException e) {
                e.printStackTrace();
            }

            // Downloading places
            try {
                PlacesManager.download(monitor);
            } catch (ServiceException e) {
                e.printStackTrace();
            }

            // Downloading vehicles
            // VehiclesManager.download(monitor, uriInfo);

            // Setting the database download status flag
            loaded = true;
        }
    }
}
