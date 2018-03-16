package etphoneshome.managers;

import etphoneshome.UILauncher;
import etphoneshome.entities.enemies.Enemy;
import etphoneshome.entities.enemies.Police;
import etphoneshome.entities.enemies.Scientist;
import etphoneshome.objects.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CollectiblesManager {
    /**
     * This class is used to manage all the collectibles that are in the game.
     * Using the addCollectible method adds a collectible to the list collectibles.
     * Using the removeCollectible method removes a collectible from the list collectibles.
     * Using getCollectiblesList allows you to return the list of collectibles in its current state.
     */
    private final List<Collectible> collectibles = new ArrayList<Collectible>();

    /**
     * Method to add collectible to the list collectibles.
     *
     * @param collectible is the collectible that is added
     */
    public void addCollectible(Collectible collectible) {
        collectibles.add(collectible);

    }

    /**
     * Method to remove collectible from the list collectibles.
     *
     * @param collectible is the collectible that is removed from the list.
     */
    public void removeCollectible(Collectible collectible) {
        collectibles.remove(collectible);

    }

    /**
     * Method to return the list collectibles in its current state.
     *
     * @return returns list collectibles.
     */
    public List<Collectible> getCollectiblesList() {
        return this.collectibles;
    }

    /**
     * Spawn num number of reeses pieces at random locations.
     * @param num The amount of reeses pieces to spawn in the game
     */
    public void spawnRandomReesesPieces(int num) {
        Random random = new Random();
        int xCord = UILauncher.getGraphicsRepainter().WIDTH / 2 + 70;
        for (double i = 0; i < num; i++) {
            xCord = random.nextInt(1920) + xCord;
            Location location = new Location(xCord, UILauncher.getGraphicsRepainter().HEIGHT - 100 - 40);
            this.addCollectible(new ReesesPieces(location));
        }
    }

    /**
     * Load the collectibles from a given level
     * @param level The level to load the collectibles from
     */
    public void loadCollectibles(Level level) {
        this.clearCollectibles();
        for (Collectible collectible : level.getPhonePieces()) {
            this.collectibles.add(collectible);
        }
        this.spawnRandomReesesPieces(10);
    }

    /**
     * Clears the collectibles list
     */
    public void clearCollectibles() {
        this.collectibles.clear();
    }
}

