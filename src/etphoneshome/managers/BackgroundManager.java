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
        this.backgroundLocation.add((int) Math.round(this.backgroundVelocity.getHorizontalVelocity()), (int) Math.round(this.backgroundVelocity.getVerticalVelocity()));
        if (this.backgroundLocation.getXcord() <= this.graphicsRepainter.WIDTH * -2) {
            this.backgroundLocation.setXcord(this.backgroundLocation.getXcord() + this.graphicsRepainter.WIDTH);
        }
        if (this.backgroundLocation.getXcord() >= -this.graphicsRepainter.WIDTH) {
            this.backgroundLocation.setXcord(this.backgroundLocation.getXcord() - this.graphicsRepainter.WIDTH);
        }
    }
}
