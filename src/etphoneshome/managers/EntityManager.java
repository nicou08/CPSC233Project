package etphoneshome.managers;

import etphoneshome.entities.characters.Character;
import etphoneshome.entities.enemies.Enemy;
import etphoneshome.entities.enemies.Police;
import etphoneshome.entities.enemies.Scientist;
import etphoneshome.objects.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *This class is used to manage all the entities that are in the game. 
 *Using the addEnemy method adds an enemy to the list enemies. 
 *Using the removeEnemy method removes an enemy from the list enemies.
 *Using getEnemyList allows you to return the list of enemies in its current state.
 * 
 */
public class EntityManager {
	 /**
     * character associated with {@code EntityManager}
     */
    private Character character;
    /**
     * List containing all of the characters in the game
     */
    private final static List<Enemy> enemies = new ArrayList<>();
    /**
     * Constructor for the class
     * @param character gives EntityManager the character associated with EntityManager
     */
    public EntityManager(Character character) {
        this.character = character;
    }
/**
 * Adds the given enemy associated with {@Code enemy) to the list enemies.
 * @param enemy the {@Code enemy} that is added to the list.
 */
    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
    }
/**
 *Method to remove the given enemy associated with {@Code enemy} from the list enemies.
 * @param enemy the {@Code enemy} that is removed from the list.
 */
    public void removeEnemy(Enemy enemy) {
        enemies.remove(enemy);
    }
/**
 * Method to return the list {@Code enemies}.
 * @return The current list {@Code enemies}.
 */
    public List<Enemy> getEnemyList() {
        return this.enemies;
    }
/**
 * Method to print out enemies in random locations 
 */
    public void spawnRandomEntities(int amount)
        {

        Random random = new Random();
        for (int i = 0; i < amount; i++) {
            int xCord = random.nextInt(20);
            int type = random.nextInt(1);
            Enemy enemy;
            if (type == 0) {
                enemy = new Police();
            } else {
                enemy = new Scientist();
            }
            enemy.setLocation(new Location(xCord + 5, 0));
            this.addEnemy(enemy);
        }
    }

    public Character getCharacter() {
        return  this.character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    // main tests the class' methods
    public static void main(String[] args) {
    	// creates an enemy we can use to test the methods.
    	Enemy testEnemy = new Enemy(); 
    	// creates a character for the entityManager constructor
    	Character testCharacter = new Character(); 
    	EntityManager entityManager = new EntityManager(testCharacter);
    	//spawns random enemies so we can test our methods
    	entityManager.spawnRandomEntities(10);
    	System.out.println("10 enemies should have spawned. Number of enemies: " + enemies.size());
    	entityManager.addEnemy(testEnemy);
    	System.out.println("List should contain 11 enemies. Number of enemies: " + enemies.size());
    	entityManager.removeEnemy(testEnemy);
    	System.out.println("List should contain 10 enemies. Number of enemies: " + enemies.size());
    	entityManager.getEnemyList();
    }	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    }

