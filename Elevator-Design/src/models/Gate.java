package models;

public class Gate {
    private boolean open;

    public void unlock() {
        open = true;
        System.out.println("Gate unlocked");
    }

    public void lock() {
        open = false;
        System.out.println("Gate locked");
    }

    public boolean isUnlocked() {
        return open;
    }
}
