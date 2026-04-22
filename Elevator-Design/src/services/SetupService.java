package services;

import controllers.BuildingManager;
import controllers.Dispatcher;
import models.Level;
import models.Lift;
import strategies.LiftSelectionPolicy;

public class SetupService {

    private final BuildingManager building;

    public SetupService(BuildingManager building) {
        this.building = building;
    }

    public void configureLevels(int lowest, int highest) {
        for (int i = lowest; i <= highest; i++) {
            building.registerLevel(new Level(i, highest, building));
        }
        System.out.println("Levels configured: " + lowest + " to " + highest);
    }

    public void configureLifts(int quantity, int topLevel) {
        for (int i = 0; i < quantity; i++) {
            building.registerLift(new Lift(i, topLevel));
        }
        System.out.println(quantity + " lifts configured");
    }

    public void applySelectionPolicy(LiftSelectionPolicy policy) {
        building.attachDispatcher(new Dispatcher(policy));
        System.out.println("Selection policy applied");
    }
}
