package it.polito.dp2.RNS.sol2;

import it.polito.dp2.RNS.lab2.PathFinder;
import it.polito.dp2.RNS.lab2.PathFinderException;
import it.polito.dp2.RNS.lab2.ServiceException;

/**
 * Defines a factory API that enables applications to obtain one or more objects
 * implementing the {@link PathFinder} interface.
 *
 */
public class PathFinderFactory extends it.polito.dp2.RNS.lab2.PathFinderFactory {

    /**
     * Creates a new instance of a {@link PathFinder} implementation.
     *
     * @return a new instance of a {@link PathFinder} implementation.
     * @throws PathFinderException if an implementation of {@link PathFinder} cannot be created.
     */
    @Override
    public PathFinder newPathFinder() throws PathFinderException {
        ServiceException se;
        try {
            return new it.polito.dp2.RNS.sol2.PathFinder();
        } catch (ServiceException e) {
            se = e;
        }
        throw new PathFinderException(se, "ServiceException thrown while creating a new PathFinder object.");
    }
}
