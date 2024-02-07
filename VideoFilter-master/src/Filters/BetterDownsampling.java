package Filters;

import Interfaces.PixelFilter;
import core.DImage;

public class BetterDownsampling implements PixelFilter {
    @Override
    public DImage processImage(DImage img) {
        short[][] grid = img.getBWPixelGrid();
        short[][] newGrid = new short[grid.length/2][grid[0].length/2];

        for (int r = 0; r < grid.length-1; r++) {
            for (int c = 0; c < grid[r].length-1; c++) {

                newGrid[r/2][c/2] = (short) ((short) (grid[r][c] + grid[r + 1][c] + grid[r][c + 1] + grid[r + 1][c + 1]) / 4);


            }
        }


        img.setPixels(newGrid);
        return img;
    }
}
