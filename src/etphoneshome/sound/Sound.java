package etphoneshome.sound;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class Sound {
	
	/**
	 * Variables for the main song
	 * Thanks to @naisusumeru for their song "Town Hall Tower" on Beepbox.co
	 */
	private static String theme = new File("src\\sounds\\MainSongET.wav").exists() ? "src\\sounds\\MainSongET.wav" : "ET Phones Home\\src\\sounds\\MainSongET.wav";
	private static Media sound = new Media(new File(theme).toURI().toString());
	private static MediaPlayer mediaPlayer = new MediaPlayer(sound);
	
	public void playTheme() {
		mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
		mediaPlayer.play();
	}
	
	public void stopTheme() {
		mediaPlayer.stop();
	}
	

    
    

    
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
