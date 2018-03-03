package etphoneshome.objects;

public class Hitbox {
    private Location topLeftCorner; //Change l to L for real code
    private int height, width;

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

    public int getHeight() {
        return height;
    }

    //Collision
    public boolean areColliding(Hitbox otherObject) {
        boolean colliding = false;

        int leftSideX = topLeftCorner.getXcord();
        int rightSideX = topLeftCorner.getXcord() + width;
        int otherRightSideX = otherObject.topLeftCorner.getXcord() + otherObject.getWidth(); //Make a getLocation???
        int otherLeftSideX = otherObject.topLeftCorner.getXcord();

        int topSideY = topLeftCorner.getYcord();
        int bottomSideY = topLeftCorner.getYcord() + height;
        int otherTopSideY = otherObject.topLeftCorner.getYcord();
        int otherBottomSideY = otherObject.topLeftCorner.getYcord() + otherObject.getHeight();

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

}
