package etphoneshome.entities.actor;

import etphoneshome.objects.Location;

/**
 * This class is used as the parent class for the {@code Character} and the {@code Enemy}. Using the getLocation method
 * returns a location object. Using setLocation allows you to set the actor's location to a specified
 * location. Using getIsDead checks if this actor is currently dead. Using setIsDead allows you to
 * set whether this actor is currently dead.
 */
public class Actor
{

    /**
     * Status of whether this actor is dead
     */
    private boolean isDead = false;	//Should be set to true if the actor dies

    /**
     * Location associated with the {@code Actor}
     */
    private Location location = new Location(0, 0);

    /**
     * Returns the location object associated with the {@code Actor}
     * @return The location object associated with the {@code Actor}
     */
    public Location getLocation()
    {

        return this.location;
    }

    /**
     * Sets the location object associated with the {@code Actor}
     * @param newLocation The new {@code location} object
     */
    public void setLocation(Location newLocation)
    {

        this.location = newLocation;
    }

    /**
     * Returns the status of whether the actor is dead
     * @return The current {@code isDead} status of the actor
     */
    public boolean getIsDead()
    {

        return this.isDead;
    }

    /**
     * Sets the status of whether the actor is dead to a new status
     * @param newStatus The new status of whether the {@code Actor} is dead
     */
    public void setIsDead(boolean newStatus)
    {

        this.isDead = newStatus;
    }

    //main tests the class methods
    public static void main(String[] args)
    {
        Actor a = new Actor();
        //test the getter and setter for isDead

        if(a.getIsDead()) //should be false originally
            System.out.println("This should not have been printed. Actor should be alive (but is dead here)");
        else
            System.out.println("Actor is alive. This is the correct outcome");

        a.setIsDead(true);

        if(a.getIsDead()) //should be true
            System.out.println("Actor is dead. This is the correct outcome.");
        else
            System.out.println("Actor is alive. This is not the correct outcome");

        System.out.println("\n");

        //test the getter and setters for location

        Location testLoc;
        Location placeIntoTestLoc = new Location(123, 234);

        testLoc = a.getLocation();  //Sets the initial (0,0) location to the testLoc location

        System.out.println("testLoc x coordinate (should be 0): " + testLoc.getXcord());
        System.out.println("testLoc y coordinate (should be 0): " + testLoc.getYcord());

        a.setLocation(placeIntoTestLoc);    //sets location in a to the new values

        testLoc = a.getLocation();  //gets the location from a which is now changed

        System.out.println("testLoc x coordinate (should be 123): " + testLoc.getXcord());
        System.out.println("testLoc y coordinate (should be 234): " + testLoc.getYcord());

    }
}