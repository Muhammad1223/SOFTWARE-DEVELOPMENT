public class Carnivore extends Agent {

    public Carnivore(int row, int col) {

        super(row, col);

        energy = 280;

        brain = new NeuralNetwork(6, 4);
    }

    @Override
    public void update(World world) {

        energy -= 2;

        neuralMove();

        eatHerbivore(world);

        reproduce(world);

        if (energy <= 0) {

            alive = false;
        }
    }

    private void neuralMove() {

        double[] inputs = {

            row / (double) World.ROWS,
            col / (double) World.COLS,
            energy / 400.0,

            Math.random(),
            Math.random(),
            Math.random()
        };

        int best;

        if (Math.random() < 0.35) {

            best =
                    (int)(Math.random() * 4);

        } else {

            best =
                    brain.getBestAction(inputs);
        }

        switch (best) {

            case 0:
                row--;
                direction = Direction.NORTH;
                break;

            case 1:
                row++;
                direction = Direction.SOUTH;
                break;

            case 2:
                col--;
                direction = Direction.WEST;
                break;

            case 3:
                col++;
                direction = Direction.EAST;
                break;
        }

        wrapPosition();
    }

    private void eatHerbivore(World world) {

        Herbivore target =
                world.findHerbivore(
                        row,
                        col);

        if (target != null &&
                target.isAlive()) {

            target.setAlive(false);

            energy += 80;

            brain.mutate();
        }
    }

    private void reproduce(World world) {

        if (energy >= 300) {

            Carnivore baby =
                    new Carnivore(
                            row,
                            col);

            baby.brain =
                    brain.copy();

            baby.brain.mutate();

            world.addCarnivore(baby);

            energy = 250;
        }
    }
}