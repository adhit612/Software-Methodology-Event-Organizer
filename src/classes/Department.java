package classes;

public enum Department {
    CS {
       @Override
       public String toString() {
           return "Computer Science";
       }
    },
    EE {
        @Override
        public String toString() {
            return "Electrical Engineering";
        }
    },
    ITI {
        @Override
        public String toString() {
            return "Information Technology and Informatics";
        }
    },
    MATH {
        @Override
        public String toString() {
            return "Mathematics";
        }
    },
    BAIT {
        @Override
        public String toString() {
            return "Business Analytics and Information Technology";
        }
    }; //constants

    public static void main(String[] args) {

        Department d = Department.CS;
        System.out.println(d.name());
    }




}
