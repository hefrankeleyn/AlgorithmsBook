package com.hef.chapter4.spt;

import com.hef.chapter4.Bag;
import com.hef.commons.In;

/**
 * @author lifei
 * @since 2020/12/13
 */
public class EdgeWeightedDigraph {

    private int E;
    private int V;
    private Bag<DirectedEdge>[] adj;

    public EdgeWeightedDigraph(int V){
        this.V = V;
        this.E = 0;
        this.adj = new Bag[V];
        for (int i = 0; i < adj.length; i++) {
            adj[i] = new Bag<>();
        }
    }

    public EdgeWeightedDigraph(In in){
        this(in.readInt());
        int E = in.readInt();
        for (int v = 0; v < E; v++) {
            add(new DirectedEdge(in.readInt(), in.readInt(), in.readDouble()));
        }
    }

    public void add(DirectedEdge e){
        adj[e.from()].add(e);
        E++;
    }

    public int E(){
        return E;
    }

    public int V(){
        return V;
    }

    public Iterable<DirectedEdge> adj(int v){
        return adj[v];
    }


}
