public class TakeCommand implements Command{
    Player p;
    String itemName;

    public TakeCommand(Player p){
        this.p = p;
    }

    @Override
    public void init(String userString) {
        this.itemName = getCommandWord(userString);
    }

    public String getCommandWord(String userString) {
        String[] words = userString.split(" ");
        return words[words.length-1];
    }

    @Override
    public boolean execute() {
        boolean success = false;
        Item item = p.getCurrentRoom().removeItem(itemName);
        if (item!=null) {
            success = true;
            p.addItem(item);
            System.out.println("you have taken a " + item.getName());
        }
        return success;
    }

}
