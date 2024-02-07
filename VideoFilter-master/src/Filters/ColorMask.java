package Filters;

import Interfaces.Interactive;
import Interfaces.PixelFilter;
import core.DImage;

public class ColorMask implements PixelFilter, Interactive {
    // yellow
    int r = 255;
    int g = 255;
    int b = 0;

    int distance = (int)(Math.sqrt(r*r + g*g + b*b));
    int d = 0;
    @Override
    public DImage processImage(DImage img) {
        short[][] red = img.getRedChannel();
        short[][] green = img.getGreenChannel();
        short[][] blue = img.getBlueChannel();

        for (int i = 0; i < red.length; i++) {
            for (int j = 0; j < red[0].length; j++) {
                d = (int)(Math.sqrt(red[i][j]*red[i][j] + green[i][j]*green[i][j] + blue[i][j]*blue[i][j]));

                if (Math.abs(d-distance) <= 50){
                    red[i][j] = 255;
                    green[i][j] = 255;
                    blue[i][j] = 255;


                } else{
                    red[i][j] = 0;
                    green[i][j] = 0;
                    blue[i][j] = 0;

                }
            }

        }



        img.setColorChannels(green, red, blue);
        return img;
    }

    private double getHue(int red, int green, int blue) {
        double r = red/255.0;
        double g = green/255.0;
        double b = blue/255.0;
        double hue = 0;
        double max = identifyMax(r,g,b);
        double min = identifyMin(r,g,b);
        if(max == 0) {
            hue = 0;
        }
        if(max == red) {
            hue = 60*(((g-b)%6)/(max-min));
        }
        if(max == green) {
            hue = 60*(2.0+(b-r)/(max-min));
        }
        if(max == blue) {
            hue = 60*(4.0+(r-g)/(max-min));
        }
        return hue;
    }
    private double identifyMax(double r, double g, double b) {
        double max = r;
        if(g>max) {
            max = g;
        }
        if(b>max) {
            max = b;
        }

        return max;
    }

    private double identifyMin(double r, double g, double b) {
        double min = r;
        if(g<min) {
            min = g;
        }
        if(b<min) {
            min = b;
        }

        return min;
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, DImage img) {

    }

    @Override
    public void keyPressed(char key) {

    }
}
