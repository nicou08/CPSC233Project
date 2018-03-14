package etphoneshome.objects;

import javafx.embed.swing.JFXPanel;
import javafx.scene.image.Image;

/**
 * This class is responsible for structures that the player and enemy cannot pass through, or can
 * move on top of (obstacles and platforms). It acts as the parent class to the {@code Platform} class.
 */
public class Obstacle {
    JFXPanel jfxPanel = new JFXPanel(); //this is needed for the class to run since there is an image attached

    /**
     * Sprite associated with the generic {@code Obstacle}
     */
    private Image sprite = new Image("/images/sprites/genericObstacle.png");

    /**
     * Location associated with the generic {@code Obstacle}
     */
    private Location location;

    /**
     * Hitbox associated with the generic {@code Obstacle}
     */
    private Hitbox hitbox;

    public Obstacle(Obstacle obstacle) {
        this.location = obstacle.getLocation();
        this.hitbox = obstacle.getHitbox();
    }

    /**
     * Main constructor to set just the Location
     *
     * @param location Location associated with the {@code Obstacle}
     */
    public Obstacle(Location location) {
        this.location = location.clone();
    }

    /**
     * Constructor that sets the location and hitbox
     *
     * @param location Location of the {@code Obstacle}
     * @param hitbox   Hitbox of the {@code Obstacle}
     */
    public Obstacle(Location location, Hitbox hitbox) {
        this(location);

        Hitbox hitboxCopy = new Hitbox(new Location(location.getXcord(), location.getYcord()), hitbox.getHeight(), hitbox.getWidth());

        this.hitbox = hitboxCopy;
    }

    /**
     * Returns the image object associated with the {@code Obstacle}
     *
     * @return Image object associated with the {@code Obstacle}
     */
    public Image getSprite() {
        return this.sprite;
    }

    /**
     * Sets the sprite of the {@code Obstacle} to the specified image URL
     *
     * @param spriteURL The image URL of the sprite
     */
    public void setSprite(String spriteURL) {
        this.sprite = new Image(spriteURL);
    }

    /**
     * Returns the location object associated with the {@code Obstacle}
     *
     * @return Location object associated with the {@code Obstacle}
     */
    public Location getLocation() {
        Location copy = new Location(this.location.getXcord(), this.location.getYcord());

        return copy;
    }

    /**
     * Returns the Hitbox object associated with the {@code Obstacle}
     *
     * @return Hitbox object associated with the {@code Obstacle}
     */
    public Hitbox getHitbox() {
        Location locationCopy = new Location(this.location.getXcord(), this.location.getYcord());
        Hitbox hitboxCopy = new Hitbox(locationCopy, this.hitbox.getHeight(), this.hitbox.getWidth());

        return hitboxCopy;
    }

    //MAIN METHOD USED SOLELY FOR TESTING OBSTACLE
    public static void main(String[] args)
    {
        System.out.println("TESTING CONSTRUCTORS....");
        Location loc1 = new Location(100,100);
        Location loc2 = new Location(300,300);
        Location def = new Location(0,0);

        Hitbox hb1 = new Hitbox(loc1, 10, 10);
        Hitbox hb2 = new Hitbox(loc2, 25, 19);
        Hitbox hdef = new Hitbox(def, 1, 1);
        Hitbox fake = new Hitbox(def, 999, 888);

        Obstacle defaultconstructor = new Obstacle(def);

        Obstacle o1 = new Obstacle(loc1, hb1);
        Obstacle o2 = new Obstacle(loc2, hb2);

        System.out.println("CONSTRUCTOR TESTING COMPLETE!");

        Obstacle o3 = new Obstacle(def, hdef);

        o3.location = o1.getLocation();     //should pass a copy of o1.location!


        Obstacle o4 = new Obstacle(def, hdef);
        o4.location = o1.location;        //reference!

        o1.location.setXcord(998);
        o1.location.setYcord(876);

        System.out.println("TESTING ENCAPSULATION OF GETLOCATION METHOD....\n");

        System.out.println("Should be 100: " + o3.location.getXcord());
        System.out.println("Should be 100: " + o3.location.getYcord());

        System.out.println("Should be 998: " + o4.location.getXcord());
        System.out.println("Should be 876: " + o4.location.getYcord());

        System.out.println("Should be 998: " + o1.location.getXcord());
        System.out.println("Should be 876: " + o1.location.getYcord());

        System.out.println("\nLOCATION ENCAPSULATION TESTING COMPLETE!\n");

        System.out.println("TESTING ENCAPSULATION OF GETHITBOX METHOD....\n");

        o3.hitbox = o2.getHitbox(); //SHOULD BE PASSED BY VALUE!
        o4.hitbox = o2.hitbox;

        o4.hitbox = fake;
        o2.hitbox = o4.hitbox;

        System.out.println("Should be 1: " + o3.hitbox.getHeight());
        System.out.println("Should be 1: " + o3.hitbox.getWidth());
        System.out.println("Should be 999: " + o4.hitbox.getHeight());
        System.out.println("Should be 888: " + o4.hitbox.getWidth());
        System.out.println("Should be 999: " + o2.hitbox.getHeight());
        System.out.println("Should be 888: " + o2.hitbox.getWidth());

        System.out.println("\nHITBOX ENCAPSULATION COMPLETE!");

        System.out.println("\nOBSTACLE TESTING COMPLETE!");

        System.exit(1);
    }

}
