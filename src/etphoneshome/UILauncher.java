package etphoneshome;

import etphoneshome.entities.characters.Character;
import etphoneshome.entities.characters.ET;
import etphoneshome.graphics.GraphicsRepainter;
import etphoneshome.listeners.InputListener;
import etphoneshome.managers.EntityManager;
import etphoneshome.managers.GameManager;
import jdk.internal.util.xml.impl.Input;

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
     * handles most checks on the game
     */
    private static GameManager gameManager;
    

    public static void main(String[] args) {
    	
    	//Creates instances of all the objects needed 
        UILauncher.character = new ET();
        UILauncher.entityManager = new EntityManager(character);
        UILauncher.inputListener = new InputListener(character);
        UILauncher.graphicsRepainter = new GraphicsRepainter();
        UILauncher.gameManager = new GameManager(inputListener, graphicsRepainter, entityManager, character);

        
        UILauncher.entityManager.spawnRandomEntities(50);
        
        //launches game
        UILauncher.graphicsRepainter.goLaunch(args);
        System.out.println("Gameover, you died!");
    }

    /**
     * 
     * @return character returns the character the user is playing as (ET)
     */
    public static Character getCharacter() {
        return UILauncher.character;
    }
    
    /**
     * 
     * @param character sets the character that the user will be playing as
     */
    public static void setCharacter(Character character) {
        UILauncher.character = character;
        UILauncher.entityManager.setCharacter(character);
        UILauncher.inputListener.setCharacter(character);
        UILauncher.gameManager.setCharacter(character);
    }

    /**
     * 
     * @return entityManager that holds all the entities associated with {@code UIlauncher}
     */
    public static EntityManager getEntityManager() {
        return UILauncher.entityManager;
    }

    /**
     * 
     * @return InputListener returns the event handler associated with {@code UILauncher}
     */
    public static InputListener getInputListener() {
        return UILauncher.inputListener;
    }

    /**
     * 
     * @return graphicsRepainter returns the drawer associated with {@code UILauncher}
     */
    public static GraphicsRepainter getGraphicsRepainter() {
        return UILauncher.graphicsRepainter;
    }

    /**
     * 
     * @return gameManager returns the game checker associated with {@code UILauncher}
     */
    public static GameManager getGameManager() {
        return UILauncher.gameManager;
    }
}
