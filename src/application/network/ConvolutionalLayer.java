package application.network;

import java.util.ArrayList;

/* Convolutional layer of the network. */

public class ConvolutionalLayer implements Layer {

    // Number of this layer
    private int serialNumber;

    // Number of feature maps in this layer
    public int featureMapsNumber;

    // Size of it's feature map
    public int featureMapSize;

    // Number of feature maps on previous layer
    private int prevFMNumber;

    // Size of previous feature map
    private int prevFMSize;

    // Number of neurons in this layer
    private int neuronsNumber;

    // Number of neurons in previous layer
    private int prevNeuronsNumber;

    // Array of neurons
    private Neuron[] neurons;

    // Kernels of this layer
    public ArrayList<Kernel> kernels;

    // Feature maps of this layer
    private FeatureMap[] featureMaps;

    // Step of convolution
    private int step;

    // Constuctor #1, if previous layer is input layer
    public ConvolutionalLayer(int serialNumber, int prevNeuronsNumber, ArrayList<Kernel> kernels, int step) {
        this.serialNumber = serialNumber;
        this.featureMapsNumber = kernels.size();
        this.featureMapSize = (int) (Math.sqrt(prevNeuronsNumber) - kernels.get(0).getSize()) / step + 1;
        this.prevFMNumber = 0;
        this.prevFMSize = 0;
        this.neuronsNumber = this.featureMapSize * this.featureMapSize * this.featureMapsNumber;
        this.prevNeuronsNumber = prevNeuronsNumber;
        this.kernels = kernels;
        this.step = step;
        this.createNeurons();
    }

    // Constuctor #2, if previous layer is convolution layer
    public ConvolutionalLayer(int serialNumber, int prevFMNumber, int prevFMSize, ArrayList<Kernel> kernels, int step) {
        this.serialNumber = serialNumber;
        this.prevNeuronsNumber = prevFMNumber * prevFMSize * prevFMSize;
        this.featureMapsNumber = kernels.size();
        this.featureMapSize = (int) (Math.sqrt(prevNeuronsNumber) - kernels.get(0).getSize()) / step + 1;
        this.prevFMNumber = 0;
        this.prevFMSize = 0;
        this.neuronsNumber = this.featureMapSize * this.featureMapSize * this.featureMapsNumber;
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

    // Creating feature maps -> so then neurons of this layer
    @Override
    public void createNeurons() {
        // todo: create this method!
        this.neurons = new Neuron[this.neuronsNumber];
        for (int f = 0; f < this.featureMapsNumber; f++)  {
            if (this.serialNumber == 1)  {
                // (int serialNumber, int size, int prevNeuronsNumber, Kernel kernel)
                this.featureMaps[f] = new FeatureMap(f, this.featureMapSize, this.prevNeuronsNumber, this.kernels.get(f));
            }
            else {
                //  (int serialNumber, int size, int prevFMNumber, int prevFMSize, Kernel kernel)
                this.featureMaps[f] = new FeatureMap(f, this.featureMapSize, this.prevFMNumber, this.prevFMSize, this.kernels.get(f));
            }

        }

/*
        int offset = this.kernels.get(0).getKernel().length * this.kernels.get(0).getKernel().length;

        for (int k = 0; k < this.kernels.size(); k++)  {
            Kernel kernel = this.kernels.get(k);
            int neuronCurrNumber = 0;
            System.out.println("this.neuronsNumber / this.kernels.size() = " + this.neuronsNumber / this.kernels.size());
            for (int i = neuronCurrNumber; i < (this.neuronsNumber / this.kernels.size()); i++)  {
                this.neurons[i] = new Neuron(prevNeuronsNumber, kernel, step, i);
                //System.out.println("i = " + i + "  k = " + k);
            }
        }*/

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

        /*if (prevImagesNumber != 1)  {
            int imgSize = prevNeuronsNumber / prevImagesNumber;
            for (int i = 0; i < this.neuronsNumber; i++)  {
                this.neurons[i].getConvolutionSignals(outputSignals, imgSize);
            }
        }
        else {}*/
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
        /*int nCounter=0;
        for (Neuron neyron : neurons) {
            System.out.println("");
            System.out.println("Neuron #" + nCounter);
            neyron.printStateNeuron();
            nCounter++;
        }*/
    }
}
