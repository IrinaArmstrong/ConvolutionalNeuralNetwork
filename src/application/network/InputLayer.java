package application.network;

/* Input layer of the network. */
// todo

public class InputLayer implements Layer {
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
