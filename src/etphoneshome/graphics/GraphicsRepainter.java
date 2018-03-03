package etphoneshome.graphics;

import etphoneshome.UILauncher;
import etphoneshome.entities.characters.Character;
import etphoneshome.entities.characters.ET;
import etphoneshome.entities.enemies.Enemy;
import etphoneshome.listeners.InputListener;
import etphoneshome.managers.EntityManager;
import etphoneshome.objects.Location;
import etphoneshome.objects.Velocity;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.swing.plaf.nimbus.State;

/**
 * Class responsible for repainting the graphics of the game
 */
public class GraphicsRepainter extends Application {

    public final int WIDTH = 1728;
    public final int HEIGHT = 972;
    private final Image BACKGROUND = new Image("/images/backgrounds/backgroundRESIZED.jpg");
    private Canvas canvas = new Canvas(this.WIDTH, this.HEIGHT);
    private GraphicsContext gc = canvas.getGraphicsContext2D();
    private Group root = new Group();
    private Scene scene = new Scene(root);
    private Timeline timeline = new Timeline();
    private int tick;
    private Stage stage;

    public void start(Stage stage) {
        System.out.println("start");
        this.stage = stage;
        this.createWindow(stage);

        Character character = new ET();
        UILauncher.setCharacter(character);
        character.getLocation().setYcord(this.HEIGHT - 100 - (int) character.getEntitySprite().getHeight());
        this.registerKeyEvents();
        this.startTimeline(character);
    }

    public void createWindow(Stage stage) {
        this.root.getChildren().add(this.canvas);
        stage.setScene(this.scene);
        this.gc.drawImage(this.BACKGROUND, 0, 0);
        stage.show();
    }

    public void goLaunch(String[] args) {
        Application.launch(args);
    }

    public void registerKeyEvents() {
        System.out.println("registering key events");
        this.scene.setOnKeyPressed(UILauncher.getInputListener().getKeyPressedEvent());
        this.scene.setOnKeyReleased(UILauncher.getInputListener().getKeyReleasedEvent());
    }

    public void startTimeline(Character character) {
        this.timeline.setCycleCount(Animation.INDEFINITE);

        KeyFrame kf = new KeyFrame(Duration.millis(20), e -> {

            this.tick++;
            Velocity velocity = character.getVelocity();
            Location characterLocation = character.getLocation();
            InputListener inputListener = UILauncher.getInputListener();
            inputListener.updateVelocities(this.tick);

            characterLocation.add((int) velocity.getHorizontalVelocity(), (int) velocity.getVerticalVelocity());

            if (inputListener.onGround() && character.isJumping() && velocity.getVerticalVelocity() != -10) {
                characterLocation.setYcord(HEIGHT - 100 - (int) character.getEntitySprite().getHeight());
                character.setJumping(false);
                velocity.setVerticalVelocity(0);
            }

            gc.drawImage(this.BACKGROUND, 0, 0);
            gc.drawImage(character.getEntitySprite(), WIDTH/2 - character.getEntitySprite().getWidth()/2, character.getLocation().getYcord());
            for (Enemy enemy : UILauncher.getEntityManager().getEnemyList()) {
                gc.drawImage(enemy.getEntitySprite(), enemy.getLocation().getXcord() - characterLocation.getXcord(), enemy.getLocation().getYcord());
            }

            stage.show();

        });

        timeline.getKeyFrames().add(kf);
        timeline.play();
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
