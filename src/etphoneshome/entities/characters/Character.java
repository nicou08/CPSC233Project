package etphoneshome.entities.characters;

import etphoneshome.objects.Location;

/**
 * This class is used as the parent class for the player character and any non-enemy characters. Using the
 * getLocation method returns a location object. Using setLocation allows you to set the character's
 * location to a specified location. Using getIsDead checks if this character is currently dead. Using
 * setIsDead allows you to set whether this character is currently dead.
 */

public class Character {

    /**
     * Status of whether this character is dead
     */
    private boolean isDead = false;    //Should be set to true if the character dies

    /**
     * Location associated with the {@code Character}
     */
    private Location location = new Location(0, 0);

    /**
     * Returns the location object associated with the {@code Character}
     *
     * @return The location object associated with the {@code Character}
     */
    public Location getLocation() {

        return this.location;
    }

    /**
     * Sets the location object associated with the {@code Character}
     *
     * @param newLocation The new {@code location} object
     */
    public void setLocation(Location newLocation) {

        this.location = newLocation;
    }

    /**
     * Returns the status of whether the character is dead
     *
     * @return The current {@code isDead} status of the character
     */
    public boolean getIsDead() {

        return this.isDead;
    }

    /**
     * Sets the status of whether the character is dead to a new status
     *
     * @param newStatus The new status of whether the {@code Character} is dead
     */
    public void setIsDead(boolean newStatus) {

        this.isDead = newStatus;
    }

    //main tests the class methods
    public static void main(String[] args) {
        Character c = new Character();
        //test the getter and setter for isDead

        if (c.getIsDead()) //should be false originally
            System.out.println("This should not have been printed. Character should be alive (but is dead here)");
        else
            System.out.println("Character is alive. This is the correct outcome");

        c.setIsDead(true);

        if (c.getIsDead()) //should be true
            System.out.println("Character is dead. This is the correct outcome.");
        else
            System.out.println("Character is alive. This is not the correct outcome");

        System.out.println("\n");

        //test the getter and setters for location

        Location testLoc;
        Location placeIntoTestLoc = new Location(123, 234);

        testLoc = c.getLocation();  //Sets the initial (0,0) location to the testLoc location

        System.out.println("testLoc x coordinate (should be 0): " + testLoc.getXcord());
        System.out.println("testLoc y coordinate (should be 0): " + testLoc.getYcord());

        c.setLocation(placeIntoTestLoc);    //sets location in c to the new values

        testLoc = c.getLocation();  //gets the location from c which is now changed

        System.out.println("testLoc x coordinate (should be 123): " + testLoc.getXcord());
        System.out.println("testLoc y coordinate (should be 234): " + testLoc.getYcord());

    }
}