package etphoneshome.entities.enemies;

/**
 * This class is used for the Scientist type enemy. It is a derived class from the {@code Enemy} class
 */
public class Scientist extends Enemy {

    public Scientist() {
        super();
        this.setRightEntitySprite("/images/sprites/scientist_sprite_right.png");
        this.setLeftEntitySprite("/images/sprites/scientist_sprite_left.png");
    }

}
