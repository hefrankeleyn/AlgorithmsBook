package com.hef.mst.base;

import java.util.Iterator;

/**
 * @author lifei
 * @since 2020/10/28
 */
public class Bag<T> implements Iterable<T> {

    private Node first;
    private int N;

    private class Node{
        T t;
        Node next;
        Node(T t, Node next){
            this.t = t;
            this.next = next;
        }
    }

    public void add(T t){
        Node nt = first;
        first = new Node(t, nt);
        N++;
    }

    public boolean isEmpty(){
        return N==0;
    }

    public int size(){
        return N;
    }


    @Override
    public Iterator<T> iterator() {
        return new MyBagIterator();
    }

    private class MyBagIterator implements Iterator<T>{

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
        Bag<String> bag = new Bag<>();
        bag.add("aaa");
        bag.add("bbb");
        bag.add("ccc");
        bag.add("ddd");
        bag.add("eee");

        for (String item : bag) {
            System.out.println(item);
        }
    }
}
