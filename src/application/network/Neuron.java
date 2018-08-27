package application.network;

 /*Class that realizes single neuron structure.*/

import java.util.Random;

public class Neuron {

    // Number of dendrits
    private int dendritNumber;

    // Summ of weights * outputs from previous layer = z
    private double weightsOutputsSumm;

    // Array of dendrits weights
    public double[] dendritWeights;

    // An offset (bias) of neuron's output signal
    private double bias;

    // Output signal of neuron
    public double output;

    // Error of neuron
    private double error;

    // Rate of learning or a step of grad descending
    private double learningRate;

    // Constructor, that init weights, bias as random numbers (at first time using neuron)
    public Neuron( int dendritNumber) {
        this.dendritNumber = dendritNumber;
        Random random = new Random();
        this.bias = random.nextDouble();
        this.dendritWeights = new double[this.dendritNumber];
        for (int i = 0; i < dendritNumber; i++)  {
            this.dendritWeights[i] = random.nextDouble();
        }
        this.error = 0.0;
        this.output = sigmoid();
    }

    // Activation (sigmoid) function
    private double sigmoid()  {
        double sigmoida = 1 / (1 + Math.exp(-this.weightsOutputsSumm));
        return sigmoida;
    }

    // Derivate of sigmoid function
    private double derivateSigmoid() {
        return sigmoid() * (1 - sigmoid());
    }

}
