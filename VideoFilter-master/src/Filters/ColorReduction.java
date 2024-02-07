package Filters;

import Interfaces.PixelFilter;
import core.DImage;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class ColorReduction implements PixelFilter {
    int k = Integer.parseInt(JOptionPane.showInputDialog("k: "));

    @Override
    public DImage processImage(DImage img) {
        short[][] red = img.getRedChannel();
        short[][] green = img.getGreenChannel();
        short[][] blue = img.getBlueChannel();
        
        ArrayList<Point> points = new ArrayList<>();
        ArrayList<Cluster> clusters;

        clusters = initClusters(k);


        // get all points in image into points array
        for (int r = 0; r < red.length; r++) {
            for (int c = 0; c < red[r].length; c++) {
                points.add(new Point(red[r][c], green[r][c], blue[r][c]));
            }
            
        }

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < clusters.size(); j++) {
                for (int l = 0; l < points.size(); l++) {
                    clusters.get(j).clear();
                    clusters.get(j).assignToCluster(points.get(l));
                    clusters.get(j).calculateCenter();

                }
            }


        }


        img.setColorChannels(green, red, blue);
        return img;

    }

    public ArrayList<Cluster> initClusters(int k){
        ArrayList<Cluster> C = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            C.add(new Cluster(new ArrayList<>(), (short)(Math.random()*256), (short)(Math.random()*256), (short)(Math.random()*256)));
        }

        return C;
    }
}
