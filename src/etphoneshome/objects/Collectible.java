package etphoneshome.objects;
import javafx.embed.swing.JFXPanel;
import javafx.scene.image.Image;


/**
 * This class is a parent class for Anything that can be picked up by ET for score or progressing in the game
 * Parent class for {@code ReesesPieces} and {@code PhonePiece}
 */
public class Collectible {
	
	
	private final static int WIDTH = 40;
	private final static int HEIGHT = 40;
	
	
	JFXPanel jfxPanel = new JFXPanel();  //needed to pass images to subclasses
	
	/**
	 * possible images for the {@code Collectible}
	 */
	private String piece1 = "images/sprites/RP_brown.png";
	private String piece2 = "images/sprites/RP_brown.png";
	private String piece3 = "images/sprites/RP_brown.png";
	
	private String[] sprites = new String[3];
	
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
		this.piece1=piece1;
		this.piece2 = piece2;
		this.piece3 = piece3;
		this.sprites[0]= piece1;
		this.sprites[1]= piece2;
		this.sprites[2]= piece3;
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

	

	public static void main(String[] args) {
		
		Location testloc = new Location(100,100);
		Hitbox testhit = new Hitbox(testloc,WIDTH,HEIGHT);
		Collectible tester = new Collectible(testhit,testloc);
		System.out.println("x cord should be 100 is: " + tester.getLocation().getXcord());
		System.out.println("y cord should be 100 is: " + tester.getLocation().getYcord());
		
		
		Location testloc2 = new Location(200,200);
		Hitbox testhit2 = new Hitbox(testloc2,WIDTH,HEIGHT);
		Collectible tester2 = new Collectible(testhit2,testloc2);
		System.out.println("x cord should be 200 is: " + tester2.getLocation().getXcord());
		System.out.println("y cord should be 200 is: " + tester2.getLocation().getYcord());
		
		boolean a;
		a= tester.getHitbox().areColliding(tester2.getHitbox());
		System.out.println("should be false: " + a);
		tester2.getLocation().setXcord(100);
		tester2.getLocation().setYcord(120);
		System.out.println("xcord should be 100 is: " + tester2.getLocation().getXcord());
		a= tester.getHitbox().areColliding(tester2.getHitbox());
		System.out.println("should be true: "+ a);
		
		
		
		
		
		
	}
}

