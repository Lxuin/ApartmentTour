import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Room hallway = new Room("Hallway", scanner);                            // Adding all the rooms in my apartment
        Room bedroom = new Room("Bedroom", scanner);
        Room livingroom = new Room("Livingroom", scanner);
        Room kitchen = new Kitchen(scanner);
        Room workroom = new Room("Workroom", scanner);
        Room bathroom = new Bathroom(scanner);
        Room gym = new Gym(scanner);
        List<Room> allRooms = new ArrayList<>(List.of(hallway, bedroom, kitchen, livingroom, gym, workroom, bathroom));
        Apartment apartment = new Apartment(hallway, allRooms);
        apartment.neighbourRooms(hallway, bedroom);                              // Determining the connections between the rooms
        apartment.neighbourRooms(hallway, kitchen);
        apartment.neighbourRooms(hallway, workroom);
        apartment.neighbourRooms(hallway, bathroom);
        apartment.neighbourRooms(bedroom, workroom);
        apartment.neighbourRooms(hallway, livingroom);
        apartment.neighbourRooms(livingroom, gym);
        apartment.enter();
    }
}