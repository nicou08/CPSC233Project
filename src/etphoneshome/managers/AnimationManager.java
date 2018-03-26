package etphoneshome.managers;

import etphoneshome.graphics.Animation;
import etphoneshome.graphics.AnimationFrames;
import etphoneshome.objects.Direction;

public class AnimationManager {

    private Animation characterAnimation = null;

    public void incrementAnimations() {
        if (this.characterAnimation != null) {
            this.characterAnimation.incrementTick();
        }
    }

    public void flipCharacterAnimationFrames(Direction direction) {
        if (this.characterAnimation != null) {
            String animationFramesName = this.characterAnimation.getAnimationFrames().name();
            if (direction == Direction.WEST) {
                this.characterAnimation.setAnimationFrames(AnimationFrames.valueOf(animationFramesName.replace("RIGHT", "LEFT")));
            } else if (direction == Direction.EAST) {
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

    public void runGarbageCollector() {
        if (this.characterAnimation != null) {
            if (this.characterAnimation.getTick() >= this.characterAnimation.getLastTick()) {
                this.characterAnimation = null;
            }
        }
    }

}
