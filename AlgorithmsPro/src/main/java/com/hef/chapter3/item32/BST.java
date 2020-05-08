package com.hef.chapter3.item32;

import com.hef.chapter3.item31.exercises.ST;

/**
 * Binary search tree symbol table
 * @Date 2020/5/8
 * @Author lifei
 */
public class BST<Key extends Comparable<Key>,Value> implements ST<Key,Value> {

    private Node root;

    private class Node{
        // key
        private Key key;
        // associated value
        private Value value;
        // links to subtrees
        private Node left,right;
        // nodes in subtree rooted here
        private int N;

        public Node(Key key, Value value, int N){
            this.key = key;
            this.value = value;
            this.N = N;
        }
    }

    @Override
    public void put(Key key, Value value) {
        root = put(root, key, value);
    }

    private Node put(Node node, Key key, Value value){
        if (node==null){
            return new Node(key, value, 1);
        }
        int comp = key.compareTo(node.key);
        if (comp>0){
            node.right = put(node.right, key, value);
        }else if (comp<0){
            node.left = put(node.left, key, value);
        }else {
            node.value = value;
        }
        node.N = size(node.left) + size(node.right) + 1;
        return node;
    }

    @Override
    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node node, Key key){
        if (node==null) return null;
        int comp = key.compareTo(node.key);
        if (comp>0){
            return get(node.right, key);
        }else if (comp < 0){
            return get(node.left, key);
        }else {
            return node.value;
        }
    }

    @Override
    public int size() {
        return size(root);
    }

    private int size(Node node){
        if (node==null) return 0;
        else return node.N;
    }

    @Override
    public boolean isEmpty() {
        return size(root)==0;
    }

    @Override
    public Iterable<Key> keys() {
        return null;
    }

    @Override
    public void delete(Key key) {
    }



}
