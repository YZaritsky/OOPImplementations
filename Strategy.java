// Step 1: Create interface for the API you want to be "Strategized" and it's implemintations (SortStrategy and its implementations)
// Step 2: In your "MainAPI" add the StrategizedAPI as a Class Member. (ClassHoldingStrategyInIt)
// Notes: Factory usually helps with step 1.

public class Strategy {
    // Step 1.
    public interface SortStrategy {
        void sort(Comparable[] data);
    }

    public class QuickSort implements SortStrategy {
        @Override
        public void sort(Comparable[] data) {
            System.out.println("Using QuickSort");
        }
    }

    public class MergeSort implements SortStrategy {
        @Override
        public void sort(Comparable[] data) {
            System.out.println("Using MergeSort");
        }
    }

    public class SortStrategyFactory {
        public static SortStrategy select(String type) {
            if ("quick".equalsIgnoreCase(type)) {
                return new QuickSort();
            } else if ("merge".equalsIgnoreCase(type)) {
                return new MergeSort();
            } else {
                throw new IllegalArgumentException("Unknown sort type: " + type);
            }
        }
    }

    // End of Step 1.

    // Step 2.
    public class ClassHoldingStrategyInIt {
        private Comparable[] contents;
        private SortStrategy sorter; // This is the strategy part.

        public ClassHoldingStrategyInIt(String strategyType) {
            this.sorter = SortStrategyFactory.select(strategyType);
        }

        public void sortContents() {
            this.sorter.sort(this.contents);
        }
    }

}
