package etphoneshome.managers;

import etphoneshome.entities.characters.Character;
import etphoneshome.entities.characters.ET;
import etphoneshome.entities.enemies.Enemy;
import etphoneshome.graphics.GraphicsRepainter;
import etphoneshome.listeners.InputListener;
import etphoneshome.objects.Location;
import etphoneshome.objects.Hitbox;

/**
 * Class in charge of all checks within the game, checkpoints, R+P pickups, enemy kills, character damage, etc.
 */
public class GameManager {

    private final InputListener inputListener;
    private final GraphicsRepainter graphicsRepainter;
    private final EntityManager entityManager;
    private Character character;

    private boolean gameOver = false;

    public GameManager(InputListener inputListener, GraphicsRepainter graphicsRepainter, EntityManager entityManager, Character character) {
        this.inputListener = inputListener;
        this.graphicsRepainter = graphicsRepainter;
        this.entityManager = entityManager;
        this.character = character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    /**
     * Invokes the {@code processInput} method from the {@code InputListener} class and checks if the character is hurt.
     * If the character is hurt then it is set to dead and {@code gameOver} is set to true. Next the game state is printed.
     */
    public void nextTurn() {
        if (this.wasCharacterHurt()) {
            character.setIsDead(true);
            this.setGameOver(true);
        }
    }

    /**
     * Returns whether the character was hurt or not
     *
     * @return true if the character was hurt, else false
     */
    public boolean wasCharacterHurt() {
    	int height = (int)this.character.getEntitySprite().getHeight();
    	int width = (int)this.character.getEntitySprite().getWidth();
    	Hitbox ET = new Hitbox(this.character.getLocation(), height, width);
    	
    	for (Enemy enemy : this.entityManager.getEnemyList()) {
    		int enHeight = (int)enemy.getEntitySprite().getHeight();
    		int enWidth = (int)enemy.getEntitySprite().getWidth();
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

    public static void main(String args[]) {
        Character character = new ET();
        InputListener inputListener = new InputListener(character);
        EntityManager entityManager = new EntityManager(character);
        GraphicsRepainter graphicsRepainter = new GraphicsRepainter();
        GameManager gameManager = new GameManager(inputListener, graphicsRepainter, entityManager, character);

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

        System.out.println("Testing out nextTurn method");
        gameManager.nextTurn();
    }


}
