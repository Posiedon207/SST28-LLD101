package strategies;

import enums.LiftStatus;
import models.HallCall;
import models.Lift;

import java.util.List;

public class ClosestLiftPolicy implements LiftSelectionPolicy {

    @Override
    public Lift selectLift(List<Lift> lifts, HallCall call) {
        Lift closest = null;
        int shortestGap = Integer.MAX_VALUE;

        for (Lift lift : lifts) {
            if (lift.getStatus() == LiftStatus.OUT_OF_SERVICE || lift.isInCrisis()) {
                continue;
            }
            int gap = Math.abs(lift.getPosition() - call.getOriginLevel());
            if (gap < shortestGap) {
                shortestGap = gap;
                closest = lift;
            }
        }
        return closest;
    }
}
