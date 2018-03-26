package etphoneshome.graphics;

import javafx.scene.image.Image;

import java.util.ArrayList;

public class Animation {

    private int tick;
    private int lastTick;
    private AnimationFrames animationFrames;

    private ArrayList<Image> frames;

    public Animation(AnimationFrames animationFrames) {
        this.frames = animationFrames.getFrames();
        this.lastTick = this.frames.size() * 20;
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

    public void getSprite() {
        this.frames.get((int) Math.floor(this.tick/20));
    }

    public AnimationFrames getAnimationFrames() {
        return this.animationFrames;
    }

    public void setAnimationFrames(AnimationFrames animationFrames) {
        this.animationFrames = animationFrames;
    }

}
