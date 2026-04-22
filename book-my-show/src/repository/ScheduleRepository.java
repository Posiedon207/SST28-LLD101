import java.util.HashMap;
import java.util.Map;

class ScheduleRepository {
    private final Map<String, FilmSchedule> store = new HashMap<>();

    void save(FilmSchedule schedule) {
        store.put(schedule.getScheduleId(), schedule);
    }

    FilmSchedule findById(String scheduleId) {
        return store.get(scheduleId);
    }

    Map<String, FilmSchedule> findAll() {
        return store;
    }
}
