package models;

import enums.Heading;
import enums.LiftStatus;
import panels.CabinPanel;
import services.ProtectionUnit;
import services.TransitHandler;
import strategies.SchedulingPolicy;
import strategies.ScanSchedulingPolicy;

import java.util.Collections;
import java.util.PriorityQueue;

public class Lift {

    private final int liftId;
    private int position;
    private LiftStatus status;
    private boolean crisis;

    private final Gate gate;
    private final WeightSensor weightSensor;
    private final ProtectionUnit protection;
    private final TransitHandler transitHandler;
    private final SchedulingPolicy scheduler;
    private final CabinPanel cabinPanel;

    private final PriorityQueue<Integer> ascendQueue = new PriorityQueue<>();
    private final PriorityQueue<Integer> descendQueue = new PriorityQueue<>(Collections.reverseOrder());

    public Lift(int liftId, int topLevel) {
        this.liftId = liftId;
        this.position = 0;
        this.status = LiftStatus.STANDBY;
        this.crisis = false;
        this.gate = new Gate();
        this.weightSensor = new WeightSensor();
        this.protection = new ProtectionUnit();
        this.transitHandler = new TransitHandler();
        this.scheduler = new ScanSchedulingPolicy();
        this.cabinPanel = new CabinPanel(topLevel);
    }

    public void enqueueStop(int level, Heading heading) {
        if (heading == Heading.ASCEND) {
            ascendQueue.add(level);
        } else {
            descendQueue.add(level);
        }
    }

    public void clearStopAtPosition() {
        ascendQueue.remove(position);
        descendQueue.remove(position);
    }

    public boolean hasStopAtPosition() {
        return ascendQueue.contains(position) || descendQueue.contains(position);
    }

    public void step() {
        transitHandler.step(this);
    }

    public int getLiftId() {
        return liftId;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int pos) {
        this.position = pos;
    }

    public LiftStatus getStatus() {
        return status;
    }

    public void setStatus(LiftStatus s) {
        this.status = s;
    }

    public boolean isInCrisis() {
        return crisis;
    }

    public void setCrisis(boolean val) {
        this.crisis = val;
    }

    public Gate getGate() {
        return gate;
    }

    public CabinPanel getCabinPanel() {
        return cabinPanel;
    }

    public WeightSensor getWeightSensor() {
        return weightSensor;
    }

    public ProtectionUnit getProtection() {
        return protection;
    }

    public SchedulingPolicy getScheduler() {
        return scheduler;
    }

    public PriorityQueue<Integer> getAscendQueue() {
        return ascendQueue;
    }

    public PriorityQueue<Integer> getDescendQueue() {
        return descendQueue;
    }
}
