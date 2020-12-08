package com.hef.mst;

import com.hef.mst.base.In;
import com.hef.mst.base.MinPQ;
import com.hef.mst.base.Queue;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

/**
 * @author lifei
 * @since 2020/11/2
 */
public class LazyPrimMST {

    private boolean[] marked;
    private MinPQ<Edged> pq;
    private Queue<Edged> mst;


    public LazyPrimMST(WeightEdgedGraph G){
        marked = new boolean[G.V()];
        pq = new MinPQ<>();
        mst = new Queue<>();

        visit(G, 0);
        while (!pq.isEmpty()){
            Edged e = pq.delMin();
            int v = e.either(), w = e.other(v);
            if (marked[v] && marked[w]) continue;
            mst.enqueue(e);
            if (!marked[v]) visit(G, v);
            if (!marked[w]) visit(G, w);
        }
    }

    private void visit(WeightEdgedGraph G, int v){
        marked[v] = true;
        for (Edged e : G.adj(v)) {
            if (!marked[e.other(v)]){
                pq.insert(e);
            }
        }
    }

    public Iterable<Edged> mst(){
        return mst;
    }

    public double weight(){
        double weight = 0.0;
        for (Edged e : mst) {
            weight = weight + e.weight();
        }
        return weight;
    }

    public static void main(String[] args) {
        ClassPathResource pathResource = new ClassPathResource("mst/tinyEWG.txt");
        try {
            In in = new In(pathResource.getFile().getCanonicalPath());
            WeightEdgedGraph G = new WeightEdgedGraph(in);
            LazyPrimMST mst = new LazyPrimMST(G);
            for (Edged e : mst.mst()) {
                System.out.println(e);
            }
            System.out.println(mst.weight());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
