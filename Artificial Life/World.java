import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class World {

    public static final int ROWS = 25;
    public static final int COLS = 35;

    private List<Plant> plants = new ArrayList<>();
    private List<Herbivore> herbivores = new ArrayList<>();
    private List<Carnivore> carnivores = new ArrayList<>();

    private List<Herbivore> newbornHerbivores =
            new ArrayList<>();

    private List<Carnivore> newbornCarnivores =
            new ArrayList<>();

    private Random random = new Random();

    private double totalRabbitEnergy = 0;
    private long rabbitSamples = 0;

    private double totalWolfEnergy = 0;
    private long wolfSamples = 0;

    private int rabbitBirths = 0;
    private int wolfBirths = 0;

    private int rabbitDeaths = 0;
    private int wolfDeaths = 0;

    public World(
            int plantCount,
            int herbivoreCount,
            int carnivoreCount) {

        for (int i = 0; i < plantCount; i++) {

            plants.add(
                    new Plant(
                            random.nextInt(ROWS),
                            random.nextInt(COLS)));
        }

        for (int i = 0; i < herbivoreCount; i++) {

            herbivores.add(
                    new Herbivore(
                            random.nextInt(ROWS),
                            random.nextInt(COLS)));
        }

        for (int i = 0; i < carnivoreCount; i++) {

            carnivores.add(
                    new Carnivore(
                            random.nextInt(ROWS),
                            random.nextInt(COLS)));
        }
    }

    public void update() {

        for (Herbivore h : herbivores) {

            totalRabbitEnergy += h.getEnergy();
            rabbitSamples++;

            h.update(this);
        }

        for (Carnivore c : carnivores) {

            totalWolfEnergy += c.getEnergy();
            wolfSamples++;

            c.update(this);
        }

        int rabbitsBefore = herbivores.size();
        int wolvesBefore = carnivores.size();

        herbivores.removeIf(h -> !h.isAlive());
        carnivores.removeIf(c -> !c.isAlive());

        rabbitDeaths +=
                rabbitsBefore - herbivores.size();

        wolfDeaths +=
                wolvesBefore - carnivores.size();

        herbivores.addAll(newbornHerbivores);
        carnivores.addAll(newbornCarnivores);

        newbornHerbivores.clear();
        newbornCarnivores.clear();

        growPlants();
    }

    private void growPlants() {

        if (plants.size() < 250 &&
                random.nextDouble() < 0.25) {

            plants.add(
                    new Plant(
                            random.nextInt(ROWS),
                            random.nextInt(COLS)));
        }
    }

    public Plant findPlant(
            int row,
            int col) {

        for (Plant p : plants) {

            if (p.getRow() == row &&
                    p.getCol() == col) {

                return p;
            }
        }

        return null;
    }

    public Herbivore findHerbivore(
            int row,
            int col) {

        for (Herbivore h : herbivores) {

            if (h.getRow() == row &&
                    h.getCol() == col &&
                    h.isAlive()) {

                return h;
            }
        }

        return null;
    }

    public void removePlant(Plant p) {

        plants.remove(p);
    }

    public void addHerbivore(
            Herbivore h) {

        newbornHerbivores.add(h);

        rabbitBirths++;
    }

    public void addCarnivore(
            Carnivore c) {

        newbornCarnivores.add(c);

        wolfBirths++;
    }

    public boolean isFinished() {

        return herbivores.isEmpty()
                || carnivores.isEmpty();
    }

    public int getPlantCount() {

        return plants.size();
    }

    public int getHerbivoreCount() {

        return herbivores.size();
    }

    public int getCarnivoreCount() {

        return carnivores.size();
    }

    public double getAverageHerbivoreEnergy() {

        if (herbivores.isEmpty()) {
            return 0;
        }

        double total = 0;

        for (Herbivore h : herbivores) {

            total += h.getEnergy();
        }

        return total / herbivores.size();
    }

    public double getAverageCarnivoreEnergy() {

        if (carnivores.isEmpty()) {
            return 0;
        }

        double total = 0;

        for (Carnivore c : carnivores) {

            total += c.getEnergy();
        }

        return total / carnivores.size();
    }

    public double getLifetimeRabbitAverage() {

        if (rabbitSamples == 0) {
            return 0;
        }

        return totalRabbitEnergy /
                rabbitSamples;
    }

    public double getLifetimeWolfAverage() {

        if (wolfSamples == 0) {
            return 0;
        }

        return totalWolfEnergy /
                wolfSamples;
    }

    public int getRabbitBirths() {

        return rabbitBirths;
    }

    public int getWolfBirths() {

        return wolfBirths;
    }

    public int getRabbitDeaths() {

        return rabbitDeaths;
    }

    public int getWolfDeaths() {

        return wolfDeaths;
    }

    public String getGridAsString() {

        String[][] grid =
                new String[ROWS][COLS];

        for (int r = 0; r < ROWS; r++) {

            for (int c = 0; c < COLS; c++) {

                grid[r][c] = "⬜";
            }
        }

        for (Plant p : plants) {

            grid[p.getRow()][p.getCol()] = "🌱";
        }

        for (Herbivore h : herbivores) {

            grid[h.getRow()][h.getCol()] = "🐰";
        }

        for (Carnivore c : carnivores) {

            grid[c.getRow()][c.getCol()] = "🐺";
        }

        StringBuilder sb =
                new StringBuilder();

        for (int r = 0; r < ROWS; r++) {

            for (int c = 0; c < COLS; c++) {

                sb.append(grid[r][c]);
            }

            sb.append("\n");
        }

        return sb.toString();
    }
}