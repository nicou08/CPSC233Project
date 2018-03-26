package etphoneshome.entities.enemies;

import etphoneshome.objects.Location;
import etphoneshome.graphics.SpriteURL;

/**
 * This class is used for the Police type enemy. It is a derived class from the {@code Enemy} class
 */
public class Police extends Enemy {

	/**
	 * default constructor that sets the sprites of {@code Police}
	 */
    public Police() {
        this.setRightEntitySprite(SpriteURL.POLICE_RIGHT.getPath());
        this.setLeftEntitySprite(SpriteURL.POLICE_LEFT.getPath());
    }

    public Police(Police police) {
        super(police);
        this.setRightEntitySprite(SpriteURL.POLICE_RIGHT.getPath());
        this.setLeftEntitySprite(SpriteURL.POLICE_LEFT.getPath());
    }

    /**
     * constructor thats sets location and sprites of {@code Police}
     * @param location starting location of {@code Police}
     */
    public Police(Location location) {
        super(location);
        this.setRightEntitySprite(SpriteURL.POLICE_RIGHT.getPath());
        this.setLeftEntitySprite(SpriteURL.POLICE_LEFT.getPath());
    }

}
