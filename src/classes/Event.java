package classes;

/**
 * Class that declares the Event object.
 * Events contain instances of other declared objects.
 * Will be created and manipulated based on user input.
 * @author Abhishek Thakare, Adhit Thakur
 */
public class Event implements Comparable<Event> {
    private Date date;
    private Timeslot startTime;
    private Location location;
    private Contact contact;
    private int duration;

    /**
     * Constructor to initialize an Event when adding.
     * @param date the Event's Date object.
     * @param startTime the Event's Timeslot enum.
     * @param location the Event's Location enum.
     * @param contact the Event's Contact enum.
     * @param duration the Event's integer duration.
     */
    public Event(Date date, Timeslot startTime, Location location, Contact contact, int duration) {
        this.date = date;
        this.startTime = startTime;
        this.location = location;
        this.contact = contact;
        this.duration = duration;
    }

    /**
     * Constructor to initialize an Event when removing.
     * Fewer parameters are needed when removing.
     * @param date the Event's Date object.
     * @param startTime the Event's Timeslot enum.
     * @param location the Event's Location enum.
     */
    public Event(Date date, Timeslot startTime, Location location) {
        this.date = date;
        this.startTime = startTime;
        this.location = location;
    }

    /**
     * toString() method to print an Event in the client requested format.
     * @return a String containing the correctly formatted data of one Event.
     */
    @Override
    public String toString() {
        int endHour = this.startTime.getHours();
        int endMinute = 0;
        if (this.startTime.getMins() + this.duration >= 60) {
            endHour += ((this.duration + this.startTime.getMins()) / 60);
            endMinute = ((this.duration + this.startTime.getMins()) % 60);
        }
        else {
            endMinute = this.startTime.getMins() + this.duration;
        }

        String beginAttachment = "";
        String endAttachment = "";
        if (this.startTime == Timeslot.MORNING && endHour == 12) {
            beginAttachment = "am";
            endAttachment = "pm";
        }
        else if (this.startTime == Timeslot.MORNING) {
            beginAttachment = "am";
            endAttachment = "am";
        }
        else {
            beginAttachment = "pm";
            endAttachment = "pm";
        }

        if (endMinute == 0) {
            return "[Event Date: " + this.date.toString() + "] [Start: "
                    + this.startTime.toString() + beginAttachment
                    + "] [End: " + endHour + ":" + endMinute + "0" + endAttachment
                    + "] @" + this.location + " (" + this.location.getBuilding() + ", "
                    + this.location.getCampus() + ") [Contact: "
                    + this.contact.getDepartment().toString() + ", "
                    + this.contact.getEmail() + "]";
        }
        return "[Event Date: " + this.date.toString() + "] [Start: "
                + this.startTime.toString() + beginAttachment + "] [End: " + endHour
                + ":" + endMinute + endAttachment + "] @" + this.location + " ("
                + this.location.getBuilding() + ", " + this.location.getCampus()
                + ") [Contact: " + this.contact.getDepartment().toString() + ", "
                + this.contact.getEmail() + "]";
    }

    /**
     * Determine if two Events are equal.
     * Compare the Contacts, Dates, Timeslots, and Locations.
     * @param obj The object to be compared if it is of Event type.
     * @return true if the Events are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Event) {
            Event event = (Event) obj;
            if (event.contact == null || this.contact == null) {
                return event.date.equals(this.date)
                        && event.startTime.equals(this.startTime)
                        && event.location.equals(this.location);
            }
            else {
                return event.date.equals(this.date) && event.startTime
                        .equals(this.startTime)
                        && event.location.equals(this.location)
                        && event.contact.getDepartment().equals(this.contact.getDepartment())
                        && event.contact.getEmail().equals(this.contact.getEmail());
            }
        }
        return false;
    }

    /**
     * Compare two events.
     * Compare the Dates and Timeslots.
     * @param event the object to be compared.
     * @return -1 if "this" earlier than event, 1 if "this" later than event, 0 if equal.
     */
    @Override
    public int compareTo(Event event) {
        if (this.date.compareTo(event.date) == -1) { //this is earlier than event
            return -1;
        }
        else if (this.date.compareTo(event.date) == 1) { //this is later than event
            return 1;
        }
        else { //equal dates
            if (this.startTime == event.startTime) {
                return 0;
            }
            else if (this.startTime == Timeslot.MORNING
                    && event.startTime == Timeslot.EVENING
                    || this.startTime == Timeslot.MORNING
                    && event.startTime == Timeslot.AFTERNOON) {
                return -1;
            }
            else if (this.startTime == Timeslot.AFTERNOON
                    && event.startTime == Timeslot.EVENING) {
                return -1;
            }
            else {
                return 1;
            }
        }
    }

    /**
     * Return the Event's Date.
     * @return Event's Date object.
     */
    public Date getDate() {
        return this.date;
    }

    /**
     * Return the Event's Timeslot.
     * @return Event's Timeslot object.
     */
    public Timeslot getTimeSlot() {
        return this.startTime;
    }

    /**
     * Return the Event's Location.
     * @return Event's Location object.
     */
    public Location getLocation() {
        return this.location;
    }

    /**
     * Return the Event's Contact.
     * @return Event's Contact object.
     */
    public Contact getContact() {
        return this.contact;
    }

    /**
     * Return the Event's Duration.
     * @return Event's integer Duration.
     */
    public int getDuration() {
        return this.duration;
    }

    /**
     * Main method to run test cases.
     * @param args Arguments entered.
     */
    public static void main(String[] args) {
        testTimeChangeFromAMtoPM();
        testCompareToForEqualDates();
        testTwoEventsEqual();
        testTwoEventsNotEqual();
    }

    private static void testTimeChangeFromAMtoPM() {
        Date date = new Date("1/30/2024");
        Contact contact = new Contact(Department.CS, "cs@rutgers.edu");
        Event event = new Event(date, Timeslot.MORNING,
                Location.ARC103, contact, 120);
        String expectedOutput = "[Event Date: 1/30/2024] [Start: 10:30am]"
                + " [End: 12:30pm]"
                + " @ARC103 (Allison Road Classroom, Busch)"
                + " [Contact: Computer Science, cs@rutgers.edu]";
        String actualOutput = event.toString();
        System.out.println("Test case 1 => Check if time changes from"
                + " AM to PM using toString() method");
        if (expectedOutput.equals(actualOutput)) {
            System.out.println("succeeded");
        }
        else {
            System.out.println("failed");
        }

    }

    private static void testCompareToForEqualDates() {
        Date date1 = new Date("3/12/2024");
        Contact contact1 = new Contact(Department.BAIT, "bait@rutgers.edu");
        Event event1 = new Event(date1, Timeslot.MORNING,
                Location.ARC103, contact1, 60);

        Date date2 = new Date("3/12/2024");
        Contact contact2 = new Contact(Department.ITI, "iti@rutgers.edu");
        Event event2 = new Event(date2, Timeslot.AFTERNOON,
                Location.HLL114, contact2, 30);

        int expectedOutput = -1;
        int actualOutput = event1.compareTo(event2);

        System.out.println("Test case 2 => Check if compareTo compares Timeslots"
                + " if Dates are equal");
        if (expectedOutput == actualOutput) {
            System.out.println("succeeded");
        }
        else {
            System.out.println("failed");
        }
    }

    private static void testTwoEventsEqual() {
        Date date1 = new Date("12/25/2023");
        Contact contact1 = new Contact(Department.CS, "cs@rutgers.edu");
        Event event1 = new Event(date1, Timeslot.EVENING,
                Location.BE_AUD, contact1, 60);

        Date date2 = new Date("12/25/2023");
        Contact contact2 = new Contact(Department.CS, "cs@rutgers.edu");
        Event event2 = new Event(date2, Timeslot.EVENING,
                Location.BE_AUD, contact2, 60);

        boolean expectedOutput = true;
        boolean actualOutput = event1.equals(event2);

        System.out.println("Test case 3 => Check if two events are equal using "
                + " .equals() method");
        if (expectedOutput == actualOutput) {
            System.out.println("succeeded");
        }
        else {
            System.out.println("failed");
        }
    }

    private static void testTwoEventsNotEqual() {
        Date date1 = new Date("11/18/2023");
        Contact contact1 = new Contact(Department.ITI, "iti@rutgers.edu");
        Event event1 = new Event(date1, Timeslot.MORNING,
                Location.HLL114, contact1, 60);

        Date date2 = new Date("10/22/2023");
        Contact contact2 = new Contact(Department.CS, "cs@rutgers.edu");
        Event event2 = new Event(date2, Timeslot.EVENING,
                Location.BE_AUD, contact2, 120);

        boolean expectedOutput = false;
        boolean actualOutput = event1.equals(event2);

        System.out.println("Test case 4 => Check if two events are not equal"
                + " using .equals() method");
        if (expectedOutput == actualOutput) {
            System.out.println("succeeded");
        }
        else {
            System.out.println("failed");
        }
    }
}
