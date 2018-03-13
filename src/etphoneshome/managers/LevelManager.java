package etphoneshome.managers;

import etphoneshome.UILauncher;
import etphoneshome.objects.Level;

import java.util.ArrayList;
import java.util.List;

public class LevelManager {

    public List<Level> levels = new ArrayList<>();

    public void addLevel(Level level) {
        this.levels.add(level);
    }

    public void removeLevel(Level level) {
        this.levels.remove(level);
    }

    public List<Level> getLevels() {
        List<Level> levels = new ArrayList<>();
        for (Level level : this.levels) {
            levels.add(new Level(level));
        }
        return levels;
    }

    public void loadLevel(int levelNum) {
        for (Level level : this.getLevels()) {
            if (level.getLevelNum() == levelNum) {
                UILauncher.getObstacleManager().loadObstacles(level);
                break;
            }
        }
    }

}
