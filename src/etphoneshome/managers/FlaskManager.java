package etphoneshome.managers;

import java.util.ArrayList;
import java.util.List;
import etphoneshome.objects.Flask;

/**
 * This class is responsible for handling the flasks thrown by the enemies.
 * Using addFlask will add the flask to the arrraylist
 * Using removeFlask will remove the flask from the arraylist
 * Using clearFlasks will remove all the flasks in the arraylist
 *
 */
public class FlaskManager {
	
	/**
	 * Arraylist that keeps all the flasks
	 */
	  private final List<Flask> flasks = new ArrayList<Flask>();
	  
	  /**
	   * add a flask to the {@code FlaskManager}
	   * @param flask flask to add to the {@code FlaskManager}
	   */
	  public void addFlask(Flask flask) {
		  this.flasks.add(flask);
	  }
	  
	  /**
	   * remove a flask from the {@code FlaskManager}
	   * @param flask flask to remove from {@code FlaskManager}
	   */
	  public void removeFlask(Flask flask) {
		  this.flasks.remove(flask);
	  }

	  /**
	   * Clear all flasks from the {@code FlaskManager}
	   */
	  public void clearFlasks() {
		  this.flasks.clear();
	  }
	  
	  /**
	   * returns the arraylist of {@code FlaskManager}
	   * @return the {@code flasks} array list of {@code FlaskManager}
	   */
	  public List<Flask> getFlaskList(){
		  return this.flasks;
	  }
	  
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
