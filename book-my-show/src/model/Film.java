class Film {
    private final String filmId;
    private final String title;
    private final String tongue;
    private final String category;
    private final int lengthMinutes;
    private FilmPhase phase;

    Film(String filmId, String title, String tongue, String category, int lengthMinutes) {
        this.filmId = filmId;
        this.title = title;
        this.tongue = tongue;
        this.category = category;
        this.lengthMinutes = lengthMinutes;
        this.phase = FilmPhase.UPCOMING;
    }

    String getFilmId() { return filmId; }
    String getTitle() { return title; }
    String getTongue() { return tongue; }
    String getCategory() { return category; }
    int getLengthMinutes() { return lengthMinutes; }
    FilmPhase getPhase() { return phase; }
    void moveToPhase(FilmPhase phase) { this.phase = phase; }

    @Override
    public String toString() {
        return title + " [" + tongue + " | " + category + "]";
    }
}
