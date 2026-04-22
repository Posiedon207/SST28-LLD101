import java.util.ArrayList;
import java.util.List;

class ScheduleService {
    private final ScheduleRepository scheduleRepo;

    ScheduleService(ScheduleRepository scheduleRepo) {
        this.scheduleRepo = scheduleRepo;
    }

    void registerSchedule(FilmSchedule schedule) {
        scheduleRepo.save(schedule);
    }

    FilmSchedule fetchSchedule(String scheduleId) {
        return scheduleRepo.findById(scheduleId);
    }

    List<FilmSchedule> fetchByCinema(String cinemaId) {
        List<FilmSchedule> result = new ArrayList<>();
        for (FilmSchedule fs : scheduleRepo.findAll().values())
            if (fs.getCinema().getCinemaId().equals(cinemaId))
                result.add(fs);
        return result;
    }

    List<FilmSchedule> fetchByFilm(String filmId) {
        List<FilmSchedule> result = new ArrayList<>();
        for (FilmSchedule fs : scheduleRepo.findAll().values())
            if (fs.getFilm().getFilmId().equals(filmId))
                result.add(fs);
        return result;
    }

    int countOpenChairs(String scheduleId) {
        FilmSchedule fs = scheduleRepo.findById(scheduleId);
        if (fs == null) return 0;
        return fs.getHall().openChairCount();
    }

    void renderLayout(String scheduleId) {
        FilmSchedule fs = scheduleRepo.findById(scheduleId);
        if (fs == null) return;
        Auditorium hall = fs.getHall();
        System.out.println("\n=== " + fs.getFilm().getTitle() + " | " + hall.getHallName() + " ===");
        System.out.print("      ");
        for (int c = 1; c <= hall.getColCount(); c++)
            System.out.printf("%-4d", c);
        System.out.println();
        for (int r = 0; r < hall.getRowCount(); r++) {
            System.out.printf("  %c   ", 'A' + r);
            for (int c = 0; c < hall.getColCount(); c++) {
                Chair ch = hall.fetchChair(r, c);
                System.out.print(ch.getState() == ChairState.OPEN ? " o  " : " x  ");
            }
            System.out.println();
        }
        System.out.println("  o = open    x = taken\n");
    }
}
