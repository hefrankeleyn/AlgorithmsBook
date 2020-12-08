package com.hef.review;


import com.hef.commons.In;
import org.springframework.core.io.ClassPathResource;
import sun.tools.java.ClassPath;

import java.io.IOException;

/**
 * @author lifei
 * @since 2020/12/8
 */
public class UFQuickFind {

    private int[] id;
    private int count;


    public UFQuickFind(int V){
        count = V;
        id = new int[V];
        for (int i = 0; i < id.length; i++) {
            id[i] = i;
        }
    }

    public void union(int v, int w){
        int vID = find(v);
        int wID = find(w);
        if (vID==wID) return;
        for (int i = 0; i < id.length; i++) {
            if (id[i]==vID){
                id[i] = wID;
            }
        }
        count--;
    }

    public int find(int v){
        return id[v];
    }

    public boolean connected(int p, int q){
        return find(p)==find(q);
    }

    public int count(){
        return count;
    }

    public static void main(String[] args) {
        ClassPathResource pathResource = new ClassPathResource("tinyG.txt");
        try {
            In in = new In(pathResource.getFile().getCanonicalPath());
            UFQuickFind uf = new UFQuickFind(in.readInt());
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
