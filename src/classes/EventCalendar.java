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

    private Event [] events = new Event[4]; //the array holding the list of events
    private int numEvents;
    public EventCalendar(Event[] events, int numEvents) {
        this.events = events;
        this.numEvents = numEvents;
    }

    private int find(Event event) {
        //search event in the list
        for(int i = 0; i < this.events.length; i++) {
            if(this.events[i] == event) {
                return i;
            }
        }
        return -1;
    }

    private void grow() {
        //increase capacity by 4
        Event[] newEvents = new Event[this.events.length + 4];
        for(int i = 0; i < this.events.length; i++) {
            newEvents[i] = this.events[i];
        }
        this.events = newEvents;
    }

    public boolean add(Event event) {
        //add event to calendar, handle array resizing
        if(this.events[this.events.length-1] != null) {
            grow();
        }
        for(int i = 0; i < this.events.length; i++) {
            if(this.events[i] == null) {
                this.events[i] = event;
                return true;
            }
        }
        return false;
    }

    public boolean remove(Event event) {
        //remove event from calendar, handle array resizing
        Event[] newEvents = new Event[this.events.length-1];
        int j = -1;
        for(int i = 0; i < this.events.length; i++) {
            if(this.events[i] == event) {
                j = i;
            }
        }
        if(j == -1) {
            return false;
        }
        for(int x = 0, k = 0; x < this.events.length; x++){
            if(x != j) {
                newEvents[k] = this.events[x];
                k++;
            }
        }
        this.events = newEvents;
        return true;
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
