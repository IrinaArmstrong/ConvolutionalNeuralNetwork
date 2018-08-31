package application.network;

import java.util.ArrayList;

/* Convolutional layer of the network. */

public class ConvolutionalLayer implements Layer {

    // Number of this layer
    private int serialNumber;

    // Number of neurons in this layer
    private int neuronsNumber;

    // Number of neurons in previous layer
    private int prevNeuronsNumber;

    // Array of neurons
    private Neuron[] neurons;

    // Kernels of this layer
    public ArrayList<Kernel> kernels;

    // Step of convolution
    private int step;

    // Constructor of this layer
    public ConvolutionalLayer(int serialNumber, int neuronsNumber, int prevNeuronsNumber, ArrayList<Kernel> kernels, int step) {
        this.serialNumber = serialNumber;
        this.neuronsNumber = neuronsNumber;
        this.prevNeuronsNumber = prevNeuronsNumber;
        this.kernels = kernels;
        this.step = step;
        this.createNeurons();
    }

    @Override
    public Neuron[] getNeurons() {
        return neurons;
    }

    @Override
    public int getNeuronsNumber() {
        return this.neuronsNumber;
    }

    // Creating neurons of this layer
    // fixme!
    @Override
    public void createNeurons() {

        this.neurons = new Neuron[this.neuronsNumber];

        int offset = this.kernels.get(0).getKernel().length * this.kernels.get(0).getKernel().length;

        /*for (int k = 1; k < this.kernels.size() + 1; k++)  {
            Kernel kernel = this.kernels.get(k - 1);
            for (int i = offset * (k - 1) + 1; i < offset * k + 1; i++)  {
                this.neurons[i] = new Neuron(prevNeuronsNumber, kernel, step, i);
                System.out.println("i = " + i);
            }
        }*/

        int neuronCounter = 0;
        for (int k = 0; k < this.kernels.size(); k++)  {
            Kernel kernel = this.kernels.get(k);
            for (int i = neuronCounter; i < this.neuronsNumber; i++)  {
                this.neurons[i] = new Neuron(prevNeuronsNumber, kernel, step, i);
                System.out.println("i = " + i + "  k = " + k);
            }
        }

    }

    // Transmit output signal from this layer's neurons to next layer's neurons
    @Override
    public double[] giveOutputSignalToLayer() {
        double[] outputSignals = new double[this.neuronsNumber];
        for (int i = 0; i < this.neuronsNumber; i++)  {
            outputSignals[i] = this.neurons[i].getOutputSignal();
        }
        return outputSignals;
    }

    // Get output signal from previous layer's neurons to this layer's neurons
    @Override
    public void getInputSignalToLayer(double[] outputSignals) {
        for (int i = 0; i < this.neuronsNumber; i++)  {
            this.neurons[i].getSignals(outputSignals);
        }
    }

    @Override
    public void getErrors(double[] errors) {

    }

    @Override
    public double[] giveErrorsToLayer() {
        return new double[0];
    }

    @Override
    public void correctWeightsOfLayer(double learningRate) {

    }

    // Print state of layer
    @Override
    public void printStateLayer() {
        System.out.println("Convolutional layer:");
        System.out.println("Neurons number = " + neuronsNumber);
        System.out.println("Kernels number = " + kernels.size());
        int kCounter=0;
        for (Kernel kernel : kernels) {
            System.out.println("");
            System.out.println("Kernel #" + kCounter);
            kernel.printStateKernel();
            kCounter++;
        }
        int nCounter=0;
        for (Neuron neyron : neurons) {
            System.out.println("");
            System.out.println("Neuron #" + nCounter);
            neyron.printStateNeuron();
            nCounter++;
        }
    }
}
