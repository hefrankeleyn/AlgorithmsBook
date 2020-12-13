package com.hef.chapter4.spt;

import com.hef.algorithms.fundamentals.bags_queues_stacks.Stack;
import com.hef.commons.In;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

/**
 * 图计算： 查询最短路径都树
 * @author lifei
 * @since 2020/12/13
 */
public class AcyclicSP {

    private DirectedEdge[] edgeTo;
    private double[] distTo;

    public AcyclicSP(EdgeWeightedDigraph G, int s){
        edgeTo = new DirectedEdge[G.V()];
        distTo = new double[G.V()];
        for (int i = 0; i < distTo.length; i++) {
            distTo[i] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = 0.0;
        Topological topological = new Topological(G);
        boolean beginFlag = false;
        if (topological.isDAG()){
            Iterable<Integer> order = topological.order();
            for (Integer v : order) {
                if (v==s && !beginFlag) {
                    beginFlag = true;
                }
                if (beginFlag) {
                    relax(G, v);
                }
            }
        }
    }

    private void relax(EdgeWeightedDigraph G, int v){
        for (DirectedEdge e : G.adj(v)) {
            if (distTo[e.to()] > distTo[e.from()] + e.weight()){
                distTo[e.to()] = distTo[e.from()] + e.weight();
                edgeTo[e.to()] = e;
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
        for(DirectedEdge e = edgeTo[v]; e!=null; e = edgeTo[e.from()]){
            path.push(e);
        }
        return path;
    }

    public static void main(String[] args) {
        ClassPathResource classPathResource = new ClassPathResource("tinyDAGEWD.txt");
        try {
            In in = new In(classPathResource.getFile().getCanonicalPath());
            EdgeWeightedDigraph G = new EdgeWeightedDigraph(in);
            int s= 5;
            AcyclicSP sp = new AcyclicSP(G, s);
            for (int v = 0; v < G.V(); v++) {
                if (sp.hasPathTo(v)){
                    System.out.print(String.format("%d->%d %.2f : ", s, v, sp.distTo(v)));
                    for (DirectedEdge e : sp.pathTo(v)) {
                        System.out.print(e + " ");
                    }
                    System.out.println();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
