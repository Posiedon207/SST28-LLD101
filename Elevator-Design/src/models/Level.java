package models;

import controllers.BuildingManager;
import panels.HallPanel;

public class Level {
    private final int levelNumber;
    private final HallPanel hallPanel;

    public Level(int levelNumber, int topLevel, BuildingManager building) {
        this.levelNumber = levelNumber;
        boolean canAscend = (levelNumber < topLevel);
        boolean canDescend = (levelNumber > 0);
        this.hallPanel = new HallPanel(canAscend, canDescend, building, levelNumber);
    }

    public int getLevelNumber() {
        return levelNumber;
    }

    public HallPanel getHallPanel() {
        return hallPanel;
    }
}
