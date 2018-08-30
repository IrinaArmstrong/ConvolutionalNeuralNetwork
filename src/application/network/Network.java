package application.network;

// Class that realizes structure of whole neuron network

import application.utils.TaskAnswerPair;

import java.util.ArrayList;

public class Network {

    // Number of this network layers
    private int layersNumber;

    // Array of layers
    private Layer[] layers;

    // Array that holds number of neurons in each layer
    private int[] neuronsInLayers;

    // Array that holds number of kernels in each layer
    private int[] kernelsInLayers;

    // Array that holds parameters of kernels for each layer
    private int[] kernelsParams;

    // Array of result signals
    public double[] results;

    // Step of convolution
    private int step;

    // Constructor of this network
    public Network(int layersNumber, int[] neuronsInLayers, int[] kernelsInLayers, int[] kernelsParams, int step) {
        if (layersNumber != neuronsInLayers.length && layersNumber != kernelsInLayers.length && layersNumber != kernelsParams.length) {
            System.err.println("Not right parameters of the method creating network!");
        }
        this.layersNumber = layersNumber;
        this.kernelsInLayers = kernelsInLayers;
        this.kernelsParams = kernelsParams;
        this.step = step;
        this.neuronsInLayers = new int[this.layersNumber];
        for (int i = 0; i < this.layersNumber; i++)  {
            this.neuronsInLayers[i] = neuronsInLayers[i];
        }
        createLayers();
    }

    // Create array of layers
    private void createLayers()  {
        this.layers = new Layer[this.layersNumber];
        for (int i = 0; i < this.layersNumber; i++)  {
            if (i == 0) this.layers[i] = new InputLayer(this.neuronsInLayers[i]);
            else {
                if (kernelsInLayers[i] != 0)  {
                    ArrayList<Kernel> kernelsInCurrLayer = new ArrayList<>();
                    for (int k = 0; k < kernelsInLayers[i]; k++)  {
                        Kernel kernel = new Kernel(kernelsParams[i]);
                        kernelsInCurrLayer.add(kernel);
                    }
                    this.layers[i] = new ConvolutionalLayer(i, neuronsInLayers[i], this.neuronsInLayers[i - 1], kernelsInCurrLayer, step);
                }
                else {
                    this.layers[i] = new FullyConnectedLayer(i, neuronsInLayers[i], this.neuronsInLayers[i - 1]);
                }
            }
        }
    }


    // Get input signal to the sensors and send it to the first layer (sensors)
    private void getInputSignal(double[] inputSignal)  {
        this.layers[0].getInputSignalToLayer(inputSignal);
    }

    // Get output signal from one layer and send it to the next layer
    private void transmitSignals(int firstLayer, int secondLayer)  {
        this.layers[secondLayer].getInputSignalToLayer(this.layers[firstLayer].giveOutputSignalToLayer());
    }

    // Get final output signal (result)
    private double[] getOutputSignal()  {
        return this.layers[this.layersNumber - 1].giveOutputSignalToLayer();
    }

    // Learning method
    public double[] learn(ArrayList<TaskAnswerPair> tasks, double learningRate, int iterations)  {
        return  results;
    }

}
