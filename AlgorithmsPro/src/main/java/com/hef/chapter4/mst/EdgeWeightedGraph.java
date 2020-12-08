package com.hef.chapter4.mst;

import com.hef.chapter4.Bag;
import com.hef.commons.In;

/**
 * @author lifei
 * @since 2020/10/7
 */
public class EdgeWeightedGraph {

    // number of vertices
    private final int V;
    // number of edges
    private int E;
    // adjacency lists
    private Bag<Edge>[] adj;

    public EdgeWeightedGraph(int V){
        this.V = V;
        this.E = 0;
        adj = (Bag<Edge>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<>();
        }
    }

    public EdgeWeightedGraph(In in){
        this(in.readInt());
        int E = in.readInt();
        for (int i = 0; i < E; i++) {
            int v = in.readInt();
            int w = in.readInt();
            double weight = in.readDouble();
            addEdge(new Edge(v, w, weight));
        }

    }

    public int V(){
        return V;
    }
    public int E(){
        return E;
    }

    public void addEdge(Edge e){
        int v = e.either(), w = e.other(v);
        adj[v].add(e);
        adj[w].add(e);
        E++;
    }

    public Iterable<Edge> adj(int v){
        return adj[v];
    }

    public Iterable<Edge> edges(){
        Bag<Edge> b = new Bag<>();
        for (int v = 0; v < V; v++) {
            for (Edge e : adj(v)) {
                if (e.other(v) > v){
                    b.add(e);
                }
            }
        }
        return b;
    }



}
