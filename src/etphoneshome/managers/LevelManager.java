package etphoneshome.managers;

import etphoneshome.UILauncher;
import etphoneshome.objects.Level;

import java.util.ArrayList;
import java.util.List;

public class LevelManager {

    public List<Level> levels = new ArrayList<>();

    public int currentLevelNum = 0;

    public void addLevel(Level level) {
        this.levels.add(level);
    }

    public void removeLevel(Level level) {
        this.levels.remove(level);
    }

    /**
     * Get a list of all the levels stored in the levels list
     *
     * @return A list of all the levels stored in the levels list
     */
    public List<Level> getLevels() {
        List<Level> levels = new ArrayList<>();
        for (Level level : this.levels) {
            levels.add(new Level(level));
        }
        return levels;
    }

    /**
     * Loads a level with the given level number
     *
     * @param levelNum The level number to be loaded
     */
    public void loadLevel(int levelNum) {
        for (Level level : this.getLevels()) {
            if (level.getLevelNum() == levelNum) {
                this.currentLevelNum = levelNum;
                UILauncher.getObstacleManager().loadObstacles(level);
                UILauncher.getEntityManager().loadEntities(level);
                UILauncher.getCollectiblesManager().loadCollectibles(level);
                break;
            }
        }
    }

    /**
     * Unload the current loaded level
     */
    public void unloadLevel() {
        for (Level level : this.getLevels()) {
            if (level.getLevelNum() == this.currentLevelNum) {
                UILauncher.getObstacleManager().clearObstacles();
                UILauncher.getEntityManager().clearEntities();
                UILauncher.getCollectiblesManager().clearCollectibles();
            }
        }
    }

}
