package Filters;

public class Point {

    private short r, g, b;
    public Point(short r, short g, short b){
        this.r = r;
        this.g = g;
        this.b = b;
    }


    public void changeVals(short newR, short newG, short newB){
        r = newR;
        g = newG;
        b = newB;
    }

    public short getR(){
        return this.r;
    }
    public short getG(){
        return this.g;
    }
    public short getB(){
        return this.b;
    }
}
