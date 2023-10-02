package classes;

public class EventCalendar {

    /**
     * TO DO:
     * implement print
     * implement printByDate()
     * implement printByCampus()
     * implement printByDepartment
     * Timeslot add toString so user can type morning, afternoon, evening

     * COMPLETED:
     *Implement constructor
     * implement find
     * implement grow
     * implement add
     * implement remove
     * implement contains
     * implement print
     * implement printByDate()
     * implement printByCampus()
     * implement printByDepartment
     * Timeslot add toString so user can type morning, afternoon, evening
     *
     */

    private Event [] events; //the array holding the list of events
    private int numEvents;
    public static final int NOT_FOUND = -1;
    public static final int ARRAY_SIZE_ADDER = 4;


    public EventCalendar(Event[] events, int numEvents) {
        this.events = events;
        this.numEvents = numEvents;
    }

    private int find(Event event) {
        //search event in the list
        for(int i = 0; i < this.events.length; i++) {
            if(this.events[i].equals(event)) {
                return i;
            }
        }
        return NOT_FOUND;
    }

    private void grow() {
        //increase capacity by 4
        Event[] newEvents = new Event[this.events.length + ARRAY_SIZE_ADDER];
        for(int i = 0; i < this.events.length; i++) {
            newEvents[i] = this.events[i];
        }
        this.events = newEvents;
    }

    public boolean add(Event event) {
        //add event to calendar, handle array resizing
        if(this.events[this.events.length-1] != null) {
            grow();
            //System.out.println("growing" + this.numEvents);
        }

        for(int i = 0; i < this.events.length; i++) {
            if(this.events[i] == null) {
                this.events[i] = event;
                this.numEvents++;
                return true;
            }
        }
        return false;
    }

    public boolean remove(Event event) {
        //remove event from calendar, handle array resizing
        //System.out.println("Length of array in remove: " + this.events.length);
        Event[] newEvents = new Event[this.events.length];
        int j = find(event);
        if(j == NOT_FOUND) {
            return false;
        }
        int k = 0;
        for(int x = 0; x < this.events.length; x++) {
           if(x == j) {
               continue;
           }
           newEvents[k] = this.events[x];
           k ++;
        }

        this.numEvents--;
        this.events = newEvents;
        return true;
    }

    public boolean contains(Event event) {
        for(int i = 0; i < this.events.length; i ++){
            if(this.events[i] == null) {
                return false;
            }
            if(this.events[i].equals(event)){
                return true;
            }
        }
        return false;
    }

    public void print() {
        //System.out.println("In Print Method " + this.events.length);
        System.out.println("* Event Calendar *");
        for(int i = 0; i < this.events.length; i++) {
            if(this.events[i] != null) {
                System.out.println(this.events[i].toString());
            }
        }
        System.out.println("* end of event calendar *");
    }

    public void printByDate() {
        System.out.println("* Event Calendar by event date and start time *");
        Event [] res = this.events;
        for(int i = 1; i < res.length; i++) {
            if(res[i] == null){
                break;
            }

            Event temp = res[i];
            int j = i - 1;
            while(j >= 0 && (res[j].getDate().compareTo(temp.getDate())) == 1) {
                res[j+1] = res[j];
                j--;
            }
            res[j+1] = temp;
        }

        for(int x = 0; x < res.length; x ++){
            if(res[x] == null){
                break;
            }
            System.out.println(res[x].toString());
        }
        System.out.println("* end of event calendar *");
    }

    public void printByCampus() {
        System.out.println("* Event Calendar by campus and building *");
        Event [] res = this.events;
        for(int i = 1; i < res.length; i++) {
            if(res[i] == null){
                break;
            }

            Event temp = res[i];
            int j = i - 1;
            while(j >= 0 && ((res[j].getLocation().getCampus()).compareTo(temp.getLocation().getCampus())) >= 1) {
                res[j+1] = res[j];
                j--;
            }
            while(j >= 0 && (res[j].getLocation().getCampus().equals(temp.getLocation().getCampus())) && ((res[j].getLocation().getBuilding()).compareTo(temp.getLocation().getBuilding())) >= 1){
                res[j+1] = res[j];
                j --;
            }
            res[j+1] = temp;
        }

        for(int x = 0; x < res.length; x ++) {
            if(res[x] == null){
                break;
            }
            System.out.println(res[x].toString());
        }

        System.out.println("* end of event calendar *");
    }

    public void printByDepartment() {
        System.out.println("* Event Calendar by department *");
        Event [] res = this.events;
        for(int i = 1; i < res.length; i++) {
            if(res[i] == null){
                break;
            }

            Event temp = res[i];
            int j = i - 1;
            while(j >= 0 && ((res[j].getContact().getDepartment().toString()).compareTo(temp.getContact().getDepartment().toString())) >= 1) {
                res[j+1] = res[j];
                j--;
            }
            res[j+1] = temp;
        }

        for(int x = 0; x < res.length; x ++) {
            if(res[x] == null){
                break;
            }
            System.out.println(res[x].toString());
        }
        System.out.println("* end of event calendar *");
    }

    public static void main(String[] args) {
        Event[] events = new Event[4];
        EventCalendar eventCalendar = new EventCalendar(events, 0);
        Date date = new Date("1/30/2023");
        Contact contact = new Contact(Department.MATH, "iti@rutgers.edu");
        Event event = new Event(date, Timeslot.EVENING, Location.HLL114, contact, 120);
        System.out.println(eventCalendar.add(event));

        Date date1 = new Date("1/30/2024");
        Contact contact1 = new Contact(Department.CS, "cs@rutgers.edu");
        Event event1 = new Event(date1, Timeslot.EVENING, Location.ARC103, contact1, 45);
        System.out.println(eventCalendar.add(event1));

        Date date2 = new Date("4/15/2024");
        Contact contact2 = new Contact(Department.BAIT, "BAIT@rutgers.edu");
        Event event2 = new Event(date2, Timeslot.EVENING, Location.TIL232, contact2, 45);
        System.out.println(eventCalendar.add(event2));

        Date date3 = new Date("5/2/2023");
        Contact contact3 = new Contact(Department.ITI, "ITI@rutgers.edu");
        Event event3 = new Event(date3, Timeslot.AFTERNOON, Location.BE_AUD, contact3, 45);
        System.out.println(eventCalendar.add(event3));

        Date date4 = new Date("12/15/2023");
        Contact contact4 = new Contact(Department.EE, "cs@rutgers.edu");
        Event event4 = new Event(date4, Timeslot.EVENING, Location.MU302, contact4, 45);
        System.out.println(eventCalendar.add(event4));

        eventCalendar.printByDate();
        System.out.println(events.length);

        System.out.println(eventCalendar.remove(event4));
        System.out.println("removed");
        System.out.println(eventCalendar.remove(event3));
        System.out.println("removed");
        //eventCalendar.print();

        //System.out.println(events[0].toString());
        //System.out.println(events[1].toString());
        //System.out.println(eventCalendar.remove(event));
        //System.out.println(eventCalendar.remove(event1));
    }

    public int getNumEvents(){
        return numEvents;
    }
}
