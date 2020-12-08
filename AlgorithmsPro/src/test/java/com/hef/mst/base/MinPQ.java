package com.hef.mst.base;

/**
 * @author lifei
 * @since 2020/10/28
 */
public class MinPQ<T extends Comparable<T>> {

    private T[] pq;
    private int N;
    private static final int CAPACITY_INIT = 5;
    public MinPQ(){
        pq = (T[]) new Comparable[CAPACITY_INIT+1];
    }

    public void insert(T t){
        if (N == pq.length-1) resize(pq.length * 2);
        pq[++N] = t;
        swim(N);
    }

    public T delMin(){
        if (isEmpty()) return null;
        T min = pq[1];
        exch(1, N--);
        pq[N+1] = null;
        sink(1);
        if (N < pq.length/4) resize(pq.length/2);
        return min;
    }

    private void resize(int size){
        if (size<CAPACITY_INIT) size = CAPACITY_INIT;
        T[] oneTs = (T[]) new Comparable[size];
        for (int i = 0; i <= N; i++) {
            oneTs[i] = pq[i];
        }
        pq = oneTs;
    }

    public boolean isEmpty(){
        return N==0;
    }

    public int size(){
        return N;
    }

    /** up-bottom */
    private void sink(int k){
        while (2*k <=N){
            int j = 2 *k;
            if (j<N && less(j+1, j)) j++;
            exch(j, k);
            k = j;
        }
    }

    /** bottom-up */
    private void swim(int k){
        while (k>1 && less(k, k/2)){
            exch(k, k/2);
            k = k/2;
        }
    }

    private boolean less(int i, int j){
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void exch(int i, int j){
        T t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }

    public static void main(String[] args) {
        MinPQ<String> minPQ = new MinPQ<>();
        minPQ.insert("aaa");
        minPQ.insert("bbb");
        minPQ.insert("eee");
        minPQ.insert("ccc");
        minPQ.insert("zzz");
        minPQ.insert("yyy");
        minPQ.insert("ddd");

        while (!minPQ.isEmpty()){
            System.out.println(minPQ.delMin());
        }

    }
}
