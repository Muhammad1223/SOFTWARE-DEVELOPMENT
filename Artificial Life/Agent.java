public abstract class Agent {

    protected int row;
    protected int col;

    protected Direction direction;

    protected double energy;

    protected boolean alive;

    protected NeuralNetwork brain;

    public Agent(int row, int col) {

        this.row = row;
        this.col = col;

        this.direction = Direction.NORTH;

        this.alive = true;
    }

    public abstract void update(World world);

    // ----------------------------
    // Getters
    // ----------------------------

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public double getEnergy() {
        return energy;
    }

    public boolean isAlive() {
        return alive;
    }

    public Direction getDirection() {
        return direction;
    }

    public NeuralNetwork getBrain() {
        return brain;
    }

    // ----------------------------
    // Setters
    // ----------------------------

    public void setEnergy(double energy) {
        this.energy = energy;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    // ----------------------------
    // Common movement helper
    // ----------------------------

    protected void wrapPosition() {

        row = (row + World.ROWS) % World.ROWS;
        col = (col + World.COLS) % World.COLS;
    }
}