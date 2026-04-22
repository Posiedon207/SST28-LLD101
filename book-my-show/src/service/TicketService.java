import java.util.ArrayList;
import java.util.List;

class TicketService {
    private final TicketRepository ticketRepo;
    private final PatronRepository patronRepo;
    private final ScheduleRepository scheduleRepo;

    TicketService(TicketRepository ticketRepo, PatronRepository patronRepo,
                  ScheduleRepository scheduleRepo) {
        this.ticketRepo = ticketRepo;
        this.patronRepo = patronRepo;
        this.scheduleRepo = scheduleRepo;
    }

    Ticket initiateTicket(String ticketId, String patronId, String scheduleId) throws Exception {
        Patron patron = patronRepo.findById(patronId);
        FilmSchedule schedule = scheduleRepo.findById(scheduleId);
        if (patron == null || schedule == null)
            throw new Exception("Patron or schedule not found");
        Ticket ticket = new Ticket(ticketId, patron, schedule);
        ticketRepo.save(ticket);
        return ticket;
    }

    Ticket fetchTicket(String ticketId) {
        return ticketRepo.findById(ticketId);
    }

    List<Ticket> fetchPatronTickets(String patronId) {
        List<Ticket> result = new ArrayList<>();
        for (Ticket t : ticketRepo.findAll().values())
            if (t.getPatron().getPatronId().equals(patronId))
                result.add(t);
        return result;
    }
}
