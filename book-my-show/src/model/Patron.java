class Patron {
    private final String patronId;
    private final String fullName;
    private final String mail;
    private final String mobile;

    Patron(String patronId, String fullName, String mail, String mobile) {
        this.patronId = patronId;
        this.fullName = fullName;
        this.mail = mail;
        this.mobile = mobile;
    }

    String getPatronId() { return patronId; }
    String getFullName() { return fullName; }
    String getMail() { return mail; }
    String getMobile() { return mobile; }

    @Override
    public String toString() {
        return fullName + " (" + patronId + ")";
    }
}
