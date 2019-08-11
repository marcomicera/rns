package it.polito.dp2.RNS.sol1;

import it.polito.dp2.RNS.RnsReaderException;

public class RnsReaderFactory extends it.polito.dp2.RNS.RnsReaderFactory {

    @Override
    public it.polito.dp2.RNS.RnsReader newRnsReader() throws RnsReaderException {
        return new RnsReader();
    }
}
