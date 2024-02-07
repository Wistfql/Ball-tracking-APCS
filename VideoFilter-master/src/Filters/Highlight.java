package Filters;

import Interfaces.Interactive;
import Interfaces.PixelFilter;
import core.DImage;

public class Highlight implements PixelFilter, Interactive {
    private int targetColor;
    private int width;

    public Highlight() {
        targetColor = 127;
        width = 50;
    }

    @Override
    public DImage processImage(DImage img) {
        short[][] pixels = img.getBWPixelGrid();

        for (int r = 0; r < pixels.length; r++) {
            for (int c = 0; c < pixels[r].length; c++) {
                short val = pixels[r][c];
                if (val < targetColor - width || val > targetColor + width) {
                    pixels[r][c] = 0;
                }
            }
        }

        img.setPixels(pixels);
        return img;
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, DImage img) {
        short[][] pixels = img.getBWPixelGrid();
        targetColor = pixels[mouseY][mouseX];

    }

    @Override
    public void keyPressed(char key) {
        if(key == '+'){

            targetColor += 10;


        }

        if(key == '-'){

            targetColor += 10;
            if (width < 0) width = 0;


        }


    }
}
