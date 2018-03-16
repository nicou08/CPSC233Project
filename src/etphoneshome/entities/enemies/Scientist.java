package etphoneshome.entities.enemies;

import etphoneshome.objects.Location;

/**
 * This class is used for the Scientist type enemy. It is a derived class from the {@code Enemy} class
 */
public class Scientist extends Enemy {

	/**
	 * default constructor that sets the sprites of {@code Scientist}
	 */
    public Scientist() {
        this.setRightEntitySprite("/images/sprites/scientist_sprite_right.png");
        this.setLeftEntitySprite("/images/sprites/scientist_sprite_left.png");
    }
    /**
     * constructor thats sets location and sprites of {@code Scientist}
     * @param location starting location of {@code Scientist}
     */
    public Scientist(Location location) {
        super(location);
        this.setRightEntitySprite("/images/sprites/scientist_sprite_right.png");
        this.setLeftEntitySprite("/images/sprites/scientist_sprite_left.png");
    }

}
