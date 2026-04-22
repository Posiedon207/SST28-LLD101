package com.example.parkinglot;

public class ParkingSlot {
    private final int slotNumber;
    private final SlotType type;
    private final int distanceFromGate;
    private boolean occupied;

    public ParkingSlot(int slotNumber, SlotType type, int distanceFromGate) {
        this.slotNumber = slotNumber;
        this.type = type;
        this.distanceFromGate = distanceFromGate;
        this.occupied = false;
    }

    public int getSlotNumber() {
        return slotNumber;
    }

    public SlotType getType() {
        return type;
    }

    public int getDistanceFromGate() {
        return distanceFromGate;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void occupy() {
        this.occupied = true;
    }

    public void vacate() {
        this.occupied = false;
    }

    @Override
    public String toString() {
        return "Slot-" + slotNumber + " (" + type + ")";
    }
}
