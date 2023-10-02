package classes;

import com.sun.source.doctree.BlockTagTree;

import java.sql.Time;
import java.util.Calendar;

public class Event implements Comparable<Event> {

    /**
     * TO DO:
     * Add testBedMain()

     * COMPLETED:
     * Override equals()
     * Add compareTo()
     * Override toString()
     */

    private Date date;
    private Timeslot startTime;
    private Location location;
    private Contact contact;
    private int duration;

    public Event(Date date, Timeslot startTime, Location location, Contact contact, int duration) {
        this.date = date;
        this.startTime = startTime;
        this.location = location;
        this.contact = contact;
        this.duration = duration;
    }

    public Event(Date date, Timeslot startTime, Location location){
        this.date = date;
        this.startTime = startTime;
        this.location = location;
    }

    @Override
    public String toString() {
        int endHour = this.startTime.getHours();
        int endMinute = 0;
        if(this.startTime.getMins() + this.duration >= 60) {
            endHour += ((this.duration + this.startTime.getMins()) / 60);
            endMinute = ((this.duration+this.startTime.getMins()) % 60);
        }
        else {
            endMinute = this.startTime.getMins() + this.duration;
        }

        String beginAttachment = "";
        String endAttachment = "";
        if(this.startTime == Timeslot.MORNING && endHour == 12) {
            beginAttachment = "am";
            endAttachment = "pm";
        }
        else if(this.startTime == Timeslot.MORNING) {
            beginAttachment = "am";
            endAttachment = "am";
        }
        else {
            beginAttachment = "pm";
            endAttachment = "pm";
        }

        if(endMinute == 0) {
            return "[Event Date: " + this.date.toString() + "] [Start: " + this.startTime.toString() + beginAttachment + "] [End: " + endHour + ":" + endMinute + "0" + endAttachment + "] @" + this.location + "(" + this.location.getBuilding() + "," + this.location.getCampus() + ") [Contact: " + this.contact.getDepartment().toString() + ", " + this.contact.getEmail() + "]";
        }
        return "[Event Date: " + this.date.toString() + "] [Start: " + this.startTime.toString() + beginAttachment + "] [End: " + endHour + ":" + endMinute + endAttachment + "] @" + this.location + "(" + this.location.getBuilding() + "," + this.location.getCampus() + ") [Contact: " + this.contact.getDepartment().toString() + ", " + this.contact.getEmail() + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Event){
            Event event = (Event)obj;
            if(event.contact == null || this.contact == null){
                return event.date.equals(this.date) && event.startTime.equals(this.startTime)
                        && event.location.equals(this.location);
            }
            else{
                return event.date.equals(this.date) && event.startTime.equals(this.startTime)
                        && event.location.equals(this.location) && event.contact.getDepartment().equals(this.contact.getDepartment()) && event.contact.getEmail().equals(this.contact.getEmail());
            }
        }
        return false;
    }

    @Override
    public int compareTo(Event event) {
        if(this.date.compareTo(event.date) == -1){ //this is earlier than event
            return -1;
        }
        else if(this.date.compareTo(event.date) == 1){ //this is later than event
            return 1;
        }
        else { //equal dates
            if(this.startTime == event.startTime){
                return 0;
            }
            else if(this.startTime == Timeslot.MORNING && event.startTime == Timeslot.EVENING || this.startTime == Timeslot.MORNING && event.startTime == Timeslot.AFTERNOON){
                return -1;
            }
            else if(this.startTime == Timeslot.AFTERNOON && event.startTime == Timeslot.EVENING){
                return -1;
            }
            else{
                return 1;
            }
        }
    }

    public Date getDate() {
        return this.date;
    }

    public Timeslot getTimeSlot(){
        return this.startTime;
    }

    public Location getLocation(){
        return this.location;
    }

    public Contact getContact(){
        return this.contact;
    }

    public int getDuration(){
        return this.duration;
    }

    public static void main(String[] args) {
        Contact contact = new Contact(Department.CS, "cs@rutgers.edu");
        Date date = new Date("2/24/2024");
        Event event = new Event(date, Timeslot.MORNING, Location.HLL114, contact, 30);

        Contact contact1 = new Contact(Department.CS, "cs@rutgers.edu");
        Date date1 = new Date("2/24/2024");
        Event event1 = new Event(date1, Timeslot.AFTERNOON, Location.HLL114, contact1, 60);

        System.out.println(event.compareTo(event1));
    }
}
