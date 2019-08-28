package it.polito.dp2.RNS.sol3.service.database;

public class PlacesManager {

//    /**
//     * Vehicles data
//     */
//    private static Vehicles vehicles = new Vehicles();
//
//    /**
//     * Retrieves RNS vehicles info.
//     *
//     * @param monitor RnsReader interface through which data must be retrieved.
//     */
//    protected synchronized static void download(RnsReader monitor) {
//
//        // Retrieving vehicles information
//        Set<VehicleReader> retrievedVehicles = monitor.getVehicles(null, null, null);
//
//        // The vehicles list contained in the local catalog
//        List<Vehicle> vehiclesList = VehiclesManager.vehicles.getVehicles();
//
//        // Adding retrieved vehicles in the local vehicles manager
//        retrievedVehicles.forEach(v -> {
//
//            // New temporary vehicle object
//            Vehicle tmpVehicle = new Vehicle();
//
//            // Setting properties
//            // TODO after loading all places....
//            // tmpVehicle.setDestination();
//
//            // Adding the temporary vehicle object to the local vehicles list
//            vehiclesList.add(tmpVehicle);
//        });
//    }

//    /**
//     * It checks whether a catalog object is well-formed or not.
//     *
//     * @param catalog the catalog to be checked.
//     * @return true if well-formed, false otherwise.
//     */
//    public static boolean isWellFormed(CatalogType catalog) {
//        return (new NfvValidationProvider<CatalogType>()).isReadable(catalog.getClass(), null, null, null);
//    }
//
//    public static synchronized CatalogType getVehicles() {
//        return vehicles;
//    }
//
//    public static synchronized VNFType getVnf(String vnfId) {
//        for (VNFType vnf : vehicles.getVNF())
//            if (vnfId.compareTo(vnf.getId()) == 0)
//                return vnf;
//
//        return null;
//    }
//
//    public static synchronized int howMany() {
//        return vehicles.getVNF().size();
//    }
}
