package panels;

public abstract class Switch {
    protected boolean activated;

    public void activate() {
        activated = true;
    }

    public void deactivate() {
        activated = false;
    }

    public boolean isActivated() {
        return activated;
    }
}
