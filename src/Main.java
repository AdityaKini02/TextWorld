import java.util.HashMap;
import java.util.Scanner;
public class Main {

    static HashMap<String, Command> commands = new HashMap<>();
    static Level level;
    static Player player;

    public static void main(String[] args) {
        level = new Level();
        player = new Player("Player1", "first player");
        initCommands();
        level.init(player);

        player.setCurrentRoom(level.getRoom("hall"));
        String response = "";
        Scanner in = new Scanner(System.in);
        System.out.println("Options: go, add, take, look, drop, or quit");

        do {
            System.out.print("> ");
            System.out.println("You are in the " + player.getCurrentRoom().getName());
            System.out.println("What do you want to do?");
            response = in.nextLine();

            Command command = lookupCommand(response);
            command.execute();
            level.moveAllCreatures();

        } while (!response.equals("quit"));

        System.out.println("you have exited the world");
    }

    private static Command lookupCommand(String response) {
        String commandWord = getFirstWord(response);
        Command c = commands.get(commandWord);
        c.init(response);
        return c;
    }

    private static String getFirstWord(String response) {
        String[] words = response.split(" ");
        return words[0];
    }

    private static void initCommands() {
        commands.put("add", new AddCommand(level));
        commands.put("drop" , new DropCommand(player));
        commands.put("go", new GoCommand(player));
        commands.put("look", new LookCommand(player));
        commands.put("take", new TakeCommand(player));
    }
}