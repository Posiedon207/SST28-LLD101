package alerts;

public class CapacityAlert implements Alert {
    public void trigger() {
        System.out.println("CAPACITY: Weight limit exceeded!");
    }
}
