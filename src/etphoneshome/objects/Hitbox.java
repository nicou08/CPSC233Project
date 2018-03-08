package etphoneshome.objects;

public class Hitbox {
    private Location topLeftCorner; //Change l to L for real code
    private double height, width;

    //Constructor
    public Hitbox(Location m, double h, double w) {
        topLeftCorner = m;
        height = h;
        width = w;
    }

    //Getter
    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    //Collision
    public boolean areColliding(Hitbox otherObject) {

        double leftSideX = topLeftCorner.getXcord();
        double rightSideX = topLeftCorner.getXcord() + width;
        double otherRightSideX = otherObject.topLeftCorner.getXcord() + otherObject.getWidth(); //Make a getLocation???
        double otherLeftSideX = otherObject.topLeftCorner.getXcord();

        double topSideY = topLeftCorner.getYcord();
        double bottomSideY = topLeftCorner.getYcord() + height;
        double otherTopSideY = otherObject.topLeftCorner.getYcord();
        double otherBottomSideY = otherObject.topLeftCorner.getYcord() + otherObject.getHeight();

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
