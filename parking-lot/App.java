package com.example.parkinglot;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        List<ParkingSlot> slots = new ArrayList<>();
        slots.add(new ParkingSlot(1, SlotType.SMALL, 0));
        slots.add(new ParkingSlot(2, SlotType.SMALL, 0));
        slots.add(new ParkingSlot(3, SlotType.MEDIUM, 0));
        slots.add(new ParkingSlot(4, SlotType.MEDIUM, 0));
        slots.add(new ParkingSlot(5, SlotType.LARGE, 0));
        slots.add(new ParkingSlot(6, SlotType.LARGE, 0));

        Map<Integer, Integer> gateADistances = new HashMap<>();
        gateADistances.put(1, 2);
        gateADistances.put(2, 4);
        gateADistances.put(3, 3);
        gateADistances.put(4, 5);
        gateADistances.put(5, 6);
        gateADistances.put(6, 8);

        Map<Integer, Integer> gateBDistances = new HashMap<>();
        gateBDistances.put(1, 8);
        gateBDistances.put(2, 6);
        gateBDistances.put(3, 5);
        gateBDistances.put(4, 3);
        gateBDistances.put(5, 4);
        gateBDistances.put(6, 2);

        Map<String, EntryGate> gates = new HashMap<>();
        gates.put("GATE-A", new EntryGate("GATE-A", gateADistances));
        gates.put("GATE-B", new EntryGate("GATE-B", gateBDistances));

        Map<SlotType, Double> hourlyRates = new HashMap<>();
        hourlyRates.put(SlotType.SMALL, 10.0);
        hourlyRates.put(SlotType.MEDIUM, 20.0);
        hourlyRates.put(SlotType.LARGE, 30.0);

        ParkingLot lot = new ParkingLot(slots, gates, hourlyRates);

        printStatus(lot);

        Vehicle bike = new Vehicle("KA-01-1234", VehicleType.TWO_WHEELER);
        LocalDateTime bikeEntry = LocalDateTime.of(2026, 3, 30, 10, 0);
        ParkingTicket bikeTicket = lot.park(bike, bikeEntry, SlotType.SMALL, "GATE-A");
        System.out.println("Parked: " + bikeTicket);

        Vehicle car = new Vehicle("KA-02-5678", VehicleType.CAR);
        LocalDateTime carEntry = LocalDateTime.of(2026, 3, 30, 10, 30);
        ParkingTicket carTicket = lot.park(car, carEntry, SlotType.MEDIUM, "GATE-B");
        System.out.println("Parked: " + carTicket);

        Vehicle bus = new Vehicle("KA-03-9999", VehicleType.BUS);
        LocalDateTime busEntry = LocalDateTime.of(2026, 3, 30, 11, 0);
        ParkingTicket busTicket = lot.park(bus, busEntry, SlotType.LARGE, "GATE-A");
        System.out.println("Parked: " + busTicket);

        printStatus(lot);

        LocalDateTime bikeExit = LocalDateTime.of(2026, 3, 30, 13, 30);
        double bikeBill = lot.exit(bikeTicket, bikeExit);
        System.out.println("Bill for " + bike + ": Rs." + bikeBill + " (3.5 hrs in " + bikeTicket.getSlotType() + " slot)");

        LocalDateTime carExit = LocalDateTime.of(2026, 3, 30, 12, 30);
        double carBill = lot.exit(carTicket, carExit);
        System.out.println("Bill for " + car + ": Rs." + carBill + " (2 hrs in " + carTicket.getSlotType() + " slot)");

        printStatus(lot);
    }

    private static void printStatus(ParkingLot lot) {
        System.out.println("\n--- Parking Status ---");
        Map<SlotType, int[]> status = lot.status();
        for (SlotType type : SlotType.values()) {
            int[] counts = status.get(type);
            System.out.println(type + ": " + counts[1] + " available / " + counts[0] + " total");
        }
        System.out.println("----------------------\n");
    }
}
