package com.hef.chapter4;

public abstract class UF {

    protected int count;
    protected int[] id;

    public UF(int N){
        // Initialize component id array
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }

    // add connection between p and q
    public abstract void union(int p, int q);

    // component identifier for p (0 to N -1)
    public abstract int find(int p);

    // return true if p and q in the same component
    public boolean connected(int p, int q){
        return find(p) == find(q);
    }

    // number of components
    public int count(){
        return count;
    }
}
