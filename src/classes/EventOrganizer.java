package classes;

import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Descriptive sentence.
 * Elaborate.
 * @author Abhishek Thakare, Adhit Thakur
 */

public class EventOrganizer {
    /**
     * TO DO:
     * Implement command line processor
     * test every step of the way
     * when reading have to add check for if the event is valid
     * check if the event has already been added
     * Specify error messages for Dates
     * Split Date.isValid() into having separate checks for withinBounds
     * Fix test cases for Date class
     * COMPLETED:
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
                contents[w] = tokenizer.nextToken();
                w++;
            }

            if (contents.length == 0) {
                continue;
            }

            //check if command is valid
            if (!isValidCommand(contents[0])) {
                System.out.println(contents[0] + " is an invalid command!");
                continue;
            }

            //working with empty
            if (!contents[0].equals("A") && collection == null) {
                System.out.println("Event calendar is empty!");
                continue;
            }

            //initializing
            if (contents[0].equals("A") && collection == null) {
                collection = new Event[4];
                calendar = new EventCalendar(collection, 0);
                if (isValidEvent(contents).equals("valid")) {
                    Event event = createEvent(contents);
                    calendar.add(event);
                    System.out.println("Event added to the calendar.");
                }
                else {
                    System.out.println(isValidEvent(contents));
                }
                continue;
            }

            //checking add
            if (contents[0].equals("A")) {
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
                continue;
            }

            //trying to do something other than add on empty calendar
            if (!contents[0].equals("A") && calendar.getNumEvents() == 0) {
                System.out.println("Event calendar is empty!");
                continue;
            }

            //removing
            if (contents[0].equals("R")) {
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
                continue;
            }

            if (contents[0].equals("P")) {
                if (contents.length == 1) {
                    calendar.print();
                }
                else {
                    System.out.println(contents[0] + " is an invalid command!");
                }
                continue;
            }

            if (contents[0].equals("PE")) {
                if (contents.length == 1) {
                    calendar.printByDate();
                }
                else {
                    System.out.println(contents[0] + " is an invalid command!");
                }
                continue;
            }

            if (contents[0].equals("PC")) {
                if (contents.length == 1) {
                    calendar.printByCampus();
                }
                else {
                    System.out.println(contents[0] + " is an invalid command!");
                }
                continue;
            }

            if (contents[0].equals("PD")) {
                if (contents.length == 1) {
                    calendar.printByDepartment();
                }
                else {
                    System.out.println(contents[0] + " is an invalid command!");
                }
            }

        }
        scanner.close();
    }

    private boolean isValidCommand(String str) {
        if (str.equals("A") || str.equals("R") || str.equals("P")
                || str.equals("PE") || str.equals("PC") || str.equals("PD")) {
            return true;
        }
        return false;
    }

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

    private Event createEvent(String[] contents) {
        Date date = new Date(contents[1]);
        Timeslot startTime = createTimeSlot(contents[2]);
        Location location = createLocation(contents[3]);
        Department department = createDepartment(contents[4]);
        Contact contact = new Contact(department, contents[5]);
        int duration = Integer.parseInt(contents[6]);

        return new Event(date, startTime, location, contact, duration);
    }

    private Event createEventForRemove(String[] contents) {
        Date date = new Date(contents[1]);
        Timeslot startTime = createTimeSlot(contents[2]);
        Location location = createLocation(contents[3]);
        return new Event(date, startTime, location);
    }

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
