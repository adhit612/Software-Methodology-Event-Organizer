package classes;

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
    public static final int NOT_FOUND = -1;
    public static final int ARRAY_SIZE_ADDER = 4;


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
        return NOT_FOUND;
    }

    private void grow() {
        //increase capacity by 4
        Event[] newEvents = new Event[this.events.length + ARRAY_SIZE_ADDER];
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
        int j = find(event);
        if(j == NOT_FOUND) {
            return false;
        }
        int k = 0;
        for(int x = 0; x < this.events.length; x++) {
           if(x == j) {
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
            if(this.events[i] == null) {
                return false;
            }
            if(this.events[i].equals(event)){
                return true;
            }
        }
        return false;
    }

    public void print() {
        for(int i = 0; i < this.events.length; i++) {
            if(this.events[i] != null) {
                System.out.println(this.events[i].toString());
            }
        }
    }

    public void printByDate() {
        /*

        int i, j;
        Event temp;
        boolean swapped;
        for (i = 0; i < this.events.length - 1; i++) {
            swapped = false;
            for (j = 0; j < this.events.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {

                    // Swap arr[j] and arr[j+1]
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }

            // If no two elements were
            // swapped by inner loop, then break
            if (swapped == false)
                break;
        }
        for(int i = 0; i < this.events.length-1; i++) {
            if(this.events[i] != null && this.events[i+1] != null) {
                if(this.events[i].compareTo(this.events[i+1]) < 0) {
                    continue;
                }
                else if(this.events[i].compareTo(this.events[i+1]) > 0) {
                    Event temp = this.events[i];
                    this.events[i] = this.events[i+1];
                    this.events[i+1] = temp;
                }
                else {
                    if(this.events[i].getTimeSlot().compareTo(this.events[i+1].getTimeSlot()) < 0) {
                        continue;
                    }
                    else if(this.events[i].getTimeSlot().compareTo(this.events[i+1].getTimeSlot())) {
                        Event temp = this.events[i];
                        this.events[i] = this.events[i+1];
                        this.events[i+1] = temp;
                    }
                }
            }
        }

         */
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

        eventCalendar.print();

        //System.out.println(events[0].toString());
        //System.out.println(events[1].toString());
        //System.out.println(eventCalendar.remove(event));
        //System.out.println(eventCalendar.remove(event1));

    }
}
