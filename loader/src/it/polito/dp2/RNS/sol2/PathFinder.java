package it.polito.dp2.RNS.sol2;

import it.polito.dp2.RNS.lab2.BadStateException;
import it.polito.dp2.RNS.lab2.ModelException;
import it.polito.dp2.RNS.lab2.ServiceException;
import it.polito.dp2.RNS.lab2.UnknownIdException;

import java.util.List;
import java.util.Set;

public class PathFinder implements it.polito.dp2.RNS.lab2.PathFinder {

    public PathFinder() {
    }

    @Override
    public boolean isModelLoaded() {
        return false;
    }

    @Override
    public void reloadModel() throws ServiceException, ModelException {

    }

    @Override
    public Set<List<String>> findShortestPaths(String source, String destination, int maxlength) throws UnknownIdException, BadStateException, ServiceException {
        return null;
    }
}
