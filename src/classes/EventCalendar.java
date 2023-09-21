package classes;

public class EventCalendar {
    private Event [] events; //the array holding the list of events
    private int numEvents;
    public EventCalendar(Event[] events, int numEvents) {

    }

    private int find(Event event) {
        //search event in the list
    }

    private void grow() {
        //increase capacity by 4
    }

    public boolean add(Event event) {
        //add event to calendar, handle array resizing
    }

    public boolean remove(Event event) {
        //remove event from calendar, handle array resizing
    }

    public boolean contains(Event event) {
        //
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
