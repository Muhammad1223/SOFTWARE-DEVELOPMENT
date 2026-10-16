import javax.swing.JButton;
import javax.swing.SwingUtilities;

public class Game {

    private static boolean started = false;

    public static void main(String[] args)
            throws Exception {

        SimulationGUI gui =
                new SimulationGUI();

        JButton startButton =
                gui.getStartButton();

        startButton.addActionListener(e -> {

            started = true;

            startButton.setEnabled(false);
        });

        //--------------------------------------------------
        // WAIT FOR START BUTTON
        //--------------------------------------------------

        while (!started) {

            Thread.sleep(100);
        }

        //--------------------------------------------------
        // READ USER INPUT
        //--------------------------------------------------

        int plants =
                gui.getPlantInput();

        int herbivores =
                gui.getHerbivoreInput();

        int carnivores =
                gui.getCarnivoreInput();

        //--------------------------------------------------
        // CREATE WORLD
        //--------------------------------------------------

        World world =
                new World(
                        plants,
                        herbivores,
                        carnivores);

        int turn = 0;

        //--------------------------------------------------
        // SIMULATION LOOP
        //--------------------------------------------------

        while (!world.isFinished()) {

            world.update();

            //--------------------------------------------------
            // UPDATE POPULATION
            //--------------------------------------------------

            final int currentTurn = turn;

            SwingUtilities.invokeLater(() -> {

                gui.updateStats(
                        currentTurn,
                        world.getPlantCount(),
                        world.getHerbivoreCount(),
                        world.getCarnivoreCount());

                gui.updateGrid(
                        world.getGridAsString());

                gui.updateEnergy(
                        world.getAverageHerbivoreEnergy(),
                        world.getAverageCarnivoreEnergy());

                gui.updateLifetimeEnergy(
                        world.getLifetimeRabbitAverage(),
                        world.getLifetimeWolfAverage());
            });

            turn++;

            Thread.sleep(200);
        }

        //--------------------------------------------------
        // FINAL GUI UPDATE
        //--------------------------------------------------

        final int finalTurn = turn;

        SwingUtilities.invokeLater(() -> {

            gui.updateStats(
                    finalTurn,
                    world.getPlantCount(),
                    world.getHerbivoreCount(),
                    world.getCarnivoreCount());

            gui.updateEnergy(
                    world.getAverageHerbivoreEnergy(),
                    world.getAverageCarnivoreEnergy());

            gui.updateLifetimeEnergy(
                    world.getLifetimeRabbitAverage(),
                    world.getLifetimeWolfAverage());

            gui.updateGrid(
                    world.getGridAsString());
        });

        //--------------------------------------------------
        // FINISH MESSAGE
        //--------------------------------------------------

        String message;

        if (world.getHerbivoreCount() == 0) {

            message =
                    "🐰 All Rabbits Died\n" +
                    "🐺 Wolves Survived";

        } else if (world.getCarnivoreCount() == 0) {

            message =
                    "🐺 All Wolves Died\n" +
                    "🐰 Rabbits Survived";

        } else {

            message =
                    "Simulation Finished";
        }

        //--------------------------------------------------
        // SHOW FINISHED MESSAGE
        //--------------------------------------------------

        SwingUtilities.invokeLater(() -> {

            gui.showFinishedMessage(message);
        });

        System.out.println(message);

        //--------------------------------------------------
        // KEEP GUI OPEN
        //--------------------------------------------------

        while (true) {

            Thread.sleep(1000);
        }
    }
}