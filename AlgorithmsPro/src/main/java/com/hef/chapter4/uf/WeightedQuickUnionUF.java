package com.hef.chapter4.uf;

import com.hef.chapter4.UF;
import com.hef.commons.StdIn;
import com.hef.commons.StdOut;

/**
 * @author lifei
 * @since 2020/7/10
 */
public class WeightedQuickUnionUF extends UF {

    /** size of component for roots (size indexed) */
    private int[] sz;
    public WeightedQuickUnionUF(int N) {
        super(N);
        sz = new int[N];
        for (int i = 0; i < N; i++) {
            sz[i] = 1;
        }
    }

    @Override
    public void union(int p, int q) {
        int i = find(p);
        int j = find(q);
        if (i == j) return;

        if (sz[i]>sz[j]){
            id[j] = i;
            sz[i] += sz[j];
        }else {
            id[i] = j;
            sz[j] += sz[i];
        }
        count--;
    }

    @Override
    public int find(int p) {
        // follow link to find a root
        while (p != id[p]) p = id[p];
        return p;
    }

    /**
     * $ javac com/hef/chapter4/uf/WeightedQuickUnionUF.java
     * $ java com/hef/chapter4/uf/WeightedQuickUnionUF < com/hef/chapter4/uf/tinyUF.txt
     * 4 3
     * 3 8
     * 6 5
     * 9 4
     * 2 1
     * 5 0
     * 7 2
     * 6 1
     * 2 components
     * @param args
     */
    public static void main(String[] args) {
        int N = StdIn.readInt();
        UF uf = new WeightedQuickUnionUF(N);
        while (!StdIn.isEmpty()){
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (uf.connected(p, q)) continue;
            uf.union(p, q);
            StdOut.println(p + " " + q);
        }
        StdOut.println(uf.count() + " components");
    }
}
