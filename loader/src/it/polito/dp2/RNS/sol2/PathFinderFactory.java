package it.polito.dp2.RNS.sol2;

import it.polito.dp2.RNS.lab2.PathFinder;
import it.polito.dp2.RNS.lab2.PathFinderException;
import it.polito.dp2.RNS.lab2.ServiceException;

public class PathFinderFactory extends it.polito.dp2.RNS.lab2.PathFinderFactory {

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
