package com.hef.algorithms.chapter3.item34;

import java.util.Iterator;

/**
 * @Date 2020/2/6
 * @Author lifei
 */
public class SeparateChainingHashST<Key, Value> {

    // number of key-value paris
    private int N;
    // hash table size
    private int M;
    // array of ST objects
    private SequentialSearchST<Key, Value>[] st;

    public SeparateChainingHashST(){
        this(997);
    }

    // Create M linked lists
    public SeparateChainingHashST(int M){
        this.M = M;
        st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[M];
        for (int i = 0; i < M; i++) {
            st[i] = new SequentialSearchST();
        }
    }

    private int hash(Key key){
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public Value get(Key key){
        return (Value) st[hash(key)].get(key);
    }

    public void put(Key key, Value val){
        st[hash(key)].put(key,val);
        N++;
    }

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

        private int tempIndex = 0;
        private Iterator<Key> currentIterator;
        public KeyIterator(){
            for (int i=0;i<M;i++) {
                if (!st[i].isEmpty()){
                    currentIterator = st[i].keys().iterator();
                    tempIndex=i;
                    break;
                }
            }
        }

        @Override
        public boolean hasNext() {
            if (currentIterator==null) return false;
            return currentIterator.hasNext();
        }

        @Override
        public Key next() {
            Key key = currentIterator.next();
            if (!currentIterator.hasNext()){
                currentIterator = nextIterator();
            }
            return key;
        }

        private Iterator<Key> nextIterator(){
            for (int i = tempIndex+1; i < M; i++) {
                if (!st[i].isEmpty()){
                    tempIndex = i;
                    return st[i].keys().iterator();
                }
            }
            return null;
        }
    }

    public static void main(String[] args) {
        SeparateChainingHashST<String, Integer> separateChainingHashST = new SeparateChainingHashST<>();
        separateChainingHashST.put("aa",23);
        separateChainingHashST.put("world", 12);
        separateChainingHashST.put("hello", 33);
        Iterable<String> iterable = separateChainingHashST.keys();
        Iterator<String> iterator = iterable.iterator();
        while (iterator.hasNext()){
            String next = iterator.next();
            System.out.println(next);
        }
    }
}
