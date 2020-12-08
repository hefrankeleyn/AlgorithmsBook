package com.hef.chapter4.direct;

import com.hef.chapter4.Bag;
import com.hef.commons.In;

/**
 * 有方向的图
 * @author lifei
 * @since 2020/9/6
 */
public class Digraph {

    private int V;
    private int E;
    private Bag<Integer>[] adj;

    public Digraph(int V){
        this.V = V;
        this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new Bag<>();
        }
    }

    public Digraph(In in){
        this(in.readInt());
        int E = in.readInt();
        for (int i = 0; i < E; i++) {
            int v = in.readInt();
            int w = in.readInt();
            addEdge(v, w);
        }
    }

    private void addEdge(int v, int w){
        adj[v].add(w);
        E++;
    }

    private Iterable<Integer> adj(int v){
        return adj[v];
    }

    public Digraph reverse(){
        Digraph digraph = new Digraph(V);
        for (int v = 0; v < V; v++) {
            for (Integer w : adj(v)) {
                digraph.addEdge(w, v);
            }
        }
        return digraph;
    }

    public static void main(String[] args) {
        String filePath = "/Users/lifei/Documents/workspace/git_test_wp/graph/tinyDG.txt";
        In in = new In(filePath);
        Digraph digraph = new Digraph(in);
        for (int i = 0; i < digraph.V; i++) {
            System.out.print(String.format("%2d:", i));
            for (Integer w : digraph.adj(i)) {
                System.out.print(String.format("%3d", w));
            }
            System.out.println();
        }

    }


}
