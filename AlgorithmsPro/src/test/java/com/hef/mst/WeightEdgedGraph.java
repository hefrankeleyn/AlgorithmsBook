package com.hef.mst;


import com.hef.mst.base.Bag;
import com.hef.mst.base.In;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;


/**
 * 带有权重的边
 * @author lifei
 * @since 2020/10/30
 */
public class WeightEdgedGraph {

    private int E;
    private int V;
    private Bag<Edged>[] adj;

    public WeightEdgedGraph(int V){
        this.V = V;
        adj = new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<>();
        }
    }

    public WeightEdgedGraph(In in){
        this(in.readInt());
        int E = in.readInt();
        for (int i = 0; i < E; i++) {
            add(new Edged(in.readInt(), in.readInt(), in.readDouble()));
        }
    }

    public int V(){
        return this.V;
    }

    public int E(){
        return this.E;
    }

    public void add(Edged e){
        int v = e.either(), w = e.other(v);
        adj[v].add(e);
        adj[w].add(e);
        E++;
    }

    public Iterable<Edged> adj(int v){
        return adj[v];
    }

    public Iterable<Edged> edges(){
        Bag<Edged> bag = new Bag<>();
        for (int v = 0; v < V; v++) {
            for (Edged e : adj(v)) {
                if (v < e.other(v)){
                    bag.add(e);
                }
            }
        }
        return bag;
    }

    public static void main(String[] args) {
        ClassPathResource pathResource = new ClassPathResource("mst/tinyEWG.txt");
        try {
            String filePath = pathResource.getFile().getCanonicalPath();
            In in = new In(filePath);
            WeightEdgedGraph G = new WeightEdgedGraph(in);
            System.out.println(G.V);
            for (Edged e : G.edges()) {
                System.out.println(e);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
