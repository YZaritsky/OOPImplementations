package generalthings;

import java.util.List;
import java.util.Set;

import generalthings.AbstractClass.Animal;
import generalthings.Generics.Car;
import generalthings.Interface.D;

import java.util.ArrayList;
import java.util.HashSet;

public class Generics {
    public class Car<T> {
        private T id;

        public T getId() {
            return id;
        }

        public Car(T id) {
            this.id = id;
        }

        public void makeSound() {
            System.out.println("honk");
        }
    }

    public class subClass<T> extends Car<int> {
        // Important! :
        // LinkedList<String> doesnt extend LinkedList<Object>;
    }

    // Upper bound for D is Iterable, thats how we can control its properties.
    public class UpperBoundCar<T, D extends Iterable> {
        private final T id;
        private final D countries;

        public UpperBoundCar(T id, D countries) {
            this.id = id;
            this.countries = countries;
        }

        public void printCountries() {
            for (Object c : countries)
                System.out.println(c);
        }

        public static void main(String[] args) {
            Set<String> countries = new HashSet<>();
            countries.add("Israel");
            countries.add("UK");
            UpperBoundCar<Integer, Set<String>> car = new UpperBoundCar<>(54321, countries);
            car.printCountries();
        }
    }

    // WildCards - Allows us to address an object as anything.
    public static void Count(Set<?> set) {
        int num = 0;
        for (Object c : set) {
            num++;
        }
        System.out.printf("Set has %d items", num);
    }

    // LinkedList<?> list = new LinkedList<String>(); // Works.
    // LinkedList<?> list = new LinkedList<?>(); // Doesnt work.

    public static void makeCarSound(List<? extends Car> cars) {
        for (Car c : cars) {
            c.makeSound();
        }

    }

    // Lower Bound for ? is atleast Integar or higher. (Number for example).
    // This List wont hold Double or Float for sure.
    public static void addIntegar(List<? super Integer> lst) {
        lst.add(1);
    }

    // Final comparison example:
    List<Dog> dogList = new ArrayList<Dog>();

    List<? extends Animal> extendsAnimalList = dogList;
    Animal animal = extendsAnimalList.get(0); // works
    extendsAnimalList.add(new Dog()); // compiler error

    List<? super Dog> superDogList = dogList;
    Animal animal2 = superDogList.get(0); // compiler error
    superDogList.add(new Dog()); // works

    public static void main(String args[]) {
        Car c = new Car(12345);
        System.out.println((Integer) c.getId());

        // Car<Integer>[] cars = new Car<Integer>[5]; // We can't create an array of
        // generics.
        List<Car<Integer>> listOfCars = new ArrayList<>(); // We CAN wrap it under an Array.
    }
}
