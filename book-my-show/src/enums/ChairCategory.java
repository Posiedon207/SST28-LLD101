enum ChairCategory {
    ECONOMY(120),
    COMFORT(200),
    LUXURY(350);

    private final int rate;

    ChairCategory(int rate) {
        this.rate = rate;
    }

    public int getRate() { return rate; }
}
