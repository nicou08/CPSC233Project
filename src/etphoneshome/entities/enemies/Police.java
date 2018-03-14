package etphoneshome.entities.enemies;

import etphoneshome.objects.Location;

/**
 * This class is used for the Police type enemy. It is a derived class from the {@code Enemy} class
 */
public class Police extends Enemy {

    public Police() {
        this.setRightEntitySprite("/images/sprites/police_sprite_right.png");
        this.setLeftEntitySprite("/images/sprites/police_sprite_left.png");
    }

    public Police(Location location) {
        super(location);
        this.setRightEntitySprite("/images/sprites/police_sprite_right.png");
        this.setLeftEntitySprite("/images/sprites/police_sprite_left.png");
    }

}
