package application.network;

 /*Class that realizes single neuron structure.*/

import java.util.Arrays;
import java.util.Random;

public class Neuron {

    // Number of dendrits
    private int dendritNumber;

    // Number of this neuron in the layer
    private int neuronNumber;

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

    // Constructor, that init weights, bias as random numbers, for the full connected layer's neurons (at first time using neuron)
    // As parameter gets а number of this neuron in the layer
    public Neuron( int dendritNumber, int neuronNumber) {
        this.dendritNumber = dendritNumber;
        this.neuronNumber = neuronNumber;
        Random random = new Random();
        this.bias = random.nextDouble();
        this.dendritWeights = new double[this.dendritNumber];
        for (int i = 0; i < dendritNumber; i++)  {
            this.dendritWeights[i] = random.nextDouble();
        }
        this.error = 0.0;
        this.output = hyperbolicTangent();
    }

    // Constructor, that init weights, bias as 1, for the input layer's neurons (at first time using neuron)
    // As parameter gets а number of this neuron in the layer
    public Neuron(int neuronNumber) {
        this.dendritNumber = 1;
        this.neuronNumber = neuronNumber;
        this.bias = 0;
        this.dendritWeights = new double[this.dendritNumber];
        this.dendritWeights[0] = 1;
        this.error = 0.0;
        this.output = hyperbolicTangent();
    }

    // Constructor, that init weights, bias as numbers got from the kernel (at first time using neuron)
    public Neuron(int dendritNumber, Kernel kernel, int step, int neuronNumber) {
        this.dendritNumber = dendritNumber;
        this.neuronNumber = neuronNumber;
        Random random = new Random();
        this.bias = random.nextDouble();
        this.dendritWeights = new double[this.dendritNumber];
        this.initWeights(kernel.getKernel(), step);
        this.error = 0.0;
        this.output = hyperbolicTangent();
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

    // Activation (hyperbolic tangent) function
    private double hyperbolicTangent()  {
        double tanh = (Math.exp(2 * this.weightsOutputsSumm) - 1) / (Math.exp(2 * this.weightsOutputsSumm + 1));
        return tanh;
    }

    // Derivate of hyperbolic tangent function
    private double derivateHyperbolicTangent() {
        return 1 - hyperbolicTangent() * hyperbolicTangent();
    }

    // Initialize dendrits weights of neuron from the kernel coefficients
    public void initWeights(double[][] kernel, int step)  {

        // Init all weights with nulls
        for (int n = 0; n < dendritNumber; n++)  {
            this.dendritWeights[n] = 0;
        }

        // Init weights with kernel coefficients
        int counter = 0;
        //while (counter < this.dendritNumber)  {}
            // fixme!
            for (int i = 0; i < kernel.length; i++)  {
                for (int j = 0; j < kernel.length; j++)  {
                    //System.out.println("counter + neuronNumber = " + counter + " + " + this.neuronNumber + " = "+ (counter + neuronNumber));
                    this.dendritWeights[counter + this.neuronNumber] = kernel[i][j];
                    counter++;
                }
                counter += step;
            }



    }

    // Give output signal to next layer's neurons
    public double getOutputSignal() {
        return output;
    }

    // Count and set output signal
    public void setOutput() {
        this.output = hyperbolicTangent();
    }

    // Get input signal to the neuron of FIRST LAYER
    public void getInputSygnal(double inputSignal)  {
        this.weightsOutputsSumm = inputSignal;
        this.setOutput();
    }

    // Get signals on dendrits from previous layer
    public void getSignals(double[] prevOutputs)  {
        for (int i = 0; i < dendritNumber; i++)  {
            this.weightsOutputsSumm += prevOutputs[i] * this.dendritWeights[i];
        }
        this.weightsOutputsSumm += this.bias;
        this.setOutput();
    }






    // Print state of neuron
    public void printStateNeuron() {
        System.out.println("Dendrit number = "+ dendritNumber + "\n");
        int cnt = 0;
        double prevLayerNeuronsNumber = Math.sqrt(dendritNumber);
        for (int i = 0; i < prevLayerNeuronsNumber; i++)  {
            for (int j = 0; j < prevLayerNeuronsNumber; j++)  {
                System.out.print(dendritWeights[cnt]);
                cnt++;
            }
            System.out.println();
        }
        System.out.println("Bias = " + bias);
        System.out.println("Weights summ = " + weightsOutputsSumm);
        System.out.println("Error = " + error);
        System.out.println("Output = " + output);
        System.out.println("Tanh = " + this.hyperbolicTangent());
        System.out.println("Deriviate tanh = " + this.derivateHyperbolicTangent());
    }

}
