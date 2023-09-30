package classes;

public class Event implements Comparable<Event> {

    /**
     * TO DO:
     * Override toString()
     * Add compareTo()

     * COMPLETED:
     * Override equals()
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
    public int compareTo(Event event) {
        return 0;
    }



}
