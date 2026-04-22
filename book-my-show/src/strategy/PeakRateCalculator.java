class PeakRateCalculator implements RateCalculator {
    @Override
    public int computeRate(ChairCategory category) {
        return (int) (category.getRate() * 1.25);
    }
}
