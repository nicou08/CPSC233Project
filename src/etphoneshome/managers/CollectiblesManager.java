package etphoneshome.managers;

import etphoneshome.objects.*;

import java.util.ArrayList;
import java.util.List;
public class CollectiblesManager {
	/**
	 * This class is used to manage all the collectibles that are in the game.
	 * Using the addCollectible method adds a collectible to the list collectibles.
	 * Using the removeCollectible method removes a collectible from the list collectibles.
	 * Using getCollectiblesList allows you to return the list of collectibles in its current state.
	 */
	private final static List<Collectible> collectibles = new ArrayList<Collectible>();
	/**
	 * constructor for the class 
	 */
	public CollectiblesManager() {
		
	}
	/**
	 * method to add collectible to the list collectibles.
	 * @param collectible is the collectible that is added
	 */
	public void addCollectible(Collectible collectible) {
		collectibles.add(collectible);
		
	}
	/**
	 * method to remove collectible from the list collectibles.
	 * @param collectible is the collectible that is removed from the list.
	 */
	public void removeCollectible(Collectible collectible) {
		collectibles.remove(collectible);
		
	}
	/**
	 * method to return the list collectibles in its current state.
	 * @return returns list collectibles.
	 */
	public List<Collectible> getCollectiblesList(){
		return this.collectibles;
		
	}
}

