package classes;

import java.util.Calendar;

/**
 * Class that declares the Date Event component.
 * Declares a Date by taking in a String.
 * The String gets split into year, month, nad day.
 * @author Abhishek Thakare, Adhit Thakur
 */
public class Date implements Comparable<Date> {
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

    /**
     * Constructor for Date.
     * Declare contact with department and email.
     * @param date input calendar date string.
     */
    public Date(String date) {
        String[] parts = date.split("/");
        this.year = Integer.parseInt(parts[2]);
        this.month = Integer.parseInt(parts[0]) - 1;
        this.day = Integer.parseInt(parts[1]);
    }

    /**
     * Determine if the Date is a valid Calendar date.
     * Not checking if date is within 6 months or in the past.
     * Only checking if the numbers given exist in Calendar.
     * @return true if the Date is a valid calendar date, false otherwise.
     */
    public boolean isValid() {
        if (!isValidMonth(this.month)) {
            return false;
        }

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
        return true;
    }

    /**
     * Determine if the Date's month is supposed to have 31 days.
     * Checking this to ensure that the number entered for the month is valid.
     * @param month the Date's month.
     * @return true if the Date's month has 31 days, false otherwise.
     */
    private boolean hasThirtyOneDays(int month) {
        if (month == JAN || month == MAR || month == MAY || month == JUL
                || month == AUG || month == OCT || month == DEC) {
            return true;
        }
        return false;
    }

    /**
     * Determine if the Date's year is within a leap year.
     * Used in isValid() to check when February is entered.
     * Makes sure February 29th is not entered in a non-leap year.
     * @param year the Date's year.
     * @return true if the Date's year is leap, false otherwise.
     */
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

    /**
     * Determine if the Date's month is a valid number Month.
     * Making sure that a month less than 1 or greater than 12 is entered.
     * @param month the Date's month.
     * @return true if month is a valid number month, false otherwise.
     */
    private boolean isValidMonth(int month) {
        if (month == JAN || month == FEB || month == MAR || month == APR
                || month == MAY || month == JUN || month == JUL
                || month == AUG || month == SEP || month == OCT
                || month == NOV || month == DEC) {
            return true;
        }
        return false;
    }

    /**
     * Determine if the given date is within 6 months of present.
     * @param month the Date's month.
     * @param year the Date's year.
     * @param day the Date's day.
     * @return 1 if given date is not within 6 months in the future, -1/0 otherwise.
     */
    public int checkIfWithinBounds(int month, int year, int day) {
        Calendar aheadDate = Calendar.getInstance();
        aheadDate.add(Calendar.MONTH, 6);

        Calendar currDate = Calendar.getInstance();
        currDate.set(Calendar.DAY_OF_MONTH, day);
        currDate.set(Calendar.MONTH, month);
        currDate.set(Calendar.YEAR, year);

        return currDate.compareTo(aheadDate);
    }

    /**
     * Determine if the given date is in the past.
     * Date cannot be in the past, as it would have already happened.
     * @param month the Date's month.
     * @param year the Date's year.
     * @param day the Date's day.
     * @return 1 if given date is in the past, -1/0 otherwise.
     */
    public int checkIfInPast(int month, int year, int day) {
        Calendar currDate = Calendar.getInstance();

        Calendar dateAtHand = Calendar.getInstance();
        dateAtHand.set(Calendar.DAY_OF_MONTH, day);
        dateAtHand.set(Calendar.MONTH, month);
        dateAtHand.set(Calendar.YEAR, year);

        return currDate.compareTo(dateAtHand);
    }

    /**
     * Generate a string that properly formats the Date.
     * @return a string containing the toString() message.
     */
    @Override
    public String toString() {
        return this.month + 1 + "/" + this.day + "/" + this.year;
    }

    /**
     * Return the Date's month.
     * @return the Date's month.
     */
    public int getMonth() {
        return this.month;
    }

    /**
     * Return the Date's year.
     * @return the Date's year.
     */
    public int getYear() {
        return this.year;
    }

    /**
     * Return the Date's day.
     * @return the Date's day.
     */
    public int getDay() {
        return this.day;
    }

    /**
     * Determine if two Dates are equal.
     * Compare the days and months and years.
     * If any one these valus is not equal, the function is false.
     * @param obj a Given object that will be compared to the "this" date if of proper type.
     * @return true if the Dates are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Date) {
            Date date = (Date) obj;
            return (date.day == this.day) && (date.month == this.month)
                    && (date.year == this.year);
        }
        return false;
    }

    /**
     * Compare two dates.
     * @param date the Date to be compared.
     * @return 1 if "this" ahead of parameter, -1 if "this" before parameter, 0 if equal.
     */
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

    /**
     * Main function to process test functions.
     * @param args arguments passed in.
     */
    public static void main(String[] args) {
        testDaysInFeb_NonLeap();
        testDaysInFeb_Leap();
        testDateFormatting();
        testMaxDaysInOct();
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
