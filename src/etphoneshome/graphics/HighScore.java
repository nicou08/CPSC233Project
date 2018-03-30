package etphoneshome.graphics;

import etphoneshome.entities.characters.Character;
import etphoneshome.entities.characters.ET;
import etphoneshome.managers.PlayerManager;
import etphoneshome.player.Player;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * this class is responsible for showing the highscores of the top 10 players
 * setPlayers will get all the previous players from the file and put them into PlayerManager
 * AddCurrentPlayer will add the player that just played
 * and SaveHighscores will write the players and score in the file again while runHighScore will run the 3 in unison
 *
 */

public class HighScore {


	/**
	 * file path assocciated with the Highscores text file which contains the top scores
	 */
	private static String fileName = "HighScores\\Highscores.txt";
	private static File highscores = new File(fileName);
	
	/**
	 * PlayerManager of the {@code HighScore}
	 */
	private static PlayerManager playerManager = new PlayerManager();
	
	/**
	 * Character associated with the {@code HighScore}
	 */
	private static Character character;
	
	/**
	 * constructor which will add the character whose score we will add
	 * @param character character of the {@code HighScore}
	 */
	public HighScore(Character character) {
		this.character=character;
	}

	/**
	 * Sets the players into {@code PlayerManager}
	 */
	private static void setPlayers() {
		String line;
		  try
	        {
				BufferedReader highscoreFile = new BufferedReader(new FileReader(fileName));
				while ((line = highscoreFile.readLine()) != null) {
					String[] variables  = line.split(" ");
					Player player = new Player(Integer.parseInt(variables[0]), variables[1]);
					playerManager.addPlayer(player);
				}
				highscoreFile.close();
	
	        }
		  catch(FileNotFoundException e)
	        {
	            System.out.println("Cannot find file " + fileName);
	        }
			catch(IOException e)
			{
				e.printStackTrace();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
	}	  
	
	/**
	 * adds the current player to the highscore
	 * @param name name given by the user
	 */
	private static void addCurrentPlayer(String name) {
		Player player = new Player(character.getScore(), name);
		playerManager.addPlayer(player);
	}
	
	/**
	 * writes the highscores and names of the players into the txt file
	 */
	private static void saveHighScores() {
	
			PrintWriter writer;
			try {
				writer = new PrintWriter(highscores);
				for(Player player : playerManager.getPlayerList()) {
					writer.println(player.getScore() + " " + player.getName());
				}
				writer.close();
			}
			catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
	}
	
	/**
	 * runs all the processes of {@code HighScore} to do with reading and writing to file
	 * @param name name given by the user
	 */
	public static void runHighScores(String name) {
		addCurrentPlayer(name);
		setPlayers();
		saveHighScores();
	}
	
	
	public static void main(String[] args) {
		character = new ET();
		character.setScore(500);
		String name = "Tester";
		runHighScores(name);
		for(Player player : playerManager.getPlayerList()) {
			System.out.println(player.toString());
		}
	}


	
}