package etphoneshome.entities.characters;

import etphoneshome.entities.actor.Actor;
import etphoneshome.objects.Location;


/**
 * This is used for a generic character (non-enemy) class. It is derived from the {@code Actor} class.
 */

public class Character extends Actor {

    private boolean isJumping, isHoldingLeft, isHoldingRight, isHoldingUp;

    public boolean isJumping() {
        return isJumping;
    }

    public void setJumping(boolean jumping) {
        isJumping = jumping;
    }

    public boolean isHoldingLeft() {
        return isHoldingLeft;
    }

    public void setHoldingLeft(boolean holdingLeft) {
        isHoldingLeft = holdingLeft;
        if (holdingLeft == true) {
            this.setFacingRight(false);
        }
    }

    public boolean isHoldingRight() {
        return isHoldingRight;
    }

    public void setHoldingRight(boolean holdingRight) {
        isHoldingRight = holdingRight;
        if (holdingRight == true) {
            this.setFacingRight(true);
        }
    }

    public boolean isHoldingUp() {
        return isHoldingUp;
    }

    public void setHoldingUp(boolean holdingUp) {
        isHoldingUp = holdingUp;
    }

    //main tests the class methods
    public static void main(String[] args)
    {
        Character c = new Character();
        //test the getter and setter for isDead

        if(c.getIsDead()) //should be false originally
            System.out.println("This should not have been printed. Character should be alive (but is dead here)");
        else
            System.out.println("Character is alive. This is the correct outcome");

        c.setIsDead(true);

        if(c.getIsDead()) //should be true
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