package strategies;

import enums.Heading;
import models.Lift;

public interface SchedulingPolicy {
    Heading resolveNextHeading(Lift lift);
}
