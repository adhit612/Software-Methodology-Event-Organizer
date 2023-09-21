package classes;

public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;

    public Date(int year, int month, int day) {

    }
    public boolean isValid() {
        //check if the date is a valid calendar date
        //use for testBed main method
    }

    @Override
    public int compareTo(Date date) {
        return 0;
    }
}
