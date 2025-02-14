import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class RenderEngine extends JPanel implements Engine {

    private ArrayList<Displayable> renderList;

    public RenderEngine(){
        renderList = new ArrayList<>();
    }

    @Override
    public void update() {
        // After the functionality is validated, we can remove this line of code
        repaint();
    }

    public void setRenderList(ArrayList<Displayable> renderList){
        this.renderList = renderList;
    }

    public void addToRenderList(Displayable displayable){
        renderList.add(displayable);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (Displayable displayable : renderList) {
            displayable.draw(g);
        }
    }
}
