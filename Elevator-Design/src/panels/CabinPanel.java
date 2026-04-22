package panels;

import enums.ActionType;
import enums.Heading;
import models.Lift;

import java.util.ArrayList;
import java.util.List;

public class CabinPanel {

    private final List<LevelSwitch> levelSwitches = new ArrayList<>();
    private final ActionSwitch openSwitch = new ActionSwitch(ActionType.DOOR_OPEN);
    private final ActionSwitch closeSwitch = new ActionSwitch(ActionType.DOOR_CLOSE);
    private final ActionSwitch panicSwitch = new ActionSwitch(ActionType.PANIC);

    public CabinPanel(int topLevel) {
        for (int i = 0; i <= topLevel; i++) {
            levelSwitches.add(new LevelSwitch(i));
        }
    }

    public void selectLevel(Lift lift, int level) {
        levelSwitches.get(level).activate();
        Heading heading = (level > lift.getPosition()) ? Heading.ASCEND : Heading.DESCEND;
        lift.enqueueStop(level, heading);
    }

    public void requestOpen(Lift lift) {
        openSwitch.activate();
        lift.getGate().unlock();
    }

    public void requestClose(Lift lift) {
        closeSwitch.activate();
        lift.getGate().lock();
    }

    public void triggerPanic(Lift lift) {
        panicSwitch.activate();
        lift.getProtection().activateCrisis(lift);
    }
}
