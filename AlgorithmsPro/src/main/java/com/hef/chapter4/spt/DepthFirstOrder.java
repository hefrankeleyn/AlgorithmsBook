package com.hef.chapter4.spt;

import com.hef.algorithms.fundamentals.bags_queues_stacks.Queue;
import com.hef.algorithms.fundamentals.bags_queues_stacks.Stack;

/**
 * @author lifei
 * @since 2020/12/13
 */
public class DepthFirstOrder {

    private Queue<Integer> pre;
    private Queue<Integer> post;
    private Stack<Integer> reversePost;
    private boolean[] marked;

    public DepthFirstOrder(EdgeWeightedDigraph G){
        pre = new Queue<>();
        post = new Queue<>();
        reversePost = new Stack<>();
        marked = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]){
                dfs(G, v);
            }
        }
    }

    private void dfs(EdgeWeightedDigraph G, int v){
        marked[v] = true;
        pre.enqueue(v);
        for (DirectedEdge w : G.adj(v)) {
            if (!marked[w.to()]) {
                dfs(G, w.to());
            }
        }
        post.enqueue(v);
        reversePost.push(v);
    }

    public Iterable<Integer> pre(){
        return pre;
    }

    public Iterable<Integer> post(){
        return post;
    }

    public Iterable<Integer> reversePost(){
        return reversePost;
    }
}
