package com.group1;

interface MyTree<T extends Comparable<T>> {
    void insert(T value);

    void delete(T value);

    boolean contains(T value);

}
