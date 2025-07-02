// Usefull for making an object that is accessible by all. (Database, Logger, Caching, etc);

// Step 1: Create a public class for the Singleton(BasicSingleton)
// Step 2: Create a 'private static field Singleton'
// Step 3: Create a private constructor. Must be Private.
// Step 4: Create a public static method 'public static Singleton getInstance()' 
//         that creates that Singleton instance for others to use.

// Also called Lazy Singleton.
public class Singleton {

    // (Step 1) [Doesn't have to be static like in here]
    public static class BasicSingleton {

        // ** MUST CREATE A STATIC FIELD THAT HOLDS THE CLASS (Step 2)
        private static BasicSingleton singleton;

        // ** MUST BE PRIVATE! (Step 3)
        private BasicSingleton() {
        }

        // ** MUST BE STATIC CLASS (Step 4)
        public static BasicSingleton getInstance() {
            if (singleton == null) {
                singleton = new BasicSingleton();
            }
            return singleton;
        }
    }

    // Eager Initialization:
    // Cons: Always creates an instance (wastes memory and CPU).
    // We usually prefer the other option.

    public class EagerSingleton {
        public static final EagerSingleton eagerSingleton = new EagerSingleton();

        private EagerSingleton() {
        };

        private static EagerSingleton getInstance() {
            return eagerSingleton;
        }
    }

    public static void main(String[] args) {
        // BasicSingleton singleton = new Singleton(); // ERROR! private constructor
        BasicSingleton singleton1 = BasicSingleton.getInstance(); // return the single object created
        BasicSingleton singleton2 = BasicSingleton.getInstance(); // these two objects are equal

        EagerSingleton eagerSingleton = EagerSingleton.getInstance();
    }
}
