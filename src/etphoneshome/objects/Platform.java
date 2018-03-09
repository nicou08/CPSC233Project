package etphoneshome.objects;

import java.util.ArrayList;

/**
 * This class is used for the platforms that the player can stand and jump on to.
 */
public class Platform extends Obstacle
{
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

    /**
     * Builds a single obstacle at the location {@code Location} and with a hitbox {@code Hitbox}.
     * @param location The location object associated with the {@code Platform}
     */
    private Platform(Location location)   //makes a single platform object. Used in below constructor
    {
        super(location);
    }

    /**
     * A constructor used to build a platform of {@code length} bricks long starting at the left end brick location
     * @param platformStartLocation The starting location of the left end brick
     * @param length The total number of bricks in the platform
     */
    public Platform(Location platformStartLocation, int length)
    {
        this.length = length;

        if(length < 1)
            System.out.println("Platform must be at least 1 brick long! Unable to construct platform!");
        else    //platform is at least 1 brick long
        {
            bricks = new ArrayList<>(this.length);

            Location nextBrickLocation = new Location(platformStartLocation.getXcord(), platformStartLocation.getYcord());

            for(int i = 0; i < this.length; i++)    //creates every brick in the array list
            {
                bricks.add(i, new Platform(nextBrickLocation));     //adds a new brick

                nextBrickLocation = this.addBrickWidth(nextBrickLocation);  //shifts the next brick location 60 pixels (WIDTH) to the right

                bricks.get(i).setSprite("images/sprites/regularPlatform.png");   //sets every brick to the default
                                                                                    //sprite of the middle brick
            }

            //sets the correct sprites based off how many bricks are in the platform
            if(this.length == 1)
                bricks.get(0).setSprite("images/sprites/singlePlatform.png");
            else if(this.length > 1)
            {
                bricks.get(0).setSprite("images/sprites/leftEndPlatform.png");
                bricks.get(this.length - 1).setSprite("images/sprites/rightEndPlatform.png");
            }

            //set the hitbox of the entire platform
            this.platformHitbox = new Hitbox(bricks.get(0).getLocation(), HEIGHT, this.length * WIDTH);

        }
    }

    /**
     * Shifts the location 60 pixels ({@code WIDTH}) to the right for the next brick
     * @param location The location of the last placed brick
     * @return The new location 60 pixels right of the last placed brick
     */
    private Location addBrickWidth(Location location)
    {
        Location newLocation = new Location(location.getXcord(), location.getYcord());

        newLocation.addX(WIDTH);

        return newLocation;
    }

    /**
     * Returns the bricks of the {@code Platform}
     * @return The bricks of the {@code Platform}
     */
    public ArrayList<Obstacle> getBricks()
    {
        return this.bricks;
    }

    /**
     * Returns the number of bricks in the {@code Platform}
     * @return The number of bricks in the {@code Platform}
     */
    public int getLength()
    {
        return this.length;
    }

    /**
     * Returns the entire hitbox of the {@code Platform}
     * @return The entire hitbox of the {@code Platform}
     */
    public Hitbox getHitbox()
    {
        return this.platformHitbox;
    }

    //MAIN METHOD USED FOR TESTING THE PLATFORM METHODS, CONSTRUCTORS AND HITBOX COLLISION

    public static void main(String[] args)
    {
        Location k1 = new Location(100,120);
        Location k2 = new Location(300, 309);
        Location k3 = new Location(600, 668);
        Location c = new Location(100,100);

        Hitbox colltest = new Hitbox(c,5,5);

        Platform singleBrick = new Platform(k1,1);
        Platform twoBrick = new Platform(k2,2);
        Platform multiBrick = new Platform(k3,5);

        //test location for each platform

        System.out.println("\nTESTING PLATFORM LOCATIONS.....\n");

        System.out.println("Should be 100: " + singleBrick.bricks.get(0).getLocation().getXcord());
        System.out.println("Should be 120: " + singleBrick.bricks.get(0).getLocation().getYcord());

        System.out.println("Should be 300: " + twoBrick.bricks.get(0).getLocation().getXcord());
        System.out.println("Should be 309: " + twoBrick.bricks.get(0).getLocation().getYcord());
        System.out.println("Should be 360: " + twoBrick.bricks.get(1).getLocation().getXcord());
        System.out.println("Should be 309: " + twoBrick.bricks.get(1).getLocation().getYcord());

        System.out.println("Should be 600: " + multiBrick.bricks.get(0).getLocation().getXcord());
        System.out.println("Should be 668: " + multiBrick.bricks.get(0).getLocation().getYcord());
        System.out.println("Should be 660: " + multiBrick.bricks.get(1).getLocation().getXcord());
        System.out.println("Should be 668: " + multiBrick.bricks.get(1).getLocation().getYcord());
        System.out.println("Should be 720: " + multiBrick.bricks.get(2).getLocation().getXcord());
        System.out.println("Should be 668: " + multiBrick.bricks.get(2).getLocation().getYcord());
        System.out.println("Should be 780: " + multiBrick.bricks.get(3).getLocation().getXcord());
        System.out.println("Should be 668: " + multiBrick.bricks.get(3).getLocation().getYcord());
        System.out.println("Should be 840: " + multiBrick.bricks.get(4).getLocation().getXcord());
        System.out.println("Should be 668: " + multiBrick.bricks.get(4).getLocation().getYcord());

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
        c.addY(14);
        System.out.println("Should be false: " + colltest.areColliding(singleBrick.getHitbox()));
        c.addY(1);
        System.out.println("Should be true: " + colltest.areColliding(singleBrick.getHitbox()));

        //bottom platform collision
        c.setYcord(160);
        System.out.println("Should be false: " + colltest.areColliding(singleBrick.getHitbox()));
        c.addY(-9);
        System.out.println("Should be false: " + colltest.areColliding(singleBrick.getHitbox()));
        c.addY(-5);
        System.out.println("Should be true: " + colltest.areColliding(singleBrick.getHitbox()));

        //left side collision
        c.setYcord(130);
        c.setXcord(90);
        System.out.println("Should be false: " + colltest.areColliding(singleBrick.getHitbox()));
        c.addX(4);
        System.out.println("Should be false: " + colltest.areColliding(singleBrick.getHitbox()));
        c.addX(1);
        System.out.println("Should be true: " + colltest.areColliding(singleBrick.getHitbox()));

        //right side collision
        c.setYcord(662);
        c.setXcord(780);
        System.out.println("Should be false: " + colltest.areColliding(multiBrick.getHitbox()));
        c.addY(3);
        System.out.println("Should be true: " + colltest.areColliding(multiBrick.getHitbox()));
        c.addY(1);
        System.out.println("Should be true: " + colltest.areColliding(multiBrick.getHitbox()));

        //double platform top collision




        System.exit(0);
    }


}
