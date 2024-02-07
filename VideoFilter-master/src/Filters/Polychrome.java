package Filters;

import Interfaces.PixelFilter;
import core.DImage;

import javax.swing.*;

public class Polychrome  implements PixelFilter {
    @Override
    public DImage processImage(DImage img) {
        short[][] grid = img.getBWPixelGrid();

        int n = Integer.parseInt(JOptionPane.showInputDialog("Greyscale values: "));
        int interval = 255/n;

        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {

                grid[r][c] = (short)((grid[r][c]/interval) * interval + interval/2);


            }
        }

        img.setPixels(grid);
        return img;
    }
}
