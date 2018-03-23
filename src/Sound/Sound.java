package Sound;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Sound {
	
	private static String theme = "C:\\Users\\agost\\Documents\\et-phones-home\\src\\Sounds\\MainSongET.wav";
	private static Media sound = new Media(new File(theme).toURI().toString());
	private static MediaPlayer mediaPlayer = new MediaPlayer(sound);
	
	public void playTheme() {
		mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
		mediaPlayer.play();
	}
	
	

    
    

    
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
