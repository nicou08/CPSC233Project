package etphoneshome.entities.enemies;

import etphoneshome.objects.Location;
import etphoneshome.objects.SpriteURL;

/**
 * This class is used for the Scientist type enemy. It is a derived class from the {@code Enemy} class
 */
public class Scientist extends Enemy {

	/**
	 * default constructor that sets the sprites of {@code Scientist}
	 */
    public Scientist() {
        this.setRightEntitySprite(SpriteURL.SCIENTIST_RIGHT.getPath());
        this.setLeftEntitySprite(SpriteURL.SCIENTIST_LEFT.getPath());
    }
    /**
     * constructor thats sets location and sprites of {@code Scientist}
     * @param location starting location of {@code Scientist}
     */
    public Scientist(Location location) {
        super(location);
        this.setRightEntitySprite(SpriteURL.SCIENTIST_RIGHT.getPath());
        this.setLeftEntitySprite(SpriteURL.SCIENTIST_LEFT.getPath());
    }

}
