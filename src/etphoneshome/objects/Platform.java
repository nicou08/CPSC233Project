package etphoneshome.objects;

import java.util.ArrayList;

/**
 * This class is used for the platforms that the player can stand and jump on to.
 */
public class Platform extends Obstacle {
    /**
     * The number of bricks in the {@code Platform}
     */
    private int length;     //number of blocks (width of each block is 60 pixels). Must be at least 1

    /**
     * The hitbox of the entire {@code Platform}
     */
    private Hitbox platformHitbox;

    /**
     * The bricks of the {@code Platform}
     */
    private ArrayList<Obstacle> bricks;

    /**
     * The width of any type of brick
     */
    private final int WIDTH = 60;

    /**
     * The height of any type of brick
     */
    private final int HEIGHT = 30;

    public Platform(Platform platform) {
        this(platform.getLocation(), platform.getLength());
    }

    /**
     * Builds a single obstacle at the location {@code Location} and with a hitbox {@code Hitbox}.
     *
     * @param location The location object associated with the {@code Platform}
     */
    private Platform(Location location)   //makes a single platform object. Used in below constructor
    {
        super(location);

        //Location locationCopy = new Location(location.getXcord(), location.getYcord());

        //this.platformHitbox = new Hitbox(locationCopy, this.HEIGHT, this.WIDTH);

        this.length = 1;
    }

    /**
     * A constructor used to build a platform of {@code length} bricks long starting at the left end brick location
     *
     * @param platformStartLocation The starting location of the left end brick
     * @param length                The total number of bricks in the platform
     */
    public Platform(Location platformStartLocation, int length) {
        super(platformStartLocation);
        this.length = length;

        if (length < 1) {
            System.out.println("Platform must be at least 1 brick long! Unable to construct platform!");
        } else {    //platform is at least 1 brick long
            bricks = new ArrayList<>(this.length);

            Location nextBrickLocation = new Location(platformStartLocation.getXcord(), platformStartLocation.getYcord());

            for (int i = 0; i < this.length; i++) {   //creates every brick in the array list
                bricks.add(i, new Platform(nextBrickLocation));     //adds a new brick

                nextBrickLocation = this.addBrickWidth(nextBrickLocation);  //shifts the next brick location 60 pixels (WIDTH) to the right

                bricks.get(i).setSprite("images/sprites/regularPlatform.png");   //sets every brick to the default
                //sprite of the middle brick
            }

            //sets the correct sprites based off how many bricks are in the platform
            if (this.length == 1) {
                bricks.get(0).setSprite("images/sprites/singlePlatform.png");
            } else if (this.length > 1) {
                bricks.get(0).setSprite("images/sprites/leftEndPlatform.png");
                bricks.get(this.length - 1).setSprite("images/sprites/rightEndPlatform.png");
            }

            //set the hitbox of the entire platform
            this.platformHitbox = new Hitbox(bricks.get(0).getLocation(), HEIGHT, this.length * WIDTH);

        }
    }

    /**
     * Shifts the location 60 pixels ({@code WIDTH}) to the right for the next brick
     *
     * @param location The location of the last placed brick
     * @return The new location 60 pixels right of the last placed brick
     */
    private Location addBrickWidth(Location location) {
        Location newLocation = new Location(location.getXcord(), location.getYcord());

        newLocation = new Location(location.getXcord() + WIDTH, location.getYcord());

        return newLocation;
    }

    /**
     * Returns the bricks of the {@code Platform}
     *
     * @return The bricks of the {@code Platform}
     */
    public ArrayList<Obstacle> getBricks() {
        ArrayList<Obstacle> bricksCopy = new ArrayList<>();

        for (int i = 0; i < this.getLength(); i++) {
            Location locationCopy = new Location(this.bricks.get(i).getLocation().getXcord(), this.bricks.get(i).getLocation().getYcord());

            bricksCopy.add(new Platform(locationCopy));

            bricksCopy.get(i).setSprite("images/sprites/regularPlatform.png");
        }
        //sets the correct sprites based off how many bricks are in the platform
        if (this.length == 1) {
            bricksCopy.get(0).setSprite("images/sprites/singlePlatform.png");
        } else if (this.length > 1) {
            bricksCopy.get(0).setSprite("images/sprites/leftEndPlatform.png");
            bricksCopy.get(this.length - 1).setSprite("images/sprites/rightEndPlatform.png");
        }

        return bricksCopy;
    }

    /**
     * Returns the number of bricks in the {@code Platform}
     *
     * @return The number of bricks in the {@code Platform}
     */
    public int getLength() {
        return this.length;
    }

    /**
     * Returns the entire hitbox of the {@code Platform}
     *
     * @return The entire hitbox of the {@code Platform}
     */
    public Hitbox getHitbox() {
        Location locationCopy = new Location(this.getLocation().getXcord(), this.getLocation().getYcord());
        Hitbox hitboxCopy = new Hitbox(locationCopy, this.platformHitbox.getHeight(), this.platformHitbox.getWidth());

        return hitboxCopy;
    }

    public int getWidth() {
        return this.WIDTH;
    }

    public int getHeight() {
        return this.HEIGHT;
    }

    //MAIN METHOD USED FOR TESTING THE PLATFORM METHODS, CONSTRUCTORS AND HITBOX COLLISION

    public static void main(String[] args) {
        Location k1 = new Location(100, 120);
        Location k2 = new Location(300, 309);
        Location k3 = new Location(600, 668);
        Location c = new Location(100, 100);

        Hitbox colltest = new Hitbox(c, 5, 5);

        Platform singleBrick = new Platform(k1, 1);
        Platform twoBrick = new Platform(k2, 2);
        Platform multiBrick = new Platform(k3, 5);

        //test location for each platform

        System.out.println("\nTESTING PLATFORM LOCATIONS.....\n");

        System.out.println("Should be 100: " + singleBrick.getBricks().get(0).getLocation().getXcord());
        System.out.println("Should be 120: " + singleBrick.getBricks().get(0).getLocation().getYcord());

        System.out.println("Should be 300: " + twoBrick.getBricks().get(0).getLocation().getXcord());
        System.out.println("Should be 309: " + twoBrick.getBricks().get(0).getLocation().getYcord());
        System.out.println("Should be 360: " + twoBrick.getBricks().get(1).getLocation().getXcord());
        System.out.println("Should be 309: " + twoBrick.getBricks().get(1).getLocation().getYcord());

        System.out.println("Should be 600: " + multiBrick.getBricks().get(0).getLocation().getXcord());
        System.out.println("Should be 668: " + multiBrick.getBricks().get(0).getLocation().getYcord());
        System.out.println("Should be 660: " + multiBrick.getBricks().get(1).getLocation().getXcord());
        System.out.println("Should be 668: " + multiBrick.getBricks().get(1).getLocation().getYcord());
        System.out.println("Should be 720: " + multiBrick.getBricks().get(2).getLocation().getXcord());
        System.out.println("Should be 668: " + multiBrick.getBricks().get(2).getLocation().getYcord());
        System.out.println("Should be 780: " + multiBrick.getBricks().get(3).getLocation().getXcord());
        System.out.println("Should be 668: " + multiBrick.getBricks().get(3).getLocation().getYcord());
        System.out.println("Should be 840: " + multiBrick.getBricks().get(4).getLocation().getXcord());
        System.out.println("Should be 668: " + multiBrick.getBricks().get(4).getLocation().getYcord());

        System.out.println("\nPLATFORM LOCATION TESTS COMPLETE!\n");

        //test hitbox for each platform
        System.out.println("\nTESTING PLATFORM HITBOX LOCATIONS.....\n");

        System.out.println("Should be 100: " + singleBrick.getHitbox().getTopLeftCorner().getXcord());
        System.out.println("Should be 120: " + singleBrick.getHitbox().getTopLeftCorner().getYcord());
        System.out.println("Should be 60: " + singleBrick.getHitbox().getWidth());
        System.out.println("Should be 30: " + singleBrick.getHitbox().getHeight());

        System.out.println("Should be 300: " + twoBrick.getHitbox().getTopLeftCorner().getXcord());
        System.out.println("Should be 309: " + twoBrick.getHitbox().getTopLeftCorner().getYcord());
        System.out.println("Should be 120: " + twoBrick.getHitbox().getWidth());
        System.out.println("Should be 30: " + twoBrick.getHitbox().getHeight());

        System.out.println("Should be 600: " + multiBrick.getHitbox().getTopLeftCorner().getXcord());
        System.out.println("Should be 668: " + multiBrick.getHitbox().getTopLeftCorner().getYcord());
        System.out.println("Should be 300: " + multiBrick.getHitbox().getWidth());
        System.out.println("Should be 30: " + multiBrick.getHitbox().getHeight());

        System.out.println("\nPLATFORM HITBOX TESTING COMPLETE!\n");

        //test hitbox collision
        System.out.println("\nTESTING PLATFORM HITBOX COLLISION.....\n");

        //top platform collision
        System.out.println("Should be false: " + colltest.areColliding(singleBrick.getHitbox()));
        c =new Location(c.getXcord(), c.getYcord() + 14);
        System.out.println("Should be false: " + colltest.areColliding(singleBrick.getHitbox()));
        c = new Location(c.getXcord(), c.getYcord() + 1);
        System.out.println("Should be true: " + colltest.areColliding(singleBrick.getHitbox()));

        //bottom platform collision
        c = new Location(c.getXcord(), 160);
        System.out.println("Should be false: " + colltest.areColliding(singleBrick.getHitbox()));
        c = new Location(c.getXcord(), c.getYcord() + -9);
        System.out.println("Should be false: " + colltest.areColliding(singleBrick.getHitbox()));
        c = new Location(c.getXcord(), c.getYcord() + -5);
        System.out.println("Should be true: " + colltest.areColliding(singleBrick.getHitbox()));

        //left side collision
        c = new Location(c.getXcord(), 130);
        c = new Location(90, c.getYcord());
        System.out.println("Should be false: " + colltest.areColliding(singleBrick.getHitbox()));
        c= new Location(c.getXcord() +4, c.getYcord());
        System.out.println("Should be false: " + colltest.areColliding(singleBrick.getHitbox()));
        c= new Location(c.getXcord() +1, c.getYcord());
        System.out.println("Should be true: " + colltest.areColliding(singleBrick.getHitbox()));

        //right side collision
        c = new Location(c.getXcord(), 662);
        c = new Location(780, c.getYcord());
        System.out.println("Should be false: " + colltest.areColliding(multiBrick.getHitbox()));
        c = new Location(c.getXcord(), c.getYcord() + 3);
        System.out.println("Should be true: " + colltest.areColliding(multiBrick.getHitbox()));
        c = new Location(c.getXcord(), c.getYcord() + 1);
        System.out.println("Should be true: " + colltest.areColliding(multiBrick.getHitbox()));

        //double platform top collision
        ArrayList<Obstacle> copyBricks = multiBrick.getBricks();

        multiBrick.bricks.remove(0);
        multiBrick.bricks.remove(0);

        System.out.println("Should be 3: " + multiBrick.bricks.size());
        System.out.println("Should be 5: " + copyBricks.size());


        System.exit(0);
    }


}
