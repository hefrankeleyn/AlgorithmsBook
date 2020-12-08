package com.hef.mst;

/**
 * 代表边
 * @author lifei
 * @since 2020/10/28
 */
public class Edged implements Comparable<Edged>{

    private int v;
    private int w;
    private double weight;

    public Edged(int v, int w, double weight){
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    /** 任意一个顶点 */
    public int either(){
        return v;
    }

    /** 另一个顶点 */
    public int other(int v){
        if (this.v == v) return this.w;
        else if (this.w == v) return this.v;
        else throw new RuntimeException("not consistent edged");
    }

    /** 权重 */
    public double weight(){
        return weight;
    }

    @Override
    public int compareTo(Edged o) {
        if (this.weight() < o.weight()) return -1;
        else if (this.weight() > o.weight()) return 1;
        return 0;
    }

    @Override
    public String toString() {
        return String.format("%d-%d %f", v, w, weight);
    }


}
