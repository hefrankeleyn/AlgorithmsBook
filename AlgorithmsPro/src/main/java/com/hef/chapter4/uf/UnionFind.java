package com.hef.chapter4.uf;

import com.hef.chapter4.UF;
import com.hef.commons.StdIn;
import com.hef.commons.StdOut;

/**
 * @author lifei
 * @since 2020/7/10
 */
public class UnionFind extends UF {

    public UnionFind(int N){
        super(N);
    }

    @Override
    public void union(int p, int q) {
        // put p and q into the same component
        int pID = find(p);
        int qID = find(q);
        // Nothing to do if p and q are already in the same component
        if (pID == qID){
            return;
        }
        // rename p's component to q's name
        for (int i = 0; i < id.length; i++) {
            if (id[i] == pID) id[i] = qID;
        }
        count--;
    }

    @Override
    public int find(int p) {
        return id[p];
    }

    /**
     * 运行 示例
     * $ javac com/hef/chapter4/uf/UnionFind.java
     * $ java com/hef/chapter4/uf/UnionFind < com/hef/chapter4/uf/tinyUF.txt
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
        UF uf = new UnionFind(N);
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
