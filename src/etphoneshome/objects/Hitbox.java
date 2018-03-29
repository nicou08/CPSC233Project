package etphoneshome.objects;

public class Hitbox {
    private Location topLeftCorner; //Change l to L for real code
    private int height, width;

    public Hitbox(Hitbox hitbox) {
        this.topLeftCorner = new Location(hitbox.getTopLeftCorner());
        this.height = hitbox.getHeight();
        this.width = hitbox.getWidth();
    }

    //Constructor
    public Hitbox(Location m, int h, int w) {
        topLeftCorner = m;
        height = h;
        width = w;
    }

    //Getter
    public int getWidth() {
        return width;
    }

    //Getter
    public int getHeight() {
        return height;
    }

    //TEMPORARY METHOD USED FOR QUICKER TESTING OF PLATFORM CLASS. REMOVE AFTER TESTING IS FINISHED

    public Location getTopLeftCorner() {
        return this.topLeftCorner;
    }

    /**
     * sets the location of {@code Hitbox}
     *
     * @param newLocation top leftHandCorner of Hitbox
     */
    public void setLocation(Location newLocation) {
        this.topLeftCorner = newLocation;
    }

    //TEMPORARY METHOD USED FOR QUICKER TESTING OF PLATFORM CLASS. REMOVE AFTER TESTING IS FINISHED

    //Collision
    public boolean areColliding(Hitbox otherHitbox) {

        int leftSideX = topLeftCorner.getXcord();
        int rightSideX = topLeftCorner.getXcord() + width;
        int otherRightSideX = otherHitbox.topLeftCorner.getXcord() + otherHitbox.getWidth();
        int otherLeftSideX = otherHitbox.topLeftCorner.getXcord();

        int topSideY = topLeftCorner.getYcord();
        int bottomSideY = topLeftCorner.getYcord() + height;
        int otherTopSideY = otherHitbox.topLeftCorner.getYcord();
        int otherBottomSideY = otherHitbox.topLeftCorner.getYcord() + otherHitbox.getHeight();

        if (rightSideX < otherLeftSideX) {
            return false;
        } // hitbox is left of other hitbox

        if (leftSideX > otherRightSideX) {
            return false;
        } // hitbox is right of other hitbox

        if (topSideY > otherBottomSideY) {
            return false;
        } // hitbox is below other hitbox

        if (bottomSideY < otherTopSideY) {
            return false;
        } // hitbox is above other hitbox

        return true; // boxes overlap

    }

    public boolean belowOtherHitbox(Hitbox otherHitbox) {
        int topSideY = topLeftCorner.getYcord();
        int otherBottomSideY = otherHitbox.topLeftCorner.getYcord() + otherHitbox.getHeight();
        return topSideY > otherBottomSideY;
    }

    public boolean aboveOtherHitbox(Hitbox otherHitbox) {
        int bottomSideY = topLeftCorner.getYcord() + height;
        int otherTopSideY = otherHitbox.topLeftCorner.getYcord();
        return bottomSideY < otherTopSideY;
    }

    public boolean toTheLeftOfOtherHitbox(Hitbox otherHitbox) {
        int rightSideX = topLeftCorner.getXcord() + width;
        int otherLeftSideX = otherHitbox.topLeftCorner.getXcord();
        return rightSideX < otherLeftSideX;
    }

    public boolean toTheRightOfOtherHitbox(Hitbox otherHitbox) {
        int leftSideX = topLeftCorner.getXcord();
        int otherRightSideX = otherHitbox.topLeftCorner.getXcord() + otherHitbox.getWidth();
        return leftSideX > otherRightSideX;
    }

}
