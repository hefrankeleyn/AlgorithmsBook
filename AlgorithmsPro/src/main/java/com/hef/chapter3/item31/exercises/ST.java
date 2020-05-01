package com.hef.chapter3.item31.exercises;

public interface ST<Key, Value> {

    void put(Key key, Value value);

    Value get(Key key);

    int size();

    boolean isEmpty();

    Iterable<Key> keys();

    void delete(Key key);
}
