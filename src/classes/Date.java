package classes;

import java.util.Calendar;

/**
 * Descriptive sentence.
 * Elaborate.
 * @author Abhishek Thakare, Adhit Thakur
 */
public class Date implements Comparable<Date> {
    /**
     * TO DO:
     * <p>
     * COMPLETED:
     * equals()
     * isValid()
     * implement compareTo()
     * implement constructor
     * test cases
     */
    private int year;
    private int month;
    private int day;

    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUATERCENTENNIAL = 400;

    public static final int JAN = 0;
    public static final int FEB = 1;
    public static final int MAR = 2;
    public static final int APR = 3;
    public static final int MAY = 4;
    public static final int JUN = 5;
    public static final int JUL = 6;
    public static final int AUG = 7;
    public static final int SEP = 8;
    public static final int OCT = 9;
    public static final int NOV = 10;
    public static final int DEC = 11;

    public static final int FEBLEAPMAX = 29;
    public static final int FEBNONLEAPMAX = 28;
    public static final int THIRTYONEMAX = 31;
    public static final int THIRTYMAX = 30;

    public Date(String date) {
        String[] parts = date.split("/");
        this.year = Integer.parseInt(parts[2]);
        this.month = Integer.parseInt(parts[0]) - 1;
        this.day = Integer.parseInt(parts[1]);
    }

    public boolean isValid() {
        //check if the date is a valid calendar date
        //use for testBed main method

        //check if valid month
        if (!isValidMonth(this.month)) {
            return false;
        }

        //check if valid day
        if (isLeap(this.year) && this.month == FEB
                && this.day > FEBLEAPMAX) {
            return false;
        }
        else if (!isLeap(this.year) && this.month == FEB
                && this.day > FEBNONLEAPMAX) {
            return false;
        }
        else if (hasThirtyOneDays(this.month) && this.day > THIRTYONEMAX) {
            return false;
        }
        else {
            if (this.day > THIRTYMAX && !hasThirtyOneDays(this.month)) {
                return false;
            }
        }
        //all else fails...
        return true;
    }

    private boolean hasThirtyOneDays(int month) {
        if (month == JAN || month == MAR || month == MAY || month == JUL
                || month == AUG || month == OCT || month == DEC) {
            return true;
        }
        return false;
    }

    private boolean isLeap(int year) {
        if (year % QUADRENNIAL == 0) {
            if (year % CENTENNIAL == 0) {
                if (year % QUATERCENTENNIAL == 0) {
                    return true;
                }
                else {
                    return false;
                }
            }
            else {
                return true;
            }
        }
        else {
            return false;
        }
    }

    private boolean isValidMonth(int month) {
        if (month == JAN || month == FEB || month == MAR || month == APR
                || month == MAY || month == JUN || month == JUL
                || month == AUG || month == SEP || month == OCT
                || month == NOV || month == DEC) {
            return true;
        }
        return false;
    }


    public int checkIfWithinBounds(int month, int year, int day) {
        Calendar aheadDate = Calendar.getInstance();
        aheadDate.add(Calendar.MONTH, 6);

        //current date
        Calendar currDate = Calendar.getInstance();
        currDate.set(Calendar.DAY_OF_MONTH, day);
        currDate.set(Calendar.MONTH, month);
        currDate.set(Calendar.YEAR, year);

        return currDate.compareTo(aheadDate);
    }

    public int checkIfInPast(int month, int year, int day) {
        Calendar currDate = Calendar.getInstance();

        Calendar dateAtHand = Calendar.getInstance();
        dateAtHand.set(Calendar.DAY_OF_MONTH, day);
        dateAtHand.set(Calendar.MONTH, month);
        dateAtHand.set(Calendar.YEAR, year);

        return currDate.compareTo(dateAtHand);
    }

    @Override
    public String toString() {
        return this.month + 1 + "/" + this.day + "/" + this.year;
    }

    public int getMonth() {
        return this.month;
    }

    public int getYear() {
        return this.year;
    }

    public int getDay() {
        return this.day;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Date) {
            Date date = (Date) obj;
            return (date.day == this.day) && (date.month == this.month)
                    && (date.year == this.year);
        }
        return false;
    }

    @Override
    public int compareTo(Date date) {

        if (this.year < date.year) {
            return -1;
        }
        else if (this.year > date.year) {
            return 1;
        }
        else if (this.month < date.month) {
            return -1;
        }
        else if (this.month > date.month) {
            return 1;
        }
        else if (this.day < date.day) {
            return -1;
        }
        else if (this.day > date.day) {
            return 1;
        }
        else if (this.day == date.day && this.month == date.month
                && this.year == date.year) {
            return 0;
        }
        else {
            return 1;
        }
    }

    public static void main(String[] args) {
        testDaysInFeb_NonLeap();
        testDaysInFeb_Leap();
        testDateFormatting();
        testMaxDaysInOct();
        testMonth_OutOfRange();
        testLeadingZeros();
        testFutureDatesOutOfRange();
        testPastDateOutOfRange();

    }

    private static void testDaysInFeb_NonLeap() {
        Date date = new Date("2/30/2023");
        boolean expectedOut = false;
        boolean actualOutput = date.isValid();
        System.out.println("Test case 1 => # of days in February"
                + " in a non-leap year");
        if (expectedOut == actualOutput) {
            System.out.println("succeeded");
        }
        else {
            System.out.println("failed");
        }
    }
    private static void testDaysInFeb_Leap() {
        Date date = new Date("2/29/2024");
        boolean expectedOut = true;
        boolean actualOutput = date.isValid();
        System.out.println("Test case 2 => # of days in February"
                + " in a leap yr");
        if (expectedOut == actualOutput) {
            System.out.println("succeeded");
        }
        else {
            System.out.println("failed");
        }
    }
    private static void testDateFormatting() {
        Date date = new Date("05/08/24");
        boolean expectedOut = false;
        boolean actualOutput = date.isValid();
        System.out.println("Test case 3 => Check if date"
                + " is entered properly");
        if (expectedOut == actualOutput) {
            System.out.println("succeeded");
        }
        else {
            System.out.println("failed");
        }
    }
    private static void testMaxDaysInOct() {
        Date date = new Date("10/32/2023");
        boolean expectedOut = false;
        boolean actualOutput = date.isValid();
        System.out.println("Test case 4 => Should not allow days past"
                + " maximum of October");
        if (expectedOut == actualOutput) {
            System.out.println("succeeded");
        }
        else {
            System.out.println("failed");
        }
    }
    private static void testMonth_OutOfRange() {
        Date date = new Date("1/30/2024");
        boolean expectedOut = true;
        boolean actualOutput = date.isValid();
        System.out.println("Test case 5 => Is month in 6-month frame?");
        if (expectedOut == actualOutput) {
            System.out.println("succeeded");
        }
        else {
            System.out.println("failed");
        }
    }
    private static void testLeadingZeros() {
        Date date = new Date("00012/00010/0002023");
        boolean expectedOut = true;
        boolean actualOutput = date.isValid();
        System.out.println("Test case 6 => Check effect of leading 0's");
        if (expectedOut == actualOutput) {
            System.out.println("succeeded");
        }
        else {
            System.out.println("failed");
        }
    }
    private static void testFutureDatesOutOfRange() {
        Date date = new Date("5/20/2024");
        int expectedOut = 1;
        int actualOutput = date.checkIfWithinBounds(date.getMonth(),
                date.getYear(), date.getDay());
        System.out.println("Test case 7 => Days that are outside"
                + " of 6-month range");
        if (expectedOut == actualOutput) {
            System.out.println("succeeded");
        }
        else {
            System.out.println("failed");
        }
    }

    private static void testPastDateOutOfRange() {
        Date date = new Date("1/16/2023");
        int expectedOut = 1;
        int actualOutput = date.checkIfInPast(date.getMonth(),
                date.getYear(), date.getDay());
        System.out.println("Test case 8 => Days that are in the past");
        if (expectedOut == actualOutput) {
            System.out.println("succeeded");
        }
        else {
            System.out.println("failed");
        }
    }
}
