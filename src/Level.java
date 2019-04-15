import java.util.ArrayList;
import java.util.HashMap;


public class Level {
    private HashMap<String, Room> rooms;
    private ArrayList<Creature> creatures;
    private Player player;


    public Level() {
        rooms = new HashMap<String, Room>();
        creatures = new ArrayList<Creature>();
    }

    public void addCreature(Creature c) {
        creatures.add(c);
    }

    public void moveAllCreatures() {
        for (Creature c : creatures) {
            c.move();
        }
    }

    public void addRoom(String name, String description) {
        Room room = new Room(name, description);
        rooms.put(name, room);
    }

    public void addUndirectedEdge(String name1, String name2) {
        Room n1 = getRoom(name1);
        Room n2 = getRoom(name2);
        n1.addNeighbor(n2);
    }

    public void addDirectedEdge(String name1, String name2) {
        Room n1 = getRoom(name1);
        Room n2 = getRoom(name2);
        n1.addNeighbor(n2);
        n2.addNeighbor(n1);
    }

    public Room getRoom(String name) {
        return rooms.get(name);

    }

    //Initialize all the rooms
    public void init(Player player) {
        this.player = player;

        addRoom("hall", "a long dank hallway");
        addRoom("closet", "a dark closet");
        addRoom("dungeon", "a deserted dungeon");

        addDirectedEdge("hall", "dungeon");
        addUndirectedEdge("hall", "closet");

        getRoom("hall").addItem(new Item("pineapple", "a ripe pineapple"));
        getRoom("dungeon").addItem(new Item("almond", "a roasted almond"));
        getRoom("closet").addItem(new Item("cake", "a vanilla cake"));

        Creature c = new Chicken(getRoom("hall"), "chicken", "");
        Creature chickenLittle = new Chicken(getRoom("hall"), "chickenLittle", "the sky is falling");
        Creature  wumpus = new Wumpus(getRoom("hall"), "wumpus", "smart, scared", player);
        Creature wumpusJunior = new Wumpus(getRoom("hall"), "wumpusJunior", "young wumpus", player);
        Creature gaga = new PopStar(getRoom("hall"), "gaga", "singer", player);

        addCreature(c);
        addCreature(chickenLittle);
        addCreature(wumpus);
        addCreature(wumpusJunior);
        addCreature(gaga);
    }

    public Player getPlayer(){
        return player;
    }






    //SUBCLASS: ROOM
    public class Room {
        private String name;
        private HashMap<String, Room> neighbors;
        private String description;
        private ArrayList<Item> items;
        private ArrayList<Creature> creatures;

        private Room(String name, String description) {
            this.name = name;
            neighbors = new HashMap<String, Room>();
            this.description = description;
            items = new ArrayList<Item>();
            creatures = new ArrayList<Creature>();
        }

        public ArrayList<Creature> getCreatures() {
            return creatures;
        }

        public Creature removeCreature(Creature c) {
            creatures.remove(c);
            return c;
        }

        public void addCreature(Creature creature) {
            creatures.add(creature);
        }

        public String displayCreatures() {
            String output = "";
            for (Creature creature : creatures) {
                output += creature.getName() + ", ";

            }
            return output;
        }

        public String getName() {
            return name;
        }

        private void addNeighbor(Room n) {
            String name = n.getName();
            neighbors.put(name, n);
        }

        public String getNeighborNames() {
            String output = "";
            for (String name : neighbors.keySet()) {
                output = output + ", " + name;
            }
            return output;
        }

        public HashMap<String, Room> getNeighbors() {
            return neighbors;
        }

        public Room getNeighbor(String name) {
            for (String n : neighbors.keySet()) {
                if (n.equals(name)) return neighbors.get(n);
            }
            return null;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public ArrayList<Item> getItems() {
            return items;
        }

        public String displayItems() {
            String output = "";
            for (Item item : items) {
                output += item.getName() + ", ";

            }
            return output;
        }

        public void addItem(String name) {
            Item item = new Item(name, "");
            items.add(item);
        }

        public void addItem(String name, String description) {
            Item item = new Item(name, description);
            items.add(item);
        }

        public void addItem(Item item) {
            items.add(item);
        }

        //removes item from a room altogether
        public Item removeItem(String name) {
            for (Item item : items) {
                if (item.getName().equals(name)) {
                    items.remove(item);
                    return item;
                }

            }
            return null;
        }


        //removes item from list and returns that item
        public boolean destroyItem(String name) {
            for (Item item : items) {
                if (item.getName().equals(name)) {
                    items.remove(item);
                    return true;
                }
            }
            return false;
        }



    }
}

