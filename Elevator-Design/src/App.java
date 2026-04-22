import controllers.BuildingManager;
import models.Level;
import models.Lift;
import services.SetupService;
import strategies.ClosestLiftPolicy;

public class App {

    public static void main(String[] args) {
        System.out.println("=== BUILDING CONFIGURATION ===");

        BuildingManager building = new BuildingManager();
        SetupService setup = new SetupService(building);

        setup.configureLevels(0, 10);
        setup.configureLifts(3, 10);
        setup.applySelectionPolicy(new ClosestLiftPolicy());

        System.out.println("=== CONFIGURATION COMPLETE ===\n");

        System.out.println("=== INCOMING REQUESTS ===");

        System.out.println("-> Passenger at Level 3 calls UP");
        Level level3 = building.getLevels().get(3);
        level3.getHallPanel().callUp();

        System.out.println("-> Passenger at Level 7 calls DOWN");
        Level level7 = building.getLevels().get(7);
        level7.getHallPanel().callDown();

        System.out.println("-> Passenger inside Lift 0 selects Level 8");
        Lift lift0 = building.getLifts().get(0);
        lift0.getCabinPanel().selectLevel(lift0, 8);

        System.out.println("-> Panic button (disabled for smooth run)");

        runSimulation(building, 10);
    }

    private static void runSimulation(BuildingManager building, int ticks) {
        System.out.println("\n=== SIMULATION RUNNING ===");

        for (int t = 1; t <= ticks; t++) {
            System.out.println("\nTick " + t);

            for (Lift lift : building.getLifts()) {
                lift.step();

                System.out.println(
                    "Lift " + lift.getLiftId() +
                    " | Level: " + lift.getPosition() +
                    " | Status: " + lift.getStatus() +
                    " | Gate: " + (lift.getGate().isUnlocked() ? "UNLOCKED" : "LOCKED")
                );
            }
        }

        System.out.println("\n=== SIMULATION COMPLETE ===");
    }
}
