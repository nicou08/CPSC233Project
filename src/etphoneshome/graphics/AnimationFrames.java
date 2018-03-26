package etphoneshome.graphics;

import javafx.scene.image.Image;

import java.util.ArrayList;

public enum AnimationFrames {

    POLICE_DEATH_LEFT(""),
    POLICE_DEATH_RIGHT(""),
    ET_HURT_LEFT(""),
    ET_HURT_RIGHT("");

    private ArrayList<Image> frames = new ArrayList<>();

    AnimationFrames(String... framePaths) {
        for (String framePath : framePaths) {
            this.frames.add(new Image(framePath));
        }
    }

    public ArrayList<Image> getFrames() {
        return this.frames;
    }
}
