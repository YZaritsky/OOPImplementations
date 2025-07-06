package mysols;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

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

    /**
     * Represents an AI-engine that can generate answers to given questions
     */
    interface LLMEngine {
        String generateAnswer(String question);
    }

    class DumbAIEngine implements LLMEngine {
        public String generateAnswer(String question) {
            return "Hello";
        }

    }

    interface EncodingMethod {
        String encode(String message);

        String decode(String message);
    }

    enum EncodingType {
        ROT13("rot13"),
        BLanguage("BLanguage");

        private String encodingType;

        EncodingType(String encodingType) {
            this.encodingType = encodingType;
        }

        public String getEncodingType() {
            return this.encodingType;
        }

    }

    class Rot13EncodingMethod implements EncodingMethod {

        public String encode(String message) {
            String encodedMessage = "";
            for (char c : message.toCharArray()) {
                int intValue = c;
                encodedMessage += (char) ((intValue + 13) % 128);
            }
            return encodedMessage;
        }

        public String decode(String message) {
            String decodedMessage = "";
            for (char c : message.toCharArray()) {
                int intValue = c;
                if (intValue - 13 >= 0)
                    decodedMessage += (char) (intValue - 13);
                else
                    decodedMessage += (char) (128 + intValue - 13);
            }
            return decodedMessage;
        }
    }

    class BLanguageEncodingMethod implements EncodingMethod {
        public String encode(String message) {
            String bMessage = "";
            for (Character c : message.toCharArray()) {
                bMessage += c;
                bMessage += 'b';
            }
            return bMessage;
        }

        public String decode(String message) {
            String nonBMessage = "";

            boolean takeChar = true;
            for (Character c : message.toCharArray()) {
                if (takeChar) {
                    nonBMessage += c;
                    takeChar = false;
                } else {
                    takeChar = true;
                }
            }
            return nonBMessage;
        }
    }

    class EncodingMethodsFactory {
        public static EncodingMethod getEncodingMethod(String encodingType) throws InvalidEntryException {
            EncodingType currentType = null;

            for (EncodingType type : EncodingType.values()) {
                if (type.getEncodingType().equals(encodingType)) {
                    currentType = type;
                    break;
                }
            }
            if (currentType == null) {
                throw new InvalidEntryException(encodingType);
            }

            switch (currentType) {
                case ROT13:
                    return new Rot13EncodingMethod();
                case BLanguage:
                    return new BLanguageEncodingMethod();
                default:
                    throw new InvalidEntryException(encodingType);
            }
        }

    }

    class InvalidEntryException extends Exception {
        private final String invalidEncodingType = "Please provide a valid encodingType. This entry is invalid: ";

        InvalidEntryException(String input) {
            super(invalidEncodingType + input);
        }
    }

    /**
     * Represents the server side of HujiChat
     */
    class HujiChatServer {
        LLMEngine llmEngine;
        EncodingMethod encodingMethod;

        public HujiChatServer(LLMEngine aiEngine, EncodingMethod encodingMethod) {
            this.llmEngine = aiEngine;
            this.encodingMethod = encodingMethod;
        }

        /**
         * uses the AIEngine to generate an answer to a question sent to the server.
         */
        public String askHujiChat(String question,
                Function<String, String> encoder,
                Function<String, String> decoder) {
            return encoder.apply(llmEngine.generateAnswer(decoder.apply(question)));
        }
    }

    /**
     * Represents the client side of HujiChat
     */
    class HujiChatClient {
        private EncodingMethod encodingMethod;
        private Scanner scanner = new Scanner(System.in);

        public HujiChatClient(EncodingMethod encodingMethod) {
            this.encodingMethod = encodingMethod;
        }

        /**
         * runs a chat session with the given server until the user types "end"
         */
        public void chatWithHuji(HujiChatServer server) {
            System.out.print("Enter your Question: ");
            String question = scanner.nextLine();
            while (!question.equals("end")) {
                System.out.print("HujiChat says: ");
                String answer = encodingMethod.decode(
                        server.askHujiChat(encodingMethod.encode(question), encodingMethod::encode,
                                encodingMethod::decode));
                System.out.println(answer);
                System.out.print("Enter your Question: ");
                question = scanner.nextLine();
            }
        }

        public static void main(String[] args) {
            EncodingMethod encodingMethod = EncodingMethodsFactory
                    .getEncodingMethod(EncodingType.ROT13.getEncodingType());
            HujiChatClient client = new HujiChatClient(encodingMethod);
            HujiChatServer server = new HujiChatServer(new DumbAIEngine());
            client.chatWithHuji(server);
        }
    }
}
