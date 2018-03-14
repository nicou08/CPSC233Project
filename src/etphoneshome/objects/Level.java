package etphoneshome.objects;

import etphoneshome.entities.enemies.Enemy;
import etphoneshome.entities.enemies.Police;
import etphoneshome.entities.enemies.Scientist;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Level {

    private int levelNum;
    private List<Obstacle> obstacles = new ArrayList<>();
    private List<Enemy> enemies = new ArrayList<>();

    public Level(Level level) {
        this.levelNum = level.getLevelNum();
        for (Obstacle obstacle : level.getObstacles()) {
            if (obstacle instanceof Platform) {
                this.obstacles.add(new Platform((Platform) obstacle));
            } else {
                this.obstacles.add(new Obstacle(obstacle));
            }
        }
    }

    public Level(String levelName) {
        File file = new File(new File("ET Phones Home").getAbsolutePath() + File.separator + "src" + File.separator + "levels" + File.separator + levelName + ".txt");

        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.startsWith("level-num: ")) {
                    this.levelNum = Integer.valueOf(line.replace("level-num: ", ""));
                }
                if (line.equals("  platform:")) {
                    int xCord = Integer.valueOf(scanner.nextLine().replace("    x-cord: ", ""));
                    int yCord = Integer.valueOf(scanner.nextLine().replace("    y-cord: ", ""));
                    int length = Integer.valueOf(scanner.nextLine().replace("    length: ", ""));
                    this.obstacles.add(new Platform(new Location(xCord, yCord), length));
                }
                if (line.equals("  police:")) {
                    int xCord = Integer.valueOf(scanner.nextLine().replace("    x-cord: ", ""));
                    int yCord = Integer.valueOf(scanner.nextLine().replace("    y-cord: ", ""));
                    this.enemies.add(new Police(new Location(xCord, yCord)));
                }
                if (line.equals("  scientist:")) {
                    int xCord = Integer.valueOf(scanner.nextLine().replace("    x-cord: ", ""));
                    int yCord = Integer.valueOf(scanner.nextLine().replace("    y-cord: ", ""));
                    this.enemies.add(new Scientist(new Location(xCord, yCord)));
                }
            }
        } catch (IOException e) {
            System.out.println("FAILED TO LOAD: " + levelName + ".txt");
            levelNum = -1;
        }
    }

    public int getLevelNum() {
        return this.levelNum;
    }

    public List<Obstacle> getObstacles() {
        return this.obstacles;
    }

    public List<Enemy> getEnemies() { return this.enemies; }


}
