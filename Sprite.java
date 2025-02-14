import java.awt.Graphics;
import java.awt.Image;

public class Sprite implements Displayable{

    protected Image image;
    protected double x, y;
    protected double width, height;

    public Sprite(double x, double y, Image image, double width, double height){
        this.x = x;
        this.y= y;
        this.image = image;
        this.width = width;
        this.height = height;
    }

    @Override
    public void draw(Graphics g){
        // Casting double to int for the drawImage parameters.
        g.drawImage(image, (int) x, (int) y, (int) width, (int) height, null);
    }

}
