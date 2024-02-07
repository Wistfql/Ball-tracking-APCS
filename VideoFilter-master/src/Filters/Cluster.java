package Filters;

import Interfaces.PixelFilter;
import core.DImage;

import java.awt.*;
import java.util.ArrayList;

public class Cluster{

    ArrayList<Point> points;
    private short r, g, b;


    public Cluster(ArrayList<Point> p, short r, short g, short b){
        this.points = p;
        this.r = r;
        this.g = g;
        this.b = b;

    }

    public void clear() {
        points.clear();
    }

    public void addPoint(Point p){
        points.add(p);
    }
    public void changeVals(short newR, short newG, short newB){
        r = newR;
        g = newG;
        b = newB;
    }

    public double calcDistance(Point p){
        return Math.sqrt((r-p.getR())*(r-p.getR()) + (g-p.getG())*(g-p.getG()) + (b-p.getB())*(b-p.getB()));
    }

    public void assignToCluster(Point p){
        if (this.calcDistance(p) < 200){
            p.changeVals(r, g, b);
        }
    }

    public void calculateCenter(){
        short r = 0;
        short g = 0;
        short b = 0;

        for (int i = 0; i < points.size(); i++) {
            r += points.get(i).getR();
            g += points.get(i).getG();
            b += points.get(i).getB();

        }

        this.changeVals(r, g, b);
    }




}
