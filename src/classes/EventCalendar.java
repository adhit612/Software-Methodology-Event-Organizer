package classes;

public class EventCalendar {

    /**
     * TO DO:
     * Implement constructor
     * implement find
     * implement grow
     * implement add
     * implement remove
     * implement contains
     * implement print
     * implement printByDate()
     * implement printByCampus()
     * implement printByDepartment

     * COMPLETED:
     *
     */

    private Event [] events; //the array holding the list of events
    private int numEvents;
    public EventCalendar(Event[] events, int numEvents) {

    }

    private int find(Event event) {
        //search event in the list
        return 0;
    }

    private void grow() {
        //increase capacity by 4
    }

    public boolean add(Event event) {
        //add event to calendar, handle array resizing
        return false;
    }

    public boolean remove(Event event) {
        //remove event from calendar, handle array resizing
        return false;
    }

    public boolean contains(Event event) {
        //
        return false;
    }

    public void print() {
        //print the array as is
    }

    public void printByDate() {
        //print events sorted by date and timeslot
    }

    public void printByCampus() {
        //print events sorted by campus and building/room
    }

    public void printByDepartment() {
        //print events sorted by department
    }
}
