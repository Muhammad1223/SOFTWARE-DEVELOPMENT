public class Plant {

    private int row;
    private int col;

    public Plant(int row, int col) {

        this.row = row;
        this.col = col;
    }

    // ----------------------------
    // Getters
    // ----------------------------

    public int getRow() {

        return row;
    }

    public int getCol() {

        return col;
    }

    // ----------------------------
    // Setters
    // ----------------------------

    public void setRow(int row) {

        this.row = row;
    }

    public void setCol(int col) {

        this.col = col;
    }

    public void setPosition(
            int row,
            int col) {

        this.row = row;
        this.col = col;
    }

    // ----------------------------
    // Utility
    // ----------------------------

    @Override
    public String toString() {

        return "Plant[row=" + row +
               ", col=" + col + "]";
    }
}