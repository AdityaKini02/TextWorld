import java.util.HashMap;

public class Wumpus extends Creature {
    Player player;

    public Wumpus(Level.Room currentRoom, String name, String description, Player p) {
        super(currentRoom, name, description);
        player = p;
    }

    public Level.Room findNextRoom(){
        HashMap<String, Level.Room> currentNeighbors = currentRoom.getNeighbors();
        Level.Room currentPlayerRoom = player.getCurrentRoom();

        if (currentRoom.getNeighbors().containsValue(player.getCurrentRoom())){
            for(Level.Room adjacentRoom : currentNeighbors.values()){
                if (adjacentRoom != currentPlayerRoom){
                    return currentPlayerRoom;
                }
            }
        }

        for( Level.Room room : currentNeighbors.values() ){
            if (! (room.getNeighbors().containsValue(currentPlayerRoom)) ){
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
