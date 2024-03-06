import java.time.Duration;
import java.time.Instant;
import java.util.Scanner;

public class Kitchen extends Room {
    private boolean stove;
    private Instant stoveTurnedOnAt;
    public Kitchen(Scanner scanner) {
        super("Kitchen", scanner, 4);
        this.stove = false;
    }
    private void switchStove() {
        this.stove = !stove;
        if (stove) {
            stoveTurnedOnAt = Instant.now();
        } else {
            Instant stoveTurnedOffAt = Instant.now();
            long timeElapsed = Duration.between(stoveTurnedOnAt, stoveTurnedOffAt).toMillis();
            double kwhconsumed = timeElapsed / 450.0;
            stoveTurnedOnAt = null;
            System.out.println(String.format("Consumed %.2f kwH", kwhconsumed));
        }
    }
    public boolean isStoveOn() {
        return stove;
    }
    @Override
    public void checkRoomState() {
        super.checkRoomState();
        if (isStoveOn()) {
            System.out.println("Warning: The stove in " + getName() +
                    " is still switched on.");
        }
    }
    @Override
    protected void offerOptionsToUser() {
        super.offerOptionsToUser();
        System.out.println("4) Switch stove");
    }
    @Override
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
            case 4:
                switchStove();
                if(stove) System.out.println("You turned on the stove.");
                else System.out.println("You turned off the stove.");
                break;
            default:
                printInvalidOptionSelected();
        }
        interactWithUser();
    }
}