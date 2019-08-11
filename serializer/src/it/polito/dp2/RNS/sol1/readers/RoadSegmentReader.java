package it.polito.dp2.RNS.sol1.readers;

import it.polito.dp2.RNS.sol1.jaxb.RoadSegmentType;

public class RoadSegmentReader extends it.polito.dp2.RNS.sol1.readers.PlaceReader
        implements it.polito.dp2.RNS.RoadSegmentReader {

    private String name;
    private String road;

    public RoadSegmentReader(RoadSegmentType roadSegment) {

        super(roadSegment.getId(), roadSegment.getCapacity(), PlaceType.PARKING_AREA);

        name = roadSegment.getName();
        road = roadSegment.getRoad();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getRoadName() {
        return road;
    }
}
