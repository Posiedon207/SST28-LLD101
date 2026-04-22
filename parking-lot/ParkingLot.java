package com.example.parkinglot;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ParkingLot {
    private final List<ParkingSlot> slots;
    private final Map<String, EntryGate> gates;
    private final Map<SlotType, Double> hourlyRates;
    private final Map<String, ParkingTicket> activeTickets;
    private int ticketCounter;

    public ParkingLot(List<ParkingSlot> slots, Map<String, EntryGate> gates, Map<SlotType, Double> hourlyRates) {
        this.slots = slots;
        this.gates = gates;
        this.hourlyRates = hourlyRates;
        this.activeTickets = new HashMap<>();
        this.ticketCounter = 0;
    }

    public ParkingTicket park(Vehicle vehicle, LocalDateTime entryTime, SlotType requestedSlotType, String entryGateId) {
        EntryGate gate = gates.get(entryGateId);
        if (gate == null) {
            throw new IllegalArgumentException("Unknown gate: " + entryGateId);
        }

        List<SlotType> compatible = vehicle.getType().getCompatibleSlots();
        if (!compatible.contains(requestedSlotType)) {
            throw new IllegalArgumentException(vehicle.getType() + " cannot park in " + requestedSlotType + " slot");
        }

        List<SlotType> fallbackOrder = compatible.subList(compatible.indexOf(requestedSlotType), compatible.size());

        for (SlotType targetType : fallbackOrder) {
            ParkingSlot nearest = slots.stream()
                    .filter(s -> s.getType() == targetType && !s.isOccupied())
                    .min(Comparator.comparingInt(s -> gate.getDistanceTo(s.getSlotNumber())))
                    .orElse(null);

            if (nearest != null) {
                nearest.occupy();
                String ticketId = "TKT-" + (++ticketCounter);
                ParkingTicket ticket = new ParkingTicket(ticketId, vehicle, nearest.getSlotNumber(), nearest.getType(), entryTime);
                activeTickets.put(ticketId, ticket);
                return ticket;
            }
        }

        throw new RuntimeException("No compatible slot available for " + vehicle);
    }

    public Map<SlotType, int[]> status() {
        Map<SlotType, int[]> availability = new HashMap<>();
        for (SlotType type : SlotType.values()) {
            availability.put(type, new int[]{0, 0});
        }
        for (ParkingSlot slot : slots) {
            int[] counts = availability.get(slot.getType());
            counts[0]++;
            if (!slot.isOccupied()) {
                counts[1]++;
            }
        }
        return availability;
    }

    public double exit(ParkingTicket ticket, LocalDateTime exitTime) {
        ParkingTicket active = activeTickets.remove(ticket.getTicketId());
        if (active == null) {
            throw new IllegalArgumentException("Invalid or already used ticket: " + ticket.getTicketId());
        }

        ParkingSlot slot = slots.stream()
                .filter(s -> s.getSlotNumber() == active.getSlotNumber())
                .findFirst()
                .orElseThrow();

        slot.vacate();

        long minutes = Duration.between(active.getEntryTime(), exitTime).toMinutes();
        long hours = (long) Math.ceil(minutes / 60.0);
        if (hours == 0) hours = 1;

        double rate = hourlyRates.get(active.getSlotType());
        return hours * rate;
    }
}
