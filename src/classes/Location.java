package classes;

public enum Location {
    HIL114("Hill Center","Busch"),
    ARC103("Allison Road Classroom","Busch"),
    BE_AUD("Beck Hall","Livingston"),
    TIL232("Tillet Hall","Livingston"),
    AB2225("Academic Building","College Avenue"),
    MU302("Murray Hall","College Avenue");

    private final String building;
    private final String campus;

    Location(String building, String campus){
        this.building = building;
        this.campus = campus;
    }

    private String getBuilding(){
        return building;
    }

    private String getCampus(){
        return campus;
    }
}
