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
		setTheImage(super.getSprites()[0]);
		
	}

	/**
	 * constructor that sets up the image, location and hitbox associated with {@code PhonePiece}
	 * @param hitbox hitbox of the phone piece
	 * @param location location of the phone piece
	 * @param index the image to choose
	 */
	public PhonePiece(Hitbox hitbox, Location location, int index) {
		super(hitbox, location);
		super.setImages("images/sprites/phoneAntenna.png", "images/sprites/phoneChassis.png","images/sprites/phoneKeypad.png" );
		if (index < 3 && index >= 0) {
			setTheImage(super.getSprites()[index]);
		}
	}

	/**
	 * sets the image to something else
	 * @param piece url of the image 
	 */
	public void setImage(int index) {
		this.ImagePath = super.getSprites()[index];
		super.setTheImage(ImagePath);
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
			Hitbox testhit = new Hitbox (testloc, WIDTH, HEIGHT);
			PhonePiece tester = new PhonePiece(testhit,testloc,0);
			System.out.println("x cord should be 100 is: " + tester.getLocation().getXcord());
			System.out.println("ycord should be 300 is: " + tester.getLocation().getYcord());
			
			Location testloc2 = new Location(70,270);
			Hitbox testhit2 = new Hitbox(testloc2, WIDTH, HEIGHT);
			PhonePiece tester2 = new PhonePiece(testhit2,testloc2,0);
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
