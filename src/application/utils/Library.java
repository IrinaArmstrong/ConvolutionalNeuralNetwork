package application.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Library {

    private static String dirPath = "C:\\Users\\Ирина\\workspace\\ConvolutionalNeuralNetwork\\ConvolutionalNeuralNetwork\\src\\resources";

    private static File dir = new File(dirPath);

    private int num;

    // Returns one training pair of task and answer
    public  TaskAnswerPair getOne() {
        return new TaskAnswerPair(getPixels(), num);
    }

    // Get matrix of pixels
    public double[][] getPixels()  {

        BufferedImage image = getImageFromFile();
        int width = image.getWidth();
        int height = image.getHeight();
        double[][] pixels = new double[height][width];

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                pixels[row][col] = image.getRGB(col, row);
                Color colorOfPixel = new Color(image.getRGB(col, row));
                int rgb = (colorOfPixel.getBlue() + colorOfPixel.getGreen() + colorOfPixel.getRed()) / 765;
                pixels[row][col] = rgb;
            }
        }
        return pixels;
    }


    //Get certain image from file system
    private BufferedImage getImageFromFile()  {

        String imgURL = "num" + generateRandNumber() + ".jpg";
        File file = new File(dir, imgURL);
        try {
            BufferedImage image = new BufferedImage(200,200,BufferedImage.TYPE_BYTE_GRAY);
            image = ImageIO.read(file);
            return image;
        } catch (IOException e) {
            System.err.println("Error in reading from file: " + e.getMessage());
            return null;
        }
    }

    // Generating random number
    private int generateRandNumber()  {
        Random random = new Random();
        num = random.nextInt(10);
        return  num;
    }
}
