package Filters;

import Interfaces.Interactive;
import Interfaces.PixelFilter;
import core.DImage;

public class DetectBallsFilter implements PixelFilter, Interactive {

    @Override
    public DImage processImage(DImage img) {
        short[][] red = img.getRedChannel();
        short[][] green = img.getGreenChannel();
        short[][] blue = img.getBlueChannel();




        img.setColorChannels(red,green,blue);
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
