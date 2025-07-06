public class DecoratorUsingAbstractClass {
    // Preperation:
    // Step 1 : Create some Abstract Class that is going to be decorated. (Fish) ->
    // Must have a Method in it.
    // Step 2 : Create Implementation of that Abstract Class. (BabyFish)-> Should
    // @Override!

    // Decoration:
    // Step 3 : Create Decorator Abstract Class, extending the first one, and
    // providing default functionality to be split between all children.
    // (FishDecorator)
    // It needs to hold a copy of the decorated subject (private Fish fish).

    // Step 4 : Extend that decorator using the object of the class from step 2.
    // (Gold),
    // This is the part where we basically enhance the first object(Decorate it).
    // First we call the super, then we decorate it.

    // Step 5: When we call it, Create a new object of the First(BabyFish), and then
    // re-create it using the 2nd(Gold).

    interface Fish {
        public abstract String getDescription();
    };

    class BabyFish implements Fish {
        @Override
        public String getDescription() {
            return "Baby Fish";
        }
    }

    abstract class FishDecorator implements Fish {
        private Fish fish;

        FishDecorator(Fish fish) {
            this.fish = fish;
        }

        @Override
        public String getDescription() {
            return fish.getDescription();
        }

        // Not a must, but this is how you make private classes that lower classes must
        // implement.
        abstract protected void func1();

    }

    class Gold extends FishDecorator {
        Gold(Fish fish) {
            super(fish);
        }

        @Override
        public String getDescription() {
            return "Gold " + super.getDescription();
        }

        @Override
        public void func1() {
        };

    }

    class Stripes extends FishDecorator {
        private String color;

        Stripes(Fish fish, String color) {
            super(fish);
            this.color = color;
        }

        @Override
        public String getDescription() {
            return this.color + " Stripes " + super.getDescription();
        }

        @Override
        public void func1() {
        };

    }

    public static void main(String[] args) {
        Fish fish = new BabyFish();
        fish = new Gold(fish);
        fish = new Stripes(fish, "White");

        // Alternative Initialization:
        // Fish fish = new Stripes(new Gold(new BabyFish()), "White");
        System.out.println("A Fish has all: " + fish.getDescription());

    }
}
