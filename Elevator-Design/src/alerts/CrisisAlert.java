package alerts;

public class CrisisAlert implements Alert {
    public void trigger() {
        System.out.println("CRISIS: Immediate halt triggered!");
    }
}
