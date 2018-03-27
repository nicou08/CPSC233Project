package etphoneshome.sound;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;

import java.io.File;

public class Sound {
	
	/**
	 * Variables for the main song
	 * Thanks to @naisusumeru for their song "Town Hall Tower" on Beepbox.co
	 */
	private static String theme = new File("sounds\\MainSongET.wav").exists() ? "sounds\\MainSongET.wav" : "ET Phones Home\\src\\sounds\\MainSongET.wav";
	private static Media themeMedia = new Media(new File(theme).toURI().toString());
	private static MediaPlayer themePlayer = new MediaPlayer(themeMedia);
	
	private static String damage = new File("sounds\\TakeDamage.wav").exists() ? "sounds\\TakeDamage.wav" : "ET Phones Home\\src\\sounds\\TakeDamage.wav";
	private static Media damageMedia = new Media(new File(damage).toURI().toString());
	private static MediaPlayer damagePlayer = new MediaPlayer(damageMedia);
	
	private static String dead = new File("sounds\\DeathET.wav").exists() ? "sounds\\DeathET.wav" : "ET Phones Home\\src\\sounds\\DeathET.wav";
	private static Media deadMedia = new Media(new File(dead).toURI().toString());
	private static MediaPlayer deadPlayer = new MediaPlayer(deadMedia);
	
	private static String win = new File("sounds\\Winning.wav").exists() ? "sounds\\Winning.wav" : "ET Phones Home\\src\\sounds\\Winning.wav";
	private static Media winMedia = new Media(new File(win).toURI().toString());
	private static MediaPlayer winPlayer = new MediaPlayer(winMedia);
	
	private static String ReesePickUp = new File("sounds\\ReesePickUp.wav").exists() ? "sounds\\ReesePickUp.wav" : "ET Phones Home\\src\\sounds\\ReesePickUp.wav";
	private static Media ReesePickUpMedia = new Media(new File(ReesePickUp).toURI().toString());
	private static MediaPlayer reesePlayer = new MediaPlayer(ReesePickUpMedia);
	
	private static String phonePickUp = new File("sounds\\PhonePickUp.wav").exists() ? "sounds\\PhonePickUp.wav" : "ET Phones Home\\src\\sounds\\PhonePickUp.wav";
	private static Media phonePickUpMedia = new Media(new File(phonePickUp).toURI().toString());
	private static MediaPlayer phonePlayer = new MediaPlayer(phonePickUpMedia);
	
	
	public void playTheme() {
		themePlayer.setCycleCount(MediaPlayer.INDEFINITE);
		themePlayer.play();
	}
	
	public void stopTheme() {
		themePlayer.stop();
	}
	
	public void takeDamageSound() {
	checkStatus(damagePlayer);
	damagePlayer.play();
		
	}

	public void playETDeath() {
		checkStatus(deadPlayer);
		deadPlayer.play();
	}
    
    public void playWin() {
    	checkStatus(winPlayer);
    	winPlayer.play();
    }

    public void playReese() {
    	checkStatus(reesePlayer);
    	reesePlayer.play();
    }
    
    public void playPhone() {
    	checkStatus(phonePlayer);
    	phonePlayer.play();
    }
    
    private void checkStatus(MediaPlayer media1) {
    	if(media1.getStatus()== Status.PLAYING) {
    		media1.stop();
    	}
    }
    

    
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
