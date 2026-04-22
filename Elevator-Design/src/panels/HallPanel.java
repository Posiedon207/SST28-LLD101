package panels;

import controllers.BuildingManager;
import enums.Heading;

public class HallPanel {

    private HeadingSwitch ascendSwitch;
    private HeadingSwitch descendSwitch;

    private final BuildingManager building;
    private final int level;

    public HallPanel(boolean canAscend, boolean canDescend, BuildingManager building, int level) {
        this.building = building;
        this.level = level;
        if (canAscend) ascendSwitch = new HeadingSwitch(Heading.ASCEND);
        if (canDescend) descendSwitch = new HeadingSwitch(Heading.DESCEND);
    }

    public void callUp() {
        if (ascendSwitch != null) {
            ascendSwitch.activate();
            building.summonLift(level, Heading.ASCEND);
        }
    }

    public void callDown() {
        if (descendSwitch != null) {
            descendSwitch.activate();
            building.summonLift(level, Heading.DESCEND);
        }
    }
}
