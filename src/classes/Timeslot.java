package classes;

/**
 * We need it so that when the user types morning, the system reads it as Timeslot.MORNING, so on and so forth
 * Right now the EvenCalendar class manually needs Timeslot.MORNING when creating an event
 */
public enum Timeslot {

    MORNING(10,30),
    AFTERNOON(2,0),
    EVENING(6,30);

    private final int hours;
    private final int mins;

    Timeslot(int hours, int mins){
        this.hours = hours;
        this.mins = mins;
    }

    private int getHours(){
        return hours;
    }

    private int getMins(){
        return mins;
    }
}
