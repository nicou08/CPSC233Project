package etphoneshome.objects;

import javafx.scene.image.Image;

public class PhonePiece extends Collectible {
	
	
	/**
	 * Image associated with {@code PhonePiece}
	 */
	private Image theImage;
	
	/**
	 * dimensions of the {@code PhonePiece}
	 */
	private final int WIDTH = 30;
	private final int HEIGHT = 70;
	

	
	/**
	 * empty constructor that will set the sprite to the phone Antenna
	 */
	public PhonePiece() {
		super.setImages("images/sprites/phoneAntenna.png", "images/sprites/phoneChassis.png","images/sprites/phoneKeypad.png" );
		theImage = super.getSprites()[0];
		
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
		theImage = super.getSprites()[index];
		
	}

	/**
	 * sets the image to something else
	 * @param piece url of the image 
	 */
	public void setImage(String piece) {
		this.theImage = new Image(piece);
	}
	
	/**
	 * returns current image of the {@code PhonePiece}
	 * @return the image of {@code PhonePiece}
	 */
	public Image getImage() {
		return this.theImage;
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
	
	


}
