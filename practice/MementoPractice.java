package practice;
import java.util.*;


public class MementoPractice {
    class Originator{
        private String state = "";

        public void setState(String val){
            this.state = val;
        }

        public Memento saveState(){
            return new Memento(this.state);
        }

        public void loadState(Memento file){
            this.state = file.state;
        }

        public static class Memento{
            private String state = "";

            Memento(String val){
                this.state = val;
            }

        }
    }
    class CareTaker{
        
        private Originator git;
        private List<Originator.Memento> saves = new ArrayList<Originator.Memento>();

        CareTaker(Originator git){
            this.git = git;
        }

        public void commit(){
            saves.add(git.saveState());
        }

        public Originator.Memento restore(int id){
            return this.saves.get(id);
        }
    }
}
