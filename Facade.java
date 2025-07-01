public class Facade {
    public abstract class Item {
    }

    public class Pasta extends Item {
    }

    public class Salt extends Item {
    }

    public class SaltShaker {
        public Salt salt(int nTSpoons) {
        };
    }

    public class Pantry {
        public Item get(String item) {
        }
    }

    public class Pot {
        public void boil(int nLiters) {
        }

        public void add(Item item) {
        }
    }

    /// ALL THIS RUBBISH ABOVE Can be simplified using Facade:
    public class Chef {
        private SaltShaker saltShaker;
        private Pantry pantry;
        private Pot pot;

        public Chef() {
            this.saltShaker = new SaltShaker();
            this.pantry = new Pantry();
            this.pot = new Pot();
        }

        public void makePasta() {
            pot.boil(2);
            Item pasta = pantry.get("pasta");
            pot.add(pasta);
            Salt salt = saltShaker.salt(1);
            pot.add(salt);

        }

    }

    public static void main(String[] args) {
        Chef chef = new Chef();
        chef.makePasta();
    }
}
