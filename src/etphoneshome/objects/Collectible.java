package etphoneshome.objects;
import javafx.embed.swing.JFXPanel;


/**
 * This class is a parent class for Anything that can be picked up by ET for score or progressing in the game
 * Parent class for {@code ReesesPieces} and {@code PhonePiece}
 */
public class Collectible {
	
	JFXPanel jfxPanel = new JFXPanel();
	
	/**
	 * Location associated with the {@code Collectible} 
	 */
	private Location location;
	
	/**
	 * Hitbox associated with the {@code Collectible} 
	 */
	private Hitbox hitbox;
	
	
	/**
	 * Empty constructor
	 */
	public Collectible() {
		
	}
	
	/**
	 * Constructor sets the location and hitbox of the collectible
	 * @param hitbox area where it is touched associated with {@code Collectible}
	 * @param location location associated with the {@code Collectible}
	 */
	public Collectible(Hitbox hitbox, Location location) {
		this.location = location;
		this.hitbox = hitbox;
	}
	
	/**
	 * Sets location for {@code Collectible}
	 * @param location new location
	 */
	public void setLocation(Location location) {
		this.location = location;
	}
	
	/**
	 * returns the location associated with {@code Collectible}
	 * @return the location of {@code Collectible}
	 */
	public Location getLocation() {
		return this.location;
	}
	
	/**
	 * sets hitbox associated with {@code Collectible}
	 * @param hitbox new hitbox associated with the {@code Collectible}
	 */
	public void setHitbox(Hitbox hitbox) {
		this.hitbox = hitbox;
	}
	
	/**
	 * returns hitbox associated with the {@code Collectible}
	 * @return this hitbox associated with {@code Collectible}
	 */
	public Hitbox getHitbox() {
		return this.hitbox;
	}
	

	

}

