package etphoneshome.entities.characters;

import etphoneshome.objects.Location;

/**
 * This class is used as the player's class. It is a derived class from the {@code Character} class.
 */
public class ET extends Character {

	/**
	 * default constructor that sets the left and right sprites of {@code ET}
	 */
    public ET() {
        this.setRightEntitySprite("images/sprites/et_sprite_right.png");
        this.setLeftEntitySprite("images/sprites/et_sprite_left.png");
    }

    /**
     * Constructor that sets initial Location and sprites of {@code ET}
     * @param location starting location of {@code Character}
     */
    public ET(Location location) {
        super(location);
        this.setRightEntitySprite("images/sprites/et_sprite_right.png");
        this.setLeftEntitySprite("images/sprites/et_sprite_left.png");
    }

}