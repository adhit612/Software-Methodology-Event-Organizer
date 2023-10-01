package classes;

import java.sql.Time;

public class Event implements Comparable<Event> {

    /**
     * TO DO:

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

    @Override
    public String toString() {
        return "[Event Date: " + this.date.toString() + "] [Start: " + this.startTime + "] [End: " + this.startTime + duration + "] @" + this.location + "(" + this.location.getBuilding() + "," + this.location.getCampus() + ") [Contact: " + this.contact.getDepartment() + "," + this.contact.getEmail() + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Event){
            Event event = (Event)obj;
            return event.date.equals(this.date) && event.startTime.equals(this.startTime)
                    && event.location.equals(this.location) && event.contact.equals(this.contact);
        }
        return false;
    }

    @Override
    public int compareTo(Event event){
        if(this.date.compareTo(event.date) == -1){ //this is earlier than event
            return -1;
        }
        else if(this.date.compareTo(event.date) == 1){ //this is later than event
            return 1;
        }
        else{ //equal dates
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
}
