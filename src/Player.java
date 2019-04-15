import java.util.ArrayList;

public class Player {
    String name, description;
    ArrayList<Item> items;
    Level.Room currentRoom;

    public Player(String name, String description) {
        this.name = name;
        this.description = description;
        items = new ArrayList<Item>();
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public Item removeItem(String name) {
        for (Item item : items) {
            if (item.getName().equals(name)) {
                items.remove(item);
                return item;
            }
        }
        return null;
    }

    public boolean destroyItem(String name) {
        for (Item item : items) {
            if (item.getName().equals(name)) {
                items.remove(item);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public String displayInventory() {
        String output = "";
        for (Item item : items) {
            output += item.getName() + ", ";
        }
        if (output.equals("")) return "nothing";
        return output;
    }

    public Level.Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Level.Room newRoom) {
        this.currentRoom = newRoom;
    }

    public boolean moveToRoom(String name) {
        Level.Room neighbor = currentRoom.getNeighbor(name);
        if (neighbor != null) {
            currentRoom = neighbor;
            return true;
        }
        return false;
    }


}
