package Sound;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Sound {
	
	/**
	 * Variables for the main song
	 * Thanks to @naisusumeru for their song "Town Hall Tower" on Beepbox.co
	 */
	private static String theme = "Sounds\\MainSongET.wav";
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
