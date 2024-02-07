package Filters;

import Interfaces.PixelFilter;
import core.DImage;

public class Convolution implements PixelFilter {

   // private short[][] pixelateKernel = {{0, -1, 0}, {-1, 5, -1}, {0, -1, 0}};
    private short[][] pixelateKernel = {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
    @Override
    public DImage processImage(DImage img) {
        short[][] red = img.getRedChannel();
        short[][] green = img.getGreenChannel();
        short[][] blue = img.getBlueChannel();





        img.setColorChannels(doConvolution(green, pixelateKernel), doConvolution(red, pixelateKernel), doConvolution(blue, pixelateKernel));
        return img;
    }

    private short computeOutput(short[][] image, short[][] kernel, int startR, int startC) {
        short output = 0;
        for (int r = startR; r < 3; r++) {
            for (int c = startC; c < 3; c++) {
                short weight = kernel[r][c];
                System.out.println("weight: " + weight);

                short pixelVal = image[r][c];
                System.out.println("pixelVal: " + pixelVal);
                output += weight*pixelVal;
                System.out.println("Output: " + output);
            }
        }

        return output;
    }

    private short[][] doConvolution(short[][] color, short[][] kernel){
        short[][] colorCopy = new short[color.length][color[0].length];
        for (int i = 0; i < colorCopy.length; i++) {
            System.arraycopy(color[i], 0, colorCopy[i], 0, colorCopy[i].length);

        }
        for (int r = 1; r < colorCopy.length-1; r++) {
            for (int c = 1; c < colorCopy[r].length-1; c++) {
                short newVal = (short) computeOutput(colorCopy, kernel, r-1, c-1);
                // System.out.println(newVal);
                color[r][c] = newVal;
            }

        }
        return color;

    }


}
