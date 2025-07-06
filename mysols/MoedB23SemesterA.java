package mysols;

public class MoedB23SemesterA {

import java.util.*;

/**
 * interface for defining the API of a package
 */
interface Package {
    float getCost();

    String getDescription();

    void arrivedAt(String address);
}

/**
 * class represents a basic package
 */
class BasicPackage implements Package {
    private String destination;// destination address
    private float weight;// package weight in grams

    public BasicPackage(String destination, int weight) {
        this.destination = destination;
        this.weight = weight;
    }

    // calculates the cost of the package as 1/2 shekel per gram
    public float getCost() {
        return weight / 2;
    }

    // return a description of the package
    public String getDescription() {
        return "This is a package that weighs " + weight + " grams.";
    }

    // activated when package arrives at a new address
    // check if the address that the package arrived at is the destination
    public void arrivedAt(String address) {
        if (address.equals(destination)) {
            System.out.println("The package has arrived!");
        }
    }
}

enum PackageFeature {
    FRAGILE("fragile"),
    TRACK("track");

    private String feature;

    PackageFeature(String feature){
        this.feature = feature;
    }

    public String getFeature() {
        return this.feature;
    }

}

class FeatureFactory {
    private static final String FRAGILE = "fragile";
    private static final String TRACK = "track";
    public static Package getFeature(String feature, Package pack){
        
        switch (feature){
            case FRAGILE:
                return new FragileSticker(pack);
            case TRACK:
                return new CloseTracking(pack);
            default:
                return pack;
        }
    }
}

/**
 * class representing a delivery company
 */
class DeliveryCompany {
    private static final String CUR_LOCATION = "Delivery Center";

    private static final int PKG_IDX = 0;
    private static final int LOC_IDX = 1;
    // PID -> <Package, curLocation>
    private HashMap<Integer, List<Package, String>> pidToPackageMap;
    private Random random;

    public DeliveryCompany() {
        this.pidToPackageMap = new HashMap<Integer, List<Package, String>>();
        this.random = new Random();
    }

    public int sendPackage(String destination, int weight, String[] features) {
        // 1. create package (destination, weight);
        Package curPackage = new BasicPackage(destination, weight);

        // 1.5 figure out the Package features:
        for (String feature : features) {
            curPackage = FeatureFactory.getFeature(feature, curPackage);
        }

        // 2. create PID - new Random().nextInt(), make sure unused.
        int newPid = random.nextInt();
        while (pidToPackageMap.get(newPid) != null) {
            newPid = random.nextInt();
        }
        // 3. Add to map, save curLocation = "Delivery Center"
        pidToPackageMap.put(newPid, new ArrayList<Package, String>(curPackage, CUR_LOCATION));

        // 4. return PID
        return newPid;

    }

    public boolean movePackage(int trackNumber, String newLoction) {
        // 0. if package doesnt exist return false
        if (pidToPackageMap.get(trackNumber) == null) {
            return false;
        }

        // 1. update the "trackNumber" package location to newLocation.
        pidToPackageMap.get(trackNumber)[LOC_IDX] = newLoction;
        // 2. activate "package".arrivedAt();
        pidToPackageMap.get(trackNumber)[PKG_IDX].arrivedAt(newLoction);

        // 3. return true;
        return true;
    }

    public String getPackageLocation(int trackNumber) {
        // 0. if it doesnt exist return null;
        if (pidToPackageMap.get(trackNumber) == null) {
            return false;
        }
        // 1. return packageLoc searching by trackNumber.
        return pidToPackageMap.get(trackNumber)[LOC_IDX];

    }
}

abstract class PackageDecorator implements Package {
    protected Package pack;

    PackageDecorator(Package pack) {
        this.pack = pack;
    }

    @Override
    public float getCost() {
        return pack.getCost();
    }

    @Override
    public String getDescription() {
        return pack.getDescription();
    }

    @Override
    public void arrivedAt(String address) {
        pack.arrivedAt(address);
    }
}

class FragileSticker extends PackageDecorator {
    private static final float STICKER_COST = 2;
    private static final String FRAGILE_MESSAGE = " with a fragile sticker on it";

    FragileSticker(Package pack) {
        super(pack);
    }

    @Override
    public float getCost() {
        return super.getCost() + STICKER_COST;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + FRAGILE_MESSAGE;
    }

    @Override
    public void arrivedAt(String address) {
        super.arrivedAt(address);
    }

}

class CloseTracking extends PackageDecorator {
    private static final String CURRENT_PACKAGE_LOCATION = "The package is at <X>";
    private static final String LOCATION_PLACEHOLDER = "<X>";
    private static final float CLOSE_TRACKING_COST = 10;

    CloseTracking(Package pack) {
        super(pack);
    }

    @Override
    public float getCost() {
        return super.getCost() + CLOSE_TRACKING_COST;
    }

    @Override
    public String getDescription() {
        return super.getDescription();
    }

    @Override
    public void arrivedAt(String address) {
        super.arrivedAt(address);
        System.out.println(CURRENT_PACKAGE_LOCATION.replace(LOCATION_PLACEHOLDER, address));
    }

}}
