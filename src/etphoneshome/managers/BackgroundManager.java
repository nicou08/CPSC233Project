package etphoneshome.managers;

import etphoneshome.graphics.GraphicsRepainter;
import etphoneshome.objects.Location;
import etphoneshome.objects.Velocity;

public class BackgroundManager {

    private GraphicsRepainter graphicsRepainter;

    public BackgroundManager(GraphicsRepainter graphicsRepainter) {
        this.graphicsRepainter = graphicsRepainter;
    }

    /**
     * The velocity of the background which makes it look like you're actually moving
     */
    private Velocity backgroundVelocity = new Velocity();
    private Location backgroundLocation = new Location(0, 0);

    public Velocity getBackgroundVelocity() {
        return this.backgroundVelocity;
    }

    public Location getBackgroundLocation() {
        return this.backgroundLocation;
    }

    public void updateBackgroundLocation() {
        int newX = this.backgroundLocation.getXcord() + (int) this.backgroundVelocity.getHorizontalVelocity();
        int newY = this.backgroundLocation.getYcord() + (int) this.backgroundVelocity.getVerticalVelocity();
        this.backgroundLocation = new Location(newX, newY);
        if (newX <= this.graphicsRepainter.WIDTH * -2) {
            newX = this.backgroundLocation.getXcord() + this.graphicsRepainter.WIDTH;
        }
        this.backgroundLocation = new Location(newX, newY);
        if (newX >= -this.graphicsRepainter.WIDTH) {
            newX = this.backgroundLocation.getXcord() - this.graphicsRepainter.WIDTH;
        }
        this.backgroundLocation = new Location(newX, newY);
    }
}
