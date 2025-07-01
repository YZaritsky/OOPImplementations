package generalthings;

public class Enum {
    // ENUM - Simple Implementation
    enum Days_S {
        SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SHABBAS
    }

    // ENUM - Advanced
    // 1. Write Constructor & Members
    // 2. Write getter
    // 3. Create all instances (Goes on top!)
    enum Days_A {
        // 3. Create all Instances
        SUNDAY("OOP!"),
        MONDAY("NAND!"),
        TUESDAY("OS!"),
        WEDNESDAY("MATH!"),
        THURSDAY("COMPUTABILITY!"),
        FRIDAY("FOOD!"),
        SHABBAS("MONEY!");

        // 1. Write Constructor & Members
        private final String dayCourse;

        Days_A(String dayCourse) {
            this.dayCourse = dayCourse;
        }

        // 2. Write Getter
        public String getCourse() {
            return this.dayCourse;
        }

    }

    public static void main(String[] args) {
        System.out.println("************************");
        System.out.println("************************");
        System.out.println("************************");

        Days_S day = Days_S.SHABBAS;
        System.out.println(day);

        System.out.println("****************************************");
        System.out.println("That was option 1");
        System.out.println("****************************************");

        for (Days_A day2 : Days_A.values()) {
            System.out.println(day2.getCourse());
        }
        System.out.println("****************************************");
        System.out.println("That was Option 2");
        System.out.println("****************************************");

    }
}
