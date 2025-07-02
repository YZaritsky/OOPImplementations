package mysols;

public class lab13 {
    // Given Code
    static class Valve {// do not edit this class
        private int currentFlow;

        public Valve() {
            currentFlow = 0;
        }

        public void setFlow(int flow) {
            currentFlow = flow;
        }

        public int getFlow() {
            return currentFlow;
        }
    }

    interface Spout {// do not edit this class
        public int flow(int flow);
    }

    static class BaseSpout implements Spout {// do not edit this class
        public int flow(int flow) {
            return flow;
        }
    }

    static class Shower {// do not edit this class
        private Valve hotValve;
        private Valve coldValve;
        private Spout spout;
        // Other members of things like shower curtain, drain, etc.

        public Shower(Valve hotValve, Valve coldValve, Spout spout) {
            this.hotValve = hotValve;
            this.coldValve = coldValve;
            this.spout = spout;
        }

        public void setColdWater(int coldFlow) {
            coldValve.setFlow(coldFlow);
            int totalFlow = hotValve.getFlow() + coldFlow;
            System.out.println(
                    "Running flow " + spout.flow(totalFlow) + " with " + (coldFlow / totalFlow) * 100 + "% cold water");
        }

        public void setHotWater(int hotFlow) {
            hotValve.setFlow(hotFlow);
            int totalFlow = hotValve.getFlow() + hotFlow;
            System.out.println(
                    "Running flow " + spout.flow(totalFlow) + " with " + (hotFlow / totalFlow) * 100 + "% hot water");
        }
    }

    interface Faucet {// do not edit this class
        static int MAX_PERCENT_FLOW = 100;

        void open(int flow, int hotPercent);
    }

    // Write your answer to parts A,B,C under this comment!
    // Part A:
    static class MixerFaucet implements Faucet {
        private Valve hotValve;
        private Valve coldValve;
        private Spout spout;

        MixerFaucet(Valve hotValve, Valve coldValve, Spout spout) {
            this.hotValve = hotValve;
            this.hotValve.setFlow(0);

            this.coldValve = coldValve;
            this.coldValve.setFlow(0);

            this.spout = spout;
            this.spout.flow(0);
        }

        @Override
        public void open(int flow, int hotPercent) {
            hotValve.setFlow(flow * hotPercent / Faucet.MAX_PERCENT_FLOW);
            coldValve.setFlow(flow * (Faucet.MAX_PERCENT_FLOW - hotPercent) / Faucet.MAX_PERCENT_FLOW);
            System.out.println("Running flow " + spout.flow(flow) + " with " + hotPercent + "% hot water");

        };
    }

    // Part B:
    static class RenovatedShower {
        private Faucet faucet;

        RenovatedShower(Faucet faucet) {
            this.faucet = faucet;
        };

        public void openWater(int flow, int hotPercent) {
            faucet.open(flow, hotPercent);
        }
    }

    // Part C - SpoutDecorator:
    static abstract class SpoutDecorator implements Spout {
        private Spout spout;

        public SpoutDecorator(Spout spout) {
            this.spout = spout;
        }

        @Override
        public int flow(int flow) {
            return spout.flow(flow);
        };
    }

    static class Hose extends SpoutDecorator {
        private final int decreaseFlowBy = 10;

        Hose(Spout spout) {
            super(spout);
        }

        @Override
        public int flow(int flow) {
            int newFlowValue = super.flow(flow);
            newFlowValue = newFlowValue - this.decreaseFlowBy;

            if (newFlowValue < 0) {
                newFlowValue = 0;
            }

            return newFlowValue;
        }

    }

    static class AeratorFilter extends SpoutDecorator {
        private final int divideFlowBy = 2;

        AeratorFilter(Spout spout) {
            super(spout);
        }

        @Override
        public int flow(int flow) {
            int newFlowValue = super.flow(flow);
            newFlowValue = newFlowValue / this.divideFlowBy;

            if (newFlowValue < 0) {
                newFlowValue = 0;
            }

            return newFlowValue;
        }

    }

    static class Main {
        public static void main(String[] args) {
            // Write your answer to part D under this comment
            // First Shower:
            Valve hotValveOne = new Valve();
            Valve coldValveOne = new Valve();
            Spout spoutOne = new BaseSpout();
            spoutOne = new Hose(spoutOne);
            MixerFaucet faucetOne = new MixerFaucet(hotValveOne, coldValveOne, spoutOne);

            RenovatedShower firstShower = new RenovatedShower(faucetOne);
            firstShower.openWater(80, 50);

            // Second Shower:
            Valve hotValveTwo = new Valve();
            Valve coldValveTwo = new Valve();
            Spout spoutTwo = new BaseSpout();
            spoutTwo = new Hose(spoutTwo);
            spoutTwo = new AeratorFilter(spoutTwo);

            MixerFaucet faucetTwo = new MixerFaucet(hotValveTwo, coldValveTwo, spoutTwo);

            RenovatedShower secondShower = new RenovatedShower(faucetTwo);
            secondShower.openWater(30, 70);

            // Third Shower:
            Valve hotValveThree = new Valve();
            Valve coldValveThree = new Valve();
            Spout spoutThree = new BaseSpout();
            spoutThree = new AeratorFilter(spoutThree);
            spoutThree = new Hose(spoutThree);

            MixerFaucet faucetThree = new MixerFaucet(hotValveThree, coldValveThree, spoutThree);

            RenovatedShower thirdShower = new RenovatedShower(faucetThree);
            thirdShower.openWater(30, 90);

        }
    }

    /**
     * Write your answer to part E INSIDE OF this comment
     * I used the Decorator Design Pattern because what we needed to do
     * was to allow new properties to be added on demand.
     * This allows us to add more properties without changing the old code,
     * and enhances Encapsulation, and applies the Open-Closed Principle.
     */
}
