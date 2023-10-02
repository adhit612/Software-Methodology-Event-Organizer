package classes;

/**
 * Descriptive sentence.
 * Elaborate.
 * @author Abhishek Thakare, Adhit Thakur
 */

public class Contact {
    /**
     * TO DO:
     * implement isValid()
     * COMPLETED:
     * implement constructor
     */
    private Department department;
    private String email;

    public Contact(Department department, String email) {
        this.department = department;
        this.email = email;
    }

    public Department getDepartment() {
        return department;
    }

    public String getEmail() {
        return email;
    }

    public boolean isValid() {
        boolean dep = false;
        boolean mail = false;

        //Test department
        if (this.department == Department.CS ||
                this.department == Department.EE
                || this.department == Department.ITI
                || this.department == Department.MATH
                || this.department == Department.BAIT) {
            dep = true;
        }
        else {
            dep = false;
            return false;
            //System.out.println("Incorrect department");
        }

        //Test email
        int len = this.email.length();
        int initial = len - 12;
        String checker = "";
        for (int i = initial; i < len; i++) {
            checker += this.email.charAt(i);
        }
        if (checker.equals("@rutgers.edu") && this.email.charAt(0) != '@') {
            mail = true;
        }
        else {
            mail = false;
            return false;
        }

        if (dep && mail) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Department d = Department.CS;
        Contact contact = new Contact(d, "cs@rutgers.edu");
        System.out.println(contact.isValid());
    }
}
