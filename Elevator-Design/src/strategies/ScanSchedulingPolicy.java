package strategies;

import enums.Heading;
import enums.LiftStatus;
import models.Lift;

public class ScanSchedulingPolicy implements SchedulingPolicy {

    @Override
    public Heading resolveNextHeading(Lift lift) {
        if (lift.getStatus() == LiftStatus.ASCENDING && !lift.getAscendQueue().isEmpty()) {
            return Heading.ASCEND;
        }
        if (lift.getStatus() == LiftStatus.DESCENDING && !lift.getDescendQueue().isEmpty()) {
            return Heading.DESCEND;
        }
        if (!lift.getAscendQueue().isEmpty()) {
            return Heading.ASCEND;
        }
        if (!lift.getDescendQueue().isEmpty()) {
            return Heading.DESCEND;
        }
        return null;
    }
}
