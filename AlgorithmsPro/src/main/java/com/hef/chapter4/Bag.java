package com.hef.chapter4;

import java.util.Iterator;

/**
 * @author lifei
 * @since 2020/7/2
 */
public class Bag<T> implements Iterable<T>{

    private Node first;
    private int N;

    private class Node{
        T t;
        Node next;
    }

    public void add(T t){
        Node oldFirst = first;
        first = new Node();
        first.t = t;
        first.next = oldFirst;
        N++;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyBagIterator();
    }

    private class MyBagIterator implements Iterator<T>{

        private Node current = first;

        @Override
        public boolean hasNext() {
            return current!=null;
        }

        @Override
        public T next() {
            T t = current.t;
            current = current.next;
            return t;
        }
    }


}
