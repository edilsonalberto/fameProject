import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.prefs.PreferenceChangeEvent;

public class GameEngine implements Engine, KeyListener {

    private final DynamicSprite hero;
    private state previousState;

    public GameEngine(DynamicSprite hero){

        this.hero = hero;
        this.previousState = hero.getState();
    }

    @Override
    public void update() {
        // 1. Check if the hero's state has changed
        if (hero.getState() != previousState) {
            // 2. Print a message only once when the state changes
            if (hero.getState() instanceof IdleState) {
                System.out.println("Hero is now idle.");
            } else if (hero.getState() instanceof WalkingState) {
                System.out.println("Hero is now walking in direction: " + hero.getDirection());
            }

            // 3. Update the previousState
            previousState = hero.getState();
        }
        hero.getState().handleRequest();
    }



    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_UP:
                hero.setDirection(Direction.NORTH);
                hero.setState(new WalkingState(hero));

                break;
            case KeyEvent.VK_DOWN:
                hero.setDirection(Direction.SOUTH);
                hero.setState(new WalkingState(hero));

                break;
            case KeyEvent.VK_LEFT:
                hero.setDirection(Direction.WEST);
                hero.setState(new WalkingState(hero));

                break;
            case KeyEvent.VK_RIGHT:
                hero.setDirection(Direction.EAST);
                hero.setState(new WalkingState(hero));
                break;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        // When the key is released, set the hero's state to idle.
        hero.setState(new IdleState(hero));
    }

}
