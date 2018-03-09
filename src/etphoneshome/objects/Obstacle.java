package etphoneshome.objects;

import javafx.embed.swing.JFXPanel;
import javafx.scene.image.Image;

/**
 * This class is responsible for structures that the player and enemy cannot pass through, or can
 * move on top of (obstacles and platforms). It acts as the parent class to the {@code Platform} class.
 */
public class Obstacle
{
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

    /**
     * Default constructor
     */
    public Obstacle()
    {

    }

    /**
     * Main constructor to set just the Location
     * @param location Location associated with the {@code Obstacle}
     */
    public Obstacle(Location location)
    {
        this.location = location;
    }

    /**
     * Constructor that sets the location and hitbox
     * @param location Location of the {@code Obstacle}
     * @param hitbox Hitbox of the {@code Obstacle}
     */
    public Obstacle(Location location, Hitbox hitbox)
    {
        this.location = location;
        this.hitbox = hitbox;
    }

    /**
     * Returns the image object associated with the {@code Obstacle}
     * @return Image object associated with the {@code Obstacle}
     */
    public Image getSprite()
    {
        return this.sprite;
    }

    /**
     * Sets the sprite of the {@code Obstacle} to the specified image URL
     * @param spriteURL The image URL of the sprite
     */
    public void setSprite(String spriteURL)
    {
        this.sprite = new Image(spriteURL);
    }

    /**
     * Returns the location object associated with the {@code Obstacle}
     * @return Location object associated with the {@code Obstacle}
     */
    public Location getLocation()
    {
        return this.location;
    }

    /**
     * Returns the Hitbox object associated with the {@code Obstacle}
     * @return Hitbox object associated with the {@code Obstacle}
     */
    public Hitbox getHitbox()
    {
        return this.hitbox;
    }


}
