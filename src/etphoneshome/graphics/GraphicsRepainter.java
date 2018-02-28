package etphoneshome.graphics;

import etphoneshome.entities.characters.Character;
import etphoneshome.managers.EntityManager;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Class responsible for repainting the graphics of the game
 */
public class GraphicsRepainter extends Application {

    private final int WIDTH = 1728;
    private final int HEIGHT = 972;
    private final Image BACKGROUND = new Image("/images/backgrounds/background.jpg");
    private Canvas canvas = new Canvas(this.WIDTH, this.HEIGHT);
    private GraphicsContext gc = canvas.getGraphicsContext2D();
    private Group root = new Group();
    private Scene scene = new Scene(root);
    private EntityManager entityManager;
    private Character character;

    public void start(Stage stage) {
        createWindow(stage);
    }

    public void createWindow(Stage stage) {

        this.root.getChildren().add(this.canvas);
        stage.setScene(this.scene);
        this.gc.drawImage(this.BACKGROUND, 0, 0);
        stage.show();
    }

    public void goLaunch(String[] args, Character character, EntityManager entityManager) {
        this.character = character;
        this.entityManager = entityManager;
        super.launch(args);
    }

    public void repaint() {
        this.repaintCharacter();
        this.repaintEnemies();
    }

    public void repaintCharacter() {

    }

    public void repaintEnemies() {

    }

}
