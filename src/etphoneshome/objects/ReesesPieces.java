package etphoneshome.objects;



import javafx.scene.image.Image;
import java.util.Random;

public class ReesesPieces extends Collectible {

	
	private final static int LENGTH = 40;
	private final static int WIDTH = 40;
	
	/**
	 * different colours for the pieces
	 */
	private Image piece1 = new Image("images/sprites/RP_brown.png");
	private Image piece2 = new Image("images/sprites/RP_orange.png");
	private Image piece3 = new Image("images/sprites/RP_yellow.png");
	
	/**
	 * Contains the different colours of Reeses Pieces for the {@code ReesesPieces}
	 */
	private Image[] sprites = new Image[] {piece1,piece2,piece3};
	
	/**
	 * image that will be used 
	 */
	private Image theImage;
	
	private final Random rand = new Random();
	
	
	/**
	 * Empty Constructor that sets the sprite associated with {@code ReesesPieces}
	 */
	public ReesesPieces() {
		setImage();
	}

	/**
	 * Places a single reesesPieces at location with a given sprite
	 * @param hitbox associated with {@code ReesesPieces}
	 * @param location associated with {@code ReesesPieces}
	 */
	public ReesesPieces(Hitbox hitbox, Location location) {
		super(hitbox, location);
		setImage();
	}
	
	private void setImage() {
		int index = rand.nextInt(3);
		this.theImage = sprites[index];
		
	}
	
	public static void main(String[] args) {
		
		Location testloc = new Location(100,300);
		Hitbox testhit = new Hitbox (testloc, WIDTH, LENGTH);
		ReesesPieces tester = new ReesesPieces(testhit,testloc);
		System.out.println("x cord should be 100 is: " + tester.getLocation().getXcord());
		System.out.println("ycord should be 300 is: " + tester.getLocation().getYcord());
		
		Location testloc2 = new Location(70,270);
		Hitbox testhit2 = new Hitbox(testloc2, WIDTH, LENGTH);
		ReesesPieces tester2 = new ReesesPieces(testhit2,testloc2);
		System.out.println("x cord should be 70 is: " + tester2.getLocation().getXcord());
		System.out.println("ycord should be 270 is: " + tester2.getLocation().getYcord());
		
		boolean a;
		a= tester.getHitbox().areColliding(tester2.getHitbox());
		System.out.println("should be true: " + a);
		tester2.getLocation().setXcord(1000);
		System.out.println("xcord should be 1000 is: " + tester2.getLocation().getXcord());
		a= tester.getHitbox().areColliding(tester2.getHitbox());
		System.out.println("should be false: "+ a);
		
		
		
		System.exit(1);
	}

}
