class AbstractClass {

    public abstract class Animal {
        // can have any type of field or method access modifier
        private int privateField;
        protected int protectedField;
        int packagePrivateField;
        public int publicField;

        // can have a constructor
        public Animal(int privateField, int protectedField, int packagePrivateField, int publicField) {
            this.privateField = privateField;
            this.protectedField = protectedField;
            this.packagePrivateField = packagePrivateField;
            this.publicField = publicField;
        }

        // can have implemented methods
        public void implementedMethod() {
            // do something
        }

        // as well as abstract methods
        public abstract void speak(); // must be implemented by the extending subclass
    }

    public class Cow extends Animal {
        public Cow(int a, int b, int c, int d) {
            super(1, 2, 3, 4);
        }
        
        public void speak() {
            return;
        }
    }

    public static void main(String args[]) {
        // Animale animal = new Animal(); // Impossible cus its abstract

    }

}