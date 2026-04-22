class Chair {
    private final String tag;
    private final int row;
    private final int col;
    private final ChairCategory category;
    private ChairState state;

    Chair(String tag, int row, int col, ChairCategory category) {
        this.tag = tag;
        this.row = row;
        this.col = col;
        this.category = category;
        this.state = ChairState.OPEN;
    }

    String getTag() { return tag; }
    int getRow() { return row; }
    int getCol() { return col; }
    ChairCategory getCategory() { return category; }
    ChairState getState() { return state; }
    void updateState(ChairState state) { this.state = state; }

    @Override
    public String toString() {
        return tag + "[" + state + "]";
    }
}
