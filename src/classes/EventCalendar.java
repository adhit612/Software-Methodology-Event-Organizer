package classes;

import java.sql.Time;

public class EventCalendar {

    /**
     * TO DO:
     * implement print
     * implement printByDate()
     * implement printByCampus()
     * implement printByDepartment
     * Timeslot add toString so user can type morning, afternoon, evening

     * COMPLETED:
     *Implement constructor
     * implement find
     * implement grow
     * implement add
     * implement remove
     * implement contains
     */

    private Event [] events; //the array holding the list of events
    private int numEvents;
    public EventCalendar(Event[] events, int numEvents) {
        this.events = events;
        this.numEvents = numEvents;
    }

    private int find(Event event) {
        //search event in the list
        for(int i = 0; i < this.events.length; i++) {
            if(this.events[i].equals(event)) {
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
                this.numEvents++;
                return true;
            }
        }
        return false;
    }

    public boolean remove(Event event) {
        //remove event from calendar, handle array resizing
        Event[] newEvents = new Event[this.events.length];
        int j = -1;
        for(int i = 0; i < this.events.length; i++) {
            if(this.events[i].equals(event)){
                j = i;
            }
        }
        if(j == -1) {
            return false;
        }

        int k = 0;
        for(int x = 0; x < this.events.length; x++){
           if(x == j){
               continue;
           }
           newEvents[k] = this.events[x];
           k ++;
        }

        this.numEvents--;
        this.events = newEvents;
        return true;
    }

    public boolean contains(Event event) {
        for(int i = 0; i < this.events.length; i ++){
            if(this.events[i].equals(event)){
                return true;
            }
        }
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

    public static void main(String[] args) {
        Event[] events = new Event[4];
        EventCalendar eventCalendar = new EventCalendar(events, 0);
        Date date = new Date("1/30/2024");
        Contact contact = new Contact(Department.ITI, "iti@rutgers.edu");
        Event event = new Event(date, Timeslot.MORNING, Location.HIL114, contact, 60);
        eventCalendar.add(event);

        Date date1 = new Date("2/30/2024");
        Contact contact1 = new Contact(Department.CS, "cs@rutgers.edu");
        Event event1 = new Event(date1, Timeslot.EVENING, Location.MU302, contact1, 30);
        eventCalendar.add(event1);

        System.out.println(events[0].toString());
        System.out.println(events[1].toString());
        eventCalendar.remove(event1);
        System.out.println(events[1].toString());
    }
}
