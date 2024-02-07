package Filters;

import Interfaces.PixelFilter;
import core.DImage;

import javax.swing.*;

public class distort implements PixelFilter {
    @Override
    public DImage processImage(DImage img) {
        short[][] grid = img.getBWPixelGrid();
        int height = grid.length;
        int width = grid[0].length;

        for (int r = 0; r < height; r++) {
            double sinValue = Math.sin(r * 0.1); // Adjust the frequency (0.1 in this case)
            int distortion = (int) (sinValue * 20); // Adjust the amplitude (20 in this case)

            for (int c = 0; c < width; c++) {
                int newColumn = (c + distortion + width) % width; // Wrap around for columns
                grid[r][c] = 0;  // Set pixel value to black
            }
        }

        img.setPixels(grid);
        return img;
    }
}
