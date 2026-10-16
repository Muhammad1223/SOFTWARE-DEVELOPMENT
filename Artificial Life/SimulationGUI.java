import javax.swing.*;
import java.awt.*;

public class SimulationGUI extends JFrame {

    // Input controls
    private JTextField plantField;
    private JTextField herbivoreField;
    private JTextField carnivoreField;
    private JButton startButton;

    // Lifetime energy labels
    private JLabel rabbitLifetimeLabel;
    private JLabel wolfLifetimeLabel;

    // Population statistics
    private JLabel turnLabel;
    private JLabel plantCountLabel;
    private JLabel herbivoreCountLabel;
    private JLabel carnivoreCountLabel;

    // Energy statistics
    private JLabel rabbitEnergyLabel;
    private JLabel wolfEnergyLabel;

    // Grid display
    private JTextArea worldArea;

    public SimulationGUI() {

        setTitle("Artificial Life Simulation");

        setSize(1400, 900);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout(10, 10));

        //--------------------------------------------------
        // TOP CONTROL PANEL
        //--------------------------------------------------

        JPanel topPanel =
                new JPanel(new GridLayout(2, 4, 10, 10));

        topPanel.setBorder(
                BorderFactory.createTitledBorder(
                        "Simulation Controls"));

        topPanel.add(new JLabel("🌱 Plants"));

        plantField = new JTextField("50");
        topPanel.add(plantField);

        topPanel.add(new JLabel("🐰 Rabbits"));

        herbivoreField = new JTextField("15");
        topPanel.add(herbivoreField);

        topPanel.add(new JLabel("🐺 Wolves"));

        carnivoreField = new JTextField("5");
        topPanel.add(carnivoreField);

        startButton = new JButton("Start Simulation");
        topPanel.add(startButton);

        add(topPanel, BorderLayout.NORTH);

        //--------------------------------------------------
        // CENTER GRID PANEL
        //--------------------------------------------------

        worldArea = new JTextArea();

        worldArea.setEditable(false);

        worldArea.setFont(
                new Font(
                        "Segoe UI Emoji",
                        Font.PLAIN,
                        30));

        worldArea.setMargin(
                new Insets(20, 20, 20, 20));

        JScrollPane scrollPane =
                new JScrollPane(worldArea);

        scrollPane.setBorder(
                BorderFactory.createTitledBorder(
                        "Artificial Life World"));

        add(scrollPane, BorderLayout.CENTER);

        //--------------------------------------------------
        // BOTTOM DASHBOARD
        //--------------------------------------------------

        JPanel bottomPanel =
                new JPanel(
                        new GridLayout(1, 2, 10, 10));

        //--------------------------------------------------
        // POPULATION PANEL
        //--------------------------------------------------

        JPanel populationPanel =
                new JPanel(
                        new GridLayout(4, 1));

        populationPanel.setBorder(
                BorderFactory.createTitledBorder(
                        "Population Statistics"));

        turnLabel =
                new JLabel("Turn: 0");

        plantCountLabel =
                new JLabel("🌱 Plants: 0");

        herbivoreCountLabel =
                new JLabel("🐰 Rabbits: 0");

        carnivoreCountLabel =
                new JLabel("🐺 Wolves: 0");

        populationPanel.add(turnLabel);
        populationPanel.add(plantCountLabel);
        populationPanel.add(herbivoreCountLabel);
        populationPanel.add(carnivoreCountLabel);

        //--------------------------------------------------
        // ENERGY PANEL
        //--------------------------------------------------

        JPanel energyPanel =
                new JPanel(
                        new GridLayout(4, 1));

        energyPanel.setBorder(
                BorderFactory.createTitledBorder(
                        "Energy Statistics"));

        // Current average energy
        rabbitEnergyLabel =
                new JLabel("🐰 Avg Energy: 0.0");

        wolfEnergyLabel =
                new JLabel("🐺 Avg Energy: 0.0");

        // Lifetime average energy
        rabbitLifetimeLabel =
                new JLabel("🐰 Lifetime Avg: 0.0");

        wolfLifetimeLabel =
                new JLabel("🐺 Lifetime Avg: 0.0");

        energyPanel.add(rabbitEnergyLabel);
        energyPanel.add(wolfEnergyLabel);
        energyPanel.add(rabbitLifetimeLabel);
        energyPanel.add(wolfLifetimeLabel);

        //--------------------------------------------------
        // ADD DASHBOARD PANELS
        //--------------------------------------------------

        bottomPanel.add(populationPanel);
        bottomPanel.add(energyPanel);

        add(bottomPanel, BorderLayout.SOUTH);

        //--------------------------------------------------
        // SHOW WINDOW
        //--------------------------------------------------

        setLocationRelativeTo(null);

        setVisible(true);
    }

    //--------------------------------------------------
    // INPUT VALUES
    //--------------------------------------------------

    public int getPlantInput() {

        return Integer.parseInt(
                plantField.getText().trim());
    }

    public int getHerbivoreInput() {

        return Integer.parseInt(
                herbivoreField.getText().trim());
    }

    public int getCarnivoreInput() {

        return Integer.parseInt(
                carnivoreField.getText().trim());
    }

    //--------------------------------------------------
    // START BUTTON
    //--------------------------------------------------

    public JButton getStartButton() {

        return startButton;
    }

    //--------------------------------------------------
    // UPDATE POPULATION STATISTICS
    //--------------------------------------------------

    public void updateStats(
            int turn,
            int plants,
            int herbivores,
            int carnivores) {

        turnLabel.setText(
                "Turn: " + turn);

        plantCountLabel.setText(
                "🌱 Plants: " + plants);

        herbivoreCountLabel.setText(
                "🐰 Rabbits: " + herbivores);

        carnivoreCountLabel.setText(
                "🐺 Wolves: " + carnivores);
    }

    //--------------------------------------------------
    // UPDATE CURRENT ENERGY
    //--------------------------------------------------

    public void updateEnergy(
            double rabbitEnergy,
            double wolfEnergy) {

        rabbitEnergyLabel.setText(
                "🐰 Avg Energy: "
                        + String.format(
                                "%.1f",
                                rabbitEnergy));

        wolfEnergyLabel.setText(
                "🐺 Avg Energy: "
                        + String.format(
                                "%.1f",
                                wolfEnergy));
    }

    //--------------------------------------------------
    // UPDATE LIFETIME ENERGY
    //--------------------------------------------------

    public void updateLifetimeEnergy(
            double rabbit,
            double wolf) {

        rabbitLifetimeLabel.setText(
                "🐰 Lifetime Avg: "
                        + String.format(
                                "%.1f",
                                rabbit));

        wolfLifetimeLabel.setText(
                "🐺 Lifetime Avg: "
                        + String.format(
                                "%.1f",
                                wolf));
    }

    //--------------------------------------------------
    // UPDATE GRID
    //--------------------------------------------------

    public void updateGrid(String gridText) {

        worldArea.setText(gridText);
    }

    //--------------------------------------------------
    // FINISHED MESSAGE
    //--------------------------------------------------

    public void showFinishedMessage(
            String message) {

        JOptionPane.showMessageDialog(
                this,
                message,
                "Simulation Finished",
                JOptionPane.INFORMATION_MESSAGE);
    }
}