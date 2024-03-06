import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Room {
    private String name;
    private boolean lighting;
    private List<Room> neighbours;
    private int interactionOptions;
    private Scanner scanner;
    public Room(String name, Scanner scanner) {
        this(name, scanner, 3);
    }
    public Room(String name, Scanner scanner, int interactionOptions) {
        this.name = name;
        this.lighting = false;
        this.neighbours = new ArrayList<Room>();
        this.interactionOptions = interactionOptions;
        if (scanner == null) {
            this.scanner = new Scanner(System.in);
        } else {
            this.scanner = scanner;
        }
    }
    public String getName() {
        return name;
    }
    public boolean isLightingOn() {
        return lighting;
    }
    public List<Room> getNeighbours() {
        return neighbours;
    }
    protected void switchLight() {
        lighting = !lighting;
    }
    public void addNeighbourRoom(Room neighbour) {
        if (!this.isAlreadyNeighboured(neighbour)) {
            this.neighbours.add(neighbour);
            neighbour.addNeighbourRoom(this);
        }
    }
    private boolean isAlreadyNeighboured(Room neighbour)
    {
        return neighbours.contains(neighbour);
    }
    public void enter() {
        interactWithUser();
    }
    protected void interactWithUser() {
        offerOptionsToUser();
        int actionId = getUserAction(1, interactionOptions);
        executeAction(actionId);
    }
    protected void offerOptionsToUser() {
        System.out.println(String.format(
                "You are in the %s\n", name) +
                "What do you want to do?\n" +
                "1) Switch light\n" +
                "2) Leave room\n" +
                "3) Enter neighbouring room");
    }
    private int getUserAction(int minOption, int maxOption)
    {
        int input = scanner.nextInt();
        if (input < minOption || input > maxOption) {
            return -1;
        }
        return input;
    }
    protected void executeAction(int actionId) {
        switch(actionId) {
            case 1:
                switchLight();
                printLightingStatus();
                break;
            case 2:
                leave();
                return;
            case 3:
                switchRoom();
                break;
            default:
                printInvalidOptionSelected();
        }
        interactWithUser();
    }
    protected void printInvalidOptionSelected() {
        System.out.println("Invalid option selected.");
    }
    protected void leave() {
        System.out.println("Leaving the " + this.name);
    }
    protected void switchRoom() {
        if (neighbours.isEmpty()) {
            System.out.println(name + " has no adjacent rooms. Please select a different action.");
            return;
        }
        System.out.println("Which room?");
        for (int i = 0; i < neighbours.size(); i++) {
            Room neighbour = neighbours.get(i);
            System.out.println(String.format("%d) %s", i+1,
                    neighbour.getName()));
        }
        int action = getUserAction(1, neighbours.size());
        if (action == -1) {
            printInvalidOptionSelected();
            switchRoom();
        } else {
            Room roomToEnter = neighbours.get(action - 1);
            roomToEnter.enter();
        }
    }
    public void checkRoomState() {
        if(isLightingOn()) {
            System.out.println("Warning: Light in the " + name + " is still switched on.");
        }
    }
    public void printLightingStatus() {
        String status;
        if (isLightingOn()) {
            status = "on";
        } else {
            status = "off";
        }
        System.out.println("The light is switched " + status);
    }
}