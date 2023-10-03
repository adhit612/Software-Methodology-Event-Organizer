package classes;

/**
 * Timeslot enum to declare a start time.
 * Contains hours and minutes as properties.
 * Converts String input to numerical time.
 * @author Abhishek Thakare, Adhit Thakur
 */
public enum Timeslot {

    MORNING(10, 30) {
        /**
         * Display the full time String based on enum constant.
         * Overriding toString().
         * @return a String containing the full time.
         */
        @Override
        public String toString() {
            return "10:30";
        }
    },
    AFTERNOON(2, 0) {
        /**
         * Display the full time String based on enum constant.
         * Overriding toString().
         * @return a String containing the full time.
         */
        @Override
        public String toString() {
            return "2:00";
        }
    },
    EVENING(6, 30) {
        /**
         * Display the full time String based on enum constant.
         * Overriding toString().
         * @return a String containing the full time.
         */
        @Override
        public String toString() {
            return "6:30";
        }
    };

    private final int hours;
    private final int mins;

    /**
     * Constructor to declare a Timeslot's properties.
     * @param hours The hours based on Timeslot constant.
     * @param mins The minutes based on Timeslot constant.
     */
    Timeslot(int hours, int mins) {
        this.hours = hours;
        this.mins = mins;
    }

    /**
     * Return the hours component of the Timeslot enum constant.
     * @return the hours of the Timeslot.
     */
    public int getHours() {
        return hours;
    }

    /**
     * Return the minutes component of the Timeslot enum constant.
     * @return the minutes of the Timeslot.
     */
    public int getMins() {
        return mins;
    }
}
