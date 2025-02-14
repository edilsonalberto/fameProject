import javax.imageio.ImageIO;
import javax.swing.*;

import java.io.File;
import java.util.ArrayList;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Main {

    JFrame displayZoneFrame;
    RenderEngine renderEngine;
    PhysicEngine physicEngine;
    GameEngine gameEngine;

    public Main() throws Exception{
        displayZoneFrame = new JFrame("Java Labs");
        displayZoneFrame.setSize(400,600);
        displayZoneFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        DynamicSprite hero = new DynamicSprite(200,300,
                ImageIO.read(new File("./img/heroTileSheetLowRes.png")),48,50);

        renderEngine = new RenderEngine();
        physicEngine = new PhysicEngine();
        gameEngine = new GameEngine(hero);

        Timer renderTimer = new Timer(50,(time)-> renderEngine.update());
        Timer gameTimer = new Timer(50,(time)-> gameEngine.update());
        Timer physicTimer = new Timer(50,(time)-> physicEngine.update());

        renderTimer.start();
        gameTimer.start();
        physicTimer.start();

        displayZoneFrame.getContentPane().add(renderEngine);
        displayZoneFrame.setVisible(true);
        displayZoneFrame.addKeyListener(gameEngine);

        Playground playground = new Playground("./level.txt");
        for (Displayable d : playground.getSpriteList()) {
            renderEngine.addToRenderList(d);
        }

        SolidSprite testSprite = new SolidSprite(250, 300,
                ImageIO.read(new File("./img/rock.png")), 64, 64);

        renderEngine.addToRenderList(testSprite);
        renderEngine.addToRenderList(hero);

        physicEngine.addToTheMovingList(hero);
        ArrayList<Sprite> environment = new ArrayList<>();
        environment.add(testSprite);
        physicEngine.setEnvironment(environment);
    }

    public static void main(String[] args) throws Exception {
        new Main();
    }

}