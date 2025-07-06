//Solves Polling by updating all the objects that observe a certain activity.
// Step 1: Create a Subject Interface holding the methods:
//         Register, Unregister, Notify.

// Step 2: Create a class that implements that interface and holds an Observer Array.

// Step 3: Create an Observer interface holding the method Update.

// Step 4: Create classes that extends the observer and implements that interface. 
// ** Make sure to register to the Subject(super) on it's constructor. ** 

public class ObserverPattern {
    // Step 1:
    public interface SubjectInterface {
        public void registerObserver(Observer observer);

        public void unregisterObserver(Observer observer);

        public void notifyObservers();
    }

    // Step 3:
    public interface Observer {
        public void update();
    }

    // Step 2:
    public class Subject implements SubjectInterface {
        private Observer[] observers;

        public void registerObserver(Observer observer) {
            observers.add(observer);
        };

        public void unregisterObserver(Observer observer) {
            observers.remove(observer);
        };

        public void notifyObservers() {
            for (Observer observer : observers) {
                observer.update();
            }
        };
    }

    // Step 4:
    public class SomeObserver implements Observer {
        public SomeObserver() {
            super.registerObserver(this);
        }

        // I also saw this implementation but it seems to be less used:
        public SomeObserver(Subject subject) {
            subject.registerObserver(this);
        }

        @Override
        public void update() {
        };
    }

    // EXAMPLE FROM LAB:
    interface NewsSubject {
        /**
         * Registers new observer
         * 
         * @return true if succesful, false otherwise
         */
        boolean registerObserver(NewsObserver observer);

        /**
         * Unregisters observer
         * 
         * @return true if succesful, false otherwise
         */
        boolean unregisterObserver(NewsObserver observer);

        /**
         * Notifies all observers with the new newsFlash
         */
        void pushNotification(String newsFlash);
    }

    interface NewsObserver {
        void pushNewsFlash(String newsFlash);
    }

    public class NewsDesk implements NewsSubject {
        private static final int MAX_OBSERVERS_AMOUNT = 10;
        private String[] news = new String[100];
        private int newsAmount = 0;
        private NewsObserver[] observers = new NewsObserver[MAX_OBSERVERS_AMOUNT];
        private int observersAmount = 0;

        @Override
        public boolean registerObserver(NewsObserver observer) {
            if (observersAmount < MAX_OBSERVERS_AMOUNT) {
                this.observers[observersAmount++] = observer;
                return true;
            }
            return false;
        }

        @Override
        public boolean unregisterObserver(NewsObserver observer) {
            for (int i = 0; i < observersAmount; i++) {
                if (this.observers[i] != null && this.observers[i].equals(observer)) {
                    this.observers[i] = null;
                    return true;
                }
            }
            return false;
        }

        @Override
        public void pushNotification(String newsFlash) {
            this.news[newsAmount++] = newsFlash;
            for (int i = 0; i < observersAmount; i++) {
                if (this.observers[i] != null) {
                    this.observers[i].pushNewsFlash(newsFlash);
                }
            }
        }

        public void printNews() {
            System.out.println("News Summary: ");
            for (int i = 0; i < newsAmount; i++) {
                System.out.println(news[i]);
            }
        }
    }

    public class Reporter {
        private NewsDesk myNewsDesk;

        Reporter(NewsDesk myNewsDesk) {
            this.myNewsDesk = myNewsDesk;
        }

        public void discoveredScoop(String newsScoop) {
            myNewsDesk.pushNotification(newsScoop);
        }

    }

    public class BrowserNewsApp extends NewsDesk implements NewsObserver {
        BrowserNewsApp() {
            super.registerObserver(this);
        }

        @Override
        public void pushNewsFlash(String newsFlash) {
            System.out.println("Browser News App News Flash: " + newsFlash);
        }
    }

    public class AndroidNewsApp extends NewsDesk implements NewsObserver {
        AndroidNewsApp() {
            super.registerObserver(this);
        }

        @Override
        public void pushNewsFlash(String newsFlash) {
            System.out.println("Android News App News Flash: " + newsFlash);

        }
    }

    public class IOSNewsApp extends NewsDesk implements NewsObserver {
        IOSNewsApp() {
            super.registerObserver(this);
        }

        @Override
        public void pushNewsFlash(String newsFlash) {
            System.out.println("IOS News App News Flash: " + newsFlash);

        }
    }

    public static void main(String[] args) {
        Subject subject = new Subject();
        SomeObserver someObserver = new SomeObserver();
        // SomeObserver someObserver = new SomeObserver(subject); // If we use the second implementation.
        subject.notifyObservers();

        /// EXAMPLE FROM LAB:
        NewsDesk newsDesk = new NewsDesk();
        Reporter pulitzerPrizeWinnerReporter = new Reporter(newsDesk);
        BrowserNewsApp browser = new BrowserNewsApp();
        AndroidNewsApp android = new AndroidNewsApp();
        IOSNewsApp ios = new IOSNewsApp();

        newsDesk.registerObserver(browser);
        pulitzerPrizeWinnerReporter.discoveredScoop("The world is going crazy!!!");

        newsDesk.registerObserver(android);
        newsDesk.registerObserver(ios);
        pulitzerPrizeWinnerReporter.discoveredScoop("The world is back to normal!!!");

        newsDesk.unregisterObserver(android);
        pulitzerPrizeWinnerReporter.discoveredScoop("The world is going crazy AGAIN!!!");

        newsDesk.printNews();
    }
}
