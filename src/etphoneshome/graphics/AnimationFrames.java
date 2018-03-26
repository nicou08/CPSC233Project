package etphoneshome.graphics;

import javafx.embed.swing.JFXPanel;

import java.util.ArrayList;

public enum AnimationFrames {

    POLICE_DEATH_LEFT(""),

    POLICE_DEATH_RIGHT(""),

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
