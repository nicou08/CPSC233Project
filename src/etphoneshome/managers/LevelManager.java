package etphoneshome.managers;

import etphoneshome.UILauncher;
import etphoneshome.objects.Level;
import etphoneshome.objects.PhonePieceType;

import java.util.ArrayList;
import java.util.List;

public class LevelManager {

    /**
     * list of the levels
     */
    private List<Level> levels = new ArrayList<>();

    /**
     * current level of the game
     */
    private int currentLevelNum = -1;

    /**
     * list of phonePieces collected
     */
    private List<PhonePieceType> collectedPieces = new ArrayList<>();

    /**
     * boolean to check if level is over
     */
    private boolean levelComplete = false;

    /**
     * add level to the game
     *
     * @param level Level to be added
     */
    public void addLevel(Level level) {
        this.levels.add(level);
    }

    /**
     * remove a level from the game
     *
     * @param level level to remove
     */
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
                this.loadLevel(level);
            }
        }
    }

    /**
     * Loads the given level
     *
     * @param level The level to be loaded
     */
    public void loadLevel(Level level) {
        this.unloadLevel();
        this.currentLevelNum = level.getLevelNum();
        UILauncher.getObstacleManager().loadObstacles(level);
        UILauncher.getEntityManager().loadEntities(level);
        UILauncher.getCollectiblesManager().loadCollectibles(level);
        UILauncher.getBackgroundManager().loadBackground(level);
        this.collectedPieces.clear();
        this.levelComplete = false;
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
                this.collectedPieces.clear();
                this.currentLevelNum = 0;
                this.levelComplete = false;
            }
        }
    }

    /**
     * Returns the current loaded level
     *
     * @return The current loaded level
     */
    public Level getCurrentLevel() {
        return this.levels.get(this.currentLevelNum);
    }

    /**
     * Get the number of phone pieces left to be collected
     *
     * @return The number of phone pieces left to be collected
     */
    public int getPhonePiecesLeft() {
        return PhonePieceType.values().length - this.collectedPieces.size();
    }

    /**
     * Adds the given phonePieceType to the collectedPieces list
     *
     * @param type the {@code PhonePieceType} to add to the collectedPieces list
     */
    public void addCollectedPhonePiece(PhonePieceType type) {
        this.collectedPieces.add(type);
    }

    /**
     * Returns a list of the collected phone piece types
     *
     * @return A list of the collected phone piece types
     */
    public List<PhonePieceType> getCollectedPhonePieceTypes() {
        return this.collectedPieces;
    }

    /**
     * Updates the levelComplete value
     *
     * @param levelComplete New value for levelComplete variable
     */
    public void setLevelComplete(boolean levelComplete) {
        this.levelComplete = levelComplete;
    }

    /**
     * Returns true if the level is complete, and false otherwise
     *
     * @return True if the level is complete, and false otherwise
     */
    public boolean isLevelComplete() {
        return this.levelComplete;
    }

}
