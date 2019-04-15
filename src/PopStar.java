import java.util.HashMap;

public class PopStar extends Creature {
    Player player;

    public PopStar(Level.Room currentRoom, String name, String description, Player p) {
        super(currentRoom, name, description);
        player = p;
    }


    public Level.Room findNextRoom(){
        HashMap<String, Level.Room> currentNeighbors = currentRoom.getNeighbors();

        if (currentRoom.getNeighbors().containsValue(player.getCurrentRoom())){
            return player.getCurrentRoom();
        }

        for( Level.Room room : currentNeighbors.values() ){
            if (room.getNeighbors().containsValue(player.getCurrentRoom())){
                return room;
            }
        }

        return getRandomAdjacentRoom();
    }

    @Override
    public void move() {
        Level.Room nextRoom = findNextRoom();
        currentRoom.removeCreature(this);
        nextRoom.addCreature(this);
        setCurrentRoom(nextRoom);
    }
}
