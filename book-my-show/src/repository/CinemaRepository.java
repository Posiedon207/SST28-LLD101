import java.util.HashMap;
import java.util.Map;

class CinemaRepository {
    private final Map<String, Cinema> store = new HashMap<>();

    void save(Cinema cinema) {
        store.put(cinema.getCinemaId(), cinema);
    }

    Cinema findById(String cinemaId) {
        return store.get(cinemaId);
    }

    Map<String, Cinema> findAll() {
        return store;
    }
}
