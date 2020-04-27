package com.hef.chapter3.item31;

import com.hef.algorithms.fundamentals.bags_queues_stacks.Queue;

/**
 * @Date 2020/4/26
 * @Author lifei
 */
public class BinarySearchST<Key extends Comparable<Key>, Value> {
    
    private Key[] keys;
    private Value[] values;
    
    private int N;
    
    public BinarySearchST(int capacity){
        keys = (Key[]) new Comparable[capacity];
        values = (Value[]) new Object[capacity];
    }

    public int size(){
        return N;
    }

    /**
     * smallest key
     * @return
     */
    public Key min(){
        if (isEmpty()){
            return null;
        }else {
            return keys[0];
        }
    }

    /**
     * key of rank k
     * @param k
     * @return
     */
    public Key select(int k){
        if (isEmpty() || k>N){
            return null;
        }else {
            return keys[k];
        }
    }

    /**
     * smallest key greater than or equal to key
     * @param key
     * @return
     */
    public Key ceiling(Key key){
        int i = rank(key);
        int com = keys[i].compareTo(key);
        if (com<0 && com==N-1){
            return null;
        }else if(com==0){
            return keys[i];
        }else {
            return keys[i+1];
        }
    }

    /**
     * largest key less than or equal to key
     * @param key
     * @return
     */
    public Key floor(Key key){
        int i = rank(key);
        int com = keys[i].compareTo(key);
        if (keys[i].compareTo(key)>0){
            return null;
        }else {
            return keys[i];
        }
    }




    /**
     * largest key
     * @return
     */
    public Key max(){
        if (isEmpty()){
            return null;
        }else {
            return keys[N-1];
        }
    }



    /**
     * recursive
     * @param key
     * @param lo
     * @param hi
     * @return
     */
    public int rankRecursive(Key key,int lo, int hi){
        if (lo>hi) return lo;
        int mid = lo + (hi-lo)/2;
        int midComp = key.compareTo(keys[mid]);
        if (midComp<0){
            return rankRecursive(key, lo, midComp-1);
        }else if (midComp>0){
            return rankRecursive(key, midComp+1, hi);
        }else {
            return mid;
        }
    }
    /**
     * directly
     * @param key
     * @return
     */
    public int rank(Key key){
        int lo = 0, hi = N -1;
        while (lo <= hi){
            int mid = lo + (hi-lo)/2;
            int com = key.compareTo(keys[mid]);
            if (com == 0){
                return mid;
            }else if (com<0){
                hi = mid - 1;
            }else if (com>0){
                lo = mid + 1;
            }
        }
        return lo;
    }

    public void put(Key key, Value value){
        int i = rank(key);
        if (i < N && keys[i].compareTo(key)==0) {
            values[i] = value;
            return;
        }
        for (int j=N; j>i;j--){
            keys[j] = keys[j-1];
            values[j] = values[j-1];
        }
        keys[i] = key;
        values[i] = value;
    }
    public boolean isEmpty(){
        return N == 0;
    }

    public Value get(Key key){
        if (isEmpty()) return null;
        int i = rank(key);
        if (i < N && keys[i].compareTo(key)==0) return values[i];
        else return null;
    }

    public void delete(Key key){
        int i = rank(key);
        if (i<N && keys[i].compareTo(key)==0){
            for (int j = i;j<N-1;j++){
                keys[j] = keys[j+1];
                values[j] = values[j+1];
            }
        }
    }

    public boolean contain(Key key){
        int i = rank(key);
        return keys[i].compareTo(key)==0;
    }

    public Iterable<Key> keys(Key lo, Key hi){
        Queue<Key> q = new Queue<>();
        for (int i = rank(lo); i< rank(hi); i++){
            q.enqueue(keys[i]);
        }
        if (contain(hi)){
            q.enqueue(keys[rank(hi)]);
        }
        return q;
    }
}
