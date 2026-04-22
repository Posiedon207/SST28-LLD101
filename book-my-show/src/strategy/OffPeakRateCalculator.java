class OffPeakRateCalculator implements RateCalculator {
    @Override
    public int computeRate(ChairCategory category) {
        return (int) (category.getRate() * 0.85);
    }
}
