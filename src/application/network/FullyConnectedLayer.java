package application.network;

/* Fully connected layer of the network. */

public class FullyConnectedLayer implements Layer {

    // Number of this layer
    private int serialNumber;

    // Number of neurons in this layer
    private int neuronsNumber;

    // Number of neurons in previous layer
    private int prevNeuronsNumber;

    // Array of neurons
    private Neuron[] neurons;

    // Constructor of this layer
    public FullyConnectedLayer(int serialNumber, int neuronsNumber, int prevNeuronsNumber) {
        this.serialNumber = serialNumber;
        this.neuronsNumber = neuronsNumber;
        this.prevNeuronsNumber = prevNeuronsNumber;
        this.createNeurons();
    }

    @Override
    public Neuron[] getNeurons() {
        return neurons;
    }

    @Override
    public int getNeuronsNumber() {
        return neuronsNumber;
    }

    // Creating neurons of this layer
    @Override
    public void createNeurons() {
        this.neurons = new Neuron[this.neuronsNumber];
        for (int i = 0; i < this.neuronsNumber; i++)  {
            this.neurons[i] = new Neuron(this.prevNeuronsNumber, i);
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
        System.out.println("Fully connected layer:");
        System.out.println("Neurons number = " + neuronsNumber);
        int cnt=0;
        for (Neuron neyron : neurons) {
            System.out.println("");
            System.out.println("Neuron #" + cnt);
            neyron.printStateNeuron();
            cnt++;
        }
    }
}
