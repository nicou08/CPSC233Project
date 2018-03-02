package etphoneshome;

import etphoneshome.entities.characters.Character;
import etphoneshome.entities.characters.ET;
import etphoneshome.graphics.GraphicsRepainter;
import etphoneshome.listeners.InputListener;
import etphoneshome.managers.EntityManager;
import etphoneshome.managers.GameManager;

public class UILauncher {

    public static void main(String[] args) {
        Character character = new ET();
        EntityManager entityManager = new EntityManager(character);
        GraphicsRepainter graphicsRepainter = new GraphicsRepainter();
        graphicsRepainter.setCharacter(character);
        InputListener inputListener = new InputListener(character);
        GameManager gameManager = new GameManager(inputListener, graphicsRepainter, entityManager, character);
        entityManager.spawnRandomEntities(10);
        graphicsRepainter.goLaunch(args, character, entityManager);
        System.out.print("this line was reached");
        graphicsRepainter.startTimeline();
        while (!gameManager.getGameOver()) {
            gameManager.nextTurn();
        }
        System.out.println("Gameover, you died!");
    }

}
