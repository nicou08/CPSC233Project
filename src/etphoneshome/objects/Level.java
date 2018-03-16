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

    private int levelNum, endCord;
    private List<Obstacle> obstacles = new ArrayList<>();
    private List<Enemy> enemies = new ArrayList<>();
    private List<Collectible> phonePieces = new ArrayList<>();

    public Level(Level level) {
        this.levelNum = level.getLevelNum();

        this.endCord = level.getEndCord();

        for (Obstacle obstacle : level.getObstacles()) {
            this.obstacles.add(obstacle);
        }

        this.enemies = new ArrayList<>(level.enemies);

        this.phonePieces = new ArrayList<>(level.phonePieces);
    }

    public Level(String levelName) {
        System.out.println("" + this.getClass().getClassLoader().getResource("").getPath());
        File file = new File(new File("ET Phones Home").getAbsolutePath() + File.separator + "src" + File.separator + "levels" + File.separator + levelName + ".txt");

        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.startsWith("level-num: ")) {
                    this.levelNum = Integer.valueOf(line.replace("level-num: ", ""));
                }
                if (line.startsWith("end-cord: ")) {
                    this.endCord = Integer.valueOf(line.replace("end-cord: ", ""));
                }
                if (line.equals("  platform:")) {
                    int xCord = Integer.valueOf(scanner.nextLine().replace("    x-cord: ", ""));
                    int yCord = Integer.valueOf(scanner.nextLine().replace("    y-cord: ", ""));
                    int length = Integer.valueOf(scanner.nextLine().replace("    length: ", ""));
                    this.obstacles.add(new Platform(new Location(xCord, yCord), length));
                }
                if (line.equals("  police:")) {
                    int xCord = Integer.valueOf(scanner.nextLine().replace("    x-cord: ", ""));
                    int yCord = Integer.valueOf(scanner.nextLine().replace("    y-cord: ", "")) - (int) new Police().getRightEntitySprite().getHeight();
                    Police police = (new Police(new Location(xCord, yCord)));
                    this.enemies.add(police);
                }
                if (line.equals("  scientist:")) {
                    int xCord = Integer.valueOf(scanner.nextLine().replace("    x-cord: ", ""));
                    int yCord = Integer.valueOf(scanner.nextLine().replace("    y-cord: ", "")) - (int) new Scientist().getRightEntitySprite().getHeight();
                    this.enemies.add(new Scientist(new Location(xCord, yCord)));
                }
                if (line.equals("  phone-piece:")) {
                    String typeString = scanner.nextLine().replace("    type: ", "");
                    PhonePieceType phonePieceType = PhonePieceType.ANTENNA;
                    if (PhonePieceType.valueOf(typeString) != null) {
                        phonePieceType = PhonePieceType.valueOf(typeString);
                    }
                    int xCord = Integer.valueOf(scanner.nextLine().replace("    x-cord: ", ""));
                    int yCord = Integer.valueOf(scanner.nextLine().replace("    y-cord: ", "")) - (int) new PhonePiece(new Location(0, 0), phonePieceType).getHeight();
                    this.phonePieces.add(new PhonePiece(new Location(xCord, yCord), phonePieceType));
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

    public int getEndCord() {
        return this.endCord;
    }

    public List<Obstacle> getObstacles() {
        return this.obstacles;
    }

    public List<Enemy> getEnemies() {
        return this.enemies;
    }

    public List<Collectible> getPhonePieces() {
        return this.phonePieces;
    }

}
