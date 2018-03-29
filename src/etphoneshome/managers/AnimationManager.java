package etphoneshome.managers;

import etphoneshome.entities.enemies.Enemy;
import etphoneshome.entities.enemies.Police;
import etphoneshome.entities.enemies.Scientist;
import etphoneshome.graphics.Animation;
import etphoneshome.graphics.AnimationFrames;
import etphoneshome.objects.Direction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnimationManager {

    private EntityManager entityManager;

    private Animation characterAnimation = null;
    private HashMap<Enemy, Animation> enemyDeathAnimations = new HashMap<>();

    public AnimationManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void incrementAnimations() {
        if (this.characterAnimation != null) {
            this.characterAnimation.incrementTick();
        }

        for (Animation animation : this.enemyDeathAnimations.values()) {
            animation.incrementTick();
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

    public void addEnemyDeathAnimation(Enemy enemy) {
        if (enemy instanceof Police) {
            if (enemy.isFacingRight()) {
                this.enemyDeathAnimations.put(enemy, new Animation(AnimationFrames.POLICE_DEATH_RIGHT));
            } else {
                this.enemyDeathAnimations.put(enemy, new Animation(AnimationFrames.POLICE_DEATH_LEFT));
            }
        } else if (enemy instanceof Scientist) {
            if (enemy.isFacingRight()) {
                this.enemyDeathAnimations.put(enemy, new Animation(AnimationFrames.SCIENTIST_DEATH_RIGHT));
            } else {
                this.enemyDeathAnimations.put(enemy, new Animation(AnimationFrames.SCIENTIST_DEATH_LEFT));
            }
        }
    }

    public Animation getEnemyDeathAnimation(Enemy enemy) {
        return this.enemyDeathAnimations.get(enemy);
    }

    public void runGarbageCollector() {
        if (this.characterAnimation != null) {
            if (this.characterAnimation.getTick() >= this.characterAnimation.getLastTick()) {
                this.characterAnimation = null;
            }
        }

        List<Enemy> tobeRemoved = new ArrayList<>();
        for (Map.Entry<Enemy, Animation> animationEntry : this.enemyDeathAnimations.entrySet()) {
            Animation enemyAnimation = animationEntry.getValue();
            if (enemyAnimation.getTick() >= enemyAnimation.getLastTick()) {
                tobeRemoved.add(animationEntry.getKey());
            }
        }

        for (Enemy enemy : tobeRemoved) {
            this.enemyDeathAnimations.remove(enemy);
            this.entityManager.removeEnemy(enemy);
        }
    }

}
