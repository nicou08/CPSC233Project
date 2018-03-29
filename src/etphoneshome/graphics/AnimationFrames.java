package etphoneshome.graphics;

import javafx.embed.swing.JFXPanel;

import java.util.ArrayList;

public enum AnimationFrames {

    POLICE_DEATH_LEFT(10, SpriteURL.POLICE_HURT_LEFT.getPath(), SpriteURL.POLICE_LEFT.getPath(), SpriteURL.POLICE_HURT_LEFT.getPath(), SpriteURL.POLICE_LEFT.getPath(), SpriteURL.POLICE_HURT_LEFT.getPath()),

    POLICE_DEATH_RIGHT(10, SpriteURL.POLICE_HURT_RIGHT.getPath(), SpriteURL.POLICE_RIGHT.getPath(), SpriteURL.POLICE_HURT_RIGHT.getPath(), SpriteURL.POLICE_RIGHT.getPath(), SpriteURL.POLICE_HURT_RIGHT.getPath()),

    SCIENTIST_DEATH_LEFT(10, SpriteURL.SCIENTIST_HURT_LEFT.getPath(), SpriteURL.SCIENTIST_LEFT.getPath(), SpriteURL.SCIENTIST_HURT_LEFT.getPath(), SpriteURL.SCIENTIST_LEFT.getPath(), SpriteURL.SCIENTIST_HURT_LEFT.getPath()),

    SCIENTIST_DEATH_RIGHT(10, SpriteURL.SCIENTIST_HURT_RIGHT.getPath(), SpriteURL.SCIENTIST_RIGHT.getPath(), SpriteURL.SCIENTIST_HURT_RIGHT.getPath(), SpriteURL.SCIENTIST_RIGHT.getPath(), SpriteURL.SCIENTIST_HURT_RIGHT.getPath()),

    ET_HURT_LEFT(10, SpriteURL.ET_HURT_LEFT.getPath(), SpriteURL.ET_LEFT.getPath(), SpriteURL.ET_HURT_LEFT.getPath(), SpriteURL.ET_LEFT.getPath(), SpriteURL.ET_HURT_LEFT.getPath()),

    ET_HURT_RIGHT(10, SpriteURL.ET_HURT_RIGHT.getPath(), SpriteURL.ET_RIGHT.getPath(), SpriteURL.ET_HURT_RIGHT.getPath(), SpriteURL.ET_RIGHT.getPath(), SpriteURL.ET_HURT_RIGHT.getPath());

    private int frameLength = 20;
    private ArrayList<String> framePaths = new ArrayList<>();
    private JFXPanel jfxPanel = new JFXPanel(); //this is needed for the class to run since there is an image attached

    AnimationFrames(String... framePaths) {
        for (String framePath : framePaths) {
            this.framePaths.add(framePath);
        }
    }

    AnimationFrames(int frameLength, String... framePaths) {
        this.frameLength = frameLength;
        for (String framePath : framePaths) {
            this.framePaths.add(framePath);
        }
    }

    public ArrayList<String> getFramePaths() {
        return this.framePaths;
    }

    public int getFrameLength() {
        return this.frameLength;
    }
}
