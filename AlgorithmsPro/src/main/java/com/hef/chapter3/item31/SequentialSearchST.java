package com.hef.chapter3.item31;

import com.hef.chapter3.item31.exercises.ST;

import java.util.Iterator;

/**
 * @Date 2020/4/18
 * @Author lifei
 */
public class SequentialSearchST<Key,Value> implements ST<Key,Value> {

    private Node first;
    private int N;

    private class Node{
        Key key;
        Value value;
        Node next;

        public Node(Key key, Value value, Node next){
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    /**
     * put key-value pair into the table
     * @param key
     * @param value
     */
    @Override
    public void put(Key key, Value value){
        // Search for key, return associated value
        for (Node node=first;node!=null;node = node.next){
            if (key.equals(node.key)){
                node.value = value;
                return;
            }
        }
        first = new Node(key, value, first);
        N++;
    }

    /**
     * value paired with key
     * @param key
     * @return
     */
    @Override
    public Value get(Key key){
        for (Node node = first; node!=null; node = node.next){
            if (key.equals(node.key)){
                return node.value;
            }
        }
        return null;
    }

    @Override
    public int size(){
        return N;
    }

    @Override
    public boolean isEmpty() {
        return N==0;
    }

    /**
     * all keys in the table, in sorted order
     * @return
     */
    @Override
    public Iterable<Key> keys(){
        return new KeyIterable();
    }

    private class KeyIterable implements Iterable<Key>{

        @Override
        public Iterator<Key> iterator() {
            return new KeyIterator();
        }
    }

    private class KeyIterator implements Iterator<Key>{
        private Node current = first;
        @Override
        public boolean hasNext() {
            return current!=null;
        }

        @Override
        public Key next() {
            Key key = current.key;
            current = current.next;
            return key;
        }
    }

    /**
     * remove key (and its value) from table
     * @param key
     */
    @Override
    public void delete(Key key){
        if (first==null){
            return;
        } else if (key.equals(first.key)){
            first = first.next;
            N--;
            return;
        }else {
            for (Node node=first,next=first.next;next!=null;node = node.next,next = next.next){
                if (key.equals(next.key)){
                    if (node==first){
                        node.next = next.next;
                        N--;
                        return;
                    }
                }
            }
        }
    }
}
