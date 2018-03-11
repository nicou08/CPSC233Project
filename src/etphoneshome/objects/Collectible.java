package etphoneshome.objects;
import javafx.embed.swing.JFXPanel;
import javafx.scene.image.Image;


/**
 * This class is a parent class for Anything that can be picked up by ET for score or progressing in the game
 * Parent class for {@code ReesesPieces} and {@code PhonePiece}
 */
public class Collectible {
	
	
	private final int WIDTH = 40;
	private final int HEIGHT = 40;
	
	
	JFXPanel jfxPanel = new JFXPanel();  //needed to pass images to subclasses
	
	/**
	 * possible images for the {@code Collectible}
	 */
	private String piece1 = "images/sprites/RP_brown.png";
	private String piece2 = "images/sprites/RP_brown.png";
	private String piece3 = "images/sprites/RP_brown.png";
	
	private String[] sprites = new String[] {piece1,piece2,piece3};
	
	private Image theImage;
	
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
		this.piece1= piece1;
		this.piece2 = piece2;
		this.piece3= piece3;
	}

	/**
	 * @return the sprites
	 */
	public String[] getSprites() {
		String[] copy = new String[3];
		System.arraycopy(sprites, 0, copy, 0, sprites.length);
		return copy;
	}

	/**
	 * @param sprites the sprites to set
	 */
	public void setSprites(String[] sprites) {
		this.sprites = sprites;
	}
	
	/**
	 * sets the image of {@code Collectible}
	 * @param theImage new image of the {@code Collectible}
	 */
	
	public void setTheImage(String theImage) {
		this.theImage = new Image (theImage);
	}
	
	/**
	 * gets the image of the {@code Collectible}
	 * @return the image of the Collectible
	 */
	public Image getTheImage() {
		Image copy = new Image("images/sprites/RP_brown.png");
		return copy;
	}

	public int getHeight() {
		return this.HEIGHT;
	}
	
	public int getWidth() {
		return this.WIDTH;
	}

	

}

