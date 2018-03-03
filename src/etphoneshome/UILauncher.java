package etphoneshome;

import etphoneshome.entities.characters.Character;
import etphoneshome.entities.characters.ET;
import etphoneshome.graphics.GraphicsRepainter;
import etphoneshome.listeners.InputListener;
import etphoneshome.managers.EntityManager;
import etphoneshome.managers.GameManager;
import jdk.internal.util.xml.impl.Input;

public class UILauncher {

    private static Character character;
    private static EntityManager entityManager;
    private static InputListener inputListener;

    public static void main(String[] args) {
        Character character = new ET();
        UILauncher.entityManager = new EntityManager(character);
        UILauncher.inputListener = new InputListener(character);
        GraphicsRepainter graphicsRepainter = new GraphicsRepainter();
        GameManager gameManager = new GameManager(inputListener, graphicsRepainter, entityManager, character);
        entityManager.spawnRandomEntities(10);
        graphicsRepainter.goLaunch(args);
        System.out.println("Gameover, you died!");
    }

    public static Character getCharacter() {
        return UILauncher.character;
    }

    public static void setCharacter(Character character) {
        UILauncher.character = character;
        UILauncher.entityManager.setCharacter(character);
        UILauncher.inputListener.setCharacter(character);
    }

    public static EntityManager getEntityManager() {
        return UILauncher.entityManager;
    }

    public static InputListener getInputListener() {
        return UILauncher.inputListener;
    }
}
