package com.hef.chapter4;

import com.hef.commons.In;

/**
 * @author lifei
 * @since 2020/7/4
 */
public class Graph {

    // number of vertices
    private int V;
    // number of edges
    private int E;
    // adjacent lists
    private Bag<Integer>[] adj;

    public Graph(int V){
        this.V = V;
        this.E = 0;
        // Create array of lists
        adj = (Bag<Integer>[]) new Bag[V];
        // Initialize all lists
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<>();
        }
    }

    public Graph(In in){
        // Read V and construct this Graph
        this(in.readInt());
        // Read E
        int E = in.readInt();
        for (int i = 0; i < E; i++) {
            // add an edge.
            // Read a vertex
            int v = in.readInt();
            // Read another vertex
            int w = in.readInt();
            // and add edge connecting them.
            addEdge(v, w);
        }
    }

    public int V(){
        return V;
    }

    public int E(){
        return E;
    }

    public void addEdge(int v, int w){
        // Add w to v's lists.
        adj[v].add(w);
        // Add v to w's lists.
        adj[w].add(v);
        E++;
    }

    public Iterable<Integer> adj(int v){
        return adj[v];
    }



}
