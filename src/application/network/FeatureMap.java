package application.network;

/*Class that realizes current depth-layer structure of single layer of network.*/

//todo: continue class!

public class FeatureMap {

    // Number of this feature map
    private int serialNumber;

    // Array of neurons
    private Neuron[] neurons;

    // Size of this feature map
    private int size;

    // Number of neurons in this feature map
    private int neuronsNumber;

    // Number if feature maps on previous layer (if it is convolution layer)
    private int prevFMNumber;

    // Size if feature maps on previous layer (if it is convolution layer)
    private int prevFMSize;

    // Number of neurons on previous layer (if it is input layer)
    private int prevNeuronsNumber;

    // Kernel of this feature map
    private Kernel kernel;

    // Constuctor #1, if previous layer is convolution layer
    public FeatureMap(int serialNumber, int size, int prevFMNumber, int prevFMSize, Kernel kernel) {
        this.serialNumber = serialNumber;
        this.size = size;
        this.neuronsNumber = size * size;
        this.prevFMNumber = prevFMNumber;
        this.prevFMSize = prevFMSize;
        this.prevNeuronsNumber = prevFMNumber * prevFMSize * prevFMSize;
        this.kernel = kernel;
    }

    // Constuctor #2, if previous layer is input layer
    public FeatureMap(int serialNumber, int size, int prevNeuronsNumber, Kernel kernel) {
        this.serialNumber = serialNumber;
        this.size = size;
        this.neuronsNumber = size * size;
        this.prevFMNumber = 0;
        this.prevFMSize = 0;
        this.prevNeuronsNumber = prevNeuronsNumber;
        this.kernel = kernel;
    }



}
