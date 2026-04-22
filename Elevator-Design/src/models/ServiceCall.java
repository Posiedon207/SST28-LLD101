package models;

public abstract class ServiceCall {
    protected final int originLevel;

    public ServiceCall(int originLevel) {
        this.originLevel = originLevel;
    }

    public int getOriginLevel() {
        return originLevel;
    }
}
