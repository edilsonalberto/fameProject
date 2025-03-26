// This class represents the State where the Dungeon is not actively moving. This is one of the states that exist
// In this state, the hero might show an idle animation or remain stationary
public class IdleState implements state {
    private DynamicSprite hero;

    public IdleState(DynamicSprite hero) {
        this.hero = hero;
    }

    @Override
    public void handleRequest() {
        // Mark the hero as not walking, or handle other idle-specific logic.
        hero.setWalking(false);
    }
}