package services;

import alerts.Alert;
import alerts.CapacityAlert;
import alerts.CrisisAlert;
import models.Lift;
import models.WeightSensor;

public class ProtectionUnit {

    private final Alert capacityAlert = new CapacityAlert();
    private final Alert crisisAlert = new CrisisAlert();

    public boolean isSafeToOperate(WeightSensor sensor, boolean crisisActive) {
        return !sensor.isExceeded() && !crisisActive;
    }

    public void activateCrisis(Lift lift) {
        lift.setCrisis(true);
        crisisAlert.trigger();
        lift.getGate().unlock();
    }

    public void handleCapacityBreach(Lift lift) {
        capacityAlert.trigger();
        lift.getGate().unlock();
    }
}
