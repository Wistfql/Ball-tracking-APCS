package Filters;

import Interfaces.PixelFilter;
import core.DImage;

import javax.swing.*;

public class RemoveColor implements PixelFilter {

    String color = JOptionPane.showInputDialog("Remove color (r, g, b): ");
    @Override
    public DImage processImage(DImage img) {
        short[][] red = img.getRedChannel();
        short[][] green = img.getGreenChannel();
        short[][] blue = img.getBlueChannel();

        if (color.equalsIgnoreCase("r")){
            red = new short[img.getHeight()][img.getWidth()];
        } else if (color.equalsIgnoreCase("g")) {
            green = new short[img.getHeight()][img.getWidth()];
        } else if (color.equalsIgnoreCase("b")) {
            blue = new short[img.getHeight()][img.getWidth()];
        }


        img.setColorChannels(red, green, blue);
        return img;
    }
}
