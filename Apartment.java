import java.util.ArrayList;
import java.util.List;

public class Apartment {
    private final List<Room> rooms;
    private Room entrance;
    public Apartment(Room entrance) {
        this.rooms = new ArrayList<>();
        addNewRoom(entrance);
        this.entrance = entrance;
    }
    public Apartment(Room entrance, List<Room> rooms) {
        this.rooms = rooms;
        this.entrance = entrance;
        if (!this.rooms.contains(this.entrance)) {
            this.rooms.add(this.entrance);
        }
    }
    public List<Room> getRooms() {
        return rooms;
    }
    public void addNewRoom(Room newRoom) {
        rooms.add(newRoom);
    }
    public void enter() {
        entrance.enter();
        checkRoomStates();
        System.out.println("Leaving apartment.");
    }
    private void checkRoomStates() {
        for (Room room : rooms) {
            room.checkRoomState();
        }
    }
    public void neighbourRooms(Room r1, Room r2) {
        if (rooms.contains(r1) && rooms.contains(r2)) {
            r1.addNeighbourRoom(r2);
        } else {
            System.out.println("Could not add neighbouring room. Both rooms are not contained within the same apartment.");
        }
    }
}