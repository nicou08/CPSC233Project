package etphoneshome;

import etphoneshome.entities.characters.Character;
import etphoneshome.entities.characters.ET;
import etphoneshome.graphics.GraphicsRepainter;
import etphoneshome.listeners.InputListener;
import etphoneshome.managers.*;
import etphoneshome.objects.Level;

public class UILauncher {
	
	/**
	 * character the user will be playing as
	 */
    private static Character character;
    
    /**
     * Variable where the Entities are store
     */
    private static EntityManager entityManager;
    
    /**
     * Variable that will lets us handle events
     */
    private static InputListener inputListener;
    
    /**
     * Class that will lets us draw on the canvas
     */
    private static GraphicsRepainter graphicsRepainter;
    
    /**
     * Handles most checks on the game
     */
    private static GameManager gameManager;

    /**
     * Manages background of the game
     */
    private static BackgroundManager backgroundManager;

    /**
     * Manages obstacles of the game
     */
    private static ObstacleManager obstacleManager;

    /**
     * Manages levels of the game
     */
    private static LevelManager levelManager;
    
    /**
     * Manages collectibles of the game
     */
    private static CollectiblesManager collectibleManager;

    /**
     * Tracks whether game is in debug mode or not
     */
    private static boolean debugMode;

    public static void main(String[] args) {
    	
    	//Creates instances of all the objects needed 
        UILauncher.character = new ET();
        UILauncher.graphicsRepainter = new GraphicsRepainter();
        UILauncher.collectibleManager = new CollectiblesManager();
        UILauncher.obstacleManager = new ObstacleManager();
        UILauncher.levelManager = new LevelManager();
        UILauncher.entityManager = new EntityManager(character);
        UILauncher.backgroundManager = new BackgroundManager(graphicsRepainter);
        UILauncher.gameManager = new GameManager(graphicsRepainter, entityManager, character);
        UILauncher.inputListener = new InputListener(character, backgroundManager, gameManager);
        UILauncher.levelManager.addLevel(new Level("level-0"));
        UILauncher.levelManager.loadLevel(0);

        //UILauncher.debugMode = true;
        
        //launches game
        UILauncher.graphicsRepainter.goLaunch(args);
    }

    /**
     * @return character returns the character the user is playing as (ET)
     */
    public static Character getCharacter() {
        return UILauncher.character;
    }
    
    /**
     * @param character sets the character that the user will be playing as
     */
    public static void setCharacter(Character character) {
        UILauncher.character = character;
        UILauncher.entityManager.setCharacter(character);
        UILauncher.inputListener.setCharacter(character);
        UILauncher.gameManager.setCharacter(character);
    }

    /**
     * @return entityManager that holds all the entities associated with {@code UIlauncher}
     */
    public static EntityManager getEntityManager() {
        return UILauncher.entityManager;
    }

    /**
     * @return InputListener returns the event handler associated with {@code UILauncher}
     */
    public static InputListener getInputListener() {
        return UILauncher.inputListener;
    }

    /**
     * @return graphicsRepainter returns the drawer associated with {@code UILauncher}
     */
    public static GraphicsRepainter getGraphicsRepainter() {
        return UILauncher.graphicsRepainter;
    }

    /**
     * @return gameManager returns the game checker associated with {@code UILauncher}
     */
    public static GameManager getGameManager() {
        return UILauncher.gameManager;
    }

    /**
     * @return backgroundManager returns the background manager associated with {@code UILauncher}
     */
    public static BackgroundManager getBackgroundManager() {
        return UILauncher.backgroundManager;
    }

    /**
     * @return backgroundManager returns the background manager associated with {@code UILauncher}
     */
    public static ObstacleManager getObstacleManager() {
        return UILauncher.obstacleManager;
    }

    /**
     * @return levelManager returns the level manager associated with {@code UILauncher}
     */
    public static LevelManager getLevelManager() {
        return UILauncher.levelManager;
    }

    /**
     * @return debugMode returns whether the program is in debug mode or not
     */
    public static boolean getDebugMode() {
        return UILauncher.debugMode;
    }
    
    public static CollectiblesManager getCollectiblesManager() {
    	return UILauncher.collectibleManager;
    }
}
