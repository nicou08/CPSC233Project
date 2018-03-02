package etphoneshome.graphics;

import etphoneshome.entities.characters.Character;
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

    private final int WIDTH = 1728;
    private final int HEIGHT = 972;
    private final Image BACKGROUND = new Image("/images/backgrounds/backgroundRESIZED.jpg");
    private Canvas canvas = new Canvas(this.WIDTH, this.HEIGHT);
    private GraphicsContext gc = canvas.getGraphicsContext2D();
    private Group root = new Group();
    private Scene scene = new Scene(root);
    private Timeline timeline = new Timeline();
    private EntityManager entityManager;
    private Character character;
    private int tick;
    private Stage stage;




    public void setCharacter(Character character) {
        this.character = character;
    }

    public void start(Stage stage) {
        this.stage = stage;
        this.createWindow(stage);
        startTimeline();
    }

    public void createWindow(Stage stage) {

        this.root.getChildren().add(this.canvas);
        stage.setScene(this.scene);
        this.gc.drawImage(this.BACKGROUND, 0, 0);
        stage.show();
    }

    public void goLaunch(String[] args, Character character, EntityManager entityManager) {
        this.character = character;
        if (character == null) {System.out.print("character is null RIGHT FROM THE JUMP");}
        this.entityManager = entityManager;
        super.launch(args);
    }

    public void startTimeline() {
        System.out.print("timeline start");
        this.timeline.setCycleCount(Animation.INDEFINITE);

        KeyFrame kf = new KeyFrame(Duration.millis(20), e -> {

            this.tick++;
            Velocity velocity = character.getVelocity();
            Location characterLocation = this.character.getLocation();

            if (this.tick % 2 ==0){
                characterLocation.add((int) Math.round(velocity.getHorizontalVelocity()), (int) Math.round(velocity.getVerticalVelocity()));
            }

            if (this.tick % 4 == 0 && velocity.getVerticalVelocity() != 0) {
                velocity.changeVerticalVelocity(-1);
            }

            /*scene.setOnKeyReleased( k -> {
                String code = k.getCode().toString();
                if (ETy==480 && code == "UP"){
                    ymotion= -50;
                }

            });*/

            /*if(isdead()){
                time.pause();
                root.getChildren().add(button);
                button.setOnMouseClicked( k -> {
                    drawInitial(scene, gc, primaryStage);
                    ETy = 480;
                    xposition = (rand.nextInt(3)+7)*200;
                    root.getChildren().remove(button);
                    time.play();

                });

            }*/

            /*if (xposition == 0) {
                xposition = (rand.nextInt(3)+7)*200;
            }*/

            System.out.print("drawing background");
            gc.drawImage(this.BACKGROUND, 0, 0);
            System.out.print("drawing chracter");
            gc.drawImage(this.character.getEntitySprite(), characterLocation.getXcord(), characterLocation.getYcord());
            gc.drawImage(this.character.getEntitySprite(), 200, 200);
            //gc.drawImage(police, xposition, y);
            stage.show();

            /*if(ETy > 480 && ymotion !=0){
                ymotion = 0;
                ETy = 480;
                gc.drawImage(background, 0, 0);
                gc.drawImage(ET, ETx, ETy);
                gc.drawImage(police, xposition, y);
                primaryStage.show();
            }*/
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
