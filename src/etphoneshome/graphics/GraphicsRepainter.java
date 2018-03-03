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
    private final Image GAMEOVER = new Image("/images/sprites/gameover.png");
    private Canvas canvas = new Canvas(this.WIDTH, this.HEIGHT);
    private GraphicsContext gc = canvas.getGraphicsContext2D();
    private Group root = new Group();
    private Scene scene = new Scene(root);
    private Timeline timeline = new Timeline();
    private int tick;
    private Stage stage;

    public void start(Stage stage) {
        this.stage = stage;
        this.createWindow(stage);

        Character character = new ET();
        UILauncher.setCharacter(character);
        character.getLocation().setXcord(this.WIDTH / 2 - (int) character.getEntitySprite().getWidth() / 2);
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
            gc.drawImage(character.getEntitySprite(), WIDTH / 2 - character.getEntitySprite().getWidth() / 2, character.getLocation().getYcord());
            for (Enemy enemy : UILauncher.getEntityManager().getEnemyList()) {
                gc.drawImage(enemy.getEntitySprite(), enemy.getLocation().getXcord() - characterLocation.getXcord() + (this.WIDTH / 2 - (int) character.getEntitySprite().getWidth() / 2), enemy.getLocation().getYcord());
            }

            if (UILauncher.getGameManager().wasCharacterHurt()) {
                character.setHealth(character.getHealth() - 1);
                if (character.getIsDead()) {
                    gc.drawImage(GAMEOVER, WIDTH / 2 - GAMEOVER.getWidth() / 2, HEIGHT / 2 - GAMEOVER.getHeight() / 2);
                    timeline.stop();
                }
            }

            if (!character.getIsDead()) {
                this.gc.drawImage(new Image("/images/sprites/heart.png"), 50, 50);
            }
                

            stage.show();

        });

        timeline.getKeyFrames().add(kf);
        timeline.play();
    }

}
