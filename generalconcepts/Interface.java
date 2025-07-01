package generalthings;

public class Interface {
    interface A {
        void funcA();
    }

    interface B extends A {
        void funcB();
    }

    static class C implements B {
        public void funcA() {
            System.out.println("FuncA");

        }

        public void funcB() {
            System.out.println("FuncB");
        }
    }

    class D implements A {
        public void funcA() {
        }
    }

    // CASTING *************************************

    interface ParentType {
        void printType();
    }

    static class Child implements ParentType {
        public void printType() {
            System.out.println("Child type");
        }

        void childPrint() {
            System.out.println("Child instance");
        }
    }

    // Dividing a single class using the exact types of instances:
    interface Printable {
        void Print(String str);
    }

    interface Faxable {
        void Fax(String str);
    }

    interface Scannable {
        void Scan(String str);
    }

    static class CanDoAllPrinter implements Printable, Faxable, Scannable {
        public void Print(String str) {
            System.out.println("Printing...");
            System.out.println(str);
        }

        public void Fax(String str) {
            System.out.println("Faxing...");
            System.out.println(str);
        }

        public void Scan(String str) {
            System.out.println("Scanning...");
            System.out.println(str);
        }
    }

    public static void main(String args[]) {
        C result = new C();
        result.funcA();
        result.funcB();

        /// UPCASTING

        Child childReference = new Child();
        childReference.childPrint(); // both valid
        childReference.printType(); // and will compile

        // UPCASTING to parent type
        ParentType parentReference = new Child();
        parentReference.printType(); // still OK
        // parentReference.childPrint(); // Does not compile!

        // Dividing a class using it's diff interfaces.
        CanDoAllPrinter printer = new CanDoAllPrinter();
        printer.Print("hey");
        printer.Fax("hey");
        printer.Scan("hey");

    }

}