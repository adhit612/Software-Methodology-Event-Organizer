package classes;

public enum Location {
    ONEONEFOUR("Hill Center","Busch"),
    ONEZEROTHREE("Allison Classroom","Busch"),
    AUD("Beck Hall","Livingston"),
    TWOTHREETWO("Tillet Hall","Livingston"),
    TWOTWOTWOFIVE("Academic Building","College Avenue"),
    THREEZEROTWO("Murray Hall","College Avenue");

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
