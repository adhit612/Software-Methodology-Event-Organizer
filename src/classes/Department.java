package classes;

/**
 * Department enum to declare a Department.
 * Given a set list, define the department with its name.
 * @author Abhishek Thakare, Adhit Thakur
 */
public enum Department {
    CS {
        /**
         * Display the full CS Department name through overriding toString().
         * @return a String output that contains the full Department name.
         */
        @Override
        public String toString() {
            return "Computer Science";
        }
    },
    EE {
        /**
         * Display the full EE Department name through overriding toString().
         * @return a String output that contains the full Department name.
         */
        @Override
        public String toString() {
            return "Electrical Engineering";
        }
    },
    ITI {
        /**
         * Display the full ITI Department name through overriding toString().
         * @return a String output that contains the full Department name.
         */
        @Override
        public String toString() {
            return "Information Technology and Informatics";
        }
    },
    MATH {
        /**
         * Display the full MATH Department name through overriding toString().
         * @return a String output that contains the full Department name.
         */
        @Override
        public String toString() {
            return "Mathematics";
        }
    },
    BAIT {
        /**
         * Display the full BAIT Department name through overriding toString().
         * @return a String output that contains the full Department name.
         */
        @Override
        public String toString() {
            return "Business Analytics and Information Technology";
        }
    };

}
