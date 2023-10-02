package classes;

/**
 * Descriptive sentence.
 * Elaborate.
 * @author Abhishek Thakare, Adhit Thakur
 */
public enum Timeslot {

    MORNING(10, 30) {
        @Override
        public String toString() {
            return "10:30";
        }
    },
    AFTERNOON(2, 0) {
        @Override
        public String toString() {
            return "2:00";
        }
    },
    EVENING(6, 30) {
        @Override
        public String toString() {
            return "6:30";
        }
    };

    private final int hours;
    private final int mins;

    Timeslot(int hours, int mins) {
        this.hours = hours;
        this.mins = mins;
    }

    public int getHours() {
        return hours;
    }

    public int getMins() {
        return mins;
    }
}
