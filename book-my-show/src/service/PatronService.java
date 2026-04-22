class PatronService {
    private final PatronRepository patronRepo;

    PatronService(PatronRepository patronRepo) {
        this.patronRepo = patronRepo;
    }

    void enroll(Patron patron) {
        patronRepo.save(patron);
    }

    Patron fetchPatron(String patronId) {
        return patronRepo.findById(patronId);
    }
}
