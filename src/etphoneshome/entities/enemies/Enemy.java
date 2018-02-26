package etphoneshome.entities.enemies;

import etphoneshome.objects.Location;

/**
 * This class is used as the parent class for the various enemy characters. Using the getLocation method
 * returns a location object. Using setLocation allows you to set the enemy's location to a specified
 * location. Using getIsDead checks if this enemy is currently dead. Using setIsDead allows you to
 * set whether this enemy is currently dead.
 */
public class Enemy {

    /**
     * Status of whether this enemy is dead
     */
    private boolean isDead = false;    //Not sure if needed for text based version. Should be set to true when he gets hit

    /**
     * Location associated with the {@code Enemy}
     */
    private Location location;

    /**
     * Returns the location object associated with the {@code Enemy}
     *
     * @return The location object associated with the {@code Enemy}
     */
    public Location getLocation() {

        return this.location;
    }

    /**
     * Sets the location object associated with the {@code Enemy}
     *
     * @param newLocation The new {@code location} object
     */
    public void setLocation(Location newLocation) {

        this.location = newLocation;
    }

    /**
     * Returns the status of whether the enemy is dead
     *
     * @return The current {@code isDead} status of the enemy
     */
    public boolean getIsDead() {

        return this.isDead;
    }

    /**
     * Sets the status of whether the enemy is dead to a new status
     *
     * @param newStatus The new status of whether the {@code Enemy} is dead
     */
    public void setIsDead(boolean newStatus) {
        this.isDead = newStatus;
    }

    //main tests the class methods
    public static void main(String[] args) {
        Enemy c = new Enemy();
        c.location = new Location(0, 0);    //Set the enemy class to a location of 0,0 for testing
        //test the getter and setter for isDead

        if (c.getIsDead()) //should be false originally
            System.out.println("This should not have been printed. Enemy should be alive (but is dead here)");
        else
            System.out.println("Enemy is alive. This is the correct outcome");

        c.setIsDead(true);

        if (c.getIsDead()) //should be true
            System.out.println("Enemy is dead. This is the correct outcome.");
        else
            System.out.println("Enemy is alive. This is not the correct outcome");

        System.out.println("\n");

        //test the getter and setters for location

        Location testLoc;
        Location placeIntoTestLoc = new Location(987, 233);

        testLoc = c.getLocation();  //Sets the initial (0,0) location to the testLoc location

        System.out.println("testLoc x coordinate (should be 0): " + testLoc.getXcord());
        System.out.println("testLoc y coordinate (should be 0): " + testLoc.getYcord());

        c.setLocation(placeIntoTestLoc);    //sets location in c to the new values

        testLoc = c.getLocation();  //gets the location from c which is now changed

        System.out.println("testLoc x coordinate (should be 987): " + testLoc.getXcord());
        System.out.println("testLoc y coordinate (should be 233): " + testLoc.getYcord());

    }
}