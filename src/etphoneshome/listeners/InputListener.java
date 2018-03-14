package etphoneshome.listeners;

import etphoneshome.UILauncher;
import etphoneshome.entities.characters.Character;
import etphoneshome.managers.BackgroundManager;
import etphoneshome.objects.Hitbox;
import etphoneshome.objects.Obstacle;
import etphoneshome.objects.Platform;
import etphoneshome.objects.Velocity;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

/**
 * This class gets input from the user and updates the character based on that input
 */

public class InputListener {

    /**
     * character associated with {@code InputLIstener}
     */
    private Character character;

    /**
     * Stores backgroundManager to be used to update background velocity/location
     */
    private BackgroundManager backgroundManager;

    /**
     * Constructor for the class
     *
     * @param character gives InputListener the character associated with InputListener
     */
    public InputListener(Character character, BackgroundManager backgroundManager) {
        this.character = character;
        this.backgroundManager = backgroundManager;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public static void main(String[] args) {
        // add new testing code
    }


    /**
     * Generate {@code EventHandler} for the key pressed down event
     *
     * @return {@code EventHandler} for the key pressed down event
     */
    public EventHandler<KeyEvent> getKeyPressedEvent() {

        return new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                String input = e.getText().toLowerCase();
                if (input.equals("w") || input.equals("up")) {
                    character.setHoldingUp(true);
                }
                if (input.equals("a") || input.equals("left")) {
                    character.setHoldingLeft(true);
                }
                if (input.equals("d") || input.equals("right")) {
                    character.setHoldingRight(true);
                }
            }
        };
    }

    /**
     * Generate {@code EventHandler} for the key released event
     *
     * @return {@code EventHandler} for the key released event
     */
    public EventHandler<KeyEvent> getKeyReleasedEvent() {

        return new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                String input = e.getText().toLowerCase();
                if (input.equals("w") || input.equals("up")) {
                    character.setHoldingUp(false);
                }
                if (input.equals("a") || input.equals("left")) {
                    character.setHoldingLeft(false);
                }
                if (input.equals("d") || input.equals("right")) {
                    character.setHoldingRight(false);

                }
            }
        };
    }

    /**
     * Update the character's velocity based on the tick of the game
     */
    public void updateVelocities() {
        Velocity velocity = character.getVelocity();
        if (velocity.getHorizontalVelocity() > 0) {
            character.setFacingRight(true);
        } else if (velocity.getHorizontalVelocity() < 0) {
            character.setFacingRight(false);
        }

        if (character.isHoldingRight() && velocity.getHorizontalVelocity() >= 0 && velocity.getHorizontalVelocity() <= 10) {
            velocity.changeHorizontalVelocity(1);
        } else if (!character.isHoldingRight() && velocity.getHorizontalVelocity() > 0) {
            double newVelocity = velocity.getHorizontalVelocity() - 1 < 0 ? 0 : velocity.getHorizontalVelocity() - 1;
            velocity.setHorizontalVelocity(newVelocity);
        }

        if (character.isHoldingLeft() && velocity.getHorizontalVelocity() <= 0 && velocity.getHorizontalVelocity() >= -10) {
            velocity.changeHorizontalVelocity(-1);
        } else if (!character.isHoldingLeft() && velocity.getHorizontalVelocity() < 0) {
            double newVelocity = velocity.getHorizontalVelocity() + 1 > 0 ? 0 : velocity.getHorizontalVelocity() + 1;
            velocity.setHorizontalVelocity(newVelocity);
        }

        // gravity
        if (character.isJumping()) {
            velocity.changeVerticalVelocity(1);
        }

        if (character.isHoldingUp() && !character.isJumping()) {
            character.setJumping(true);
            velocity.setVerticalVelocity(-20);
        }

        this.updateBackgroundVelocity();

    }

    public void updateBackgroundVelocity() {
        backgroundManager.getBackgroundVelocity().setHorizontalVelocity(character.getVelocity().getHorizontalVelocity() / -2.0);
    }


    /**
     * Checks if player is on the ground or not
     *
     * @return whether the player is on the ground or not
     */
    public boolean onGround() {
        if (character.getLocation().getYcord() > UILauncher.getGraphicsRepainter().HEIGHT - 100 - character.getRightEntitySprite().getHeight()) {
            return true;
        } else {
            int height = (int) character.getRightEntitySprite().getHeight();
            int width = (int) character.getRightEntitySprite().getWidth();
            Hitbox testCharacterHitbox = new Hitbox(character.getLocation().clone().addY(3), height, width);
            for (Obstacle obstacle : UILauncher.getObstacleManager().getObstacleList()) {
                if (obstacle instanceof Platform) {
                    Platform platform = (Platform) obstacle;
                    for (Obstacle brick : platform.getBricks()) {
                        if (testCharacterHitbox.areColliding(platform.getHitbox())) {
                            character.setOnPlatform(true);
                            return true;
                        }
                    }
                } else {
                    if (testCharacterHitbox.areColliding(obstacle.getHitbox())) {
                        character.setOnPlatform(true);
                        return true;
                    }
                }
            }
            if (character.isOnPlatform()) {
                character.setOnPlatform(false);
                character.setJumping(true);
            }
            return false;
        }
    }
}


