package classes;

/**
 * Descriptive sentence.
 * Elaborate.
 * @author Abhishek Thakare, Adhit Thakur
 */

public class EventCalendar {

    /**
     * TO DO:
     * <p>
     * COMPLETED:
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
     * Timeslot add toString so user can type morning, afternoon, evening
     */

    private Event[] events; //the array holding the list of events
    private int numEvents;
    public static final int NOT_FOUND = -1;
    public static final int ARRAY_SIZE_ADDER = 4;


    public EventCalendar(Event[] events, int numEvents) {
        this.events = events;
        this.numEvents = numEvents;
    }

    private int find(Event event) {
        //search event in the list
        for (int i = 0; i < this.events.length; i++) {
            if (this.events[i].equals(event)) {
                return i;
            }
        }
        return NOT_FOUND;
    }

    private void grow() {
        //increase capacity by 4
        Event[] newEvents = new Event[this.events.length + ARRAY_SIZE_ADDER];
        for (int i = 0; i < this.events.length; i++) {
            newEvents[i] = this.events[i];
        }
        this.events = newEvents;
    }

    public boolean add(Event event) {
        //add event to calendar, handle array resizing
        if (this.events[this.events.length - 1] != null) {
            grow();
        }

        for (int i = 0; i < this.events.length; i++) {
            if (this.events[i] == null) {
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
        if (j == NOT_FOUND) {
            return false;
        }
        int k = 0;
        for (int x = 0; x < this.events.length; x++) {
            if (x == j) {
                continue;
            }
            newEvents[k] = this.events[x];
            k++;
        }

        this.numEvents--;
        this.events = newEvents;
        return true;
    }

    public boolean contains(Event event) {
        for (int i = 0; i < this.events.length; i++) {
            if (this.events[i] == null) {
                return false;
            }
            if (this.events[i].equals(event)) {
                return true;
            }
        }
        return false;
    }

    public void print() {
        System.out.println("* Event calendar *");
        for (int i = 0; i < this.events.length; i++) {
            if (this.events[i] != null) {
                System.out.println(this.events[i].toString());
            }
        }
        System.out.println("* end of event calendar *");
    }

    public void printByDate() {
        System.out.println("* Event calendar by event date and start time *");
        for (int i = 1; i < this.events.length; i++) {
            if (this.events[i] == null) {
                break;
            }

            Event temp = this.events[i];
            int j = i - 1;
            while (j >= 0 && (this.events[j].getDate()
                    .compareTo(temp.getDate())) == 1) {
                this.events[j + 1] = this.events[j];
                j--;
            }
            while (j >= 0 && this.events[j].getDate().equals(temp.getDate())
                    && compareTimeslots(temp.getTimeSlot(), this.events[j].getTimeSlot())) {
                this.events[j + 1] = this.events[j];
                j--;
            }
            this.events[j + 1] = temp;
        }

        for (int x = 0; x < this.events.length; x++) {
            if (this.events[x] == null) {
                break;
            }
            System.out.println(this.events[x].toString());
        }
        System.out.println("* end of event calendar *");
    }

    public void printByCampus() {
        System.out.println("* Event calendar by campus and building *");
        for (int i = 1; i < this.events.length; i++) {
            if (this.events[i] == null) {
                break;
            }

            Event temp = this.events[i];
            int j = i - 1;
            while (j >= 0 && ((this.events[j].getLocation().getCampus())
                    .compareTo(temp.getLocation().getCampus())) >= 1) {
                this.events[j + 1] = this.events[j];
                j--;
            }
            while (j >= 0 && (this.events[j].getLocation().getCampus()
                    .equals(temp.getLocation().getCampus()))
                    && ((this.events[j].getLocation().getBuilding())
                    .compareTo(temp.getLocation().getBuilding())) >= 1) {
                this.events[j + 1] = this.events[j];
                j--;
            }
            this.events[j + 1] = temp;
        }

        for (int x = 0; x < this.events.length; x++) {
            if (this.events[x] == null) {
                break;
            }
            System.out.println(this.events[x].toString());
        }

        System.out.println("* end of event calendar *");
    }

    public void printByDepartment() {
        System.out.println("* Event calendar by department *");
        for (int i = 1; i < this.events.length; i++) {
            if (this.events[i] == null) {
                break;
            }

            Event temp = this.events[i];
            int j = i - 1;
            while (j >= 0 && ((this.events[j].getContact().getDepartment().toString())
                    .compareTo(temp.getContact().getDepartment().toString())) >= 1) {
                this.events[j + 1] = this.events[j];
                j--;
            }
            this.events[j + 1] = temp;
        }

        for (int x = 0; x < this.events.length; x++) {
            if (this.events[x] == null) {
                break;
            }
            System.out.println(this.events[x].toString());
        }
        System.out.println("* end of event calendar *");
    }

    public int getNumEvents() {
        return numEvents;
    }

    public boolean compareTimeslots(Timeslot one, Timeslot two) {
        //returns true if one is earlier than two
        if (one.toString().equals(two.toString())) {
            return false;
        }
        else if (one == Timeslot.MORNING && two == Timeslot.AFTERNOON) {
            return true;
        }
        else if (one == Timeslot.AFTERNOON && two == Timeslot.EVENING) {
            return true;
        }
        else if (one == Timeslot.MORNING && two == Timeslot.EVENING) {
            return true;
        }
        else {
            return false;
        }
    }
}
