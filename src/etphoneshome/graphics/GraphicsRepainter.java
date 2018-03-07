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
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.swing.plaf.nimbus.State;

/**
 * Class responsible for repainting the graphics of the game
 */
public class GraphicsRepainter extends Application {

    /**
     * Width and height of the screen
     */
    public final int WIDTH = 1728;
    public final int HEIGHT = 700;

    /**
     * images needed to play the game
     */
    private final Image BACKGROUND = new Image("/images/backgrounds/backgroundRESIZED.jpg");
    private final Image GAMEOVER = new Image("/images/sprites/gameover.png");

    /**
     * Instances needed to draw on the stage and make the screen
     */
    private Canvas canvas = new Canvas(this.WIDTH, this.HEIGHT);
    private GraphicsContext gc = canvas.getGraphicsContext2D();
    private Group root = new Group();
    private Scene scene = new Scene(root);
    private Stage stage;

    /**
     * Instance associated with running a game loop
     */
    private Timeline timeline = new Timeline();

    /**
     * a button needed to restart the game
     */
    private Button button;

    public void start(Stage stage) {

        //creating stage
        this.stage = stage;
        this.createWindow(stage);

        // creating button 
        button = new Button();
        button.setText("Play Again");
        button.setTranslateX(WIDTH / 2 - 100);
        button.setTranslateY(50);
        button.setPrefSize(200, 100);
        button.setFont(new Font("Arial", 20));

        //making character and setting it's starting point
        Character character = new ET();
        UILauncher.setCharacter(character);
        character.getLocation().setXcord(UILauncher.getGameManager().getCenterXCord());
        character.getLocation().setYcord(UILauncher.getGameManager().getGroundLevel());

        this.registerKeyEvents();

        //staring the actual game
        this.startTimeline(character);
    }

    /**
     * Creates the window
     *
     * @param stage is the stage the window will br created in
     */
    public void createWindow(Stage stage) {
        this.root.getChildren().add(this.canvas);
        stage.setScene(this.scene);
        this.gc.drawImage(this.BACKGROUND, 0, 0);
        stage.show();
    }

    /**
     * lets the start method be initiated in  {@code UILauncher}
     *
     * @param args for {@code UILauncher}
     */
    public void goLaunch(String[] args) {
        Application.launch(args);
    }

    /**
     * sets the event handlers for the game
     */
    public void registerKeyEvents() {
        this.scene.setOnKeyPressed(UILauncher.getInputListener().getKeyPressedEvent());
        this.scene.setOnKeyReleased(UILauncher.getInputListener().getKeyReleasedEvent());
    }

    /**
     * starts the timeline the game will run in
     *
     * @param character the player the user will play as
     */
    public void startTimeline(Character character) {
        this.timeline.setCycleCount(Animation.INDEFINITE);

        KeyFrame kf = new KeyFrame(Duration.millis(20), e -> {

            //getting location and velocity of character
            Velocity velocity = character.getVelocity();
            Location characterLocation = character.getLocation();

            //Will update velocity if the user is holding a correct key
            InputListener inputListener = UILauncher.getInputListener();
            inputListener.updateVelocities();

            //updates location of character based on the velocity
            characterLocation.add((int) velocity.getHorizontalVelocity(), (int) velocity.getVerticalVelocity());

            this.runGroundCheck(character, velocity);
            this.repaintEntities(character);

            //will take health away if character touches an enemy
            if (UILauncher.getGameManager().wasCharacterHurt()) {
                character.takeSinglePointOfDamage();
            }

            this.runHealthCheck(character);
            stage.show();
        });

        //starts timeline
        timeline.getKeyFrames().add(kf);
        timeline.play();
    }

    /**
     * Check to see if character is on ground, if so, set character to exact ground height, set vertical velocity to 0 and set jumping boolean to false
     *
     * @param character {@code Character}
     * @param velocity  {@code Velocity} of character
     */
    public void runGroundCheck(Character character, Velocity velocity) {
        if (UILauncher.getInputListener().onGround() && character.isJumping() && velocity.getVerticalVelocity() != -10) {
            character.getLocation().setYcord(HEIGHT - 100 - (int) character.getEntitySprite().getHeight());
            character.setJumping(false);
            velocity.setVerticalVelocity(0);
        }
    }

    /**
     * Repaint background and entities
     *
     * @param character {@code Character}
     */
    public void repaintEntities(Character character) {
        gc.drawImage(this.BACKGROUND, 0, 0);
        gc.drawImage(character.getEntitySprite(), WIDTH / 2 - character.getEntitySprite().getWidth() / 2, character.getLocation().getYcord());
        for (Enemy enemy : UILauncher.getEntityManager().getEnemyList()) {
            gc.drawImage(enemy.getEntitySprite(), enemy.getLocation().getXcord() - character.getLocation().getXcord() + (this.WIDTH / 2 - (int) character.getEntitySprite().getWidth() / 2), enemy.getLocation().getYcord());
        }
    }

    /**
     * Check if character is dead. If not dead, draw hearts and continue playing. If dead pause timeline and sets a button on screen that will
     * reset the game if the user clicks it
     *
     * @param character {@code Character}
     */
    public void runHealthCheck(Character character) {
        if (!character.getIsDead()) {
            this.gc.drawImage(new Image("/images/sprites/heart.png"), 50, 50);
        } else {
            gc.drawImage(GAMEOVER, WIDTH / 2 - GAMEOVER.getWidth() / 2, HEIGHT / 2 - GAMEOVER.getHeight() / 2);
            root.getChildren().add(button);
            timeline.pause();
            UILauncher.getGameManager().setGameOver(true);
            button.setOnMouseClicked(k -> {

                //clears enemies and makes new ones
                UILauncher.getEntityManager().getEnemyList().clear();
                UILauncher.getEntityManager().spawnRandomEntities(50);

                //resets character position and health
                character.getLocation().setXcord(UILauncher.getGameManager().getCenterXCord());
                character.getLocation().setYcord(UILauncher.getGameManager().getGroundLevel());
                character.setIsDead(false);
                character.setHealth(1);
                UILauncher.getGameManager().setGameOver(false);

                //removes button and starts timeline again
                root.getChildren().remove(button);
                timeline.play();
            });

        }
    }

}
