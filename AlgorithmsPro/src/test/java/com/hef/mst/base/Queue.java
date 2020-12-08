package com.hef.mst.base;

import java.util.Iterator;

/**
 * @author lifei
 * @since 2020/10/28
 */
public class Queue<T> implements Iterable<T> {

    private Node first;
    private Node last;
    private int N;

    private class Node{
        T t;
        Node next;
        Node(T t, Node next){
            this.t = t;
            this.next = next;
        }
    }

    public void enqueue(T t){
        if (isEmpty()){
            first = new Node(t, null);
            last = first;
        }else {
            Node nt = new Node(t, null);
            last.next = nt;
            last = nt;
        }
        N++;
    }

    public T dequeue(){
        if (isEmpty()) return null;
        Node tf = first;
        first = first.next;
        return tf.t;
    }

    public int size(){
        return N;
    }

    public boolean isEmpty(){
        return N==0;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyQueueIterator();
    }

    private class MyQueueIterator implements Iterator<T>{

        private Node currentNode = first;

        @Override
        public boolean hasNext() {
            return currentNode!=null;
        }

        @Override
        public T next() {
            Node tn = currentNode;
            currentNode = currentNode.next;
            return tn.t;
        }
    }

    public static void main(String[] args) {
        Queue<String> queue = new Queue<>();
        queue.enqueue("a");
        queue.enqueue("e");
        queue.enqueue("b");
        queue.enqueue("d");
        queue.enqueue("c");
        queue.enqueue("f");

        for (String item : queue) {
            System.out.println(item);
        }
    }
}
