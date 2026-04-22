import java.util.HashMap;
import java.util.Map;

class TicketRepository {
    private final Map<String, Ticket> store = new HashMap<>();

    void save(Ticket ticket) {
        store.put(ticket.getTicketId(), ticket);
    }

    Ticket findById(String ticketId) {
        return store.get(ticketId);
    }

    Map<String, Ticket> findAll() {
        return store;
    }
}
