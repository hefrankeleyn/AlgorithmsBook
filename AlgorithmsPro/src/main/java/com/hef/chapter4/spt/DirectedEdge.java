package com.hef.chapter4.spt;

/**
 * @author lifei
 * @since 2020/12/13
 */
public class DirectedEdge {

    private int v;
    private int w;
    private double weight;

    public DirectedEdge(int v, int w, double weight){
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public int from(){
        return v;
    }

    public int to(){
        return w;
    }

    public double weight(){
        return weight;
    }

    @Override
    public String toString() {
        return String.format("%d->%d %s", v, w, weight);
    }

}
