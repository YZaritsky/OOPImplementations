import java.util.*;

public class ObserverWithSingleton {

    interface ObserverSubject {
        public void registerObserver(Observer observer);

        public void unregisterObserver(Observer observer);

        public void notifyObservers();
    }

    class Subject implements ObserverSubject {
        private static Subject instance;
        List<Observer> observers = new ArrayList<Observer>();

        private Subject() {
        };

        public static Subject getInstance() {
            if (instance == null) {
                instance = new Subject();
            }
            return instance;
        }

        public void registerObserver(Observer observer) {
            observers.add(observer);
        }

        public void unregisterObserver(Observer observer) {
            observers.remove(observer);
        }

        public void notifyObservers() {
            for (Observer observer : observers) {
                observer.update();
            }
        }
    }

    abstract class Observer {
        Observer() {
            Subject.getInstance().registerObserver(this);
        }

        public abstract void update();
    }

    class SomeObserver extends Observer {
        SomeObserver() {
            super();
        }

        public void update() {
            return;
        }
    }
}
