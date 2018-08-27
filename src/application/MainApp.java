package application;

import application.utils.Library;
import application.utils.TaskAnswerPair;

public class MainApp {
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

    }
}
