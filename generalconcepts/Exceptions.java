package generalthings;

public class Exceptions {
    // 1.Compiler Errors -> 2.Runtime Errors -> 3.Known bugs -> 4.Unknown bugs.
    // 'assert isGameOver(board)' -> helps move type-3/4 errors to type-2 Errors.

    // Error(OS Errors) -> Throwable <- Exception <- Runtime(NullPointer,
    // IndexOutOfBounds..) / Other(IOException, SQLException)

    public static void badDivide() throws ArithmeticException {
        int i = 5 / 0;
    }

    public static void badPointer() throws NullPointerException {
        throw new NullPointerException("This is an error");
    }

    public static void tryCatchFunction() {
        try {
            badDivide();
            badPointer();
        }
        // Bad Practice:

        // catch (Exception e) {
        // System.out.println("Caught any error");
        // }

        // Good Practice -> Erors arranged from lowest to highest and divided for
        // different options.
        catch (ArithmeticException e) {
            System.out.println("Anothe error");
        } catch (NullPointerException e) {
            System.out.println("There was an error");
        } catch (RuntimeException e) {
            System.out.println("Another error of diff type");
        } catch (Exception e) {
            System.out.println("Another");
        }
    }

    class VerySpecificException extends RuntimeException {
    };

    public void methodUsingThatClass() throws VerySpecificException {
        try {
            throw new VerySpecificException();
        } catch (RuntimeException e) {
            String message = e.getMessage();
            System.out.println(message);
        }
    }
}
