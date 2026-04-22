package controllers;

import enums.Heading;
import models.HallCall;
import models.Level;
import models.Lift;

import java.util.ArrayList;
import java.util.List;

public class BuildingManager {

    private final List<Lift> lifts = new ArrayList<>();
    private final List<Level> levels = new ArrayList<>();
    private Dispatcher dispatcher;

    public void attachDispatcher(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    public void registerLift(Lift lift) {
        lifts.add(lift);
    }

    public void registerLevel(Level level) {
        levels.add(level);
    }

    public void summonLift(int level, Heading heading) {
        dispatcher.processHallCall(lifts, new HallCall(level, heading));
    }

    public List<Lift> getLifts() {
        return lifts;
    }

    public List<Level> getLevels() {
        return levels;
    }
}
