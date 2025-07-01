package generalthings;

public class InterfaceMethods {
    interface Playable {
        void play();

        default void repeat(int times) {
            int i = 0;
            while (i < times) {
                play();
                i++;
            }
        }
    }
}
