import java.util.Random;

public class NeuralNetwork {

    private double[][] weights;
    private double[] biases;

    private static final Random random =
            new Random();

    public NeuralNetwork(
            int inputs,
            int outputs) {

        weights =
                new double[outputs][inputs];

        biases =
                new double[outputs];

        for (int i = 0; i < outputs; i++) {

            biases[i] =
                    random.nextDouble() * 2 - 1;

            for (int j = 0; j < inputs; j++) {

                weights[i][j] =
                        random.nextDouble() * 2 - 1;
            }
        }
    }

    //--------------------------------
    // Forward Pass
    //--------------------------------

    public double[] forward(
            double[] inputs) {

        double[] outputs =
                new double[weights.length];

        for (int i = 0; i < weights.length; i++) {

            outputs[i] = biases[i];

            for (int j = 0;
                 j < inputs.length;
                 j++) {

                outputs[i] +=
                        weights[i][j]
                        * inputs[j];
            }

            outputs[i] =
                    Math.tanh(outputs[i]);
        }

        return outputs;
    }

    //--------------------------------
    // Best Action
    //--------------------------------

    public int getBestAction(
            double[] inputs) {

        double[] outputs =
                forward(inputs);

        int best = 0;

        for (int i = 1;
             i < outputs.length;
             i++) {

            if (outputs[i]
                    > outputs[best]) {

                best = i;
            }
        }

        return best;
    }

    //--------------------------------
    // Deep Copy
    //--------------------------------

    public NeuralNetwork copy() {

        NeuralNetwork clone =
                new NeuralNetwork(
                        weights[0].length,
                        weights.length);

        for (int i = 0; i < weights.length; i++) {

            clone.biases[i] =
                    biases[i];

            for (int j = 0;
                 j < weights[i].length;
                 j++) {

                clone.weights[i][j] =
                        weights[i][j];
            }
        }

        return clone;
    }

    //--------------------------------
    // Mutation
    //--------------------------------

    public void mutate() {

        mutate(
                0.05,
                0.20);
    }

    public void mutate(
            double mutationRate,
            double mutationStrength) {

        for (int i = 0; i < weights.length; i++) {

            for (int j = 0;
                 j < weights[i].length;
                 j++) {

                if (random.nextDouble()
                        < mutationRate) {

                    weights[i][j] +=

                            (random.nextDouble()
                                    * 2 - 1)

                            * mutationStrength;
                }
            }

            if (random.nextDouble()
                    < mutationRate) {

                biases[i] +=

                        (random.nextDouble()
                                * 2 - 1)

                        * mutationStrength;
            }
        }
    }

    //--------------------------------
    // Getters
    //--------------------------------

    public int getInputCount() {

        return weights[0].length;
    }

    public int getOutputCount() {

        return weights.length;
    }

    //--------------------------------
    // Debug
    //--------------------------------

    @Override
    public String toString() {

        return "NeuralNetwork["

                + getInputCount()

                + " Inputs, "

                + getOutputCount()

                + " Outputs]";
    }
}