import java.util.ArrayList;
import java.util.List;

// Like Git. Allows managing Versions. State Management.

// Originator - The subject we want to save states for.
// Memento - Holds the state. (public static class);
// Caretaker - Holds a DB to store the savefiles.

// Works using static nested class.

// Step 1: Create Originator with:
//     a. private state.
//     b. Methods: Memento createMemento(), void restoreMemento(Memento memento);
//     c. public static class Memento() [Must be static or I can't access from outside.]

// Step 2: Create Memento with:
//     a. private final state.
//     b. private constructor that gets a state and updates it.

// Step 3: Create CareTaker (Usually inside main):
//     a. Holds an array of Saves (of type Mementos)
//     b. Holds the Originator and does manipulations on it.

public class MementoImplementation {

    // Step 1.
    public class Originator {
        private String state;

        // Extra function to update states. not part of the memento but needed
        // obviously.
        public void setState(String state) {
            this.state = state;
        }

        public Memento createMemento() {
            return new Memento(this.state);
        };

        public void restoreMemento(Memento memento) {
            this.state = memento.state;
        };

        // Step 2.
        public static class Memento {
            private final String state;

            private Memento(String stateToSave) {
                state = stateToSave;
            }
            // Accessible by outerclass only.
        }
    }

    public static void main(String[] args) {
        // CareTaker code:
        // Step 3.
        List<Originator.Memento> saves = new ArrayList<Originator.Memento>();
        Originator subject = new Originator();

        subject.setState("1");
        subject.setState("2");

        saves.add(subject.createMemento());

        subject.setState("3");
        subject.setState("4");
        saves.add(subject.createMemento());

        subject.restoreMemento(saves.get(0));
    }
}
