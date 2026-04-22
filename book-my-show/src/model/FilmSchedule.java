import java.time.LocalDateTime;

class FilmSchedule {
    private final String scheduleId;
    private final Film film;
    private final Cinema cinema;
    private final Auditorium hall;
    private final LocalDateTime playTime;
    private final RateCalculator rateCalculator;

    FilmSchedule(String scheduleId, Film film, Cinema cinema, Auditorium hall,
                 LocalDateTime playTime, RateCalculator rateCalculator) {
        this.scheduleId = scheduleId;
        this.film = film;
        this.cinema = cinema;
        this.hall = hall;
        this.playTime = playTime;
        this.rateCalculator = rateCalculator;
    }

    String getScheduleId() { return scheduleId; }
    Film getFilm() { return film; }
    Cinema getCinema() { return cinema; }
    Auditorium getHall() { return hall; }
    LocalDateTime getPlayTime() { return playTime; }
    RateCalculator getRateCalculator() { return rateCalculator; }

    @Override
    public String toString() {
        return film.getTitle() + " | " + cinema.getCinemaName() + " | " + playTime;
    }
}
