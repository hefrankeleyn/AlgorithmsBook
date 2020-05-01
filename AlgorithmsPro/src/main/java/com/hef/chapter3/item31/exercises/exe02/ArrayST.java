package com.hef.chapter3.item31.exercises.exe02;

import com.hef.algorithms.fundamentals.bags_queues_stacks.Queue;
import com.hef.chapter3.item31.exercises.ST;

/**
 * @Date 2020/5/1
 * @Author lifei
 */
public class ArrayST<Key, Value> implements ST<Key,Value> {

    private static int initCapacity= 8;

    private Key[] keys;
    private Value[] values;
    private int N;

    public ArrayST(){
        keys = (Key[]) new Object[initCapacity];
        values = (Value[]) new Object[initCapacity];
    }
    @Override
    public void put(Key key, Value value) {
        delete(key);
        if (N == keys.length){
            resize(keys.length * 2);
        }
        keys[N] = key;
        values[N] = value;
        ++N;
    }

    private void resize(int capacity){
        Key[] aKeys = (Key[]) new Object[capacity];
        Value[] aValues = (Value[]) new Object[capacity];
        for (int i = 0; i < N && i < capacity; i++) {
            aKeys[i] = keys[i];
            aValues[i] = values[i];
        }
        keys = aKeys;
        values = aValues;
    }

    @Override
    public Value get(Key key) {
        for (int i = 0; i < N; i++) {
            if (keys[i].equals(key)){
                return values[i];
            }
        }
        return null;
    }

    @Override
    public int size() {
        return N;
    }

    @Override
    public boolean isEmpty() {
        return N == 0;
    }

    @Override
    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<>();
        for (int i = 0; i < N; i++) {
            queue.enqueue(keys[i]);
        }
        return queue;
    }

    @Override
    public void delete(Key key) {
        for (int i = 0; i < N; i++) {
            if (keys[i].equals(key)){
                keys[i] = keys[N-1];
                values[i] = values[N-1];
                keys[N-1] = null;
                values[N-1] = null;
                --N;
                if (N>0 && N < keys.length/4) {
                    resize(keys.length/2);
                }
                return;
            }
        }
    }
}
