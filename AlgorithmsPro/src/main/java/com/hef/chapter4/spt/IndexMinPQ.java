package com.hef.chapter4.spt;

import com.hef.commons.In;

/**
 * 带索引带最小优先级队列
 * @author lifei
 * @since 2020/12/13
 */
public class IndexMinPQ<T extends Comparable<T>> {

    /** pq[i]=k */
    private int[] pq;

    /** qp[k] = i
     * pq[qp[k]] = i
     */
    private int[] qp;

    /** value[k] = t */
    private T[] values;

    private int N;


    public IndexMinPQ(int V){
        pq = new int[V+1];
        qp = new int[V+1];
        values = (T[]) new Comparable[V+1];
        for (int i = 0; i < qp.length; i++) {
            qp[i] = -1;
        }
    }

    public void insert(int k, T t){
        pq[++N] = k;
        qp[k] = N;
        values[k] = t;
        swim(N);
    }

    public T min(){
        if (isEmpty()) return null;
        return values[pq[1]];
    }

    public int delMin(){
        int k = pq[1];
        exch(pq[1], pq[N--]);
        sink(1);
        values[pq[N+1]] = null;
        qp[pq[N+1]] = -1;
        return k;
    }

    public int index(int k){
        return pq[k];
    }

    public T item(int v){
        return values[v];
    }

    public void exchange(int v, T t){
        values[v] = t;
        sink(qp[v]);
        swim(qp[v]);
    }

    public boolean isEmpty(){
        return N==0;
    }

    public boolean contains(int k){
        return qp[k] != -1;
    }

    /**
     * Top-bottom
     */
    private void sink(int k){
        while (2*k<=N){
            int j = 2*k;
            if (j<N && less(pq[j+1], pq[j])) j++;
            exch(pq[k], pq[j]);
            k = j;
        }
    }

    /**
     * bottom-top
     */
    private void swim(int k){
        while (k>1 && less(pq[k], pq[k/2])){
            exch(pq[k], pq[k/2]);
            k = k/2;
        }
    }

    private boolean less(int i, int j){
        return values[i].compareTo(values[j])<0;
    }

    private void exch(int i, int j){
        int temp = qp[i];
        qp[i] = qp[j];
        qp[j] = temp;

        pq[qp[i]] = i;
        pq[qp[j]] = j;
    }


    public static void main(String[] args) {
        IndexMinPQ<String> pq = new IndexMinPQ<>(5);
        pq.insert(0, "aa");
        pq.insert(3, "cc");
        pq.insert(2, "bb");
        pq.insert(1, "ff");

        while (!pq.isEmpty()){
            String min = pq.min();
            System.out.println(pq.delMin() + " : " + min);
        }

    }
}
