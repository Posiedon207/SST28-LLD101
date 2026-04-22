package models;

public class CabinCall extends ServiceCall {
    private final int targetLevel;

    public CabinCall(int originLevel, int targetLevel) {
        super(originLevel);
        this.targetLevel = targetLevel;
    }

    public int getTargetLevel() {
        return targetLevel;
    }
}
