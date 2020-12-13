package com.hef.chapter4.spt;


import com.hef.algorithms.fundamentals.bags_queues_stacks.Stack;
import com.hef.commons.In;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

/**
 * @author lifei
 * @since 2020/12/13
 */
public class DijkstraSP {

    private DirectedEdge[] edgeTo;
    private double[] distTo;
    private IndexMinPQ<Double> pq;

    public DijkstraSP(EdgeWeightedDigraph G, int s){
        edgeTo = new DirectedEdge[G.V()];
        distTo = new double[G.V()];
        pq = new IndexMinPQ<>(G.V());
        for (int v = 0; v < distTo.length; v++) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = 0.0;
        pq.insert(s, 0.0);
        while (!pq.isEmpty()){
            relax(G, pq.delMin());
        }
    }

    private void relax(EdgeWeightedDigraph G, int v){
        for (DirectedEdge e : G.adj(v)) {
            int w = e.to();
            if (distTo[w] > distTo[v] + e.weight()){
                distTo[w] = distTo[v] + e.weight();
                edgeTo[w] = e;
                if (pq.contains(w)) pq.exchange(w, distTo[w]);
                else pq.insert(w, distTo[w]);
            }
        }
    }

    public double distTo(int v){
        return distTo[v];
    }

    public boolean hasPathTo(int v){
        return distTo[v] != Double.POSITIVE_INFINITY;
    }

    public Iterable<DirectedEdge> pathTo(int v){
        if (!hasPathTo(v)) return null;
        Stack<DirectedEdge> path = new Stack<>();
        int w = v;
        for(DirectedEdge e = edgeTo[v];e!=null; e = edgeTo[e.from()]){
            path.push(e);
        }
        return path;
    }

    public static void main(String[] args) {
        try {
            EdgeWeightedDigraph G = new EdgeWeightedDigraph(new In(new ClassPathResource("tinyEWD.txt").getFile().getCanonicalPath()));
            int s = 0;
            DijkstraSP sp = new DijkstraSP(G, s);
            for (int v = 0; v < G.V(); v++) {
                System.out.print(s + " to " + v);
                System.out.print(String.format("(%4.2f): ", sp.distTo(v)));
                if (sp.hasPathTo(v)){
                    for (DirectedEdge e : sp.pathTo(v)) {
                        System.out.print(e + " ");
                    }
                    System.out.println();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
