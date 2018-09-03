package application.network;

// Main class of application

import application.utils.Library;
import application.utils.TaskAnswerPair;

public class Main {

    public static void main(String[] args) {

// Testing library
        Library library = new Library();
        TaskAnswerPair tap = library.getOne();
        System.out.println("Number is " + tap.getNumber());
        System.out.println("Task is ");
        double[][] t = tap.getTask();
        for (int row = 0; row < 28; row++) {
            for (int col = 0; col < 28; col++) {
                System.out.print (t[row][col] + " ");
            }
            System.out.println();
        }


// Testing network
        int[] neuronsInLayers = {841, 1014, 1250, 100, 10};
        int[] kernelsInLayers = {0, 6, 50, 0, 0};
        int[] kernelsParams = {0, 5, 5, 0, 0};
        int step = 2;
        Teacher teacher = new Teacher(5, neuronsInLayers, 0.01, 100, 20,
                kernelsInLayers, kernelsParams, step);
        teacher.train();
    }

}
