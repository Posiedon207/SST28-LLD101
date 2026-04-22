import java.util.ArrayList;
import java.util.List;

class FilmService {
    private final FilmRepository filmRepo;

    FilmService(FilmRepository filmRepo) {
        this.filmRepo = filmRepo;
    }

    void registerFilm(Film film) {
        filmRepo.save(film);
    }

    Film fetchFilm(String filmId) {
        return filmRepo.findById(filmId);
    }

    List<Film> fetchRunningFilms() {
        List<Film> result = new ArrayList<>();
        for (Film f : filmRepo.findAll().values())
            if (f.getPhase() == FilmPhase.RUNNING)
                result.add(f);
        return result;
    }
}
