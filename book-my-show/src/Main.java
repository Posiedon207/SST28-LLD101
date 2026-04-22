import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        TicketReservationPlatform platform = TicketReservationPlatform.getInstance();

        Cinema waveDelhi = new Cinema("C1", "Wave Cinemas", "Noida, Delhi NCR");
        Cinema galaxyPune = new Cinema("C2", "Galaxy Cineplex", "Koregaon Park, Pune");
        Cinema regalHyd = new Cinema("C3", "Regal Talkies", "Banjara Hills, Hyderabad");

        platform.cinemas().registerCinema(waveDelhi);
        platform.cinemas().registerCinema(galaxyPune);
        platform.cinemas().registerCinema(regalHyd);

        Auditorium hall1 = new Auditorium("H1", "Dolby Atmos", 5, 8);
        Auditorium hall2 = new Auditorium("H2", "4DX Experience", 7, 10);
        Auditorium hall3 = new Auditorium("H3", "Classic Hall", 6, 9);

        waveDelhi.attachHall(hall1);
        waveDelhi.attachHall(hall2);
        galaxyPune.attachHall(hall3);

        Film f1 = new Film("F1", "Oppenheimer", "English", "Biography", 180);
        Film f2 = new Film("F2", "Jawan", "Hindi", "Action", 169);
        Film f3 = new Film("F3", "Interstellar", "English", "Sci-Fi", 169);

        f1.moveToPhase(FilmPhase.RUNNING);
        f2.moveToPhase(FilmPhase.RUNNING);
        f3.moveToPhase(FilmPhase.UPCOMING);

        platform.films().registerFilm(f1);
        platform.films().registerFilm(f2);
        platform.films().registerFilm(f3);

        LocalDateTime base = LocalDateTime.now();

        FilmSchedule morningSlot = new FilmSchedule(
                "FS1", f1, waveDelhi, hall1,
                base.plusDays(1).withHour(9).withMinute(30),
                new OffPeakRateCalculator()
        );

        FilmSchedule eveningSlot = new FilmSchedule(
                "FS2", f1, waveDelhi, hall1,
                base.plusDays(1).withHour(19).withMinute(0),
                new PeakRateCalculator()
        );

        FilmSchedule nightSlot = new FilmSchedule(
                "FS3", f2, waveDelhi, hall2,
                base.plusDays(2).withHour(22).withMinute(0),
                new OffPeakRateCalculator()
        );

        platform.schedules().registerSchedule(morningSlot);
        platform.schedules().registerSchedule(eveningSlot);
        platform.schedules().registerSchedule(nightSlot);

        Patron p1 = new Patron("P1", "Vikram Mehta", "vikram@mail.com", "8800112233");
        Patron p2 = new Patron("P2", "Sneha Iyer", "sneha@mail.com", "7799001122");
        Patron p3 = new Patron("P3", "Karan Desai", "karan@mail.com", "9988776655");

        platform.patrons().enroll(p1);
        platform.patrons().enroll(p2);
        platform.patrons().enroll(p3);

        System.out.println("============ Ticket Reservation Platform ============\n");

        System.out.println("-- Cinemas in Delhi NCR --");
        List<Cinema> delhiCinemas = platform.cinemas().fetchByLocation("Delhi");
        for (Cinema c : delhiCinemas)
            System.out.println("   > " + c);

        System.out.println("\n-- Currently Running Films --");
        List<Film> running = platform.films().fetchRunningFilms();
        for (Film f : running)
            System.out.println("   > " + f);

        platform.schedules().renderLayout("FS1");

        System.out.println("-- Ticket Reservation Flow --\n");

        System.out.println("Phase 1: Initiate ticket");
        Ticket t1 = platform.tickets().initiateTicket("TK1", "P1", "FS1");
        System.out.println("   " + t1);

        System.out.println("\nPhase 2: Pick chairs");
        Chair c1 = hall1.fetchChair(0, 2);
        Chair c2 = hall1.fetchChair(0, 3);
        t1.pickChair(c1);
        t1.pickChair(c2);
        System.out.println("   Picked: " + c1.getTag() + ", " + c2.getTag());
        System.out.println("   Total: Rs." + t1.getGrandTotal());

        System.out.println("\nPhase 3: Process transaction");
        Transaction txn1 = new Transaction("TX1", t1.getGrandTotal(), "NET_BANKING");
        txn1.execute();
        System.out.println("   " + txn1);

        System.out.println("\nPhase 4: Finalize ticket");
        boolean ok = t1.finalizeTicket(txn1);
        System.out.println("   Finalized: " + ok);
        System.out.println("   " + t1);

        System.out.println("\n-- Second Reservation --\n");

        Ticket t2 = platform.tickets().initiateTicket("TK2", "P2", "FS2");
        Chair c3 = hall1.fetchChair(2, 4);
        Chair c4 = hall1.fetchChair(2, 5);
        Chair c5 = hall1.fetchChair(2, 6);

        t2.pickChair(c3);
        t2.pickChair(c4);
        t2.pickChair(c5);

        System.out.println("Ticket: " + t2);
        System.out.println("Chairs: " + c3.getTag() + ", " + c4.getTag() + ", " + c5.getTag());
        System.out.println("Total (Peak): Rs." + t2.getGrandTotal());

        Transaction txn2 = new Transaction("TX2", t2.getGrandTotal(), "DEBIT_CARD");
        txn2.execute();
        t2.finalizeTicket(txn2);
        System.out.println("Transaction: " + txn2);
        System.out.println("State: " + t2.getState());

        System.out.println("\n-- Updated Layout --");
        platform.schedules().renderLayout("FS1");

        System.out.println("-- Patron History --\n");
        List<Ticket> vikramTickets = platform.tickets().fetchPatronTickets("P1");
        System.out.println("Tickets for " + p1.getFullName() + ":");
        for (Ticket t : vikramTickets)
            System.out.println("   > " + t);

        System.out.println("\n-- Open Chair Count --");
        int openCount = platform.schedules().countOpenChairs("FS1");
        System.out.println("Open chairs for " + morningSlot.getFilm().getTitle() + ": " + openCount);

        System.out.println("\n============ Demo Complete ============");
    }
}
