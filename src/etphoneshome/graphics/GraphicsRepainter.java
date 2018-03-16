package etphoneshome.graphics;

import etphoneshome.UILauncher;
import etphoneshome.entities.characters.Character;
import etphoneshome.entities.characters.ET;
import etphoneshome.entities.enemies.Enemy;
import etphoneshome.listeners.InputListener;
import etphoneshome.managers.BackgroundManager;
import etphoneshome.managers.GameManager;
import etphoneshome.managers.LevelManager;
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
    private Label score = new Label();

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
     * A button needed to playAgain the game
     */
    private Button playAgainButton;

    /**
     * A button used for exiting the game
     */
    private Button exitButton;

    public void start(Stage stage) {

        //creating stage
        this.stage = stage;
        this.createWindow(stage);

        //making character and setting it's starting point
        Character character = new ET();
        UILauncher.setCharacter(character);
        Location characterLocation = character.getLocation();
        character.setLocation(new Location(UILauncher.getGameManager().getCenterXCord(), UILauncher.getGameManager().getGroundLevel(character)));

        this.setupButtons(character);

        score.setText("" + character.getScore());
        score.setFont(new Font("Arial", 20));
        score.setTranslateX(WIDTH / 2);
        score.setTranslateY(100);
        score.setTextFill(Color.WHITE);
        root.getChildren().add(score);

        this.registerKeyEvents();

        //UILauncher.getCollectiblesManager().spawnRandomReesesPieces(10);

        //staring the actual game
        this.startTimeline(character);
    }

    /**
     * Method used to setup buttons
     * @param character The character object of the game
     */
    private void setupButtons(Character character) {

        // creating playAgainButton 
        playAgainButton = new Button();
        playAgainButton.setText("Play Again");
        playAgainButton.setTranslateX(WIDTH / 2 - 100);
        playAgainButton.setTranslateY(50);
        playAgainButton.setPrefSize(200, 100);
        playAgainButton.setFont(new Font("Arial", 20));

        // creating exit button
        exitButton = new Button();
        exitButton.setText("Exit Game");
        exitButton.setTranslateX(WIDTH - WIDTH / 10);
        exitButton.setTranslateY(50);
        exitButton.setPrefSize(100, 50);
        exitButton.setFont(new Font("Arial", 14));

        root.getChildren().add(exitButton);

        this.setupButtonEvents(character);

    }

    /**
     * Method used to setup button events
     * @param character The character object of the game
     */
    private void setupButtonEvents(Character character) {

        exitButton.setOnMouseClicked(k -> {
            timeline.stop();
            stage.close();
        });

        playAgainButton.setOnMouseClicked(k -> {

            UILauncher.getLevelManager().loadLevel(0);

            //resets character position and health
            character.setLocation(new Location(UILauncher.getGameManager().getCenterXCord(), UILauncher.getGameManager().getGroundLevel(character)));
            character.setIsDead(false);
            character.setHealth(1);
            character.setFacingRight(true);
            character.getVelocity().setHorizontalVelocity(0);
            character.getVelocity().setVerticalVelocity(0);
            character.setScore(0);
            UILauncher.getGameManager().setGameOver(false);
            UILauncher.getLevelManager().unloadLevel();
            UILauncher.getLevelManager().loadLevel(0);

            //removes playAgainButton and starts timeline again
            root.getChildren().remove(playAgainButton);
            timeline.play();
        });
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
     * Allows the start method be initiated in  {@code UILauncher}
     *
     * @param args for {@code UILauncher}
     */
    public void goLaunch(String[] args) {
        GraphicsRepainter.launch(args);
    }

    /**
     * Setsup the key event handlers for the game
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

            GameManager gameManager = UILauncher.getGameManager();
            BackgroundManager backgroundManager = UILauncher.getBackgroundManager();
            InputListener inputListener = UILauncher.getInputListener();
            LevelManager levelManager = UILauncher.getLevelManager();

            //getting location and velocity of character
            Velocity velocity = character.getVelocity();
            Location characterLocation = character.getLocation();

            //Will update velocity if the user is holding a movement key (w, a, s, d)

            // check if game was won
            if (levelManager.getPhonePiecesLeft() == 0 && characterLocation.getXcord() >= levelManager.getCurrentLevel().getEndCord()) {
                character.getVelocity().setHorizontalVelocity(10);
                character.getVelocity().setVerticalVelocity(0);
                character.setLocation(new Location(characterLocation.getXcord() + (int) velocity.getHorizontalVelocity(), characterLocation.getYcord() + (int) velocity.getVerticalVelocity()));
                levelManager.setLevelComplete(true);
            } else {
                inputListener.updateVelocities();
                Location newLocation = new Location(characterLocation.getXcord() + (int) velocity.getHorizontalVelocity(), characterLocation.getYcord() + (int) velocity.getVerticalVelocity());
                //updates location of character based on the velocity
                Direction direction = gameManager.runObstacleCollisionCheck(character, characterLocation, newLocation);
                if (direction == null) {
                    character.setLocation(newLocation);
                    backgroundManager.updateBackgroundLocation();
                } else if (direction == Direction.ABOVE || direction == Direction.BELOW) {
                    backgroundManager.updateBackgroundLocation();
                }

                gameManager.runGroundCheck(character, velocity);
                gameManager.runCollectibleCheck();
            }

            // repaint view
            this.repaintBackgroundAndObstacles(character);
            this.repaintEntities(character);
            this.repaintCollectibles(character);

            if (levelManager.isLevelComplete() && character.getLocation().getXcord() >= levelManager.getCurrentLevel().getEndCord() + WIDTH / 2 + character.getRightEntitySprite().getWidth() / 2) {
                Image youWonImage = new Image("images/sprites/you-won.png");
                gc.drawImage(youWonImage, WIDTH / 2 - (int) youWonImage.getWidth() / 2, HEIGHT / 2 - (int) youWonImage.getHeight() / 2);
                timeline.pause();
            }

            // update score
            score.setText("" + character.getScore());

            //will take health away if character touches an enemy
            if (gameManager.wasCharacterHurt()) {
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
     * Repaint background and entities
     *
     * @param character {@code Character}
     */
    public void repaintEntities(Character character) {
        LevelManager levelManager = UILauncher.getLevelManager();
        if (levelManager.isLevelComplete()) {
            Level level = levelManager.getCurrentLevel();
            gc.drawImage(character.getRightEntitySprite(), character.getLocation().getXcord() - level.getEndCord() + UILauncher.getGameManager().getCenterXCord(), character.getLocation().getYcord());
        } else {
            if (character.isFacingRight()) {
                gc.drawImage(character.getRightEntitySprite(), WIDTH / 2 - character.getRightEntitySprite().getWidth() / 2, character.getLocation().getYcord());
            } else {
                gc.drawImage(character.getLeftEntitySprite(), WIDTH / 2 - character.getLeftEntitySprite().getWidth() / 2, character.getLocation().getYcord());
            }
        }

        if (UILauncher.getDebugMode()) {
            Location loc = character.getLocation();
            int height = (int) character.getRightEntitySprite().getHeight();
            int width = (int) character.getRightEntitySprite().getWidth();
            this.drawHitbox(character, loc, height, width, Color.GREEN);
        }

        for (Enemy enemy : UILauncher.getEntityManager().getEnemyList()) {
            if (enemy.isFacingRight()) {
                gc.drawImage(enemy.getRightEntitySprite(), enemy.getLocation().getXcord() - character.getLocation().getXcord() + (this.WIDTH / 2 - (int) character.getRightEntitySprite().getWidth() / 2), enemy.getLocation().getYcord());
            } else {
                gc.drawImage(enemy.getLeftEntitySprite(), enemy.getLocation().getXcord() - character.getLocation().getXcord() + (this.WIDTH / 2 - (int) character.getRightEntitySprite().getWidth() / 2), enemy.getLocation().getYcord());
            }

            if (UILauncher.getDebugMode()) {
                Location loc = enemy.getLocation();
                int height = (int) enemy.getRightEntitySprite().getHeight();
                int width = (int) enemy.getRightEntitySprite().getWidth();
                this.drawHitbox(character, loc, height, width, Color.RED);
            }
        }
    }

    public void repaintBackgroundAndObstacles(Character character) {
        Location backgroundManagerLoc = UILauncher.getBackgroundManager().getBackgroundLocation();
        gc.drawImage(this.BACKGROUND, backgroundManagerLoc.getXcord(), backgroundManagerLoc.getYcord());

        for (Obstacle obstacle : UILauncher.getObstacleManager().getObstacleList()) {
            if (obstacle instanceof Platform) {
                Platform platform = (Platform) obstacle;
                for (Obstacle brick : platform.getBricks()) {
                    gc.drawImage(brick.getSprite(), brick.getLocation().getXcord() - character.getLocation().getXcord() + (this.WIDTH / 2 - (int) character.getRightEntitySprite().getWidth() / 2), brick.getLocation().getYcord());
                }
                Location loc = new Location(platform.getLocation());

                if (UILauncher.getDebugMode()) {
                    int height = platform.getHeight();
                    int width = platform.getWidth() * platform.getLength();
                    this.drawHitbox(character, loc, height, width, Color.ORANGE);

                }
            }
        }
    }

    public void repaintCollectibles(Character character) {
        for (Collectible collectible : UILauncher.getCollectiblesManager().getCollectiblesList()) {
            gc.drawImage(collectible.getTheImage(), collectible.getLocation().getXcord() - character.getLocation().getXcord() + (this.WIDTH / 2 - (int) character.getRightEntitySprite().getWidth() / 2), collectible.getLocation().getYcord());

            if (UILauncher.getDebugMode()) {
                this.drawHitbox(character, collectible.getLocation(), (int) collectible.getTheImage().getHeight(), (int) collectible.getTheImage().getWidth(), Color.YELLOW);
            }
        }
    }


    /**
     * Check if character is dead. If not dead, draw hearts and continue playing. If dead pause timeline and sets a playAgainButton on screen that will
     * reset the game if the user clicks it
     *
     * @param character {@code Character}
     */
    public void runHealthCheck(Character character) {
        if (!character.getIsDead()) {
            this.gc.drawImage(new Image("/images/sprites/heart.png"), 25, 25);
        } else {
            gc.drawImage(GAMEOVER, WIDTH / 2 - GAMEOVER.getWidth() / 2, HEIGHT / 2 - GAMEOVER.getHeight() / 2);
            root.getChildren().add(playAgainButton);
            timeline.pause();
            UILauncher.getGameManager().setGameOver(true);

        }
    }

    /**
     * Draws hitbox using given location, height, and width
     *
     * @param character The character object of the game
     * @param loc    Top left corner {@code Location} of {@code Hitbox}
     * @param height Height of {@code Hitbox}
     * @param width  Width of {@code Hitbox}
     * @param color  The color of the hitbox to be drawn
     */
    public void drawHitbox(Character character, Location loc, int height, int width, Color color) {
        loc = new Location(loc.getXcord() + (-character.getLocation().getXcord()) + (this.WIDTH / 2 - (int) character.getRightEntitySprite().getWidth() / 2), loc.getYcord());
        gc.setStroke(color);
        gc.setLineWidth(2);

        gc.strokeLine(loc.getXcord(), loc.getYcord(), loc.getXcord(), loc.getYcord() + height);

        gc.strokeLine(loc.getXcord(), loc.getYcord() + height, loc.getXcord() + width, loc.getYcord() + height);

        gc.strokeLine(loc.getXcord() + width, loc.getYcord() + height, loc.getXcord() + width, loc.getYcord());

        gc.strokeLine(loc.getXcord() + width, loc.getYcord(), loc.getXcord(), loc.getYcord());
    }

}
