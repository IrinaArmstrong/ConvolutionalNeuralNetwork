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
        // fixme!

        for (int i = 0; i < this.layersNumber; i++)  {

            if (i == 0) this.layers[i] = new InputLayer(this.neuronsInLayers[i]);
            else {
                if (kernelsInLayers[i] != 0)  {
                    ArrayList<Kernel> kernelsInCurrLayer = new ArrayList<>();

                    for (int k = 0; k < kernelsInLayers[i]; k++)  {
                        Kernel kernel = new Kernel(kernelsParams[i]);
                        kernelsInCurrLayer.add(kernel);
                    }
                    // If previous layer is input layer (int serialNumber, int prevNeuronsNumber, ArrayList<Kernel> kernels, int step)
                    if (i == 1)  this.layers[i] = new ConvolutionalLayer(i, neuronsInLayers[i - 1], kernelsInCurrLayer, step);
                    // If previous layer is convolution layer (int serialNumber, int prevFMNumber, int prevFMSize, ArrayList<Kernel> kernels, int step)
                    else {
                        int prevFMSize = (int) (Math.sqrt(layers[i - 1].getNeuronsNumber()) / this.kernelsInLayers[i - 1]) ;
                        this.layers[i] = new ConvolutionalLayer(i, this.kernelsInLayers[i - 1], prevFMSize, kernelsInCurrLayer, step);
                    }
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

        // Get array of right answers
        double[] answers = new double[tasks.size()];
        System.out.println("Answers: ");
        for (int i = 0; i < tasks.size(); i++)  {
            answers[i] = tasks.get(i).getNumber();
            System.out.print(answers[i] + "; ");
        }

        // Go through all tasks from the set
        for (int taskNum = 0; taskNum < tasks.size(); taskNum++)  {

            // Create answer
            double[] answer = new double[neuronsInLayers[layersNumber - 1]];
            for (int i = 0; i < answer.length; i++)  {
                if (i == answers[taskNum]) answer[i] = 1;
                else answer[i] = 0.0;
            }

            // Tasks to linear structure
            int taskLenght = tasks.get(taskNum).getTask().length;
            double[] task = new double[taskLenght * taskLenght];
            int taskIterator = 0;
            for (int row = 0; row < taskLenght; row++) {
                for (int col = 0; col < taskLenght; col++) {
                    task[taskIterator] = tasks.get(taskNum).getTask()[row][col];
                    taskIterator++;
                }
            }

            printStateNetwork();

            // Send signals through all layers
            for (int i = 0; i < this.layersNumber; i++)  {
                if (i == 0) this.layers[i].getInputSignalToLayer(task);
                else {
                    transmitSignals(i - 1, i);
                }
            }

            // Send final signal from the last layer to the output
            this.results = this.getOutputSignal();

            printStateNetwork();

        }




        return  results;
    }

    // Show state of weights in network
    public void printStateNetwork()  {
        System.out.println("Network: ");
        /*layers[0].getNeurons()[0].printStateNeuron();*/
        System.out.println("Layers: ");
        for (int i = 0; i < layersNumber; i++) {
            layers[i].printStateLayer();
        }

        double[] outputs = this.getOutputSignal();
        System.out.println("Outputs: ");
        for (int i = 0; i < outputs.length; i++) {
            System.out.print(outputs[i] + "; ");
        }
        System.out.println();
    }

}
