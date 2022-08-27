package src.main.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Team {

    private String house;
    private String keeper;
    private String seeker;
    private String[] chasers;

    private static final String POSITION_CHASER = "chaser";
    private static final String POSITION_SEEKER = "seeker";
    private static final String POSITION_KEEPER = "keeper";

    /*
     * FREQUENTLY ASKED QUESTIONS:
     * 
     * Question: the constants are final, so why can't we make them public? It's not
     * possible for the caller to update them.
     * Answer: Even if the constant is final, I prefer to expose a method instead of
     * the variable. But if you want to expose the variable, that's also correct.
     * 
     */


    // Constr
    public Team(String house, String keeper, String seeker, String[] chasers) {
        if (house.isBlank() || house.isBlank() || house == null ||
            keeper.isBlank() || keeper.isBlank() || keeper == null ||
            seeker.isBlank() || seeker.isBlank() || seeker == null ||
            chasers.length != 3) {

                throw new IllegalArgumentException("house, keeper seker or the chasers cant be empty nor null.");
        }
        this.house = house;
        this.keeper = keeper;
        this.seeker = seeker;
        this.chasers = Arrays.copyOf(chasers, chasers.length);
    }

    //Chasers has null?
    public static boolean chasersIsNull(String[] array) {
        return Arrays.stream(array).anyMatch(element -> element == null);

    }

    // Chasers is blank?
    public static boolean hasBlank(String[] array){
        return Arrays.stream(array).anyMatch(element -> element.isBlank());
    }


    // Copay const.
    public Team(Team source) {
        this.house = source.house;
        this.keeper = source.keeper;
        this.seeker = source.seeker;
        this.chasers = Arrays.copyOf(source.chasers, source.chasers.length);
    }

    // Get
    public String getHouse() {
        return house;
    }

    public String getKeeper() {
        return keeper;
    }

    public String getSeeker() {
        return seeker;
    }

    public String[] getChasers() {
        return Arrays.copyOf(chasers, chasers.length);
    }

    // Set
    public void setHouse(String house) {
        if (house.isBlank() ||house == null) {
            throw new IllegalArgumentException("Value can't be null or empty.");
        }
        this.house = house;
    }

    public void setKeeper(String keeper) {
        if (keeper.isBlank() ||keeper == null) {
            throw new IllegalArgumentException("Value can't be null or empty.");
        }
        this.keeper = keeper;
    }

    public void setSeeker(String seeker) {
        if (seeker.isBlank() ||seeker == null) {
            throw new IllegalArgumentException("Value can't be null or empty.");
        }
        this.seeker = seeker;
    }

    public void setChasers(String[] chasers) {
        if (chasers.length != 3) {
            throw new IllegalArgumentException("Chasers must contain 3 elements.");
        }
        for (int i = 0; i < chasers.length; i++) {
            if (chasers[i] == null || chasers[i].isEmpty()) {
                throw new IllegalArgumentException("Value can't be null or empty.");
            }
        }
        this.chasers = Arrays.copyOf(chasers, chasers.length);
    }

    // toString
    public String toString() {
        return "House: " + this.house + "\n" +
                "Keeper: " + this.keeper + "\n" +
                "Seeker: " + this.seeker + "\n" +
                "Chasers: " + Arrays.toString(this.chasers) + "\n";
    }

    public boolean equals(Object obj) {
        if (obj == null){
            throw new IllegalArgumentException("Object cannot be null");
        }
        if (!(obj instanceof Team)) {
            throw new IllegalArgumentException("Object is not Team object");
        }
        Team team = (Team)obj;
        return this.house.equalsIgnoreCase(team.house);
    }

    public int hashCode() {
        return Objects.hash(house, keeper, seeker, Arrays.toString(chasers));
    }

    public static String getPositionChaser() {
        return POSITION_CHASER;
    }

    public static String getPositionSeeker() {
        return POSITION_SEEKER;
    }

    public static String getPositionKeeper() {
        return POSITION_KEEPER;
    }

}
