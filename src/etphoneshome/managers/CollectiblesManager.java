package etphoneshome.managers;

import etphoneshome.objects.*;

import java.util.ArrayList;
import java.util.List;
public class CollectiblesManager {

	private final static List<Collectible> collectibles = new ArrayList<Collectible>();
	
	public CollectiblesManager() {
		
	}
	public void addCollectible(Collectible collectible) {
		collectibles.add(collectible);
		
	}
	public void removeCollectible(Collectible collectible) {
		collectibles.remove(collectible);
		
	}
	public List<Collectible> getCollectiblesList(){
		return this.collectibles;
		
	}
}

