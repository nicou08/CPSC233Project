package etphoneshome.objects;

import etphoneshome.graphics.SpriteURL;
import javafx.scene.image.Image;

/**
 * This class is for the attack of the scientists in the game.
 * Using getLocation will return a copy of the location
 */
public class Flask {

    private static final int WIDTH = 30;
    private static final int HEIGHT = 40;
    private Velocity velocity;
    private Direction direction;

    private Image image = new Image(SpriteURL.FLASK.getPath());

    private Location location;

    private Hitbox hitbox;

    public Flask(Location location, Velocity velocity) {
        this.location = location;
        this.hitbox = new Hitbox(location, WIDTH, HEIGHT);
        this.velocity = velocity;
    }

    public Location getLocation() {
        return new Location(this.location);
    }

    public void setLocation(Location location) {
        this.location = location;
        hitbox = new Hitbox(this.location, WIDTH, HEIGHT);
    }

    public Hitbox getHitbox() {
        return this.hitbox;
    }

    public Image getSprite() {
        return this.image;
    }

    public int getWidth() {
        return WIDTH;
    }

    public int getHeight() {
        return HEIGHT;
    }

    public Velocity getVelocity() {
        return this.velocity;
    }


    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
