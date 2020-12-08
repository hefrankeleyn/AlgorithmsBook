package com.hef.review;

import com.hef.commons.In;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

/**
 * @author lifei
 * @since 2020/12/8
 */
public class UFWeightQuickUnion {

    private int[] id;
    private int[] sz;
    private int count;

    public UFWeightQuickUnion(int V){
        count = V;
        id = new int[V];
        for (int i = 0; i < id.length; i++) {
            id[i] = i;
        }
        sz = new int[V];
        for (int i = 0; i < sz.length; i++) {
            sz[i] = 1;
        }
    }

    public void union(int p, int q){
        int x = find(p), y = find(q);
        if (x == y) return;
        // 确保小的根指向大大根（不让大的根变得更大）
        if (sz[x]<sz[y]){
            id[x] = y;
            sz[x] += sz[y];
        }else {
            id[y] = x;
            sz[y] += sz[x];
        }
        count--;
    }

    public int find(int v){
        while (v!=id[v]) v = id[v];
        return v;
    }

    public int count(){
        return count;
    }

    public boolean connected(int p, int q){
        return find(p) == find(q);
    }

    public static void main(String[] args) {
        ClassPathResource pathResource = new ClassPathResource("tinyG.txt");
        try {
            In in = new In(pathResource.getFile().getCanonicalPath());
            UFWeightQuickUnion uf = new UFWeightQuickUnion(in.readInt());
            int E = in.readInt();
            for (int i = 0; i < E; i++) {
                uf.union(in.readInt(), in.readInt());
            }
            System.out.println("count component: " + uf.count());
            System.out.println(uf.connected(8,9));
            System.out.println(uf.connected(8,7));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
