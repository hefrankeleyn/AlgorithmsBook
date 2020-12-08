package com.hef.chapter4.mst;

import com.hef.commons.In;

/**
 * @author lifei
 * @since 2020/10/7
 */
public class MSTTest {

    public static void main(String[] args) {
        String filePath = "";
        In in = new In(filePath);
        EdgeWeightedGraph G = new EdgeWeightedGraph(in);
        MST mst = new MST(G);
        for (Edge edge : mst.edges()) {
            System.out.println(edge);
        }
        System.out.println(mst.weight());
    }

}
