package application.network;

import java.util.Random;

public class Kernel {

    private double[][] kernel;

    // Constructor, that init kernel coefficients as random numbers (at first time creating kernel)
    public Kernel(int size) {
        this.kernel = new double[size][size];
        Random random = new Random();
        for (int i = 0; i < size; i++)  {
            for (int j = 0; j < size; j++)  {
                this.kernel[i][j] = random.nextDouble();
            }
        }
    }

    public double[][] getKernel() {
        return kernel;
    }

    // Set whole kernel
    public void setKernel(double[][] kernel) {
        this.kernel = kernel;
    }

    // Set element of kernel
    public void setKernelElement(double element, int row, int col)  {
        this.kernel[row][col] = element;
    }

    // Get element of kernel
    public double getKernelElement(int row, int col)  {
        return this.kernel[row][col];
    }
}
