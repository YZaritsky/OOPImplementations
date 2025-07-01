package generalthings;

import java.util.function.*;

interface SomeInterface {
    void someMethod();
}

// Renamed class to avoid conflict with java.lang.FunctionalInterface
public class FunctionalInterfaceDemo {
    // Annonymous Functions (Must implement some interface or extend some class);
    SomeInterface annonymousObject = new SomeInterface() {
        @Override
        public void someMethod() {
            return;
        }
    };

    // Functional Interface:
    // 1. API Must contain exactly 1 abstract Method.
    // 2. Can contain default,private,static methods except that one.
    // 3. Marked with @FunctionalInterface.

    @FunctionalInterface
    interface PriceComponent {
        public double getCost();

        // This is find cus its not public (Part of the API).
        private static double freeRide() {
            return 0;
        }
    }

    // Lambda Expression:
    // 1. Only works on FunctionalInterface.
    // 2. Sugar syntax.
    class LambdaExample {
        PriceComponent sum = () -> {
            return 1;
        };
    }

    @FunctionalInterface
    interface MathMaker {
        public int sum(int a, int b);
    }

    class LambdaMathExample {
        MathMaker result = (a, b) -> a + b;
    }

    // java.util.function.*
    // Contains -> Predicate, Consumer, Supplier, Function, BiFunction.
    class PredicateExample {
        Predicate<Integer> result = (a) -> a < 10;
    }

    class ConsumerExample {
        Consumer<Integer> result = (a) -> System.out.println(a);
    }

    class SupplierExample {
        Supplier<String> stringSupplier = () -> "Hello, Supplier!";
        // System.out.println(stringSupplier); Would print that.
    };

    class FunctionExample {
        Function<String, Integer> result = s -> s.length();
    }

    class BiFunctionExample {
        BiFunction<String, Character, Integer> charInString = (str, character) -> {
            int count = 0;
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == character) {
                    count++;
                }
            }
            return count;
        };
    }

    // Effectivly Final:
    interface Employee {
        void employeeData(String name);
    }

    class LambdaEffectivelyFinalTest {
        public static void main(String[] args) {
            int points = 100;
            Employee employee = name -> {
                System.out.printf("The employee %s has %s points: ", name, points); // Must be final or effectivly final
                // points++; // If we dont cross this line this doesnt work, because its not
                // effectivly final with it.
                employee.employeeData("Sami");
            };
        }

    }

    // Method Reference:
    public static double raiseToIntegerPowerAndAdd(double num, int power) {
        return num + Math.pow(num, power);
    }

    public static void applyAndPrint(BiFunction<Double, Integer, Double> function, double a, int b) {
        System.out.println(function.apply(a, b));
    }

    public static void main2(String[] args) {
        applyAndPrint((num, power) -> num + Math.pow(num, power), 5.5, 2); // with lambda implementing BiFunction
        applyAndPrint((num, power) -> raiseToIntegerPowerAndAdd(num, power), 5.5, 2); // with lambda calling the method
        applyAndPrint(FunctionalInterfaceDemo::raiseToIntegerPowerAndAdd, 5.5, 2); // without lambdas
    }
}
