package com.hef.chapter4.uf;

import com.hef.chapter4.UF;
import com.hef.commons.StdIn;
import com.hef.commons.StdOut;

/**
 * @author lifei
 * @since 2020/7/10
 */
public class QuickUnionUF extends UF{

    public QuickUnionUF(int N){
        super(N);
    }

    @Override
    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) return;
        id[pRoot] = qRoot;
        count--;
    }

    @Override
    public int find(int p) {
        while (p != id[p]) p = id[p];
        return p;
    }

    /**
     * $ javac com/hef/chapter4/uf/QuickUnionUF.java
     * $ java com/hef/chapter4/uf/QuickUnionUF < com/hef/chapter4/uf/tinyUF.txt
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
        UF uf = new QuickUnionUF(N);
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
