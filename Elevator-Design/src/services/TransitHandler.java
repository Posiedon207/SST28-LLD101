package services;

import enums.Heading;
import enums.LiftStatus;
import models.Lift;

public class TransitHandler {

    public void step(Lift lift) {
        if (lift.getGate().isUnlocked()) {
            lift.getGate().lock();
        }

        if (lift.getWeightSensor().isExceeded()) {
            lift.getProtection().handleCapacityBreach(lift);
            return;
        }

        if (!lift.getProtection().isSafeToOperate(lift.getWeightSensor(), lift.isInCrisis())) {
            return;
        }

        Heading next = lift.getScheduler().resolveNextHeading(lift);

        if (next == null) {
            lift.setStatus(LiftStatus.STANDBY);
            return;
        }

        if (next == Heading.ASCEND) {
            lift.setStatus(LiftStatus.ASCENDING);
            lift.setPosition(lift.getPosition() + 1);
        } else {
            lift.setStatus(LiftStatus.DESCENDING);
            lift.setPosition(lift.getPosition() - 1);
        }

        if (lift.hasStopAtPosition()) {
            System.out.println("Lift " + lift.getLiftId() +
                " stopping at level " + lift.getPosition());
            lift.getGate().unlock();
            lift.clearStopAtPosition();
        }
    }
}
