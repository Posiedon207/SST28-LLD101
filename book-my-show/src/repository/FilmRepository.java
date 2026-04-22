import java.util.HashMap;
import java.util.Map;

class FilmRepository {
    private final Map<String, Film> store = new HashMap<>();

    void save(Film film) {
        store.put(film.getFilmId(), film);
    }

    Film findById(String filmId) {
        return store.get(filmId);
    }

    Map<String, Film> findAll() {
        return store;
    }
}
