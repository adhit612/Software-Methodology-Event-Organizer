package classes;

/**
 * Location enum to declare a Location.
 * Contains building and campus name properties.
 * Will be used in Event as one of the properties.
 * @author Abhishek Thakare, Adhit Thakur
 */
public enum Location {
    HLL114("Hill Center", "Busch"),
    ARC103("Allison Road Classroom", "Busch"),
    BE_AUD("Beck Hall", "Livingston"),
    TIL232("Tillet Hall", "Livingston"),
    AB2225("Academic Building", "College Avenue"),
    MU302("Murray Hall", "College Avenue");

    private final String building;
    private final String campus;

    /**
     * Declare constructor for the Location enum.
     * Given the building and campus Strings, perform a hard copy.
     * @param building The building String to be added to Location.
     * @param campus The campus String to be added ot Location.
     */
    Location(String building, String campus) {
        this.building = building;
        this.campus = campus;
    }

    /**
     * Return the building Location component.
     * @return the String building component.
     */
    public String getBuilding() {
        return building;
    }

    /**
     * Return the campus Location component.
     * @return the String campus component.
     */
    public String getCampus() {
        return campus;
    }
}
