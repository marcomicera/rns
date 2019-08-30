package it.polito.dp2.RNS.sol3.service;

import it.polito.dp2.RNS.FactoryConfigurationError;
import it.polito.dp2.RNS.RnsReaderException;
import it.polito.dp2.RNS.lab3.ServiceException;
import it.polito.dp2.RNS.sol3.service.database.local.RnsDatabase;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/")
public class RestApplication extends Application {

    public RestApplication() throws ServiceException {

        try {
            // Initializing the RNS database
            RnsDatabase.init();
        } catch (/*RnsReaderException |*/ FactoryConfigurationError e) {
            throw new ServiceException("Could not load initial RNS data: " + e.getMessage(), e.getCause());
        }
    }
}
