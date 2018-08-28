package application.network;

/* Input layer of the network. */

public class InputLayer implements Layer {

    // Number of neurons in this layer
    private int neuronsNumber;

    // Array of neurons
    private Neuron[] neurons;

    // Constructor of this layer
    public InputLayer(int neuronsNumber) {
        this.neuronsNumber = neuronsNumber;
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

    @Override
    public void createNeurons() {
        this.neurons = new Neuron[this.neuronsNumber];
        for (int i = 0; i < this.neuronsNumber; i++)  {
            this.neurons[i] = new Neuron();
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

    // Just get input signals
    @Override
    public void getInputSignalToLayer(double[] outputSignals) {
        for (int i = 0; i < this.neuronsNumber; i++)  {
            this.neurons[i].getInputSygnal(outputSignals[i]);
        }
    }

    // Not used
    @Override
    public void getErrors(double[] errors) {

    }

    // Not used
    @Override
    public double[] giveErrorsToLayer() {
        return new double[0];
    }

    // Not used
    @Override
    public void correctWeightsOfLayer(double learningRate) {

    }

    @Override
    public void printStateLayer() {
        System.out.println("Input layer:");
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
