import java.util.ArrayList;

public class PhysicEngine implements Engine {

    ArrayList<Sprite> movingSpriteList;
    ArrayList<Sprite> environment;

    public void addToTheMovingList(Sprite sprite){
        movingSpriteList.add(sprite);
    }

    public void setEnvironment(ArrayList<Sprite> environment) {
        this.environment = environment;
    }

    public PhysicEngine(){
        movingSpriteList = new ArrayList<>();
        environment = new ArrayList<>();
    }

    @Override
    public void update(){

        for (Sprite sprite: movingSpriteList){
            if (sprite instanceof  DynamicSprite){

                ((DynamicSprite) sprite).moveIfPossible(environment);
            }
        }
    }
}
