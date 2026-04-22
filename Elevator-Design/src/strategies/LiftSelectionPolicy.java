package strategies;

import enums.Heading;
import models.HallCall;
import models.Lift;

import java.util.List;

public interface LiftSelectionPolicy {
    Lift selectLift(List<Lift> lifts, HallCall call);
}
