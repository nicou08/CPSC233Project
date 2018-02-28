package etphoneshome.graphics;

import etphoneshome.entities.characters.Character;
import etphoneshome.entities.characters.ET;
import etphoneshome.entities.enemies.Enemy;
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

    private final int W = 933;
    private final int H = 700;
    private final Image BACKGROUND = new Image("/etphoneshome/graphics/background.jpg");
    private Canvas canvas = new Canvas(W, H);
    private GraphicsContext gc = canvas.getGraphicsContext2D();
    private Group root = new Group();
    private Scene scene = new Scene(root);
    private final EntityManager entityManager;
    private final Character character;

    public GraphicsRepainter(Character character, EntityManager entityManager) {
        this.character = character;
        this.entityManager = entityManager;
    }

    public void start(Stage stage) {
        createWindow(stage);
    }

    public void createWindow(Stage stage) {

        root.getChildren().add(canvas);
        stage.setScene(scene);
        gc.drawImage(BACKGROUND, 0, 0);
        stage.show();
    }

    public void goLaunch(String[] args) {
        super.launch(args);
    }

    public void repaint() {

    }

}
