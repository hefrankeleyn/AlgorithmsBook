package com.hef.algorithms.chapter3.item34;

import java.util.Iterator;

/**
 * 顺序查询的key-value 表
 *
 * @Date 2020/2/6
 * @Author lifei
 */
public class SequentialSearchST<Key, Value> {

    private Node first;

    private class Node {
        Key key;
        Value val;
        Node next;

        public Node(Key key, Value val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    public boolean isEmpty(){
        if (first==null) return true;
        return false;
    }

    /**
     * Search for key, return associated value
     *
     * @param key
     * @return
     */
    public Value get(Key key) {
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                return x.val;
            }
        }
        return null;
    }

    /**
     * Search for key. Update value if found; grow table if new
     *
     * @param key
     * @param val
     */
    public void put(Key key, Value val) {
        for (Node x = first; x != null; x = x.next) {
            // Search hit: update val
            if (key.equals(x.key)) {
                x.val = val;
                return;
            }
        }
        // Search miss: add new node
        first = new Node(key, val, first);
    }

    public Iterable<Key> keys() {
        return new KeyIterable();
    }

    private class KeyIterable implements Iterable<Key> {

        @Override
        public Iterator<Key> iterator() {
            return new KeyIterator();
        }
    }

    private class KeyIterator implements Iterator<Key> {

        private Node current = first;

        @Override
        public boolean hasNext() {
            if (current == null) return false;
            return true;
        }

        @Override
        public Key next() {
            Key key = current.key;
            current = current.next;
            return key;
        }
    }

    public static void main(String[] args) {
        SequentialSearchST<String, Integer> sequentialSearchST = new SequentialSearchST<>();
        sequentialSearchST.put("aa", 23);
        sequentialSearchST.put("bb", 33);
        sequentialSearchST.put("cc", 12);
        System.out.println(sequentialSearchST.first.key);
        Iterable<String> keys = sequentialSearchST.keys();
        Iterator<String> iterator = keys.iterator();
        while (iterator.hasNext()) {
            String next = iterator.next();
            System.out.println(next);
        }

    }
}
