import java.util.ArrayList;

public class Chicken extends Creature {

    public Chicken(Level.Room currentRoom, String name, String description) {
        super(currentRoom, name, description);
    }

    @Override
    public void move() {
        Level.Room nextRoom = this.getRandomAdjacentRoom();
        currentRoom.removeCreature(this);
        nextRoom.addCreature(this);
        setCurrentRoom(nextRoom);
    }
}
