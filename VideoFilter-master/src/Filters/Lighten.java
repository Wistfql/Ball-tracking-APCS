package Filters;

import Interfaces.PixelFilter;
import core.DImage;

public class Lighten implements PixelFilter {
    @Override
    public DImage processImage(DImage img) {
        short[][] grid = img.getBWPixelGrid();

        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {

                grid[r][c] += (255-grid[r][c]) * 0.6;


            }
        }

        img.setPixels(grid);
        return img;
    }
}
