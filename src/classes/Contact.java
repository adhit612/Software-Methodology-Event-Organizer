package classes;

/**
 * Class that declares the Contact Event component.
 * Declares the Contact object with Department and email parameters.
 * Department has its own enum declaration.
 * @author Abhishek Thakare, Adhit Thakur
 */
public class Contact {
    private Department department;
    private String email;

    /**
     * Constructor for Contact.
     * Declare contact with department and email.
     * @param department Department enum.
     * @param email email String.
     */
    public Contact(Department department, String email) {
        this.department = department;
        this.email = email;
    }

    /**
     * Return the Contact's Department.
     * @return the Contact's department.
     */
    public Department getDepartment() {
        return department;
    }

    /**
     * Return the Contact's email.
     * @return the Contact's email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Determine if the Contact's department and email are valid.
     * Return false if either is not proper.
     * Valid department => one of the enum constants.
     * Valid email => contains "@rutgers.edu".
     * @return true if the department and email are valid, otherwise false.
     */
    public boolean isValid() {
        boolean dep = false;
        boolean mail = false;

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
        }

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
}
