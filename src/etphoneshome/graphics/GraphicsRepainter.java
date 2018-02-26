package etphoneshome.graphics;

import etphoneshome.entities.characters.Character;
import etphoneshome.entities.characters.ET;
import etphoneshome.entities.enemies.Enemy;
import etphoneshome.managers.EntityManager;

/**
 * Class responsible for repainting the graphics of the game
 */
public class GraphicsRepainter {

    private final EntityManager entityManager;
    private final Character character;

    public GraphicsRepainter(Character character, EntityManager entityManager) {
        this.character = character;
        this.entityManager = entityManager;
    }

    /**
     * Prints the state of the current game
     */
    public void printState() {
        // loop thru enemies in entity manager and print how far away they are from the character
        boolean enemiesNearby = false;
        for (Enemy enemy : this.entityManager.getEnemyList()) {
            if (enemy.getLocation().getDistance(this.character.getLocation()) <= 5) {
                System.out.println("Enemy " + enemy.getLocation().getDistance(this.character.getLocation()) + " meters from you");
                enemiesNearby = true;
            }
        }

        if (!enemiesNearby) {
            System.out.println("There are no enemies nearby");
        }
    }

    public static void main(String[] args) {
        Character character = new ET();
        EntityManager entityManager = new EntityManager(character);
        // Create objects made for GraphicRepainter constructor
        entityManager.spawnRandomEntities(5);
        // spawn random entities so we can test printState

        GraphicsRepainter graphicsRepainter = new GraphicsRepainter(character, entityManager);

        for (int i = 0; i < 5; i++) {
            // move character forward in-case no enemies are within 5m so as to test the <= 5m check
            character.getLocation().setXcord(character.getLocation().getXcord() + 1);
            // rest printState
            graphicsRepainter.printState();
            System.out.println("");
        }

    }

}
