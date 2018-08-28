package application.network;

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
        return new Neuron[0];
    }

    @Override
    public int getNeuronsNumber() {
        return 0;
    }

    @Override
    public void createNeurons() {

    }

    @Override
    public double[] giveOutputSignalToLayer() {
        return new double[0];
    }

    @Override
    public void getInputSignalToLayer(double[] outputSignals) {

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

    @Override
    public void printStateLayer() {

    }
}
