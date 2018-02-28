package etphoneshome.entities.actor;

import etphoneshome.objects.Location;
import javafx.embed.swing.JFXPanel;
import javafx.scene.image.Image;
import etphoneshome.objects.Velocity;

/**
 * This class is used as the parent class for the {@code Character} and the {@code Enemy}. Using the getLocation method
 * returns a location object. Using setLocation allows you to set the actor's location to a specified
 * location. Using getIsDead checks if this actor is currently dead. Using setIsDead allows you to
 * set whether this actor is currently dead. Using getHealth returns the current health of the {@code Actor}.
 * Using setHealth allows you to set the health of the {@code Actor} and updates isDead status accordingly.
 * Using takeSinglePointOfDamage applies a single point of damage to the {@code Actor} health and updates isDead
 * status accordingly. Using getEntitySprite returns the image object of the {@code Actor}. Using setEntitySprite allows
 * you to change the image object of the {@code Actor} using a URL/file path address.
 */
public class Actor {
    JFXPanel jfxPanel = new JFXPanel(); //this is needed for the class to run since there is an image attached
    /**
     * Status of whether this actor is dead
     */
    private boolean isDead = false;    //Should be set to true if the actor dies

    /**
     * Location associated with the {@code Actor}
     */
    private Location location = new Location(0, 0);

    /**
     * Amount of lives/health associated with the {@code Actor}
     */
    private int health = 1;     //can be used for both characters and enemies. A default value of 1 is given

    /**
     * A placeholder image associated with a generic {@code Actor}
     */
    private Image entitySprite = new Image("/images/sprites/PlaceholderSpriteTexture80X120.jpg");

    /**
     * The velocity object associated with the {@code Actor} with the default values of 0
     */
    private Velocity actorVelocity = new Velocity();

    /**
     * Returns the location object associated with the {@code Actor}
     *
     * @return The location object associated with the {@code Actor}
     */
    public Location getLocation() {
        return this.location;
    }

    /**
     * Sets the location object associated with the {@code Actor}
     *
     * @param newLocation The new {@code location} object
     */
    public void setLocation(Location newLocation) {
        this.location = newLocation;
    }

    /**
     * Returns the status of whether the actor is dead
     *
     * @return The current {@code isDead} status of the actor
     */
    public boolean getIsDead() {     //true means the actor is dead
        return this.isDead;
    }

    /**
     * Sets the status of whether the actor is dead to a new status
     *
     * @param newStatus The new status of whether the {@code Actor} is dead
     */
    public void setIsDead(boolean newStatus)        //true means the actor is dead
    {
        this.isDead = newStatus;
    }

    /**
     * Returns the current health amount of the {@code Actor}
     *
     * @return The current {@code health} of the {@code Actor}
     */
    public int getHealth() {
        return this.health;
    }

    /**
     * Sets the health of the {@code Actor} to a new amount which must be non negative.
     *
     * @param newHealth The new {@code health} amount for the {@code Actor}
     */
    public void setHealth(int newHealth)    //This could be useful for instantly killing an enemy, setting initial-
    {                                       //-health or if we implement healing in the future
        if (newHealth >= 0)      //we can only have non negative health
            this.health = newHealth;
        else
            System.out.println("Health cannot be negative! Heatlh is unchanged");

        if (this.getHealth() <= 0)
            this.setIsDead(true);
        else
            this.setIsDead(false);
    }

    /**
     * Applies 1 point of damage to the {@code Actor} and checks if the actor is dead
     */
    public void takeSinglePointOfDamage() {
        this.health = this.health - 1;

        if (this.health <= 0)    //indicates the actor is dead
            setIsDead(true);
        else
            setIsDead(false);
    }


    /**
     * Returns the image/sprite object associated with the {@code Actor}
     *
     * @return The image/sprite object associated with the {@code Actor}
     */
    public Image getEntitySprite() {
        return this.entitySprite;
    }

    /**
     * Assigns a new image/sprite to the {@code Actor}
     *
     * @param newSpriteURL The URL/file address of the new imagee/sprite
     */
    public void setEntitySprite(String newSpriteURL) {
        this.entitySprite = new Image(newSpriteURL);
    }

    //main tests the class methods
    public static void main(String[] args) {
        Actor a = new Actor();
        //test the getter and setter for isDead

        if (a.getIsDead()) //should be false originally
            System.out.println("This should not have been printed. Actor should be alive (but is dead here)");
        else
            System.out.println("Actor is alive. This is the correct outcome");

        a.setIsDead(true);

        if (a.getIsDead()) //should be true
            System.out.println("Actor is dead. This is the correct outcome.");
        else
            System.out.println("Actor is alive. This is not the correct outcome");

        System.out.println("\n");

        //test the getter and setters for location

        Location testLoc;
        Location placeIntoTestLoc = new Location(153, 238);

        testLoc = a.getLocation();  //Sets the initial (0,0) location to the testLoc location

        System.out.println("testLoc x coordinate (should be 0): " + testLoc.getXcord());
        System.out.println("testLoc y coordinate (should be 0): " + testLoc.getYcord());

        a.setLocation(placeIntoTestLoc);    //sets location in a to the new values

        testLoc = a.getLocation();  //gets the location from a which is now changed

        System.out.println("testLoc x coordinate (should be 153): " + testLoc.getXcord());
        System.out.println("testLoc y coordinate (should be 238): " + testLoc.getYcord());

        //tests for health, getHealth, setHealth and takeSinglePointOfDamage
        System.out.println("Testing health. Should be 1: " + a.getHealth());
        a.setHealth(8);
        System.out.println("Testing health. Should be 8: " + a.getHealth());
        a.takeSinglePointOfDamage();
        a.takeSinglePointOfDamage();
        a.takeSinglePointOfDamage();
        System.out.println("Testing health. Should be 5: " + a.getHealth());
        a.setHealth(-1);
        System.out.println("Testing health. Should be 5:" + a.getHealth());
        a.setHealth(1);
        System.out.println("Testing health. Should be 1:" + a.getHealth());
        System.out.println("Should be false: " + a.getIsDead());
        a.setHealth(0);
        System.out.println("Testing health. Should be 0:" + a.getHealth());
        System.out.println("Should be true: " + a.getIsDead());
        a.setHealth(1);
        System.out.println("Testing health. Should be 1:" + a.getHealth());
        System.out.println("Should be false: " + a.getIsDead());
        a.setHealth(-1);
        System.out.println("Testing health. Should be 1:" + a.getHealth());
        System.out.println("Should be false: " + a.getIsDead());
        a.takeSinglePointOfDamage();
        System.out.println("Testing health. Should be 0:" + a.getHealth());
        System.out.println("Should be true: " + a.getIsDead());

        //entitySprite methods can't be tested until we setup the display window

        System.out.println("Testing velocity. Should be 0.0: " + a.actorVelocity.getHorizontalVelocity());
        System.out.println("Testing velocity. Should be 0.0: " + a.actorVelocity.getVerticalVelocity());

        a.actorVelocity.setHorizontalVelocity(200);
        a.actorVelocity.setVerticalVelocity(-125);

        System.out.println("Testing velocity. Should be 200.0: " + a.actorVelocity.getHorizontalVelocity());
        System.out.println("Testing velocity. Should be -125.0: " + a.actorVelocity.getVerticalVelocity());

        a.actorVelocity.changeHorizontalVelocity(-250);
        a.actorVelocity.changeVerticalVelocity(126);

        System.out.println("Testing velocity. Should be -50.0: " + a.actorVelocity.getHorizontalVelocity());
        System.out.println("Testing velocity. Should be 1.0: " + a.actorVelocity.getVerticalVelocity());

    }
}