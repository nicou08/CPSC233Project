package etphoneshome.managers;

import etphoneshome.UILauncher;
import etphoneshome.entities.actor.Actor;
import etphoneshome.entities.characters.Character;
import etphoneshome.entities.characters.ET;
import etphoneshome.entities.enemies.Enemy;
import etphoneshome.graphics.GraphicsRepainter;
import etphoneshome.listeners.InputListener;
import etphoneshome.objects.Hitbox;
import etphoneshome.objects.Velocity;

/**
 * Class in charge of all checks within the game, checkpoints, R+P pickups, enemy kills, character damage, etc.
 */
public class GameManager {

    private final GraphicsRepainter graphicsRepainter;
    private final EntityManager entityManager;

    private Character character;
    private boolean gameOver = false;

    public GameManager(GraphicsRepainter graphicsRepainter, EntityManager entityManager, Character character) {
        this.graphicsRepainter = graphicsRepainter;
        this.entityManager = entityManager;
        this.character = character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    /**
     * Returns whether the character was hurt or not
     *
     * @return true if the character was hurt, else false
     */
    public boolean wasCharacterHurt() {
    	int height = (int)this.character.getRightEntitySprite().getHeight();
    	int width = (int)this.character.getRightEntitySprite().getWidth();
    	Hitbox ET = new Hitbox(this.character.getLocation(), height, width);
    	
    	for (Enemy enemy : this.entityManager.getEnemyList()) {
    		int enHeight = (int)enemy.getLeftEntitySprite().getHeight();
    		int enWidth = (int)enemy.getLeftEntitySprite().getWidth();
    		Hitbox ene = new Hitbox(enemy.getLocation(), enHeight, enWidth);
    		boolean x = ET.areColliding(ene);
    		if(x == true){
    			return x;
    		}
        }
        return false;
    }

    /**
     * Returns {@code gameOver} variable
     *
     * @return {@code gameOver}
     */
    public boolean getGameOver() {
        return this.gameOver;
    }

    /**
     * Updates the {@code gameOver} variable
     * @param gameOver New value for {@code gameOver}
     */
    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    /**
     * Calculate ground level based on actor height and window height
     *
     * @param actor Which actor to calculate ground level for
     * @return Returns the ground level of the game
     */
    public int getGroundLevel(Actor actor) {
        return this.graphicsRepainter.HEIGHT - 100 - (int) actor.getRightEntitySprite().getHeight();
    }

    public int getCenterXCord() {
        return this.graphicsRepainter.WIDTH/2 - (int) this.character.getRightEntitySprite().getWidth();
    }

    /**
     * Check to see if character is on ground, if so, set character to exact ground height, set vertical velocity to 0 and set jumping boolean to false
     *
     * @param character {@code Character}
     * @param velocity  {@code Velocity} of character
     */
    public void runGroundCheck(Character character, Velocity velocity) {
        if (UILauncher.getInputListener().onGround() && character.isJumping() && velocity.getVerticalVelocity() != -10) {
            character.getLocation().setYcord(this.graphicsRepainter.HEIGHT - 100 - (int) character.getRightEntitySprite().getHeight());
            character.setJumping(false);
            velocity.setVerticalVelocity(0);
        }
    }

    public static void main(String args[]) {
        Character character = new ET();
        InputListener inputListener = new InputListener(character);
        EntityManager entityManager = new EntityManager(character);
        GraphicsRepainter graphicsRepainter = new GraphicsRepainter();
        GameManager gameManager = new GameManager(graphicsRepainter, entityManager, character);

        System.out.println("Testing out wasCharacterHurt");

        // should be false since no entities have spawned yet
        System.out.println("Was character hurt? " + (gameManager.wasCharacterHurt())); // false = correct

        entityManager.spawnRandomEntities(5);
        // set character location to same as an enemy so that we can test if character is hurt
        character.setLocation(entityManager.getEnemyList().get(0).getLocation().clone());
        System.out.println("Was character hurt? " + (gameManager.wasCharacterHurt())); // true = correct

        System.out.println("Is game over? " + (gameManager.getGameOver())); // false = correct

        System.out.println("Setting gameOver to true...");
        gameManager.setGameOver(true);
        System.out.println("Is game over? " + (gameManager.getGameOver())); // true = correct
    }


}
