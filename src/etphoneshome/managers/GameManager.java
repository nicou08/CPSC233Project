package etphoneshome.managers;

import etphoneshome.UILauncher;
import etphoneshome.entities.actor.Actor;
import etphoneshome.entities.characters.Character;
import etphoneshome.entities.characters.ET;
import etphoneshome.entities.enemies.Enemy;
import etphoneshome.graphics.GraphicsRepainter;
import etphoneshome.listeners.InputListener;
import etphoneshome.objects.*;

import java.util.List;

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
    	Hitbox characterHitbox = character.getHitbox();

    	for (Enemy enemy : this.entityManager.getEnemyList()) {
    		Hitbox enemyHitbox = enemy.getHitbox();
    		boolean areColliding = characterHitbox.areColliding(enemyHitbox);
    		if (areColliding) {
    			return areColliding;
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
        return this.graphicsRepainter.HEIGHT - 100 -  (int) actor.getRightEntitySprite().getHeight();
    }

    public int getCenterXCord() {
        return this.graphicsRepainter.WIDTH/2 -  (int) this.character.getRightEntitySprite().getWidth();
    }

    /**
     * Check to see if character is on ground, if so, set character to exact ground height, set vertical velocity to 0 and set jumping boolean to false
     *
     * @param character {@code Character}
     * @param velocity  {@code Velocity} of character
     */
    public void runGroundCheck(Character character, Velocity velocity) {
        if (UILauncher.getInputListener().onGround() && character.isJumping()) {
            character.setJumping(false);
            velocity.setVerticalVelocity(0);
        }
    }
    
    public void runCollectibleCheck() {
    	int height = (int) this.character.getRightEntitySprite().getHeight();
    	int width = (int) this.character.getRightEntitySprite().getWidth();
    	Hitbox ET = new Hitbox(this.character.getLocation(), height, width);
    	List<Collectible> collectibleList = UILauncher.getCollectiblesManager().getCollectiblesList();
    	for(int i = collectibleList.size() - 1; i >= 0; i--) {
    	    Collectible collectible = collectibleList.get(i);
    		if (collectible instanceof ReesesPieces) {
    			int colHeight = (int) collectible.getHeight();
        		int colWidth = (int) collectible.getWidth();
        		Hitbox col = new Hitbox(collectible.getLocation(),colWidth,colHeight);
        		if (ET.areColliding(col)) {
        			UILauncher.getCollectiblesManager().removeCollectible(collectible);
        			character.addScore(100);
        		}
    		}
    	}
    }

    public Direction runObstacleCollisionCheck(Character character, Location oldLocation, Location newLocation) {
        int height = (int) character.getRightEntitySprite().getHeight();
        int width = (int) character.getRightEntitySprite().getWidth();
        Hitbox oldCharacterHitbox = new Hitbox(oldLocation, height, width);
        Hitbox newCharacterHitbox = new Hitbox(newLocation, height, width);
        for (Obstacle obstacle : UILauncher.getObstacleManager().getObstacleList()) {
            Hitbox obstacleHitbox = obstacle.getHitbox();
            if (newCharacterHitbox.areColliding(obstacleHitbox)) {
                if (oldCharacterHitbox.toTheLeftOfOtherHitbox(obstacleHitbox)) {
                    int newX = obstacleHitbox.getTopLeftCorner().getXcord() - width - 1;
                    int newY = oldLocation.getYcord() + (int) character.getVelocity().getVerticalVelocity();
                    character.setLocation(new Location(newX, newY));
                    return Direction.LEFT_OF;

                }
                if (oldCharacterHitbox.toTheRightOfOtherHitbox(obstacleHitbox)) {
                    int newX = obstacleHitbox.getTopLeftCorner().getXcord() + obstacleHitbox.getWidth() + 1;
                    if (obstacle instanceof Platform) {
                        Platform platform = (Platform) obstacle;
                        int xCord = platform.getLocation().getXcord();
                        for (Obstacle brick : platform.getBricks()) {
                            xCord += brick.getSprite().getWidth();
                        }
                        newX = xCord + 1;
                    }
                    int newY = oldLocation.getYcord() + (int) character.getVelocity().getVerticalVelocity();
                    character.setLocation(new Location(newX, newY));
                    return Direction.RIGHT_OF;
                }

                if (oldCharacterHitbox.belowOtherHitbox(obstacleHitbox)) {
                    character.getVelocity().setVerticalVelocity(0);
                    int newX = oldLocation.getXcord() + (int) character.getVelocity().getHorizontalVelocity();
                    int newY = obstacleHitbox.getTopLeftCorner().getYcord() + obstacleHitbox.getHeight();
                    character.setLocation(new Location(newX, newY));
                    return Direction.BELOW;
                }
                if (oldCharacterHitbox.aboveOtherHitbox(obstacleHitbox)) {
                    int newX = oldLocation.getXcord() + (int) character.getVelocity().getHorizontalVelocity();
                    int newY = obstacleHitbox.getTopLeftCorner().getYcord() - height - 1;
                    character.setLocation(new Location(newX, newY));
                    return Direction.ABOVE;
                }
            }
        }
        return null;
    }
    
    public static void main(String args[]) {
        Character character = new ET();
        GraphicsRepainter graphicsRepainter = new GraphicsRepainter();
        BackgroundManager backgroundManager = new BackgroundManager(graphicsRepainter);
        InputListener inputListener = new InputListener(character, backgroundManager);
        EntityManager entityManager = new EntityManager(character);
        GameManager gameManager = new GameManager(graphicsRepainter, entityManager, character);

        System.out.println("Testing out wasCharacterHurt");

        // should be false since no entities have spawned yet
        System.out.println("Was character hurt? " + (gameManager.wasCharacterHurt())); // false = correct

        entityManager.spawnRandomEntities(5);
        // set character location to same as an enemy so that we can test if character is hurt
        character.setLocation(entityManager.getEnemyList().get(0).getLocation());
        System.out.println("Was character hurt? " + (gameManager.wasCharacterHurt())); // true = correct

        System.out.println("Is game over? " + (gameManager.getGameOver())); // false = correct

        System.out.println("Setting gameOver to true...");
        gameManager.setGameOver(true);
        System.out.println("Is game over? " + (gameManager.getGameOver())); // true = correct
    }


}
