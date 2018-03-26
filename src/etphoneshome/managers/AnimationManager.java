package etphoneshome.managers;

import etphoneshome.graphics.Animation;
import etphoneshome.graphics.AnimationFrames;

public class AnimationManager {

    private Animation characterAnimation;

    public void incrementAnimations() {
        this.characterAnimation.incrementTick();
    }

    public void flipCharacterAnimationFrames() {
        if (this.characterAnimation != null) {
            String animationFramesName = this.characterAnimation.getAnimationFrames().name();
            if (animationFramesName.contains("RIGHT")) {
                this.characterAnimation.setAnimationFrames(AnimationFrames.valueOf(animationFramesName.replace("RIGHT", "LEFT")));
            } else {
                this.characterAnimation.setAnimationFrames(AnimationFrames.valueOf(animationFramesName.replace("LEFT", "RIGHT")));
            }
        }
    }

    public void setCharacterAnimation(Animation characterAnimation) {
        this.characterAnimation = characterAnimation;
    }

    public Animation getCharacterAnimation() {
        return this.characterAnimation;
    }

}
