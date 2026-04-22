package panels;

public class LevelSwitch extends Switch {
    private final int level;

    public LevelSwitch(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
}
