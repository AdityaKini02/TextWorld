import java.util.ArrayList;
import java.util.HashMap;

public abstract class Creature {
    protected Level.Room currentRoom;
    protected String name;
    protected String description;

    public Creature(Level.Room r, String name, String description){
        currentRoom = r;
        this.name = name;
        this.description = description;
        currentRoom.addCreature(this);
    }

    //General Methods
    public abstract void move();

    protected Level.Room getRandomAdjacentRoom(){
        ArrayList<Level.Room> rooms = new ArrayList<Level.Room> (currentRoom.getNeighbors().values());
        if (rooms.size() == 0) return currentRoom;
        int randomIndex = (int)(Math.random()*rooms.size());
        return  rooms.get(randomIndex);
    }

    protected void setCurrentRoom(Level.Room r) {
        System.out.println(this.getName()+ " has moved from " + currentRoom.getName() + " to " + r.getName());
        this.currentRoom = r;
    }


    protected boolean isPlayerAdjacent(Player p){
        Level.Room playerRoom = p.getCurrentRoom();
        if(currentRoom.getNeighbors().containsValue(playerRoom)){
            return true;
        }
        return false;
    }

    protected String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }

    protected String getDescription() {
        return description;
    }

    protected void setDescription(String description) {
        this.description = description;
    }

    protected Level.Room getCurrentRoom(){
        return currentRoom;
    }







}
