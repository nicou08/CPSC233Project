package etphoneshome.objects;

import java.util.ArrayList;

/**
 * This class is used for the platforms that the player can stand and jump on to.
 * Using the getLeftEndBrick returns the left end brick of the {@code Platform}
 * Using the getRightEndBrick returns the right end brick of the {@code Platform}
 * Using the getMiddleBricks returns the middle bricks of the {@code Platform}
 */
public class Platform extends Obstacle
{
    /**
     * The number of bricks in the {@code Platform}
     */
    private int length;     //number of blocks (width of each block is 60 pixels). Must be at least 2 (end blocks)

    /**
     * The left end brick of the {@code Platform}
     */
    private Obstacle leftEndBrick;

    /**
     * The right end brick of the {@code Platform}
     */
    private Obstacle rightEndBrick;

    /**
     * The middle bricks of the {@code Platform}
     */
    private ArrayList<Obstacle> middleBricks;

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
     * @param hitbox The hitbox object associated with the {@code Platform}
     */
    private Platform(Location location, Hitbox hitbox)   //makes a single platform object. Used in below constructor
    {
        super(location, hitbox);
    }

    /**
     * A constructor used to build a platform of {@code length} bricks long starting at the left end brick location
     * @param leftEndBrickLocation The starting location of the left end brick
     * @param length The total number of bricks in the platform
     */
    public Platform(Location leftEndBrickLocation, int length)
    {
        this.length = length;

        if(length == 2) //no middle bricks, just end bricks
        {
            //creates the left end brick with proper location and hitbox
            leftEndBrick = new Obstacle(leftEndBrickLocation, new Hitbox(leftEndBrickLocation, HEIGHT, WIDTH));

            //makes a new location for later use
            Location rightEndBrickLocation = new Location(leftEndBrickLocation.getXcord(), leftEndBrickLocation.getYcord());
            rightEndBrickLocation.addX(WIDTH);  //shifts the new location above 60 pixels (WIDTH) to the right

            //creates the right end brick with proper location and hitbox
            rightEndBrick = new Obstacle(rightEndBrickLocation, new Hitbox(rightEndBrickLocation, HEIGHT, WIDTH));
        }
        else if(length > 2) //has middle bricks
        {
            middleBricks = new ArrayList<>(length - 2);    //sets arraylist of middle bricks up to be 2 less than the total length
                                                        //since the end bricks take up one spot each

            //creates the left end brick with proper location and hitbox
            leftEndBrick = new Obstacle(leftEndBrickLocation, new Hitbox(leftEndBrickLocation, HEIGHT, WIDTH));

            //makes a new location for later use
            Location nextBrickLocation = new Location(leftEndBrickLocation.getXcord(), leftEndBrickLocation.getYcord());
            nextBrickLocation = this.addBrickWidth(nextBrickLocation);  //shifts the new location above 60 pixels (WIDTH) to the right

            for(int i = 2, j = 0; i < length; i++, j++)        //creates the middle bricks. i is for brick number in platform
            {                                                  //j is for going through the middleBrick arraylist
                middleBricks.add(j, new Obstacle(nextBrickLocation, new Hitbox(nextBrickLocation, HEIGHT, WIDTH)));
                nextBrickLocation = this.addBrickWidth(nextBrickLocation);  //shift location 60 pixels right
                middleBricks.get(j).setSprite("images/sprites/middleBrickGeneric.png");
            }

            //creates the right end brick with proper location and hitbox
            rightEndBrick = new Obstacle(nextBrickLocation, new Hitbox(nextBrickLocation, HEIGHT, WIDTH));
        }
        else    //invalid number of bricks (less than 2)
            System.out.println("Platform must be at least 2 bricks long! Unable to construct platform.");

        leftEndBrick.setSprite("images/sprites/leftEndBrickGeneric.png");
        rightEndBrick.setSprite("images/sprites/rightEndBrickGeneric.png");
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
     * Returns the left end brick of the {@code Platform}
     * @return The left end brick of the {@code Platform}
     */
    public Obstacle getLeftEndBrick()
    {
        return this.leftEndBrick;
    }

    /**
     * Returns the right end brick of the {@code Platform}
     * @return The right end brick of the {@code Platform}
     */
    public Obstacle getRightEndBrick()
    {
        return this.rightEndBrick;
    }

    /**
     * Returns the middle bricks of the {@code Platform}
     * @return The middle bricks of the {@code Platform}
     */
    public ArrayList<Obstacle> getMiddleBricks()
    {
        return this.middleBricks;
    }


    //MAIN METHOD USED FOR TESTING THE PLATFORM METHODS, CONSTRUCTORS AND HITBOX COLLISION
/*
    public static void main(String[] args)
    {
        Location testLoc = new Location(200, 200);
        Location testShortLoc = new Location(660, 100);

        Platform testPlatform = new Platform(testLoc,5);    //2 middle bricks

        Platform testShortPlatform = new Platform(testShortLoc, 2);


        System.out.println("Should be 200(left end brick x loc): " + testPlatform.leftEndBrick.getLocation().getXcord());
        System.out.println("Should be 200(left end brick y loc): " + testPlatform.leftEndBrick.getLocation().getYcord());
        System.out.println("Should be 200(left end brick x hitbox): " + testPlatform.leftEndBrick.getHitbox().getTopLeftCorner().getXcord());
        System.out.println("Should be 200(left end brick y hitbox): " + testPlatform.leftEndBrick.getHitbox().getTopLeftCorner().getYcord());
        System.out.println("Should be 30(left end brick hitbox h): " + testPlatform.leftEndBrick.getHitbox().getHeight());
        System.out.println("Should be 60(left end brick hitbox w): " + testPlatform.leftEndBrick.getHitbox().getWidth());

        System.out.println("LEFT BRICK TESTING COMPLETE! TESTING MIDDLE BRICKS.....");

        System.out.println("Should be 260: " + testPlatform.middleBricks.get(0).getLocation().getXcord());
        System.out.println("Should be 200: " + testPlatform.middleBricks.get(0).getLocation().getYcord());
        System.out.println("Should be 320: " + testPlatform.middleBricks.get(1).getLocation().getXcord());
        System.out.println("Should be 200: " + testPlatform.middleBricks.get(1).getLocation().getYcord());
        System.out.println("Should be 260: " + testPlatform.middleBricks.get(0).getHitbox().getTopLeftCorner().getXcord());
        System.out.println("Should be 200: " + testPlatform.middleBricks.get(0).getHitbox().getTopLeftCorner().getYcord());
        System.out.println("Should be 30: " + testPlatform.middleBricks.get(0).getHitbox().getHeight());
        System.out.println("Should be 60: " + testPlatform.middleBricks.get(0).getHitbox().getWidth());
        System.out.println("Should be 320: " + testPlatform.middleBricks.get(1).getHitbox().getTopLeftCorner().getXcord());
        System.out.println("Should be 200: " + testPlatform.middleBricks.get(1).getHitbox().getTopLeftCorner().getYcord());
        System.out.println("Should be 30: " + testPlatform.middleBricks.get(1).getHitbox().getHeight());
        System.out.println("Should be 60: " + testPlatform.middleBricks.get(1).getHitbox().getWidth());
        System.out.println("Should be 380: " + testPlatform.middleBricks.get(2).getLocation().getXcord());
        System.out.println("Should be 200: " + testPlatform.middleBricks.get(2).getLocation().getYcord());
        System.out.println("Should be 380: " + testPlatform.middleBricks.get(2).getHitbox().getTopLeftCorner().getXcord());
        System.out.println("Should be 200: " + testPlatform.middleBricks.get(2).getHitbox().getTopLeftCorner().getYcord());
        System.out.println("Should be 30: " + testPlatform.middleBricks.get(2).getHitbox().getHeight());
        System.out.println("Should be 60: " + testPlatform.middleBricks.get(2).getHitbox().getWidth());

        System.out.println("MIDDLE BRICK TESTING COMPLETE! TESTING RIGHT BRICK.....");

        System.out.println("Should be 440: " + testPlatform.rightEndBrick.getLocation().getXcord());
        System.out.println("Should be 200: " + testPlatform.rightEndBrick.getLocation().getYcord());
        System.out.println("Should be 440: " + testPlatform.rightEndBrick.getHitbox().getTopLeftCorner().getXcord());
        System.out.println("Should be 200: " + testPlatform.rightEndBrick.getHitbox().getTopLeftCorner().getYcord());
        System.out.println("Should be 30(left end brick hitbox h): " + testPlatform.rightEndBrick.getHitbox().getHeight());
        System.out.println("Should be 60(left end brick hitbox w): " + testPlatform.rightEndBrick.getHitbox().getWidth());

        System.out.println("RIGHT BRICK TESTING COMPLETE! LONG PLATFORM TESTING COMPLETE!");
        System.out.println("TESTING SHORT PLATFORM! TESTING LEFT BRICK....");

        System.out.println("Should be 660: " + testShortPlatform.leftEndBrick.getLocation().getXcord());
        System.out.println("Should be 100: " + testShortPlatform.leftEndBrick.getLocation().getYcord());
        System.out.println("Should be 660: " + testShortPlatform.leftEndBrick.getHitbox().getTopLeftCorner().getXcord());
        System.out.println("Should be 100: " + testShortPlatform.leftEndBrick.getHitbox().getTopLeftCorner().getYcord());
        System.out.println("Should be 30(left end brick hitbox h): " + testShortPlatform.leftEndBrick.getHitbox().getHeight());
        System.out.println("Should be 60(left end brick hitbox w): " + testShortPlatform.leftEndBrick.getHitbox().getWidth());

        System.out.println("LEFT BRICK TESTING COMPLETE!");
        System.out.println("TESTING SHORT PLATFORM! TESTING RIGHT BRICK....");

        System.out.println("Should be 720: " + testShortPlatform.rightEndBrick.getLocation().getXcord());
        System.out.println("Should be 100: " + testShortPlatform.rightEndBrick.getLocation().getYcord());
        System.out.println("Should be 720: " + testShortPlatform.rightEndBrick.getHitbox().getTopLeftCorner().getXcord());
        System.out.println("Should be 100: " + testShortPlatform.rightEndBrick.getHitbox().getTopLeftCorner().getYcord());
        System.out.println("Should be 30(left end brick hitbox h): " + testShortPlatform.rightEndBrick.getHitbox().getHeight());
        System.out.println("Should be 60(left end brick hitbox w): " + testShortPlatform.rightEndBrick.getHitbox().getWidth());

        System.out.println("SHORT PLATFORM TESTING COMPLETE!\n\n");
        System.out.println("TESTING PLATFORM COLLISION!");

        Location l1 = new Location(210,190);
        Hitbox h1 = new Hitbox(l1, 5, 5);

        System.out.println("Should be false: " + testPlatform.leftEndBrick.getHitbox().areColliding(h1));

        l1.addY(4);
        System.out.println("Should be false: " + testPlatform.leftEndBrick.getHitbox().areColliding(h1));

        l1.addY(1);
        System.out.println("Should be ?true?: " + testPlatform.leftEndBrick.getHitbox().areColliding(h1));

        l1.addY(1);
        System.out.println("Should be definitely be true: " + testPlatform.leftEndBrick.getHitbox().areColliding(h1));

        l1.addX(-20);
        System.out.println("Should be false: " + testPlatform.leftEndBrick.getHitbox().areColliding(h1));

        l1.addX(10);
        System.out.println("Should be true: " + testPlatform.leftEndBrick.getHitbox().areColliding(h1));



        System.exit(0);
    }
*/

}
