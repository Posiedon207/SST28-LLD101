package com.example.parkinglot;

import java.time.LocalDateTime;

public class ParkingTicket {
    private final String ticketId;
    private final Vehicle vehicle;
    private final int slotNumber;
    private final SlotType slotType;
    private final LocalDateTime entryTime;

    public ParkingTicket(String ticketId, Vehicle vehicle, int slotNumber, SlotType slotType, LocalDateTime entryTime) {
        this.ticketId = ticketId;
        this.vehicle = vehicle;
        this.slotNumber = slotNumber;
        this.slotType = slotType;
        this.entryTime = entryTime;
    }

    public String getTicketId() {
        return ticketId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public int getSlotNumber() {
        return slotNumber;
    }

    public SlotType getSlotType() {
        return slotType;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    @Override
    public String toString() {
        return "Ticket[" + ticketId + "] " + vehicle + " -> Slot-" + slotNumber + " (" + slotType + ") at " + entryTime;
    }
}
