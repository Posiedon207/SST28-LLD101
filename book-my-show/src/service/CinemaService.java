import java.util.ArrayList;
import java.util.List;

class CinemaService {
    private final CinemaRepository cinemaRepo;

    CinemaService(CinemaRepository cinemaRepo) {
        this.cinemaRepo = cinemaRepo;
    }

    void registerCinema(Cinema cinema) {
        cinemaRepo.save(cinema);
    }

    Cinema fetchCinema(String cinemaId) {
        return cinemaRepo.findById(cinemaId);
    }

    List<Cinema> fetchByLocation(String location) {
        List<Cinema> result = new ArrayList<>();
        for (Cinema c : cinemaRepo.findAll().values())
            if (c.getLocation().toLowerCase().contains(location.toLowerCase()))
                result.add(c);
        return result;
    }
}
