package classes;

import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Class that regulates interactive manipulation of an EventCalendar.
 * Using Scanner to take in user input to alter an EventCalendar.
 * @author Abhishek Thakare, Adhit Thakur
 */
public class EventOrganizer {

    /**
     * Runner for Scanner input processing.
     * Taking in user input until "Q" is entered.
     * Processing each command at a very high level.
     */
    public void run() {
        Event[] collection = null;
        EventCalendar calendar = null;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Event Organizer running...");
        while (true) {
            String line = scanner.nextLine();
            if (line.equals("Q")) {
                System.out.println("Event Organizer terminated.");
                break;
            }
            StringTokenizer tokenizer = new StringTokenizer(line, " ");
            String[] contents = new String[tokenizer.countTokens()];
            int w = 0;
            while (tokenizer.hasMoreTokens()) {
                contents[w++] = tokenizer.nextToken();
            }
            if (contents.length == 0) {
            }
            else if (!isValidCommand(contents[0])) {
                System.out.println(contents[0] + " is an invalid command!");
            }
            else if (!contents[0].equals("A") && collection == null) {
                System.out.println("Event calendar is empty!");
            }
            else if (contents[0].equals("A") && collection == null) {
                collection = new Event[4];
                calendar = new EventCalendar(collection, 0);
                aCommandInit(collection,calendar,contents);
            }
            else {
                runThroughNonEdgeCases(collection,calendar,contents);
            }
        }
        scanner.close();
    }

    /**
     * Determining what to output to user after all edge cases are tackled.
     * @param collection Event[] to be used.
     * @param calendar EventCalendar to be altered.
     * @param contents User input line to be processed.
     */
    public void runThroughNonEdgeCases(Event [] collection, EventCalendar calendar, String [] contents){
        if (contents[0].equals("A")) {
            aCommandAfterInit(collection,calendar,contents);
        }
        else if (!contents[0].equals("A") && calendar.getNumEvents() == 0) {
            System.out.println("Event calendar is empty!");
        }
        else if (contents[0].equals("R")) {
            rCommandChecker(collection,calendar,contents);
        }
        else if (contents[0].equals("P")) {
            System.out.println("* Event calendar *");
            pCommandChecker(collection,calendar,contents);
            System.out.println("* end of event calendar *");
        }
        else if (contents[0].equals("PE")) {
            System.out.println("* Event calendar by event date and start time *");
            peCommandChecker(collection,calendar,contents);
            System.out.println("* end of event calendar *");
        }
        else if (contents[0].equals("PC")) {
            System.out.println("* Event calendar by campus and building *");
            pcCommandChecker(collection,calendar,contents);
            System.out.println("* end of event calendar *");
        }
        else if(contents[0].equals("PD")) {
            System.out.println("* Event calendar by department *");
            pdCommandChecker(collection,calendar,contents);
            System.out.println("* end of event calendar *");
        }
    }

    /**
     * Generate the appropriate message when adding event to a currently null calendar.
     * @param collection Event[] to be used.
     * @param calendar EventCalendar to be altered.
     * @param contents User input line to be processed.
     */
    public void aCommandInit(Event [] collection, EventCalendar calendar, String [] contents){
        if (isValidEvent(contents).equals("valid")) {
            Event event = createEvent(contents);
            calendar.add(event);
            System.out.println("Event added to the calendar.");
        }
        else {
            System.out.println(isValidEvent(contents));
        }
    }

    /**
     * Generate appropriate message when adding event to a non-null calendar.
     * @param collection Event[] to be used.
     * @param calendar EventCalendar to be altered.
     * @param contents User input line to be processed.
     */
    public void aCommandAfterInit(Event [] collection, EventCalendar calendar, String [] contents){
        if (isValidEvent(contents).equals("valid")) {
            Event event = createEvent(contents);
            if (calendar.contains(event)) {
                System.out.println("The event is already on the calendar");
            }
            else {
                calendar.add(event);
                System.out.println("Event added to the calendar.");
            }
        }
        else {
            System.out.println(isValidEvent(contents));
        }
    }

    /**
     * Package the remove command processing.
     * Determine whether Event can be removed and performing the operation.
     * @param collection Event[] to be used.
     * @param calendar EventCalendar to be altered.
     * @param contents User input line to be processed.
     */
    public void rCommandChecker(Event [] collection, EventCalendar calendar, String [] contents){
        if (isValidRemove(contents).equals("valid")) {
            Event event = createEventForRemove(contents);
            if (calendar.contains(event)) {
                calendar.remove(event);
                System.out.println("Event has been removed from the calendar!");
            }
            else {
                System.out.println("Cannot remove; event is not in the calendar!");
            }
        }
        else {
            System.out.println(isValidRemove(contents));
        }
    }

    /**
     * Process printing the unsorted calendar if the "P" command is correctly entered.
     * @param collection Event[] to be used.
     * @param calendar EventCalendar to be altered.
     * @param contents User input line to be processed.
     */
    public void pCommandChecker(Event [] collection, EventCalendar calendar, String [] contents){
        if (contents.length == 1) {
            calendar.print();
        }
        else {
            System.out.println(contents[0] + " is an invalid command!");
        }
    }

    /**
     * Process printing the calendar sorted by date and timeslot if the "PE" command is correctly entered.
     * @param collection Event[] to be used.
     * @param calendar EventCalendar to be altered.
     * @param contents User input line to be processed.
     */
    public void peCommandChecker(Event [] collection, EventCalendar calendar, String [] contents) {
        if (contents.length == 1) {
            calendar.printByDate();
        }
        else {
            System.out.println(contents[0] + " is an invalid command!");
        }
    }

    /**
     * Process printing the calendar sorted by campus and building if the "PC" command is correctly entered.
     * @param collection Event[] to be used.
     * @param calendar EventCalendar to be altered.
     * @param contents User input line to be processed.
     */
    public void pcCommandChecker(Event [] collection, EventCalendar calendar, String [] contents) {
        if (contents.length == 1) {
            calendar.printByCampus();
        }
        else {
            System.out.println(contents[0] + " is an invalid command!");
        }
    }

    /**
     * Process printing the calendar sorted by department if the "PD" command is correctly entered.
     * @param collection Event[] to be used.
     * @param calendar EventCalendar to be altered.
     * @param contents User input line to be processed.
     */
    public void pdCommandChecker(Event [] collection, EventCalendar calendar, String [] contents) {
        if (contents.length == 1) {
            calendar.printByDepartment();
        }
        else {
            System.out.println(contents[0] + " is an invalid command!");
        }
    }

    /**
     * Check if the given command is of proper type.
     * Determining if the command is one of the 6 possible inputs.
     * @param str Command to be checked.
     * @return true if command is one of 6 possible inputs, otherwise false.
     */
    private boolean isValidCommand(String str) {
        if (str.equals("A") || str.equals("R") || str.equals("P")
                || str.equals("PE") || str.equals("PC") || str.equals("PD")) {
            return true;
        }
        return false;
    }

    /**
     * Determine if the event to be added is valid.
     * Process each component of Event to ensure it is fully valid.
     * Generate appropriate message.
     * @param contents Command to be checked.
     * @return Error message depending on the issue with Event parameter, otherwise "valid".
     */
    private String isValidEvent(String[] contents) {
        Date date = new Date(contents[1]);
        if (!date.isValid()) {
            return contents[1] + ": Invalid calendar date!";
        }
        else if (date.checkIfWithinBounds(date.getMonth(),
                date.getYear(), date.getDay()) > 0) {
            return contents[1] + ": Event date must be within 6 months!";
        }
        else if (date.checkIfInPast(date.getMonth(),
                date.getYear(), date.getDay()) > 0) {
            return contents[1] + ": Event date must be a future date!";
        }
        if (!contents[2].toLowerCase().equals("morning")
                && !contents[2].toLowerCase()
                .equals("afternoon") && !contents[2].toLowerCase()
                .equals("evening")) {
            return "Invalid time slot!";
        }
        String roomToLower = contents[3].toLowerCase();
        if (!roomToLower.equals("hll114") && !roomToLower.equals("arc103")
                && !roomToLower.equals("be_aud") && !roomToLower.equals("til232")
                && !roomToLower.equals("ab2225") && !roomToLower.equals("mu302")) {
            return "Invalid location!";
        }
        String departmentToLower = contents[4].toLowerCase();
        if (!departmentToLower.equals("cs") && !departmentToLower.equals("ee")
                && !departmentToLower.equals("iti") && !departmentToLower.equals("math")
                && !departmentToLower.equals("bait")) {
            return "Invalid contact information!";
        }
        Contact contact = new Contact(Department.CS, contents[5]);
        if (!contact.isValid()) {
            return "Invalid contact information!";
        }
        int duration = Integer.parseInt(contents[6]);
        if (duration < 30 || duration > 120) {
            return "Event duration must be at least 30 minutes"
                    + " and at most 120 minutes";
        }
        return "valid";
    }

    /**
     * Determines if the event to be removed is valid.
     * Checks if Date, Timeslot, and Location are proper.
     * @param contents The remove command to be processed.
     * @return Error message depending on the issue with Event parameter, otherwise "valid".
     */
    private String isValidRemove(String[] contents) {
        Date date = new Date(contents[1]);
        if (!date.isValid()) {
            return contents[1] + ": Invalid calendar date!";
        }
        else if (date.checkIfWithinBounds(date.getMonth(),
                date.getYear(), date.getDay()) > 0) {
            return contents[1] + ": Event date must be within 6 months!";
        }
        else if (date.checkIfInPast(date.getMonth(),
                date.getYear(), date.getDay()) > 0) {
            return contents[1] + ": Event date must be a future date!";
        }
        if (!contents[2].toLowerCase().equals("morning") && !contents[2].toLowerCase()
                .equals("afternoon") && !contents[2].toLowerCase().equals("evening")) {
            return "Invalid time slot!";
        }
        String roomToLower = contents[3].toLowerCase();
        if (!roomToLower.equals("hll114") && !roomToLower.equals("arc103")
                && !roomToLower.equals("be_aud") && !roomToLower.equals("til232")
                && !roomToLower.equals("ab2225") && !roomToLower.equals("mu302")) {
            return "Invalid location!";
        }
        return "valid";
    }

    /**
     * Creating an event based on user input to add.
     * Once Event is determined to be valid, generate Event.
     * Add that event to the EventCalendar.
     * @param contents Command to be processed into an Event.
     * @return the Event that will be added to the EventCalendar.
     */
    private Event createEvent(String[] contents) {
        Date date = new Date(contents[1]);
        Timeslot startTime = createTimeSlot(contents[2]);
        Location location = createLocation(contents[3]);
        Department department = createDepartment(contents[4]);
        Contact contact = new Contact(department, contents[5]);
        int duration = Integer.parseInt(contents[6]);
        return new Event(date, startTime, location, contact, duration);
    }

    /**
     * Creating an event based on user input to remove.
     * Once Event is determined to be valid, generate Event.
     * Remove that event from the EventCalendar.
     * @param contents Command to be processed into an Event.
     * @return the Event that will be added to the EventCalendar.
     */
    private Event createEventForRemove(String[] contents) {
        Date date = new Date(contents[1]);
        Timeslot startTime = createTimeSlot(contents[2]);
        Location location = createLocation(contents[3]);
        return new Event(date, startTime, location);
    }

    /**
     * Create a Timeslot for an Event based on user input.
     * Once Event is determined to be valid, create its Timeslot.
     * Turn the String input into a Timeslot enum constant.
     * @param str The string to be converted to Timeslot enum.
     * @return the Timeslot enum constant that was generated.
     */
    private Timeslot createTimeSlot(String str) {
        if (str.toLowerCase().equals("morning")) {
            return Timeslot.MORNING;
        }
        else if (str.toLowerCase().equals("afternoon")) {
            return Timeslot.AFTERNOON;
        }
        else {
            return Timeslot.EVENING;
        }
    }

    /**
     * Create a Location for an Event based on user input.
     * Once Event is determined to be valid, create its Location.
     * Turn the String input into a Location enum constant.
     * @param str The string to be converted to Location enum.
     * @return the Location enum constant that was generated.
     */
    private Location createLocation(String str) {
        if (str.toLowerCase().equals("hll114")) {
            return Location.HLL114;
        }
        else if (str.toLowerCase().equals("arc103")) {
            return Location.ARC103;
        }
        else if (str.toLowerCase().equals("be_aud")) {
            return Location.BE_AUD;
        }
        else if (str.toLowerCase().equals("til232")) {
            return Location.TIL232;
        }
        else if (str.toLowerCase().equals("ab2225")) {
            return Location.AB2225;
        }
        else {
            return Location.MU302;
        }
    }

    /**
     * Create a Department for an Event based on user input.
     * Once Event is determined to be valid, create its Department.
     * Turn the String input into a Department enum constant.
     * @param str The string to be converted to Department enum.
     * @return the Department enum constant that was generated.
     */
    private Department createDepartment(String str) {
        if (str.toLowerCase().equals("cs")) {
            return Department.CS;
        }
        else if (str.toLowerCase().equals("ee")) {
            return Department.EE;
        }
        else if (str.toLowerCase().equals("iti")) {
            return Department.ITI;
        }
        else if (str.toLowerCase().equals("math")) {
            return Department.MATH;
        }
        else {
            return Department.BAIT;
        }
    }
}
