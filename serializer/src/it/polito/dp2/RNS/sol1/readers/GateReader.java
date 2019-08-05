package it.polito.dp2.RNS.sol1.readers;

import it.polito.dp2.RNS.GateType;

public class GateReader extends it.polito.dp2.RNS.sol1.readers.PlaceReader
        implements it.polito.dp2.RNS.GateReader {

    private GateType type;

    public GateReader(it.polito.dp2.RNS.sol1.jaxb.GateType gate) {
        super(gate.getId(), gate.getCapacity(), PlaceType.GATE);
        type = GateType.fromValue(gate.getType().value());
    }

    @Override
    public GateType getType() {
        return type;
    }
}
