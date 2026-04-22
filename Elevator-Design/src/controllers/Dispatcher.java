package controllers;

import enums.Heading;
import models.CabinCall;
import models.HallCall;
import models.Lift;
import strategies.LiftSelectionPolicy;

import java.util.List;

public class Dispatcher {

    private final LiftSelectionPolicy selectionPolicy;

    public Dispatcher(LiftSelectionPolicy selectionPolicy) {
        this.selectionPolicy = selectionPolicy;
    }

    public void processHallCall(List<Lift> lifts, HallCall call) {
        Lift selected = selectionPolicy.selectLift(lifts, call);
        if (selected != null) {
            selected.enqueueStop(call.getOriginLevel(), call.getHeading());
        }
    }

    public void processCabinCall(Lift lift, CabinCall call) {
        Heading heading = (call.getTargetLevel() > lift.getPosition())
                ? Heading.ASCEND : Heading.DESCEND;
        lift.enqueueStop(call.getTargetLevel(), heading);
    }
}
