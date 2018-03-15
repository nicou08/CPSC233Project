package etphoneshome.entities.enemies;

import etphoneshome.entities.actor.Actor;
import etphoneshome.objects.Location;
/**
 * This is used as the parent class for the enemies. It is derived from the {@code Actor} class.
 */

public abstract class Enemy extends Actor {

    public Enemy() {
        this.setFacingRight(false);
    }

    public Enemy(Location location) {
        super(location);
        this.setFacingRight(false);
    }

    //main tests the class methods
    public static void main(String[] args)
    {
        Location initialLoc = new Location(0, 0);    //Set the enemy class to a location of 0,0 for testing
        Enemy c = new Police(initialLoc);

        c.setLocation(initialLoc);
        //test the getter and setter for isDead

        if(c.getIsDead()) //should be false originally
            System.out.println("This should not have been printed. Enemy should be alive (but is dead here)");
        else
            System.out.println("Enemy is alive. This is the correct outcome");

        c.setIsDead(true);

        if(c.getIsDead()) //should be true
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