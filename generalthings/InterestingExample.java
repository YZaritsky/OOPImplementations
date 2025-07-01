package generalthings;
public class InterestingExample {
    public static class WaterSource {
        private static final int DEEP = 2;
        private int depth;

        public WaterSource(int depth) {
            this.depth = depth;
        }
    }

    public static class Ocean extends WaterSource {
        private static final double SAFE_WAVE_SPEED = 20;
        private double waveSpeed;

        public Ocean(int depth, double waveSpeed) {
            super(depth);
            this.waveSpeed = waveSpeed;
            System.out.println(DEEP);
        }
    }

    public static void main(String[] args) {
        System.out.println("Practice 3: Java practice file.");
        Ocean ocean = new Ocean(10, 20);
    }
}
