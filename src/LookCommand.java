public class LookCommand implements Command{
    Player player;

    public LookCommand(Player player){
        this.player = player;
    }

    @Override
    public void init(String userString) {

    }

    @Override
    public boolean execute() {
        System.out.println("Items currently in the room: " + player.getCurrentRoom().displayItems());
        System.out.println("Creatures in the room: " + player.getCurrentRoom().displayCreatures());
        return true;

    }
}
