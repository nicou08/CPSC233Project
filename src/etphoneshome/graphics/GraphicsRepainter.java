package etphoneshome.graphics;

import etphoneshome.UILauncher;
import etphoneshome.entities.characters.Character;
import etphoneshome.entities.characters.ET;
import etphoneshome.entities.enemies.Enemy;
import etphoneshome.listeners.InputListener;
import etphoneshome.objects.*;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Class responsible for repainting the graphics of the game
 */
public class GraphicsRepainter extends Application {

    /**
     * Width and height of the screen
     */
    public final int WIDTH = 1920;
    public final int HEIGHT = 1080;
    
    
    private Label score= new Label();

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
     * A button needed to restart the game
     */
    private Button restartButton;

    /**
     * A button used for exiting the game
     */
    private Button exitButton;

    public void start(Stage stage) {

        //creating stage
        this.stage = stage;
        this.createWindow(stage);

        // creating restartButton 
        restartButton = new Button();
        restartButton.setText("Play Again");
        restartButton.setTranslateX(WIDTH / 2 - 100);
        restartButton.setTranslateY(50);
        restartButton.setPrefSize(200, 100);
        restartButton.setFont(new Font("Arial", 20));

        exitButton = new Button();
        exitButton.setText("Exit Game");
        exitButton.setTranslateX(WIDTH - WIDTH / 10);
        exitButton.setTranslateY(50);
        exitButton.setPrefSize(100, 50);
        exitButton.setFont(new Font("Arial", 14));

        root.getChildren().add(exitButton);

        exitButton.setOnMouseClicked(k -> {
            timeline.stop();
            stage.close();
        });
        

        //making character and setting it's starting point
        Character character = new ET();
        UILauncher.setCharacter(character);
        character.getLocation().setXcord(UILauncher.getGameManager().getCenterXCord());
        character.getLocation().setYcord(UILauncher.getGameManager().getGroundLevel(character));
        
        
        score.setText(""+character.getScore());
        score.setFont(new Font("Arial", 20));
        score.setTranslateX(WIDTH/2);
        score.setTranslateY(100);
        score.setTextFill(Color.WHITE);
        root.getChildren().add(score);

        //UILauncher.getBackgroundManager().getBackgroundLocation().setXcord(-this.WIDTH);

        this.registerKeyEvents();

        UILauncher.getCollectiblesManager().spawnRandomReesesPieces(3);
        
        
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
        stage.setFullScreen(true);
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
            Location newLocation = characterLocation.clone();
            newLocation.add((int) velocity.getHorizontalVelocity(), (int) velocity.getVerticalVelocity());
            Direction direction = this.runObstacleCollisionCheck(character, characterLocation, newLocation);
            if (direction == null) {
                characterLocation.add((int) velocity.getHorizontalVelocity(), (int) velocity.getVerticalVelocity());
                UILauncher.getBackgroundManager().updateBackgroundLocation();
            } else if (direction == Direction.ABOVE || direction == Direction.BELOW) {
                UILauncher.getBackgroundManager().updateBackgroundLocation();
            }

            UILauncher.getGameManager().runGroundCheck(character, velocity);
            
            UILauncher.getGameManager().CollectiblePickUp();
            score.setText(""+character.getScore());

            this.repaintBackgroundAndObstacles(character);

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

    public Direction runObstacleCollisionCheck(Character character, Location oldLocation, Location newLocation) {
        int height = (int) character.getRightEntitySprite().getHeight();
        int width = (int) character.getRightEntitySprite().getWidth();
        Hitbox oldCharacterHitbox = new Hitbox(oldLocation, height, width);
        Hitbox newCharacterHitbox = new Hitbox(newLocation, height, width);
        for (Obstacle obstacle : UILauncher.getObstacleManager().getObstacleList()) {
            Hitbox obstacleHitbox = obstacle.getHitbox();
            if (newCharacterHitbox.areColliding(obstacleHitbox)) {
                if (oldCharacterHitbox.toTheLeftOfOtherHitbox(obstacleHitbox)) {
                    oldLocation.setXcord(obstacleHitbox.getTopLeftCorner().getXcord() - width - 1);
                    oldLocation.addY((int) character.getVelocity().getVerticalVelocity());
                    return Direction.LEFT_OF;

                }
                if (oldCharacterHitbox.toTheRightOfOtherHitbox(obstacleHitbox)) {
                    if (obstacle instanceof Platform) {
                        Platform platform = (Platform) obstacle;
                        int xCord = platform.getLocation().getXcord();
                        for (Obstacle brick : platform.getBricks()) {
                            xCord += brick.getSprite().getWidth();
                        }
                        oldLocation.setXcord(xCord + 1);
                    } else {
                        oldLocation.setXcord(obstacleHitbox.getTopLeftCorner().getXcord() + obstacleHitbox.getWidth() + 1);
                    }
                    oldLocation.addY((int) character.getVelocity().getVerticalVelocity());
                    return Direction.RIGHT_OF;
                }

                if (oldCharacterHitbox.belowOtherHitbox(obstacleHitbox)) {
                    character.getVelocity().setVerticalVelocity(0);
                    oldLocation.addX((int) character.getVelocity().getHorizontalVelocity());
                    oldLocation.setYcord(obstacleHitbox.getTopLeftCorner().getYcord() + obstacleHitbox.getHeight());
                    return Direction.BELOW;
                }
                if (oldCharacterHitbox.aboveOtherHitbox(obstacleHitbox)) {
                    oldLocation.setYcord(obstacleHitbox.getTopLeftCorner().getYcord() - height - 1);
                    oldLocation.addX((int) character.getVelocity().getHorizontalVelocity());
                    return Direction.ABOVE;
                }
            }
        }
        return null;
    }

    /**
     * Repaint background and entities
     *
     * @param character {@code Character}
     */
    public void repaintEntities(Character character) {
        if (character.isFacingRight()) {
            gc.drawImage(character.getRightEntitySprite(), WIDTH / 2 - character.getRightEntitySprite().getWidth() / 2, character.getLocation().getYcord());
        } else {
            gc.drawImage(character.getLeftEntitySprite(), WIDTH / 2 - character.getLeftEntitySprite().getWidth() / 2, character.getLocation().getYcord());
        }

        for (Enemy enemy : UILauncher.getEntityManager().getEnemyList()) {
            if (enemy.isFacingRight()) {
                gc.drawImage(enemy.getRightEntitySprite(), enemy.getLocation().getXcord() - character.getLocation().getXcord() + (this.WIDTH / 2 - (int) character.getRightEntitySprite().getWidth() / 2), enemy.getLocation().getYcord());
            } else {
                gc.drawImage(enemy.getLeftEntitySprite(), enemy.getLocation().getXcord() - character.getLocation().getXcord() + (this.WIDTH / 2 - (int) character.getRightEntitySprite().getWidth() / 2), enemy.getLocation().getYcord());
            }
        }
    }

    public void repaintBackgroundAndObstacles(Character character) {
        Location backgroundManagerLoc = UILauncher.getBackgroundManager().getBackgroundLocation();
        gc.drawImage(this.BACKGROUND, backgroundManagerLoc.getXcord(), backgroundManagerLoc.getYcord());
        
        for (Collectible collectible : UILauncher.getCollectiblesManager().getCollectiblesList()) {
            if (collectible instanceof ReesesPieces) {
                ReesesPieces piece = (ReesesPieces) collectible;
                gc.drawImage(piece.getTheImage(), piece.getLocation().getXcord() -character.getLocation().getXcord() + (this.WIDTH / 2 - (int) character.getRightEntitySprite().getWidth() / 2), piece.getLocation().getYcord());
                Location loc = piece.getLocation().clone();
                loc.addX((-character.getLocation().getXcord()) + (this.WIDTH / 2 - (int) character.getRightEntitySprite().getWidth() / 2));
            }
        }

        for (Obstacle obstacle : UILauncher.getObstacleManager().getObstacleList()) {
            if (obstacle instanceof Platform) {
                Platform platform = (Platform) obstacle;
                for (Obstacle brick : platform.getBricks()) {
                    gc.drawImage(brick.getSprite(), brick.getLocation().getXcord() - character.getLocation().getXcord() + (this.WIDTH / 2 - (int) character.getRightEntitySprite().getWidth() / 2), brick.getLocation().getYcord());
                }
                Location loc = platform.getLocation().clone();
                loc.addX((-character.getLocation().getXcord()) + (this.WIDTH / 2 - (int) character.getRightEntitySprite().getWidth() / 2));
                if (UILauncher.getDebugMode()) {
                    gc.setStroke(Color.ORANGE);
                    gc.setLineWidth(3);

                    gc.strokeLine(loc.getXcord(), loc.getYcord(), loc.getXcord(), loc.getYcord() + platform.getHeight());

                    gc.strokeLine(loc.getXcord(), loc.getYcord() + platform.getHeight(), loc.getXcord() + platform.getWidth() * platform.getLength(), loc.getYcord() + platform.getHeight());

                    gc.strokeLine(loc.getXcord() + platform.getWidth() * platform.getLength(), loc.getYcord() + platform.getHeight(), loc.getXcord() + platform.getWidth() * platform.getLength(), loc.getYcord());

                    gc.strokeLine(loc.getXcord() + platform.getWidth() * platform.getLength(), loc.getYcord(), loc.getXcord(), loc.getYcord());

                }
            }
        }
        
    }


    /**
     * Check if character is dead. If not dead, draw hearts and continue playing. If dead pause timeline and sets a restartButton on screen that will
     * reset the game if the user clicks it
     *
     * @param character {@code Character}
     */
    public void runHealthCheck(Character character) {
        if (!character.getIsDead()) {
            this.gc.drawImage(new Image("/images/sprites/heart.png"), 25, 25);
        } else {
            gc.drawImage(GAMEOVER, WIDTH / 2 - GAMEOVER.getWidth() / 2, HEIGHT / 2 - GAMEOVER.getHeight() / 2);
            root.getChildren().add(restartButton);
            timeline.pause();
            UILauncher.getGameManager().setGameOver(true);
            restartButton.setOnMouseClicked(k -> {

                //clears enemies and makes new ones
                UILauncher.getEntityManager().getEnemyList().clear();
                UILauncher.getEntityManager().spawnRandomEntities(50);

                //resets character position and health
                character.getLocation().setXcord(UILauncher.getGameManager().getCenterXCord());
                character.getLocation().setYcord(UILauncher.getGameManager().getGroundLevel(character));
                character.setIsDead(false);
                character.setHealth(1);
                character.setFacingRight(true);
                character.getVelocity().setHorizontalVelocity(0);
                character.getVelocity().setVerticalVelocity(0);
                UILauncher.getGameManager().setGameOver(false);

                //removes restartButton and starts timeline again
                root.getChildren().remove(restartButton);
                timeline.play();
            });

        }
    }

}
