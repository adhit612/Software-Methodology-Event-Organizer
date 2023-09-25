package classes;

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
