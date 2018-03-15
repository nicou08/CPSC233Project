package etphoneshome.entities.characters;

import etphoneshome.objects.Location;

/**
 * This class is used as the player's class. It is a derived class from the {@code Character} class.
 */
public class ET extends Character {

    public ET() {
        this.setRightEntitySprite("images/sprites/et_sprite_right.png");
        this.setLeftEntitySprite("images/sprites/et_sprite_left.png");
    }

    public ET(Location location) {
        super(location);
        this.setRightEntitySprite("images/sprites/et_sprite_right.png");
        this.setLeftEntitySprite("images/sprites/et_sprite_left.png");
    }

}