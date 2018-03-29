package etphoneshome.entities.enemies;

import etphoneshome.graphics.SpriteURL;
import etphoneshome.objects.Location;

/**
 * This class is used for the Scientist type enemy. It is a derived class from the {@code Enemy} class
 */
public class Scientist extends Enemy {

    /**
     * Variable to see if the specific {@code Scientist} has thrown a flask
     */
    private boolean thrownFlask = false;

    /**
     * default constructor that sets the sprites of {@code Scientist}
     */
    public Scientist() {
        this.setRightEntitySprite(SpriteURL.SCIENTIST_RIGHT.getPath());
        this.setLeftEntitySprite(SpriteURL.SCIENTIST_LEFT.getPath());
    }

    /**
     * constructor thats sets location and sprites of {@code Scientist}
     *
     * @param location starting location of {@code Scientist}
     */
    public Scientist(Location location) {
        super(location);
        this.setRightEntitySprite(SpriteURL.SCIENTIST_RIGHT.getPath());
        this.setLeftEntitySprite(SpriteURL.SCIENTIST_LEFT.getPath());
    }

    public Scientist(Scientist scientist) {
        super(scientist);
        this.setRightEntitySprite(SpriteURL.SCIENTIST_RIGHT.getPath());
        this.setLeftEntitySprite(SpriteURL.SCIENTIST_LEFT.getPath());
        this.setThrownFlask(scientist.getThrownFlask());
    }

    /**
     * get the thrownflask of the {@code Scientist}
     *
     * @return thrownFlask of {@code Scientist}
     */
    public boolean getThrownFlask() {
        return this.thrownFlask;
    }

    /**
     * Sets the status of thrownFlask of {@code Scientist}
     *
     * @param thrown if {@code Scientist} has thrown a flask
     */
    public void setThrownFlask(boolean thrown) {
        this.thrownFlask = thrown;
    }

}
