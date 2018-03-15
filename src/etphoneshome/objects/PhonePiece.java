package etphoneshome.objects;

import javafx.scene.image.Image;

public class PhonePiece extends Collectible {
	
	
	/**
	 * dimensions of the {@code PhonePiece}
	 */
	private final static int WIDTH = 30;
	private final static int HEIGHT = 70;
	
	private String ImagePath;

	
	/**
	 * empty constructor that will set the sprite to the phone Antenna
	 */
	public PhonePiece() {
		super.setImages("images/sprites/phoneAntenna.png", "images/sprites/phoneChassis.png","images/sprites/phoneKeypad.png" );
		this.setImage(PhonePieceType.ANTENNA);
	}

	/**
	 * constructor that sets up the image, location and hitbox associated with {@code PhonePiece}
	 * @param location location of the phone piece
	 * @param phonePieceType the type of phone piece to create
	 */
	public PhonePiece(Location location, PhonePieceType phonePieceType) {
		super(location);
		super.setImages("images/sprites/phoneAntenna.png", "images/sprites/phoneChassis.png","images/sprites/phoneKeypad.png" );
		setImage(phonePieceType);
	}

	/**
	 * sets the image to something else
	 * @param index index of the phone piece image
	 */
	public void setImage(int index) {
		this.ImagePath = this.getSprites()[index];
		super.setTheImage(ImagePath);
	}

	/**
	 * sets the image to something else
	 * @param type type of phone piece
	 */
	public void setImage(PhonePieceType type) {
		this.setImage(type.getIndex());
	}
	
	/**
	 * returns current image of the {@code PhonePiece}
	 * @return the image of {@code PhonePiece}
	 */
	public Image getImage() {
		Image copy = new Image(ImagePath);
		return copy;
	}
	
	/**
	 * gets width
	 * @return width
	 */
	 public int getWidth() {
		 return this.WIDTH;
	 }
	 
	 /**
	  * gets height
	  * @return height
	  */
	 public int getHeight() {
		 return this.HEIGHT;
	 }
	
		public static void main(String[] args) {
			
			Location testloc = new Location(100,300);
			PhonePiece tester = new PhonePiece(testloc, PhonePieceType.ANTENNA);
			System.out.println("x cord should be 100 is: " + tester.getLocation().getXcord());
			System.out.println("ycord should be 300 is: " + tester.getLocation().getYcord());
			
			Location testloc2 = new Location(70,270);
			PhonePiece tester2 = new PhonePiece(testloc2,PhonePieceType.ANTENNA);
			System.out.println("x cord should be 70 is: " + tester2.getLocation().getXcord());
			System.out.println("ycord should be 270 is: " + tester2.getLocation().getYcord());
			
			boolean a;
			a= tester.getHitbox().areColliding(tester2.getHitbox());
			System.out.println("should be true: " + a);
			tester2.setLocation(new Location(1000, tester2.getLocation().getYcord()));
			System.out.println("xcord should be 1000 is: " + tester2.getLocation().getXcord());
			a= tester.getHitbox().areColliding(tester2.getHitbox());
			System.out.println("should be false: "+ a);
			
			
			
			System.exit(1);
		}



}
