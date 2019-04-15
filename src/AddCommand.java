public class AddCommand implements Command {
    Level level;
    String roomName;

    public AddCommand(Level level){
        this.level = level;
    }
    @Override
    public void init(String userString) {
        this.roomName = getCommandWord(userString);
    }

    private String getCommandWord(String userString) {
        String[] words = userString.split(" ");
        return words[words.length-1];
    }

    @Override
    public boolean execute() {
        level.addRoom(roomName, "no description");
        level.addDirectedEdge(level.getPlayer().getCurrentRoom().getName(), roomName);
        System.out.println("you have added a new room called " + roomName);
        return true;
    }
}
