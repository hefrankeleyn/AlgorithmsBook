package com.hef.chapter2.pq;

/**
 * @author lifei
 * @since 2020/10/8
 */
public class MaxPQ<Key extends Comparable<Key>> {

    private int N = 0;
    private Key[] pq;
    public MaxPQ(){}

    /** create a priority queue of initial capacity max */
    public MaxPQ(int max){
        pq = (Key[]) new Comparable[max+1];
    }

    /** create a priority queue from the keys in a[] */
    public MaxPQ(Key[] a){
        pq = (Key[]) new Comparable[a.length + 1];
        for (int i = 0; i < a.length; i++) {
            insert(a[i]);
        }
    }

    /** insert a key into the priority queue */
    public void insert(Key v){
        pq[++N] = v;
        swim(N);
    }

    /** return the largest key */
    public Key max(){
        return pq[1];
    }

    /** return and remove the largest key */
    public Key delMax(){
        Key m = pq[1];
        exch(1, N--);
        pq[N+1] = null;
        return m;
    }

    /** is the priority queue empty? */
    public boolean isEmpty(){
        return false;
    }

    /** number of keys in the priority queue */
    public int size(){
        return N;
    }



    /** bottom-up restoring heap order */
    private void swim(int k){
        while (k>1 && less(k/2, k)){
            exch(k/2, k);
            k = k/2;
        }
    }

    /** top-down restoring heap order */
    private void sink(int k){
        while (2*k<=N){
            int j = 2*k;
            if (j<N && less(j, j+1)) j++;
            if (!less(k, j)) break;
            exch(k, j);
            k = j;
        }
    }


    private boolean less(int i, int j){
        return pq[i].compareTo(pq[j])<0;
    }

    private void exch(int i, int j){
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }


}
