package etphoneshome.entities.characters;

import etphoneshome.entities.actor.Actor;
import etphoneshome.objects.Location;


/**
 * This is used for a generic character (non-enemy) class. It is derived from the {@code actor} class.
 */

public abstract class Character extends Actor {

	/**
	 * booleans of {@code Character}
	 */
    private boolean isJumping, isHoldingLeft, isHoldingRight, isHoldingUp, onPlatform;
    
    /**
     * score of {@code Character}
     */
    private int score = 0;

    /**
     * empty default constructor
     */
    public Character () {}

    /**
     * Constructor with initial Location of {@code Character}
     * @param location location of {@code Character}
     */
    public Character(Location location) {
        super(location);
    }

    /**
     * return true if {@code Character is jumping}
     * @return isJumping of {@code Actor}
     */
    public boolean isJumping() {
        return isJumping;
    }

    /**
     * Sets the jumping status of {@code Character}
     * @param jumping jumping status of {@code Character} 
     */
    public void setJumping(boolean jumping) {
        isJumping = jumping;
    }

    /**
     * returns isHoldingLeft of {@code Character}
     * @return isHoldingLeft of {@code Character}
     */
    public boolean isHoldingLeft() {
        return isHoldingLeft;
    }

    /**
     * sets the isHoldingLeft of {@code Character}
     * @param holdingLeft status of user holding the left/a key
     */
    public void setHoldingLeft(boolean holdingLeft) {
        isHoldingLeft = holdingLeft;
        if (holdingLeft == true) {
            this.setFacingRight(false);
        }
    }

    /**
     * gets the isHoldingRight of {@code Character}
     * @return isHoldingRIght of {@code Character}
     */
    public boolean isHoldingRight() {
        return isHoldingRight;
    }

    /**
     * sets status of isHoldingRight of {@code Character}
     * @param holdingRight status of isHoldingRight {@code CHaracter}
     */
    public void setHoldingRight(boolean holdingRight) {
        isHoldingRight = holdingRight;
    }

    /**
     * returns isHoldingup of {@code Character}
     * @return isHoldingUp of {@code Character}
     */
    public boolean isHoldingUp() {
        return isHoldingUp;
    }

    /**
     * sets isHoldingup of {@code Character}
     * @param holdingUp status of {@code Character}
     */
    public void setHoldingUp(boolean holdingUp) {
        isHoldingUp = holdingUp;
    }

    /**
     * sets isOnPLatform of {@code Character}
     * @return isOnPlatform {@code Character}
     */
    public boolean isOnPlatform() {
        return onPlatform;
    }

    /**
     * sets setOnPlatform of {@code Character}
     * @param onPlatform new status of onPLatform of {@code Character}
     */
    public void setOnPlatform(boolean onPlatform) {
        this.onPlatform = onPlatform;
    }
    
    /**
     * adds score to the current score of {@code Character}
     * @param num score to add
     */
    public void addScore(int num) {
    	this.score += num;
    }
    
    /**
     * returns the score of {@code Character}
     * @return score of {@code Character}
     */
    public int getScore() {
    	return this.score;
    }

    /**
     * sets the score of {@code Character}
     * @param score sets the score to this amount
     */
    public void setScore(int score) {
        this.score = score;
    }

    //main tests the class methods
    public static void main(String[] args)
    {
        Character c = new ET();
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