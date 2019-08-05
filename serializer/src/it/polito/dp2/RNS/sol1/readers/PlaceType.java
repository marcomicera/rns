package it.polito.dp2.RNS.sol1.readers;

public enum PlaceType {

    ROAD_SEGMENT {
        @Override
        public String toString() {
            return "Road segment";
        }
    },

    PARKING_AREA {
        @Override
        public String toString() {
            return "Parking area";
        }
    },

    GATE {
        @Override
        public String toString() {
            return "Gate";
        }
    };

    @Override
    public String toString() {
        return this.name();
    }
}
