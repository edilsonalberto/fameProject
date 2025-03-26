// This class represents the State where the Dungeon is  actively moving. This is one of the states that exist
// In this state, the hero is moving and going in a certain direction
public class WalkingState implements state {
    private DynamicSprite hero;

    public WalkingState(DynamicSprite hero) {
        this.hero = hero;
    }

    @Override
    public void handleRequest() {
        // Marking the Hero as moving
        hero.setWalking(true);
    }
}
