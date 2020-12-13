package com.hef.chapter4.spt;

import com.hef.commons.In;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

/**
 * @author lifei
 * @since 2020/12/13
 */
public class Topological {

    private Iterable<Integer> order;

    public Topological(EdgeWeightedDigraph G){
        DigraphCycle cycle = new DigraphCycle(G);
        if (!cycle.hasCycle()){
            this.order = new DepthFirstOrder(G).reversePost();
        }
    }

    public Iterable<Integer> order(){
        return order;
    }

    public boolean isDAG(){
        return order!=null;
    }

    public static void main(String[] args) {
        ClassPathResource classPathResource = new ClassPathResource("tinyDAGEWD.txt");
        try {
            In in = new In(classPathResource.getFile().getCanonicalPath());
            EdgeWeightedDigraph G = new EdgeWeightedDigraph(in);
            Topological topological = new Topological(G);
            System.out.println("DAG : " + topological.isDAG());
            if (topological.isDAG()){
                for (Integer e : topological.order()) {
                    System.out.print(e + " ");
                }
                System.out.println();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
