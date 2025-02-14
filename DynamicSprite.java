import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class DynamicSprite extends SolidSprite {

    boolean isWalking = true;
    double speed = 5;
    final int spriteSheetNumberOfColumn = 10;
    int timeBetweenFrame = 200;
    Direction direction;


    public DynamicSprite(double x, double y, Image image, double width, double height) {
        super(x, y, image, width, height);
        this.direction = Direction.SOUTH;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public void draw(Graphics g) {
        int frameIndex = (int) ((System.currentTimeMillis() / timeBetweenFrame) % spriteSheetNumberOfColumn);
        // Retrieve an integer attitude corresponding to the numerical value of the direction.
        int attitude = direction.getFrameLineNumber();
        // Calculate the source coordinates from the sprite sheet.
        int srcX1 = frameIndex * (int) width;
        int srcY1 = attitude * (int) height;
        int srcX2 = (frameIndex + 1) * (int) width;
        int srcY2 = (attitude + 1) * (int) height;
        g.drawImage(image, (int) x, (int) y, (int) (x + width), (int) (y + height), srcX1, srcY1, srcX2, srcY2, null);
    }
    // We use this method  to update the position
    private void move() {

        switch (direction) {
            case NORTH:
                this.y -= speed;
                break;
            case SOUTH:
                this.y += speed;
                break;
            case EAST:
                this.x += speed;
                break;
            case WEST:
                this.x -= speed;
                break;
        }

    }
    // Checking if Moving can be Possible or Not
    private boolean isMovingPossible(ArrayList<Sprite> environment) {

        double newX = this.x;
        double newY = this.y;
        switch (direction) {
            case NORTH:
                newY = this.y - speed;
                break;
            case SOUTH:
                newY = this.y + speed;
                break;
            case EAST:
                newX = this.x + speed;
                break;
            case WEST:
                newX = this.x - speed;
                break;
        }
        // We create a hitbox for the new position
        Rectangle2D.Double hitBox = new Rectangle2D.Double(newX, newY, this.width, this.height);
        // Check for collisions with each sprite in the environment.
        for (Sprite s : environment) {
            if (s instanceof SolidSprite && s != this) {
                Rectangle2D.Double spriteRect = new Rectangle2D.Double(s.x, s.y, s.width, s.height);
                if (hitBox.intersects(spriteRect)) {
                    return false;
                }
            }
        }
        return true;
    }
    // Public method that moves the sprite if no collisions are detected.
    public void moveIfPossible(ArrayList<Sprite> environment) {
        if (isMovingPossible(environment)) {
            move();
        }

    }
}

