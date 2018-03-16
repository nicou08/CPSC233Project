package etphoneshome.listeners;

import etphoneshome.UILauncher;
import etphoneshome.entities.characters.Character;
import etphoneshome.managers.BackgroundManager;
import etphoneshome.managers.GameManager;
import etphoneshome.objects.*;
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
     * Stores gameManager to be used to calculate ground level
     */
    private GameManager gameManager;

    /**
     * Constructor for the class
     *
     * @param character gives InputListener the character associated with InputListener
     * @param backgroundManager stores the backgroundManager in order to use it later
     */
    public InputListener(Character character, BackgroundManager backgroundManager, GameManager gameManager) {
        this.character = character;
        this.backgroundManager = backgroundManager;
        this.gameManager = gameManager;
    }

    /**
     * sets the character of {@code InputListener}
     * @param character character of {@code InputListener}
     */
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
                if (input.equals("w")) {
                    character.setHoldingUp(false);
                }
                if (input.equals("a")) {
                    character.setHoldingLeft(false);
                }
                if (input.equals("d")) {
                    character.setHoldingRight(false);

                }
            }
        };
    }

    /**
     * Update the character's velocity based on the tick of the game
     */
    public void updateVelocities() {
<<<<<<< HEAD
        Velocity velocity = this.character.getVelocity();
=======
    	
    	//gets direction the character is facing
        Velocity velocity = character.getVelocity();
>>>>>>> 16b754056efdea956b328a4ce79b12b7cd7a267d
        if (velocity.getHorizontalVelocity() > 0) {
            this.character.setFacingRight(true);
        } else if (velocity.getHorizontalVelocity() < 0) {
            this.character.setFacingRight(false);
        }

<<<<<<< HEAD
        if (this.character.isHoldingRight() && velocity.getHorizontalVelocity() >= 0 && velocity.getHorizontalVelocity() <= 10) {
=======
        //changes velocity if moving right
        if (character.isHoldingRight() && velocity.getHorizontalVelocity() >= 0 && velocity.getHorizontalVelocity() <= 10) {
>>>>>>> 16b754056efdea956b328a4ce79b12b7cd7a267d
            velocity.changeHorizontalVelocity(1);
        } else if (!this.character.isHoldingRight() && velocity.getHorizontalVelocity() > 0) {
            double newVelocity = velocity.getHorizontalVelocity() - 1 < 0 ? 0 : velocity.getHorizontalVelocity() - 1;
            velocity.setHorizontalVelocity(newVelocity);
        }

<<<<<<< HEAD
        if (this.character.isHoldingLeft() && velocity.getHorizontalVelocity() <= 0 && velocity.getHorizontalVelocity() >= -10) {
=======
        //changes velocity is moving left
        if (character.isHoldingLeft() && velocity.getHorizontalVelocity() <= 0 && velocity.getHorizontalVelocity() >= -10) {
>>>>>>> 16b754056efdea956b328a4ce79b12b7cd7a267d
            velocity.changeHorizontalVelocity(-1);
        } else if (!this.character.isHoldingLeft() && velocity.getHorizontalVelocity() < 0) {
            double newVelocity = velocity.getHorizontalVelocity() + 1 > 0 ? 0 : velocity.getHorizontalVelocity() + 1;
            velocity.setHorizontalVelocity(newVelocity);
        }

        // gravity
        if (this.character.isJumping()) {
            velocity.changeVerticalVelocity(1);
        }

        if (this.character.isHoldingUp() && !character.isJumping()) {
            this.character.setJumping(true);
            velocity.setVerticalVelocity(-20);
        }

        this.updateBackgroundVelocity();

    }

    /**
     * moves the background of the stage
     */
    public void updateBackgroundVelocity() {
        backgroundManager.getBackgroundVelocity().setHorizontalVelocity(this.character.getVelocity().getHorizontalVelocity() / -2.0);
    }

    /**
     * Checks if player is on the ground or not
     *
     * @return whether the player is on the ground or not
     */
    public boolean onGround() {
<<<<<<< HEAD
        if (this.character.getLocation().getYcord() >= this.gameManager.getGroundLevel(this.character)) {
            this.character.setLocation(new Location(this.character.getLocation().getXcord(), this.gameManager.getGroundLevel(this.character)));
=======
    	//return true if on ground level
        if (character.getLocation().getYcord() > UILauncher.getGraphicsRepainter().HEIGHT - 100 - character.getRightEntitySprite().getHeight()) {
>>>>>>> 16b754056efdea956b328a4ce79b12b7cd7a267d
            return true;
         
            //return true if on a platform
        } else {
            int height = (int) this.character.getRightEntitySprite().getHeight();
            int width = (int) this.character.getRightEntitySprite().getWidth();
            Hitbox testCharacterHitbox = new Hitbox(new Location(this.character.getLocation().getXcord(), this.character.getLocation().getYcord() + 3), height, width);
            for (Obstacle obstacle : UILauncher.getObstacleManager().getObstacleList()) {
                if (obstacle instanceof Platform) {
                    Platform platform = (Platform) obstacle;
                        if (testCharacterHitbox.areColliding(platform.getHitbox())) {
                            this.character.setOnPlatform(true);
                            return true;
                        }
                } else {
                    if (testCharacterHitbox.areColliding(obstacle.getHitbox())) {
                        this.character.setOnPlatform(true);
                        return true;
                    }
                }
            }
            if (this.character.isOnPlatform()) {
                this.character.setOnPlatform(false);
                this.character.setJumping(true);
            }
            return false;
        }
    }
}


