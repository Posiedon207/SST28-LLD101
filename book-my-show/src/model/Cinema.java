import java.util.ArrayList;
import java.util.List;

class Cinema {
    private final String cinemaId;
    private final String cinemaName;
    private final String location;
    private final List<Auditorium> halls;

    Cinema(String cinemaId, String cinemaName, String location) {
        this.cinemaId = cinemaId;
        this.cinemaName = cinemaName;
        this.location = location;
        this.halls = new ArrayList<>();
    }

    void attachHall(Auditorium hall) {
        halls.add(hall);
    }

    Auditorium findHall(String hallId) {
        for (Auditorium h : halls)
            if (h.getHallId().equals(hallId)) return h;
        return null;
    }

    String getCinemaId() { return cinemaId; }
    String getCinemaName() { return cinemaName; }
    String getLocation() { return location; }
    List<Auditorium> getHalls() { return halls; }

    @Override
    public String toString() {
        return cinemaName + " @ " + location;
    }
}
