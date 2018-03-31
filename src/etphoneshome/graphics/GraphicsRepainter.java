package etphoneshome.graphics;

import etphoneshome.UILauncher;
import etphoneshome.entities.characters.Character;
import etphoneshome.entities.characters.ET;
import etphoneshome.entities.enemies.Enemy;
import etphoneshome.listeners.InputListener;
import etphoneshome.managers.AnimationManager;
import etphoneshome.managers.BackgroundManager;
import etphoneshome.managers.GameManager;
import etphoneshome.managers.LevelManager;
import etphoneshome.objects.*;
import etphoneshome.sound.Sound;
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


    private int RENDER_RANGE;

    /**
     * images needed to play the game
     */

    private final Image GAMEOVER = new Image(SpriteURL.GAMEOVER.getPath());

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

    private Sound sound = new Sound();

    public void start(Stage stage) {

        //creating stage
        this.stage = stage;
        this.createWindow(stage);

        //making character and setting it's starting point
        Character character = new ET();
        character.setHealth(3);
        UILauncher.setCharacter(character);
        character.setLocation(new Location(UILauncher.getGameManager().getCenterXCord(), UILauncher.getGameManager().getGroundLevel(character)));

        RENDER_RANGE = (this.WIDTH) + (int) character.getRightEntitySprite().getWidth();

        this.setupButtons(character);

        //set label for the score
        score.setText("" + character.getScore());
        score.setFont(new Font("Arial", 20));
        score.setTranslateX(WIDTH / 2);
        score.setTranslateY(100);
        score.setTextFill(Color.WHITE);
        root.getChildren().add(score);

        this.registerKeyEvents();

        //UILauncher.getCollectiblesManager().spawnRandomReesesPieces(10);


        sound.playTheme();


        //staring the actual game
        this.startTimeline(character);
    }

    /**
     * Method used to setup buttons
     *
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
     *
     * @param character The character object of the game
     */
    private void setupButtonEvents(Character character) {

        exitButton.setOnMouseClicked(k -> {
            timeline.stop();
            stage.close();
        });

        playAgainButton.setOnMouseClicked(k -> {

            //resets character position and health
            character.setLocation(new Location(UILauncher.getGameManager().getCenterXCord(), UILauncher.getGameManager().getGroundLevel(character)));
            character.setIsDead(false);
            character.setHealth(3);
            character.setFacingRight(true);
            character.getVelocity().setHorizontalVelocity(0);
            character.getVelocity().setVerticalVelocity(0);
            character.setScore(0);
            UILauncher.getGameManager().setGameOver(false);
            LevelManager levelManager = UILauncher.getLevelManager();
            levelManager.loadLevel(levelManager.getCurrentLevel());
            UILauncher.getFlaskManager().clearFlasks();
            sound.playTheme();

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
        this.timeline.setCycleCount(javafx.animation.Animation.INDEFINITE);

        KeyFrame kf = new KeyFrame(Duration.millis(20), e -> {

            GameManager gameManager = UILauncher.getGameManager();
            BackgroundManager backgroundManager = UILauncher.getBackgroundManager();
            InputListener inputListener = UILauncher.getInputListener();
            LevelManager levelManager = UILauncher.getLevelManager();
            AnimationManager animationManager = UILauncher.getAnimationManager();

            //getting location and velocity of character
            Velocity velocity = character.getVelocity();
            Location oldLocation = character.getLocation();

            //Will update velocity if the user is holding a movement key (w, a, s, d)

            // check if game was won
            if (levelManager.getPhonePiecesLeft() == 0 && oldLocation.getXcord() >= levelManager.getCurrentLevel().getEndCord()) {
                character.setHoldingRight(true);
                inputListener.updateVelocities();
                Location newLocation = new Location(oldLocation.getXcord() + (int) velocity.getHorizontalVelocity(), oldLocation.getYcord() + (int) velocity.getVerticalVelocity());
                character.setLocation(newLocation);
                levelManager.setLevelComplete(true);
            } else {
                inputListener.updateVelocities();
                Location newLocation = new Location(oldLocation.getXcord() + (int) velocity.getHorizontalVelocity(), oldLocation.getYcord() + (int) velocity.getVerticalVelocity());
                //updates location of character based on the velocity
                Direction direction = gameManager.runObstacleCollisionCheck(character, oldLocation, newLocation);
                if (direction == null) {
                    character.setLocation(newLocation);
                    if (newLocation.getXcord() < 0) {
                        newLocation.setXcord(0);
                        character.setLocation(newLocation);
                    } else if (newLocation.getXcord() >= gameManager.getCenterXCord() - (int) character.getRightEntitySprite().getWidth() / 2) {
                        backgroundManager.updateBackgroundLocation();
                    }
                } else if (direction == Direction.ABOVE || direction == Direction.BELOW) {
                    backgroundManager.updateBackgroundLocation();
                }
            }

            gameManager.runEnemyCheck(oldLocation);
            gameManager.runGroundCheck(character, velocity);
            gameManager.runCollectibleCheck();

            // check if entity was hurt
            if (gameManager.checkFlasks() || gameManager.wasCharacterHurt()) {
                character.takeSinglePointOfDamage();
                sound.takeDamageSound();
                if (!character.getIsDead()) {
                    character.setInvincible(true);
                    if (character.isFacingRight()) {
                        animationManager.setCharacterAnimation(new Animation(AnimationFrames.ET_HURT_RIGHT));
                    } else {
                        animationManager.setCharacterAnimation(new Animation(AnimationFrames.ET_HURT_LEFT));
                    }
                }
            }

            // increment animation ticks
            animationManager.incrementAnimations();
            animationManager.runGarbageCollector();
            if (animationManager.getCharacterAnimation() == null) {
                character.setInvincible(false);
            }

            // repaint view
            this.repaintBackgroundAndObstacles(character);
            this.repaintEntities(character);
            this.repaintCollectibles(character);
            this.repaintFlasks(character);

            //sets the view if you win the game
            if (levelManager.isLevelComplete() && character.getLocation().getXcord() >= levelManager.getCurrentLevel().getEndCord() + WIDTH / 2 + character.getRightEntitySprite().getWidth() / 2) {
                Image youWonImage = new Image(SpriteURL.YOU_WON.getPath());
                gc.drawImage(youWonImage, WIDTH / 2 - (int) youWonImage.getWidth() / 2, HEIGHT / 2 - (int) youWonImage.getHeight() / 2);
                sound.playWin();
                timeline.pause();
            }

            // update score
            score.setText("" + character.getScore());

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
        Location loc = character.getLocation();
        GameManager gameManager = UILauncher.getGameManager();
        AnimationManager animationManager = UILauncher.getAnimationManager();

        Animation characterAnimation = animationManager.getCharacterAnimation();
        Image sprite = character.getRightEntitySprite();

        if (characterAnimation != null) {
            sprite = characterAnimation.getSprite();
        } else if (!character.isFacingRight()) {
            sprite = character.getLeftEntitySprite();
        }

        //gets the level of the game
        LevelManager levelManager = UILauncher.getLevelManager();
        if (levelManager.isLevelComplete()) {
            Level level = levelManager.getCurrentLevel();
            gc.drawImage(sprite, loc.getXcord() - level.getEndCord() + UILauncher.getGameManager().getCenterXCord(), loc.getYcord());
        } else if (loc.getXcord() < gameManager.getCenterXCord()) {
            gc.drawImage(sprite, loc.getXcord(), loc.getYcord());
        } else {
            gc.drawImage(sprite, WIDTH / 2 - character.getLeftEntitySprite().getWidth() / 2, loc.getYcord());
        }

        //debug modes sets outline around hitboxes (for testing)
        if (UILauncher.getDebugMode()) {
            int height = (int) character.getRightEntitySprite().getHeight();
            int width = (int) character.getRightEntitySprite().getWidth();
            this.drawHitbox(character, loc, height, width, Color.GREEN);
        }

        //draws enemies
        for (Enemy enemy : UILauncher.getEntityManager().getEnemyList()) {

            if (Math.abs(character.getLocation().getXcord() - enemy.getLocation().getXcord()) <= RENDER_RANGE) {
                sprite = enemy.getRightEntitySprite();
                if (animationManager.getEnemyDeathAnimation(enemy) != null) {
                    sprite = animationManager.getEnemyDeathAnimation(enemy).getSprite();
                } else if (!enemy.isFacingRight()) {
                    sprite = enemy.getLeftEntitySprite();
                }

                if (levelManager.isLevelComplete()) {
                    gc.drawImage(sprite, enemy.getLocation().getXcord() + (this.WIDTH / 2 - (int) character.getRightEntitySprite().getWidth() / 2) - levelManager.getCurrentLevel().getEndCord(), enemy.getLocation().getYcord());
                } else {
                    gc.drawImage(sprite, enemy.getLocation().getXcord() - character.getLocation().getXcord() + (this.WIDTH / 2 - (int) character.getRightEntitySprite().getWidth() / 2), enemy.getLocation().getYcord());
                }
            }


            if (UILauncher.getDebugMode()) {
                int height = (int) enemy.getRightEntitySprite().getHeight();
                int width = (int) enemy.getRightEntitySprite().getWidth();
                this.drawHitbox(character, loc, height, width, Color.RED);
            }
        }
    }

    /**
     * draws the background, obstacles of the level
     *
     * @param character character the player is using
     */
    public void repaintBackgroundAndObstacles(Character character) {
        BackgroundManager backgroundManager = UILauncher.getBackgroundManager();
        Location backgroundLoc = backgroundManager.getBackgroundLocation();
        gc.drawImage(backgroundManager.getBackgroundSprite(), backgroundLoc.getXcord(), backgroundLoc.getYcord());
        LevelManager levelManager = UILauncher.getLevelManager();

        Image finishLineSprite;

        //following if's set the finish line sprite based off the current level. Default sprite is level-0 sprite
        if (levelManager.getCurrentLevel().getLevelNum() == 1) {
            finishLineSprite = new Image(SpriteURL.FINISHLINE_LEVEL_1.getPath());
        } else if (levelManager.getCurrentLevel().getLevelNum() == 2) {
            finishLineSprite = new Image(SpriteURL.FINISHLINE_LEVEL_2.getPath());
        } else if (levelManager.getCurrentLevel().getLevelNum() == 3) {
            finishLineSprite = new Image(SpriteURL.FINISHLINE_LEVEL_3.getPath());
        } else {
            finishLineSprite = new Image(SpriteURL.FINISHLINE_LEVEL_0.getPath());
        }

        //draws finish line based off phone pieces collected and current level number
        if (levelManager.isLevelComplete())  //when the background stops and player keeps moving
        {
            gc.drawImage(finishLineSprite, (levelManager.getCurrentLevel().getEndCord() + 500) + (this.WIDTH / 2 - (int) character.getRightEntitySprite().getWidth() / 2) - levelManager.getCurrentLevel().getEndCord(), 370);
        } else if (levelManager.getPhonePiecesLeft() == 0 && (levelManager.getCurrentLevel().getLevelNum() == 0 || levelManager.getCurrentLevel().getLevelNum() == 3)) //this is used to make the finish line not randomly "pop" into the window
        {
            if (character.getLocation().getXcord() >= levelManager.getCurrentLevel().getEndCord() - 1200) {
                gc.drawImage(finishLineSprite, (levelManager.getCurrentLevel().getEndCord() + 500) - character.getLocation().getXcord() + (this.WIDTH / 2 - (int) character.getRightEntitySprite().getWidth() / 2), 370);
            }
        } else if (levelManager.getPhonePiecesLeft() == 2 && levelManager.getCurrentLevel().getLevelNum() == 1)    //collected 1 phone piece
        {
            if (character.getLocation().getXcord() >= levelManager.getCurrentLevel().getEndCord() - 1200) {
                gc.drawImage(finishLineSprite, (levelManager.getCurrentLevel().getEndCord() + 500) - character.getLocation().getXcord() + (this.WIDTH / 2 - (int) character.getRightEntitySprite().getWidth() / 2), 370);
            }
        } else if (levelManager.getPhonePiecesLeft() == 1 && levelManager.getCurrentLevel().getLevelNum() == 2)    //collected 2 phone pieces
        {
            if (character.getLocation().getXcord() >= levelManager.getCurrentLevel().getEndCord() - 1200) {
                gc.drawImage(finishLineSprite, (levelManager.getCurrentLevel().getEndCord() + 500) - character.getLocation().getXcord() + (this.WIDTH / 2 - (int) character.getRightEntitySprite().getWidth() / 2), 370);
            }
        }

        //drawing obstacles
        for (Obstacle obstacle : UILauncher.getObstacleManager().getObstacleList()) {

            if (Math.abs(character.getLocation().getXcord() - obstacle.getLocation().getXcord()) <= RENDER_RANGE) {

                if (obstacle instanceof Platform) {
                    Platform platform = (Platform) obstacle;
                    if (levelManager.isLevelComplete()) {
                        gc.drawImage(new Image(SpriteURL.SINGLE_PLATFORM.getPath()), platform.getLocation().getXcord() + (this.WIDTH / 2 - (int) character.getRightEntitySprite().getWidth() / 2) - levelManager.getCurrentLevel().getEndCord(), platform.getLocation().getYcord());

                        if (platform.getLength() == 1)   //single platform of length 1
                        {
                            gc.drawImage(new Image(SpriteURL.SINGLE_PLATFORM.getPath()), platform.getLocation().getXcord() + (this.WIDTH / 2 - (int) character.getRightEntitySprite().getWidth() / 2) - levelManager.getCurrentLevel().getEndCord(), platform.getLocation().getYcord());
                        } else if (platform.getLength() > 1) {
                            for (int i = 0; i <= platform.getLength(); i++) {
                                if (i == 0)  //left end brick
                                {
                                    gc.drawImage(new Image(SpriteURL.LEFT_END_PLATFORM.getPath()), (60 * i) + platform.getLocation().getXcord() + (this.WIDTH / 2 - (int) character.getRightEntitySprite().getWidth() / 2) - levelManager.getCurrentLevel().getEndCord(), platform.getLocation().getYcord());
                                } else if (i < platform.getLength() - 1)  //middle bricks
                                {
                                    gc.drawImage(new Image(SpriteURL.REGULAR_PLATFORM.getPath()), (60 * i) + platform.getLocation().getXcord() + (this.WIDTH / 2 - (int) character.getRightEntitySprite().getWidth() / 2) - levelManager.getCurrentLevel().getEndCord(), platform.getLocation().getYcord());
                                } else if (i == platform.getLength() - 1)  //right end bricks
                                {
                                    gc.drawImage(new Image(SpriteURL.RIGHT_END_PLATFORM.getPath()), (60 * i) + platform.getLocation().getXcord() + (this.WIDTH / 2 - (int) character.getRightEntitySprite().getWidth() / 2) - levelManager.getCurrentLevel().getEndCord(), platform.getLocation().getYcord());
                                }
                            }
                        }
                    } else {
                        if (platform.getLength() == 1)   //single platform of length 1
                        {
                            gc.drawImage(new Image(SpriteURL.SINGLE_PLATFORM.getPath()), platform.getLocation().getXcord() - character.getLocation().getXcord() + (this.WIDTH / 2 - (int) character.getRightEntitySprite().getWidth() / 2), platform.getLocation().getYcord());
                        } else if (platform.getLength() > 1) {
                            for (int i = 0; i <= platform.getLength(); i++) {
                                if (i == 0)  //left end brick
                                {
                                    gc.drawImage(new Image(SpriteURL.LEFT_END_PLATFORM.getPath()), (60 * i) + platform.getLocation().getXcord() - character.getLocation().getXcord() + (this.WIDTH / 2 - (int) character.getRightEntitySprite().getWidth() / 2), platform.getLocation().getYcord());
                                } else if (i < platform.getLength() - 1)  //middle bricks
                                {
                                    gc.drawImage(new Image(SpriteURL.REGULAR_PLATFORM.getPath()), (60 * i) + platform.getLocation().getXcord() - character.getLocation().getXcord() + (this.WIDTH / 2 - (int) character.getRightEntitySprite().getWidth() / 2), platform.getLocation().getYcord());
                                } else if (i == platform.getLength() - 1)  //right end bricks
                                {
                                    gc.drawImage(new Image(SpriteURL.RIGHT_END_PLATFORM.getPath()), (60 * i) + platform.getLocation().getXcord() - character.getLocation().getXcord() + (this.WIDTH / 2 - (int) character.getRightEntitySprite().getWidth() / 2), platform.getLocation().getYcord());
                                }
                            }
                        }
                    }
/*                for (Obstacle brick : platform.getBricks()) {
                    if (levelManager.isLevelComplete()) {
                        gc.drawImage(brick.getSprite(), brick.getLocation().getXcord() + (this.WIDTH / 2 - (int) character.getRightEntitySprite().getWidth() / 2) - levelManager.getCurrentLevel().getEndCord(), brick.getLocation().getYcord());
                    } else {
                        gc.drawImage(brick.getSprite(), brick.getLocation().getXcord() - character.getLocation().getXcord() + (this.WIDTH / 2 - (int) character.getRightEntitySprite().getWidth() / 2), brick.getLocation().getYcord());
                    }
*/
                    Location loc = new Location(platform.getLocation());

                    if (UILauncher.getDebugMode()) {
                        int height = 30;
                        int width = 60 * platform.getLength();
                        this.drawHitbox(character, loc, height, width, Color.ORANGE);

                    }
                }
            }
        }

    }

    /**
     * repaints the collectibles of the level
     *
     * @param character Character variable
     */
    public void repaintCollectibles(Character character) {
        LevelManager levelManager = UILauncher.getLevelManager();

        //drawing collectibles
        for (Collectible collectible : UILauncher.getCollectiblesManager().getCollectiblesList()) {

            if (Math.abs(character.getLocation().getXcord() - collectible.getLocation().getXcord()) <= RENDER_RANGE) {

                if (levelManager.isLevelComplete()) {
                    gc.drawImage(collectible.getTheImage(), collectible.getLocation().getXcord() + (this.WIDTH / 2 - (int) character.getRightEntitySprite().getWidth() / 2) - levelManager.getCurrentLevel().getEndCord(), collectible.getLocation().getYcord());
                } else {
                    gc.drawImage(collectible.getTheImage(), collectible.getLocation().getXcord() - character.getLocation().getXcord() + (this.WIDTH / 2 - (int) character.getRightEntitySprite().getWidth() / 2), collectible.getLocation().getYcord());
                }

                if (UILauncher.getDebugMode()) {
                    this.drawHitbox(character, collectible.getLocation(), (int) collectible.getTheImage().getHeight(), (int) collectible.getTheImage().getWidth(), Color.YELLOW);
                }
            }

        }
    }

    public void repaintFlasks(Character character) {
        for (Flask flask : UILauncher.getFlaskManager().getFlaskList()) {
            gc.drawImage(flask.getSprite(), flask.getLocation().getXcord() - character.getLocation().getXcord() + (this.WIDTH / 2 - (int) character.getRightEntitySprite().getWidth() / 2), flask.getLocation().getYcord());
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
            int x = 25;
            for (int i = 0; i < character.getHealth(); i++) {
                this.gc.drawImage(new Image("/images/sprites/heart.png"), x, 25);
                x += 36 + 25;
            }
        } else {
            gc.drawImage(GAMEOVER, WIDTH / 2 - GAMEOVER.getWidth() / 2, HEIGHT / 2 - GAMEOVER.getHeight() / 2);
            sound.playETDeath();
            sound.stopTheme();
            root.getChildren().add(playAgainButton);
            timeline.pause();
            UILauncher.getGameManager().setGameOver(true);

        }
    }

    /**
     * Draws hitbox using given location, height, and width
     *
     * @param character The character object of the game
     * @param loc       Top left corner {@code Location} of {@code Hitbox}
     * @param height    Height of {@code Hitbox}
     * @param width     Width of {@code Hitbox}
     * @param color     The color of the hitbox to be drawn
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
