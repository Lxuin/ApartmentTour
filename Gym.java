import java.time.Duration;
import java.time.Instant;
import java.util.Scanner;

public class Gym extends Room {
    private boolean treadmill;
    private Instant treadmillTurnedOnAt;

    public Gym(Scanner scanner) {
        super("Gym", scanner, 4);
        this.treadmill = false;
        this.treadmillTurnedOnAt = null;
    }

    @Override
    protected void offerOptionsToUser() {
        super.offerOptionsToUser();
        System.out.println("4) Switch treadmill");
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
                switchtreadmill();
                if (treadmill) System.out.println("Switched on treadmill.");
                else System.out.println("Switched off treadmill.");
                break;
            default:
                printInvalidOptionSelected();
        }
        interactWithUser();
    }

    private void switchtreadmill() {
        this.treadmill = !treadmill;
        if (treadmill) {
            treadmillTurnedOnAt = Instant.now();
        } else {
            Instant treadmillTurnedOffAt = Instant.now();
            long timeElapsed = Duration.between(treadmillTurnedOnAt, treadmillTurnedOffAt).toMillis();
            double consumed = timeElapsed / 450.0;
            treadmillTurnedOnAt = null;
            System.out.println(String.format("Consumed %.2f kwH.", consumed));
        }
    }

    @Override
    public void checkRoomState() {
        super.checkRoomState();
        if (treadmill) {
            System.out.println("Warning: The treadmill is still switched on.");
        }
    }
}