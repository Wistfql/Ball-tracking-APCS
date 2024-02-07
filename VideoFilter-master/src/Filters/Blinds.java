package Filters;

import Interfaces.PixelFilter;
import core.DImage;

public class Blinds implements PixelFilter {
    @Override
    public DImage processImage(DImage img) {
        short[][] grid = img.getBWPixelGrid();

        for (int r = 0; r < grid.length; r+=50) {
            for (int c = 0; c < grid[r].length; c++) {
                for (int i = 0; i < 25; i++) {
                    grid[r + i][c] = 0;
                }


            }
        }

        img.setPixels(grid);
        return img;
    }
}
