package com.hef.algorithms.fundamentals.symboltable;


import com.hef.algorithms.fundamentals.bags_queues_stacks.Queue;

/**
 * @Date 2019/10/20
 * @Author lifei
 */
public class BinarySearchTreeSymbolTable<Key extends Comparable<Key>, Value> {

    private Node root;

    private class Node {
        private Key key;
        private Value value;
        private Node left, right;
        private int N;

        public Node(Key key, Value value, int N) {
            this.key = key;
            this.value = value;
            this.N = N;
        }
    }

    public int size() {
        return size(root);
    }

    public int size(Node x) {
        if (x == null) return 0;
        return x.N;
    }

    /**
     * get key
     *
     * @param key
     * @return
     */
    public Value get(Key key) {
        return get(root, key);
    }

    public Value get(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else return x.value;
    }

    /**
     * put key value
     *
     * @param k
     * @param v
     */
    public void put(Key k, Value v) {
        root = put(root, k, v);
    }

    public Node put(Node x, Key k, Value v) {
        if (x == null) return new Node(k, v, 1);
        int cmp = k.compareTo(x.key);
        if (cmp < 0) x.left = put(x.left, k, v);
        else if (cmp > 0) x.right = put(x.right, k, v);
        else x.value = v;
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public Key min() {
        if (root == null) return null;
        return min(root).key;
    }

    public Node min(Node x) {
        if (x.left == null) return x;
        return min(x.left);
    }

    public Key max() {
        if (root == null) return null;
        return max(root).key;
    }

    public Node max(Node x) {
        if (x.right == null) return x;
        return max(x.right);
    }

    /**
     * the largest key in the BST less than or equal to key
     *
     * @param key
     * @return
     */
    public Key floor(Key key) {
        Node x = floor(root, key);
        if (x == null) return null;
        return x.key;
    }

    private Node floor(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp < 0) return floor(x.left, key);
        Node t = floor(x.right, key);
        if (t != null) return t;
        else return x;
    }

    /**
     * the smallest key in the BST greater than or equal to key
     *
     * @param key
     * @return
     */
    public Key ceiling(Key key) {
        Node x = ceiling(root, key);
        if (x == null) return null;
        return x.key;
    }

    public Node ceiling(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp > 0) return ceiling(x.right, key);
        Node t = ceiling(x.left, key);
        if (t != null) return t;
        else return x;
    }

    /**
     * 查询第几个元素的key值（从0开始计数）
     *
     * @param ki
     * @return
     */
    public Key select(int ki) {
        Node x = select(root, ki);
        if (x != null) return x.key;
        return null;
    }

    public Node select(Node x, int ki) {
        if (x == null) return null;
        int t = size(x.left);
        if (t > ki) return select(x.left, ki);
        else if (t < ki) return select(x.right, ki - t - 1);
        else return x;
    }

    /**
     * 查询key元素的角标（或查询有多少个小于key的元素）
     *
     * @param key
     * @return
     */
    public int rank(Key key) {
        return rank(root, key);
    }

    public int rank(Node x, Key key) {
        if (x == null) return 0;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return rank(x.left, key);
        else if (cmp > 0) return size(x.left) + 1 + rank(x.right, key);
        else return size(x.left);
    }

    /**
     * delete 最大值
     */
    public void deleteMin() {
        root = deleteMin(root);
    }

    public Node deleteMin(Node x) {
        if (x == null) return null;
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    /**
     * delete 最小值
     */
    public void deleteMax() {
        root = deleteMax(root);
    }

    public Node deleteMax(Node x) {
        if (x == null) return null;
        if (x.right == null) return x.left;
        x.right = deleteMax(x.right);
        x.N = size(x.right) + size(x.left) + 1;
        return x;
    }

    /**
     * 删除一个值，如果该值左右节点都存在，用右节点最小值补充
     * @param key
     */
    public void delete(Key key) {
        root=delete(root, key);
    }

    public Node delete(Node x, Key k) {
        if (x == null) return null;
        int cmp = k.compareTo(x.key);
        if (cmp < 0) x.left = delete(x.left, k);
        else if (cmp > 0) x.right = delete(x.right, k);
        else {
            if (x.right == null) return x.left;
            if (x.left == null) return x.right;
            Node t = x;
            x = min(x.right);
            x.right = deleteMin(x.right);
            x.left = t.left;
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public Iterable<Key> keys(){
        return keys(min(), max());
    }

    public Iterable<Key> keys(Key lo, Key hi){
        Queue<Key> queue = new Queue<>();
        keys(root, queue, lo, hi);
        return queue;
    }

    private void keys(Node x, Queue<Key> queue,Key lo, Key hi){
        if (x==null) return;
        int cmpLo = lo.compareTo(x.key);
        int cmpHi = hi.compareTo(x.key);
        if (cmpLo<0) keys(x.left, queue, lo, hi);
        if (cmpLo<=0 && cmpHi>=0) queue.enqueue(x.key);
        if (cmpHi>0) keys(x.right, queue, lo ,hi);

    }

}
