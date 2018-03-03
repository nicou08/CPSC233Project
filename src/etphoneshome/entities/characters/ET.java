package etphoneshome.entities.characters;

import javafx.scene.image.Image;

/**
 * This class is used as the player's class. It is a derived class from the {@code Character} class.
 */
public class ET extends Character {

    private Image entitySprite = new Image("/images/sprites/et_sprite_right.png");

    public ET() {
        this.setEntitySprite("images/sprites/et_sprite_right.png");
    }

}