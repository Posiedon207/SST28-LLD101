import java.util.HashMap;
import java.util.Map;

class PatronRepository {
    private final Map<String, Patron> store = new HashMap<>();

    void save(Patron patron) {
        store.put(patron.getPatronId(), patron);
    }

    Patron findById(String patronId) {
        return store.get(patronId);
    }

    Map<String, Patron> findAll() {
        return store;
    }
}
