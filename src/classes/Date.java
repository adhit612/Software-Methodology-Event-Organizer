package classes;

import java.util.Calendar;
import java.util.Optional;

public class Date implements Comparable<Date> {
    /**
     * TO DO:
     *
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
        String [] parts = date.split("/");
        this.year = Integer.parseInt(parts[2]);
        this.month = Integer.parseInt(parts[0]) - 1;
        this.day = Integer.parseInt(parts[1]);
    }

    public boolean isValid() {
        //check if the date is a valid calendar date
        //use for testBed main method

        //check if valid month
        if(!isValidMonth(this.month)) {
            System.out.println("Not valid month for month " + this.month);
            return false;
        }

        //check if valid day
        if(isLeap(this.year) && this.month == FEB && this.day > FEBLEAPMAX) {
            System.out.println("in leap not valid");
            return false;
        }
        else if(!isLeap(this.year) && this.month == FEB && this.day > FEBNONLEAPMAX) {
            System.out.println("In leap non valid");
            return false;
        }
        else if(hasThirtyOneDays(this.month) && this.day > THIRTYONEMAX) {
            System.out.println("in has thirty one days");
            return false;
        }
        else {
            if(this.day > THIRTYMAX) {
                System.out.println("in else");
                return false;
            }
        }

        if(checkIfWithinBounds(this.month, this.year, this.day) > 0) { //current date is after the time limit
            System.out.println("is not within bounds");
            return false;
        }

        //all else fails...
        System.out.println("Valid date");
        return true;
    }

    private boolean hasThirtyOneDays(int month) {
        if(month == JAN || month == MAR || month == MAY || month == JUL || month == AUG || month == OCT || month == DEC) {
            return true;
        }
        return false;
    }

    private boolean isLeap(int year) {
//        System.out.println("In isLeap with " + year);
        if(year%QUADRENNIAL == 0) {
            if(year%CENTENNIAL == 0) {
                if(year%QUATERCENTENNIAL == 0) {
//                    System.out.println("Is leap year");
                     return true;
                }
                else {
//                    System.out.println("In not leap year 2");
                    return false;
                }
            }
            else {
//                System.out.println("Is leap year 2");
                return true;
            }
        }
        else {
//            System.out.println("In not leap year 3");
            return false;
        }
    }

    private boolean isValidMonth(int month) {
        if(month == JAN || month == FEB || month == MAR || month == APR || month == MAY || month == JUN || month == JUL
        && month == AUG || month == SEP || month == OCT || month == NOV || month == DEC) {
            return true;
        }
        return false;
    }

    private int checkIfWithinBounds(int month, int year, int day) {
        Calendar aheadDate = Calendar.getInstance();
        aheadDate.add(Calendar.MONTH, 6);
        System.out.println(aheadDate.getTime());

        //current date
        Calendar currDate = Calendar.getInstance();
        currDate.set(Calendar.DAY_OF_MONTH, day);
        currDate.set(Calendar.MONTH, month);
        currDate.set(Calendar.YEAR, year);
        System.out.println(currDate.getTime());

        return currDate.compareTo(aheadDate);
    }

    @Override
    public String toString() {
        return this.month + 1 + "/" + this.day + "/" + this.year;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Date) {
            Date date = (Date) obj;
            return (date.day == this.day) && (date.month == this.month)
                    && (date.year == this.year);
        }
        return false;
    }

    @Override
    public int compareTo(Date date) {

        if(this.year < date.year) {
            return -1;
        }
        else if(this.month < date.month) {
            return -1;
        }
        else if(this.day < date.day) {
            return -1;
        }
        else if(this.day == date.day && this.month == date.month && this.year == date.year) {
            return 0;
        }

//        Calendar one = Calendar.getInstance();
//        one.set(Calendar.DAY_OF_MONTH,this.day);
//        one.set(Calendar.MONTH,this.month);
//        one.set(Calendar.YEAR,this.year);
//
//        Calendar two = Calendar.getInstance();
//        two.set(Calendar.DAY_OF_MONTH,date.day);
//        two.set(Calendar.MONTH,date.month);
//        two.set(Calendar.YEAR,date.year);

        return 1;
    }

    public static void main(String [] args) {
        testDaysInFeb_NonLeap();
        testDaysInFeb_Leap();
        testMonth_OutOfRange();
    }


    private static void testDaysInFeb_NonLeap() {
        Date date = new Date("2/29/2023");
        boolean expectedOut = false;
        boolean actualOutput = date.isValid();
        System.out.println("Test case 1 => # of days in February in a non-leap year");
        if(expectedOut == actualOutput) {
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
        System.out.println("Test case 2 => # of days in February in a leap yr");
        if(expectedOut == actualOutput) {
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
        System.out.println("Test case 3 => Is month in 6-month frame?");
        if(expectedOut == actualOutput) {
            System.out.println("succeeded, meaning date is valid");
        }
        else {
            System.out.println("failed, date not valid");
        }
    }
}
