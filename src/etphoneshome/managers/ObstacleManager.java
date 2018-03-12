package etphoneshome.managers;

import etphoneshome.objects.*;
import etphoneshome.UILauncher;
import etphoneshome.entities.characters.Character;
import etphoneshome.entities.enemies.Enemy;
import etphoneshome.entities.enemies.Police;
import etphoneshome.entities.enemies.Scientist;
import etphoneshome.objects.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class ObstacleManager {
	
	private final static List<Obstacle> obstacles = new ArrayList<>();
	
	public ObstacleManager() {
		
	}
	
	public void addObstacle(Obstacle obstacle) {
		obstacles.add(obstacle);
	}
	
	public void removeObstacle(Obstacle obstacle) {
		obstacles.remove(obstacle);
		
	}
	public List<Obstacle> getObstacleList(){
		return this.obstacles;
	}
}
