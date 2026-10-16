public class Herbivore extends Agent {

    public Herbivore(int row, int col) {

        super(row, col);

        energy = 150;

        brain = new NeuralNetwork(6, 4);
    }

    @Override
    public void update(World world) {

        energy -= 2;

        neuralMove();

        eatPlant(world);

        reproduce(world);

        if (energy <= 0) {

            alive = false;
        }
    }

    private void neuralMove() {

        double[] inputs = {

            row / (double) World.ROWS,
            col / (double) World.COLS,
            energy / 300.0,

            Math.random(),
            Math.random(),
            Math.random()
        };

        int best =
                brain.getBestAction(inputs);

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

    private void eatPlant(World world) {

        Plant plant =
                world.findPlant(row, col);

        if (plant != null) {

            world.removePlant(plant);

            energy += 30;

            brain.mutate(0.03, 0.20);
        }
    }

    private void reproduce(World world) {

        if (energy >= 250) {

            Herbivore baby =
                    new Herbivore(row, col);

            baby.brain =
                    brain.copy();

            baby.brain.mutate(
                    0.10,
                    0.30);

            world.addHerbivore(baby);

            energy *= 0.25;
        }
    }
}