package etphoneshome.listeners;

import etphoneshome.UILauncher;
import etphoneshome.entities.characters.Character;
import etphoneshome.managers.*;
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
     * Stores levelManager used to check if level is complete
     */
    private LevelManager levelManager;

    /**
     * Used to update the velocity of flasks (gravity)
     */
    private FlaskManager flaskManager;

    /**
     * Used to update direction of character animation
     */
    private AnimationManager animationManager;

    /**
     * Constructor for the class
     *
     * @param character         gives InputListener the character associated with InputListener
     * @param backgroundManager stores the backgroundManager in order to use it later
     * @param gameManager       stores the gameManager object used to check the ground level
     */
    public InputListener(Character character, BackgroundManager backgroundManager, GameManager gameManager, LevelManager levelManager, FlaskManager flaskManager, AnimationManager animationManager) {
        this.character = character;
        this.backgroundManager = backgroundManager;
        this.gameManager = gameManager;
        this.levelManager = levelManager;
        this.flaskManager = flaskManager;
        this.animationManager = animationManager;
    }

    /**
     * Sets the character of {@code InputListener}
     *
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
                if (!levelManager.isLevelComplete()) {
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
                if (!levelManager.isLevelComplete()) {
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
            }
        };
    }

    /**
     * Update the character's velocity based on the tick of the game
     */
    public void updateVelocities() {
        Velocity velocity = this.character.getVelocity();

        //gets direction the character is facing
        if (velocity.getHorizontalVelocity() > 0) {
            if (!this.character.isFacingRight()) {
                System.out.println("change from left to right");
                animationManager.flipCharacterAnimationFrames(Direction.EAST);
            }
            this.character.setFacingRight(true);
        } else if (velocity.getHorizontalVelocity() < 0) {
            if (this.character.isFacingRight()) {
                System.out.println("change from right to left");
                animationManager.flipCharacterAnimationFrames(Direction.WEST);
            }
            this.character.setFacingRight(false);
        }

        if (this.character.isHoldingRight() && velocity.getHorizontalVelocity() >= 0 && velocity.getHorizontalVelocity() <= 10) {
            //changes velocity if moving right
            velocity.changeHorizontalVelocity(1);
        } else if (!this.character.isHoldingRight() && velocity.getHorizontalVelocity() > 0) {
            double newVelocity = velocity.getHorizontalVelocity() - 1 < 0 ? 0 : velocity.getHorizontalVelocity() - 1;
            velocity.setHorizontalVelocity(newVelocity);
        }

        if (this.character.isHoldingLeft() && velocity.getHorizontalVelocity() <= 0 && velocity.getHorizontalVelocity() >= -10) {
            //changes velocity is moving left
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
        this.updateFlaskVelocities();

    }

    public void updateFlaskVelocities() {
        for (Flask flask : this.flaskManager.getFlaskList()) {
            flask.getVelocity().changeVerticalVelocity(1);
        }
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

        //return true if on ground level
        if (this.character.getLocation().getYcord() >= this.gameManager.getGroundLevel(this.character)) {
            this.character.setLocation(new Location(this.character.getLocation().getXcord(), this.gameManager.getGroundLevel(this.character)));
            return true;
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
        }

        if (this.character.isOnPlatform()) {
            this.character.setOnPlatform(false);
            this.character.setJumping(true);
        }
        return false;
    }
}



