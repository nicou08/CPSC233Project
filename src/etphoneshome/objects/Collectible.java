package etphoneshome.objects;
import javafx.embed.swing.JFXPanel;
import javafx.scene.image.Image;


/**
 * This class is a parent class for Anything that can be picked up by ET for score or progressing in the game
 * Parent class for {@code ReesesPieces} and {@code PhonePiece}
 */
public class Collectible {
	
	
	
	JFXPanel jfxPanel = new JFXPanel();
	
	/**
	 * possible images for the {@code Collectible}
	 */
	private Image piece1 = new Image("images/sprites/RP_brown.png");
	private Image piece2 = new Image("images/sprites/RP_brown.png");
	private Image piece3 = new Image("images/sprites/RP_brown.png");
	
	private Image[] sprites = new Image[] {piece1,piece2,piece3};
	
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
	
	/**
	 * sets the possible images for the {@code Collectible}
	 * @param piece1 url of image1
	 * @param piece2 url of image2
	 * @param piece3 url of image3
	 */
	public void setImages(String piece1,String piece2, String piece3){
		this.piece1= new Image(piece1);
		this.piece2 = new Image(piece2);
		this.piece3= new Image(piece3);
	}

	/**
	 * @return the sprites
	 */
	public Image[] getSprites() {
		return sprites;
	}

	/**
	 * @param sprites the sprites to set
	 */
	public void setSprites(Image[] sprites) {
		this.sprites = sprites;
	}
	
	

}

