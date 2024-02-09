package Filters;

import Interfaces.Interactive;
import Interfaces.PixelFilter;
import core.DImage;

public class ColorMask implements PixelFilter, Interactive {
    // yellow
    short r = 255;
    short g = 255;
    short b = 0;
    double hue = 0;
    double threshold = 10;
    double currHue = 0;
    @Override
    public DImage processImage(DImage img) {
        short[][] red = img.getRedChannel();
        short[][] green = img.getGreenChannel();
        short[][] blue = img.getBlueChannel();

        for (int i = 0; i < red.length; i++) {
            for (int j = 0; j < red[0].length; j++) {
                currHue = getHue(red[i][j],green[i][j],blue[i][j]);

                if (currHue > hue-threshold && currHue < hue+threshold){
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

    private double getHue(short red, short green, short blue) {
        double r = red/1.0;
        double g = green/1.0;
        double b = blue/1.0;
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
        short[][] red = img.getRedChannel();
        short[][] green = img.getGreenChannel();
        short[][] blue = img.getBlueChannel();
        r = red[mouseY][mouseX];
        g = green[mouseY][mouseX];
        b = blue[mouseY][mouseX];
        hue = getHue(r,g,b);
    }

    @Override
    public void keyPressed(char key) {
        if(key == 'g') {
            threshold+=0.5;
        }
        if(key == 'h') {
            threshold-=0.5;
        }
    }
}
