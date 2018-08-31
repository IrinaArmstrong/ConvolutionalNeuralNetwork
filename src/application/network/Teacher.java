package application.network;

import application.utils.Library;
import application.utils.TaskAnswerPair;

import java.util.ArrayList;

// Class, that holds main data to set the neuron network's parameters

public class Teacher {

    // Neural network
    private Network network;

    // Library
    private Library library;

    // Learning coefficient
    private double coefficient;

    // Number of iterations
    private int iterations;

    // Accuracy of results
    private double accuracy;

    // Number of layers
    private int layersNumber;

    // Number of neurons in each layer
    private int[] neuronsInLayers;

    // Number of tasks in set
    private int numberOfTasks;

    // Array that holds number of kernels in each layer
    private int[] kernelsInLayers;

    // Array that holds parameters of kernels for each layer
    private int[] kernelsParams;

    // Step of convolution
    private int step;

    // Current results of network
    private double[] results;

    // Constructor
    public Teacher(int layersNumber, int[] neuronsInLayers, double coefficient, int iterations, int numberOfTasks,
                   int[] kernelsInLayers, int[] kernelsParams, int step) {
        this.layersNumber = layersNumber;
        this.neuronsInLayers = neuronsInLayers;
        this.network = new Network(layersNumber, neuronsInLayers, kernelsInLayers, kernelsParams, step);
        this.library = new Library();
        this.coefficient = coefficient;
        this.iterations = iterations;
        this.numberOfTasks = numberOfTasks;
    }

    // Train network
    public void train()  {

        ArrayList<TaskAnswerPair> tasks = new ArrayList<>();
        for (int j = 0; j < tasks.size(); j++)  {
            tasks.add(this.library.getOne());
        }

        // Get array of right answers
        double[] answers = new double[tasks.size()];
        for (int i = 0; i < tasks.size(); i++)  {
            answers[i] = tasks.get(i).getNumber();
        }

        // Training network
        network.learn(tasks, coefficient, iterations);

    }

}
