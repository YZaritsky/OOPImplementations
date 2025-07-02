package mysols;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MoedA23SemesterA {
    // Question 1:

    // Answer A:
    // Observer abstract class
    abstract class TsunamiAlertApp {

        protected abstract void renderMessage(String message); // LEARN: Should be protected abstract and not public
                                                               // abstract.

        protected abstract void makeAlarmSound();

        public void activateAlarm(String message) {
            this.makeAlarmSound();
            this.renderMessage(message);
        };

        // Answer C:
        public void registerToZone(String zone) {
            try {
                TsunamiAlertCenter.getInstance().addAlertApp(this, zone);
            } catch (InvalidEntryException invalidEntry) {
                System.out.println(invalidEntry.getMessage());
            }
        }
    }

    // Answer B:
    // Singleton & ObserverSubject
    class TsunamiAlertCenter {
        public static String ALERT_MESSAGE = "Tsunami Alert! you have <X> seconds to get to a safe place";
        public static String SECONDS_PLACEHOLDER = "<X>";

        private static TsunamiAlertCenter center; // LEARN: Static field shouldnt be accessed with "this."

        private HashMap<String, List<TsunamiAlertApp>> zoneToAppsMap = new HashMap<String, List<TsunamiAlertApp>>();
        private HashMap<String, Integer> dangerZones = new HashMap<String, Integer>(); // LEARN: Should use Integer and
                                                                                       // not int.
        private List<String> history = new ArrayList<String>();

        // LEARN: Not the correct useage. need to make the singleton static and call
        // it's getInstance();

        // public TsunamiAlertCenter(){
        // if (this.center == null){
        // this.center = new TsunamiAlertCenter();
        // }
        // return this.center;
        // }
        private TsunamiAlertCenter() {
        };

        public static TsunamiAlertCenter getInstance() {
            if (center == null) {
                center = new TsunamiAlertCenter();
            }
            return center;
        }

        public void addZone(String zoneName, int defenceTime) {
            dangerZones.put(zoneName, defenceTime);
        }

        public void addAlertApp(TsunamiAlertApp app, String zone) throws InvalidEntryException {
            if (dangerZones.get(zone) == null) {
                throw new InvalidEntryException(zone);
            }
            if (zoneToAppsMap.get(zone).isEmpty()) {
                zoneToAppsMap.put(zone, new ArrayList<TsunamiAlertApp>());
            }
            zoneToAppsMap.get(zone).add(app);
        }

        public void sendAlert(String zone) throws InvalidEntryException {
            if (dangerZones.get(zone) == null) {
                throw new InvalidEntryException(zone);
            }
            for (TsunamiAlertApp app : zoneToAppsMap.get(zone)) {
                // LEARN: Use "String.valueOf" when using replace.
                app.activateAlarm(ALERT_MESSAGE.replace(SECONDS_PLACEHOLDER, String.valueOf(dangerZones.get(zone))));
            }
            history.add(LocalDateTime.now() + "," + zone);
        }

        public List<String> getPreviousAlerts() {
            return new ArrayList<>(history);
        }
    }

    class InvalidEntryException extends Exception {
        private String entryError = "The <ENTRY> was not found. Please add it before calling this method.";
        private String entryPlaceholder = "<ENTRY>";

        InvalidEntryException(String entry) {
            super(this.entryError.replace(this.entryPlaceholder, String.valueOf(entry)));
        }
    }

    // Answer D:
    class AndroidTsunamiApp extends TsunamiAlertApp {

        @Override
        public void renderMessage(String message) {
            System.out.println(message);
        }

        @Override
        public void makeAlarmSound() {
            System.out.println("Alarm Sounded!");
        }

        public void renderAlertHistory() {
            for (String msg : TsunamiAlertCenter.getInstance().getPreviousAlerts()) {
                this.renderMessage(msg);
            }
        }
    }

    /// Question 2:

}
