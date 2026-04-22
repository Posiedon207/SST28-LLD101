package com.example.parkinglot;

import java.util.Map;

public class EntryGate {
    private final String gateId;
    private final Map<Integer, Integer> distanceToSlot;

    public EntryGate(String gateId, Map<Integer, Integer> distanceToSlot) {
        this.gateId = gateId;
        this.distanceToSlot = distanceToSlot;
    }

    public String getGateId() {
        return gateId;
    }

    public int getDistanceTo(int slotNumber) {
        return distanceToSlot.getOrDefault(slotNumber, Integer.MAX_VALUE);
    }
}
