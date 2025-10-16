/*
 * Course: SWE2410-121
 * Fall 2025-2026
 * File header contains class IslandObject
 * Name: aubryg and mirzaa
 * Created 10/9/2025
 */
package coconuts;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

// an object in the game, either something coming from the island or falling on it
// Each island object has a location and can determine if it hits another island object
// This is a domain class; do not introduce JavaFX or other GUI components here
public abstract class IslandObject {
    protected final int width;
    protected final OhCoconutsGameManager containingGame;
    protected int x, y;
    ImageView imageView = null;

    public IslandObject(OhCoconutsGameManager game, int x, int y, int width, Image image) {
        containingGame = game;
        if (image != null) {
            imageView = new ImageView(image);
            imageView.setPreserveRatio(true);
            imageView.setFitWidth(width);
        }
        this.x = x;
        this.y = y;
        this.width = width;
        display();
        //System.out.println(this + " left " + left() + " right " + right());
    }

    protected ImageView getImageView() {
        return imageView;
    }

    public void display() {
        if (imageView != null) {
            imageView.setLayoutX(x);
            imageView.setLayoutY(y);
        }
    }

    public boolean isHittable() {
        return false;
    }

    protected int hittable_height() {
        return (int) imageView.getFitHeight();
    }

    public boolean isGroundObject() {
        return false;
    }

    public boolean isFalling() {
        return false;
    }

    public boolean canHit(IslandObject other) {
        return false;
    }

    public boolean isTouching(IslandObject other) {
        int thisY = this.y;
        if (this.isFalling()) {
            thisY = this.y + this.hittable_height();
        }

        int otherY = other.y;
        if (other.isFalling()) {
            otherY = other.y + other.hittable_height();
        }

        int thisCenterX = this.x + (this.width / 2);
        int otherCenterX = other.x + (other.width / 2);

        boolean centerOfThisInOther = (thisCenterX >= other.x) && (thisCenterX < other.x + other.width);
        boolean centerOfOtherInThis = (otherCenterX >= this.x) && (otherCenterX < this.x + this.width);

        // checking for a longer bound.
        boolean horizontallyOverlaps = this.x < other.x + other.width && this.x + this.width > other.x;

        boolean verticallyClose = Math.abs(thisY - otherY) < 10;

        return (centerOfThisInOther || centerOfOtherInThis || horizontallyOverlaps) && verticallyClose;
    }


    public abstract void step();

    public boolean isLaser() {
        return false;
    }
}
