package com.hef.chapter3.item33;

/**
 * @author lifei
 * @since 2020/6/11
 */
public class RedBlackBST<Key extends Comparable<Key>, Value> {

    private static final Boolean RED = true;
    private static final Boolean BLOCK = false;
    private Node root;

    private class Node{
        private Key key;
        private Value value;
        private Boolean color;
        private Node left,right;
        private int N;

        public Node(Key key, Value value, Boolean color, int N){
            this.key = key;
            this.value =  value;
            this.color = color;
            this.N = N;
        }
    }

    private boolean isRed(Node x){
        if (x==null) return false;
        return x.color == RED;
    }


}
