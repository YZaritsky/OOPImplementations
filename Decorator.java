public class Decorator {
    // Step 1 : Create some Interface that is going to be decorated. (Fish) -> Must
    // have a Method in it.
    // Step 2 : Create Implementation of that interface. (BabyFish)-> Should @Override!
    // Step 3 : Create Decorator Interface, extending the first one. (FishDecorator)
    // Step 4 : Implement that decorator using the object of the class from step 2. (Gold),
    // This is the part where we basically enhance the first object(Decorate it).
    // Step 5: When we call it, Create a new object of the First(BabyFish), and then re-create it using the 2nd(Gold).
    
    interface Fish {
        String getDescription();
    };

    static class BabyFish implements Fish {
        @Override
        public String getDescription() {
            return "Baby Fish";
        }
    }

    interface FishDecorator extends Fish {
    }

    static class Gold implements FishDecorator {
        Fish fish;

        public Gold(Fish fish) {
            this.fish = fish;
        }

        @Override
        public String getDescription() {
            return "Gold " + fish.getDescription();
        }

    }

    static class Stripes implements FishDecorator {
        Fish fish;
        private String color;

        public Stripes(Fish fish, String color) {
            this.fish = fish;
            this.color = color;
        }

        @Override
        public String getDescription() {
            return this.color + " Stripes " + fish.getDescription();
        }

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
