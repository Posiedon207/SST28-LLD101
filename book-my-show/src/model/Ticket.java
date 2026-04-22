import java.util.ArrayList;
import java.util.List;

class Ticket {
    private final String ticketId;
    private final Patron patron;
    private final FilmSchedule schedule;
    private final List<Chair> selectedChairs;
    private TicketState state;
    private Transaction transaction;
    private int grandTotal;

    Ticket(String ticketId, Patron patron, FilmSchedule schedule) {
        this.ticketId = ticketId;
        this.patron = patron;
        this.schedule = schedule;
        this.selectedChairs = new ArrayList<>();
        this.state = TicketState.INITIATED;
        this.grandTotal = 0;
    }

    void pickChair(Chair chair) throws Exception {
        if (chair.getState() != ChairState.OPEN)
            throw new Exception("Chair " + chair.getTag() + " is unavailable");
        selectedChairs.add(chair);
        chair.updateState(ChairState.HELD);
        grandTotal += schedule.getRateCalculator().computeRate(chair.getCategory());
    }

    void releaseAllChairs() {
        for (Chair ch : selectedChairs)
            ch.updateState(ChairState.OPEN);
        selectedChairs.clear();
        grandTotal = 0;
    }

    boolean finalizeTicket(Transaction txn) {
        if (txn.getState() != TransactionState.COMPLETED) return false;
        for (Chair ch : selectedChairs)
            ch.updateState(ChairState.OCCUPIED);
        this.transaction = txn;
        this.state = TicketState.APPROVED;
        return true;
    }

    String getTicketId() { return ticketId; }
    Patron getPatron() { return patron; }
    FilmSchedule getSchedule() { return schedule; }
    List<Chair> getSelectedChairs() { return selectedChairs; }
    TicketState getState() { return state; }
    Transaction getTransaction() { return transaction; }
    int getGrandTotal() { return grandTotal; }

    @Override
    public String toString() {
        return "Ticket#" + ticketId + " | " + patron.getFullName()
                + " | " + selectedChairs.size() + " chairs | Rs."
                + grandTotal + " | " + state;
    }
}
