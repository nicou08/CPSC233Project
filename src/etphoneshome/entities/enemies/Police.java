package etphoneshome.entities.enemies;

/**
 * This class is used for the Police type enemy. It is a derived class from the {@code Enemy} class
 */
public class Police extends Enemy {

    public Police() {
        super();
        this.setRightEntitySprite("/images/sprites/police_sprite_right.png");
        this.setLeftEntitySprite("/images/sprites/police_sprite_left.png");
    }

}
