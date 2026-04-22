class TicketReservationPlatform {
    private static TicketReservationPlatform sole;

    private final CinemaRepository cinemaRepo;
    private final FilmRepository filmRepo;
    private final ScheduleRepository scheduleRepo;
    private final PatronRepository patronRepo;
    private final TicketRepository ticketRepo;

    private final CinemaService cinemaService;
    private final FilmService filmService;
    private final ScheduleService scheduleService;
    private final PatronService patronService;
    private final TicketService ticketService;

    private TicketReservationPlatform() {
        cinemaRepo = new CinemaRepository();
        filmRepo = new FilmRepository();
        scheduleRepo = new ScheduleRepository();
        patronRepo = new PatronRepository();
        ticketRepo = new TicketRepository();

        cinemaService = new CinemaService(cinemaRepo);
        filmService = new FilmService(filmRepo);
        scheduleService = new ScheduleService(scheduleRepo);
        patronService = new PatronService(patronRepo);
        ticketService = new TicketService(ticketRepo, patronRepo, scheduleRepo);
    }

    static TicketReservationPlatform getInstance() {
        if (sole == null)
            sole = new TicketReservationPlatform();
        return sole;
    }

    CinemaService cinemas() { return cinemaService; }
    FilmService films() { return filmService; }
    ScheduleService schedules() { return scheduleService; }
    PatronService patrons() { return patronService; }
    TicketService tickets() { return ticketService; }
}
