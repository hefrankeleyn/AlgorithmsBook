package com.hef.chapter4.spt;


import com.hef.algorithms.fundamentals.bags_queues_stacks.Stack;
import com.hef.commons.In;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

/**
 * @author lifei
 * @since 2020/12/13
 */
public class DigraphCycle {

    private boolean[] onStack;
    private boolean[] marked;
    private DirectedEdge[] edgeTo;
    private Stack<Integer> cycle;

    public DigraphCycle(EdgeWeightedDigraph G){
        edgeTo = new DirectedEdge[G.V()];
        onStack = new boolean[G.V()];
        marked = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]){
                dfs(G, v);
            }
        }
    }

    private void dfs(EdgeWeightedDigraph G, int v){
        onStack[v] = true;
        marked[v] = true;
        for (DirectedEdge w : G.adj(v)) {
            if (hasCycle()) return;
            else if (!marked[w.to()]) {
                edgeTo[w.to()] = w;
                dfs(G, w.to());
            }else if (onStack[w.to()]){
                System.out.println(w);
                cycle = new Stack<>();
                for(int x = v; x!=w.to(); x = edgeTo[x].from()){
                    cycle.push(x);
                }
                cycle.push(w.to());
                cycle.push(w.from());
            }
        }
        onStack[v] = false;
    }

    public boolean hasCycle(){
        return cycle != null;
    }

    public Iterable<Integer> cycle(){
        return cycle;
    }

    public static void main(String[] args) {
        ClassPathResource classPathResource = new ClassPathResource("tinyEWD.txt");
        try {
            In in = new In(classPathResource.getFile().getCanonicalPath());
            EdgeWeightedDigraph G = new EdgeWeightedDigraph(in);
            DigraphCycle cycle = new DigraphCycle(G);
            System.out.println("have cycle: " + cycle.hasCycle());
            if (cycle.hasCycle()){
                for (Integer e : cycle.cycle()) {
                    System.out.print(e + " ");
                }
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
