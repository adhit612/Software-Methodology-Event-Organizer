package classes;

public class Event implements Comparable<Event> {
    private Date date;
    private Timeslot startTime;
    private Location location;
    private Contact contact;
    private int duration;

    public Event(Date date, Timeslot startTime, Location location, Contact contact, int duration) {

    }

    @Override
    public int compareTo(Event event) {
        return 0;
    }

}
