package application.network;

/* Interface for Layer instance */

public interface Layer {

    // Get array of neurons
    Neuron[] getNeurons();

    // Get number of neurons in this layer
    int getNeuronsNumber();

    // Create array of neurons
    void createNeurons();

    // Transmit output signal from this layer's neurons to next layer's neurons
    double[] giveOutputSignalToLayer();

    // Get output signal from previous layer's neurons to this layer's neurons
    // If this is the first layer of network, just get input signals
    void getInputSignalToLayer(double[] outputSignals);

    // Get errors from next layer's neurons
    void getErrors(double[] errors);

    // Give errors to previous layer's neurons
    double[] giveErrorsToLayer();

    // Corrects weights and biases of all this layer's neurons
    void correctWeightsOfLayer(double learningRate);

    // Print state of layer
    void printStateLayer();
}
