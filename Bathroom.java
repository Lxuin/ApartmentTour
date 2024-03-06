import java.time.Duration;
import java.time.Instant;
import java.util.Scanner;

public class Bathroom extends Room {
    private boolean shower;
    private Instant showerTurnedOnAt;

    public Bathroom(Scanner scanner) {
        super("Bathroom", scanner, 4);
        this.shower = false;
        this.showerTurnedOnAt = null;
    }

    @Override
    protected void offerOptionsToUser() {
        super.offerOptionsToUser();
        System.out.println("4) Switch shower");
    }

    @Override
    public void checkRoomState() {
        super.checkRoomState();
        if (isShowerOn()) {
            System.out.println("Warning: The shower in " + getName() + " is still switched on.");
        }
    }

    public boolean isShowerOn() {
        return shower;
    }

    @Override
    protected void executeAction(int actionId) {
        switch (actionId) {
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
            case 4:
                switchShower();
                if(shower) System.out.println("You turned on the shower.");
                else System.out.println("You turned off the shower.");
                break;
            default:
                printInvalidOptionSelected();
        }
        interactWithUser();
    }

    private void switchShower() {
        this.shower = !shower;
        if (shower) {
            showerTurnedOnAt = Instant.now();
        } else {
            Instant showerTurnedOffAt = Instant.now();
            long timeElapsed = Duration.between(showerTurnedOnAt, showerTurnedOffAt).toMillis();
            double kwhconsumed = timeElapsed / 450.0;
            showerTurnedOnAt = null;
            System.out.println(String.format("Consumed %.2f kwH", kwhconsumed));
        }
    }
}
