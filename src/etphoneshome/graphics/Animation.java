package etphoneshome.graphics;

import javafx.embed.swing.JFXPanel;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class Animation {

    private JFXPanel jfxPanel = new JFXPanel(); //this is needed for the class to run since there is an image attached

    private int tick;
    private int lastTick;
    private AnimationFrames animationFrames;
    private ArrayList<Image> frames = new ArrayList<>();

    public Animation(AnimationFrames animationFrames) {
        this.setAnimationFrames(animationFrames);
        this.lastTick = this.frames.size() * animationFrames.getFrameLength();
    }

    public int getTick() {
        return this.tick;
    }

    public void incrementTick() {
        this.tick++;
    }

    public int getLastTick() {
        return this.lastTick;
    }

    public Image getSprite() {
        return this.frames.get((int) Math.floor(this.tick / animationFrames.getFrameLength()));
    }

    public AnimationFrames getAnimationFrames() {
        return this.animationFrames;
    }

    public void setAnimationFrames(AnimationFrames animationFrames) {
        this.animationFrames = animationFrames;
        this.frames.clear();
        for (String framePath : animationFrames.getFramePaths()) {
            this.frames.add(new Image(framePath));
        }
    }

}
