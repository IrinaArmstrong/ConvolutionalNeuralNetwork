package application.network;

import java.util.Random;

public class Kernel {

    private double[][] kernel;
    private double bias;

    // Constructor, that init kernel coefficients as random numbers (at first time creating kernel)
    public Kernel(int size) {
        this.kernel = new double[size][size];
        Random random = new Random();
        for (int i = 0; i < size; i++)  {
            for (int j = 0; j < size; j++)  {
                this.kernel[i][j] = random.nextDouble();
            }
        }
        this.bias = random.nextDouble();
    }

    // Get kernel
    public double[][] getKernel() {
        return kernel;
    }

    // Get size of kernel
    public int getSize()  {
        return this.kernel.length;
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

    // Print state of kernel
    public void printStateKernel() {
        System.out.println("Kernel: ");
        System.out.println("Kernel size = "+ kernel.length + "*" + kernel.length + "\n");
        for (int i = 0; i < kernel.length; i++)  {
            for (int j = 0; j < kernel.length; j++)  {
                System.out.print(kernel[i][j] + " ");
            }
        }
        System.out.println("Bias = " + bias);

    }
}
