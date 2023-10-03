package classes;

/**
 * Class that manages an Array of events.
 * Maintains an Event array through add, remove, and print operations.
 * @author Abhishek Thakare, Adhit Thakur
 */
public class EventCalendar {
    private Event[] events;
    private int numEvents;
    public static final int NOT_FOUND = -1;
    public static final int ARRAY_SIZE_ADDER = 4;

    /**
     * Constructor to initialize an EventCalendar.
     * @param events The array of Events, initially size 4.
     * @param numEvents The number of events, initially 0.
     */
    public EventCalendar(Event[] events, int numEvents) {
        this.events = events;
        this.numEvents = numEvents;
    }

    /**
     * Locate a given Event within the EventCalendar.
     * @param event the event to be searched for.
     * @return The index of the event if found, NOT_FOUND otherwise.
     */
    private int find(Event event) {
        for (int i = 0; i < this.events.length; i++) {
            if (this.events[i].equals(event)) {
                return i;
            }
        }
        return NOT_FOUND;
    }

    /**
     * Grow the EventCalendar if max capacity is reached.
     * If the last element in the EventCalendar is non-null, the array needs to grow.
     * Makes 4 more spaces for additional Events.
     */
    private void grow() {
        Event[] newEvents = new Event[this.events.length + ARRAY_SIZE_ADDER];
        for (int i = 0; i < this.events.length; i++) {
            newEvents[i] = this.events[i];
        }
        this.events = newEvents;
    }

    /**
     * Add an event to the calendar.
     * Checking if valid Event not done here.
     * Simply adding to Calendar.
     * @param event The Event to be added.
     * @return true if added to EventCalendar, false otherwise.
     */
    public boolean add(Event event) {
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

    /**
     * Remove an Event from the EventCalendar.
     * Given an Event, remove that Event entirely from the EventCalendar.
     * @param event The Event to be removed.
     * @return true if Event was removed, false otherwise.
     */
    public boolean remove(Event event) {
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

    /**
     * Check if Event is within the EventCalendar.
     * @param event The Event to be checked for.
     * @return true if Event is in EventCalendar, false otherwise.
     */
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

    /**
     * Print the EventCalendar unsorted.
     * Based on the order added, print the EventCalendar.
     */
    public void print() {
        for (int i = 0; i < this.events.length; i++) {
            if (this.events[i] != null) {
                System.out.println(this.events[i].toString());
            }
        }
    }

    /**
     * Print EventCalendar sorted by Date and Timeslot.
     * First sort by Date then Timeslot within each date.
     * Done using insertion sort in place.
     */
    public void printByDate() {
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
    }

    /**
     * Print EventCalendar sorted by Campus and Building.
     * First sort by Campus then Building within each Campus.
     * Done using insertion sort in place.
     */
    public void printByCampus() {
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
    }

    /**
     * Print EventCalendar sorted by Department.
     * Sort alphabetically by Department.
     * Done using insertion sort in place.
     */
    public void printByDepartment() {
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
    }

    /**
     * Return the number of events.
     * NOT total capacity.
     * @return number of events currently in EventCalendar.
     */
    public int getNumEvents() {
        return numEvents;
    }

    /**
     * Compare the Timeslots of two events.
     * Used to sort based on Timeslots, determining which Event is earlier.
     * @param one Event that is comparing.
     * @param two Event that is being compared.
     * @return true if one is earlier than two, false otherwise.
     */
    public boolean compareTimeslots(Timeslot one, Timeslot two) {
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
