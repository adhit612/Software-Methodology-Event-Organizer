package classes;

import java.util.Calendar;

public class Date implements Comparable<Date> {
    /**
     * TO DO:
     *
     * COMPLETED:
     * equals()
     * isValid()
     * implement compareTo()
     * implement constructor
     */
    private int year;
    private int month;
    private int day;

    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUATERCENTENNIAL = 400;

    public static final int JAN = 1;
    public static final int FEB = 2;
    public static final int MAR = 3;
    public static final int APR = 4;
    public static final int MAY = 5;
    public static final int JUN = 6;
    public static final int JUL = 7;
    public static final int AUG = 8;
    public static final int SEP = 9;
    public static final int OCT = 10;
    public static final int NOV = 11;
    public static final int DEC = 12;

    public static final int FEBLEAPMAX = 29;
    public static final int FEBNONLEAPMAX = 28;
    public static final int THIRTYONEMAX = 31;
    public static final int THIRTYMAX = 30;

    public Date(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public boolean isValid(){
        //check if the date is a valid calendar date
        //use for testBed main method

        //check if valid month
        if(!isValidMonth(this.month)){
            return false;
        }

        //check if valid day
        if(isLeap(this.year) && this.month == FEB && this.day > FEBLEAPMAX){
            System.out.println("in leap not valid");
            return false;
        }
        else if(!isLeap(this.year) && this.month == FEB && this.day > FEBNONLEAPMAX){
            return false;
        }
        else if(hasThirtyOneDays(this.month) && this.day > THIRTYONEMAX){
            return false;
        }
        else{
            if(this.day > THIRTYMAX){
                return false;
            }
        }

        if(checkIfWithinBounds(this.month,this.year,this.day) > 0){ //current date is after the time limit
            System.out.println("in not within modes");
            return false;
        }

        //all else fails...
        return true;
    }

    private boolean hasThirtyOneDays(int month){
        if(month == JAN || month == MAR || month == MAY || month == JUL || month == AUG || month == OCT || month == DEC){
            return true;
        }
        return false;
    }

    private boolean isLeap(int year){
        if(year%QUADRENNIAL == 0){
            if(year%CENTENNIAL == 0){
                if(year%QUATERCENTENNIAL == 0){
                     return true;
                }
                else{
                    return false;
                }
            }
            else{
                return true;
            }
        }
        else{
            return false;
        }
    }

    private boolean isValidMonth(int month){
        if(month == JAN || month == FEB || month == MAR || month == APR || month == MAY || month == JUN || month == JUL
        && month == AUG || month == SEP || month == OCT || month == NOV || month == DEC){
            return true;
        }
        return false;
    }

    private int checkIfWithinBounds(int month, int year, int day){
        Calendar aheadDate = Calendar.getInstance();
        aheadDate.add(Calendar.MONTH, 6);
        int updatedDay = Calendar.DAY_OF_MONTH;
        aheadDate.set(Calendar.DAY_OF_MONTH,updatedDay ++);

        //current date
        Calendar currDate = Calendar.getInstance();
        currDate.set(Calendar.DAY_OF_MONTH,this.day);
        currDate.set(Calendar.MONTH,this.month);
        currDate.set(Calendar.YEAR,this.year);

        return currDate.compareTo(aheadDate);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Date){
            Date date= (Date)obj;
            return (date.day == this.day) && (date.month == this.month)
                    && (date.year == this.year);
        }
        return false;
    }

    @Override
    public int compareTo(Date date) {
        Calendar one = Calendar.getInstance();
        one.set(Calendar.DAY_OF_MONTH,this.day);
        one.set(Calendar.MONTH,this.month);
        one.set(Calendar.YEAR,this.year);

        Calendar two = Calendar.getInstance();
        one.set(Calendar.DAY_OF_MONTH,date.day);
        one.set(Calendar.MONTH,date.month);
        one.set(Calendar.YEAR,date.year);

        return one.compareTo(two);
    }


    private static void testDaysInFeb_NonLeap() {

    }
    public static void main(String [] args) {
        Date d = new Date(2024,2,29);
        System.out.println(d.isValid());
    }
}
