package application.utils;

 /*Class, to store a pair: task and right answer.
 * Task - two dimensional array of double;
 * Answer - int number;
 * */

public class TaskAnswerPair {

    private double[][] task;
    private int number;

    public TaskAnswerPair(double[][] task, int number) {
        this.task = task;
        this.number = number;
    }

    public double[][] getTask() {
        return task;
    }

    public void setTask(double[][] task) {
        this.task = task;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
