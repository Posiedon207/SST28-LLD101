class Auditorium {
    private final String hallId;
    private final String hallName;
    private final int rowCount;
    private final int colCount;
    private final Chair[][] grid;

    Auditorium(String hallId, String hallName, int rowCount, int colCount) {
        this.hallId = hallId;
        this.hallName = hallName;
        this.rowCount = rowCount;
        this.colCount = colCount;
        this.grid = new Chair[rowCount][colCount];
        buildGrid();
    }

    private void buildGrid() {
        for (int r = 0; r < rowCount; r++) {
            for (int c = 0; c < colCount; c++) {
                String tag = String.valueOf((char) ('A' + r)) + (c + 1);
                ChairCategory cat = resolveCategory(r);
                grid[r][c] = new Chair(tag, r, c, cat);
            }
        }
    }

    private ChairCategory resolveCategory(int row) {
        if (row >= rowCount - 2) return ChairCategory.LUXURY;
        if (row >= rowCount / 2) return ChairCategory.COMFORT;
        return ChairCategory.ECONOMY;
    }

    Chair fetchChair(int r, int c) {
        if (r < 0 || r >= rowCount || c < 0 || c >= colCount) return null;
        return grid[r][c];
    }

    int openChairCount() {
        int count = 0;
        for (Chair[] row : grid)
            for (Chair ch : row)
                if (ch.getState() == ChairState.OPEN) count++;
        return count;
    }

    String getHallId() { return hallId; }
    String getHallName() { return hallName; }
    int getRowCount() { return rowCount; }
    int getColCount() { return colCount; }
    Chair[][] getGrid() { return grid; }
}
