package com.group1;

public interface ITree<T extends Comparable<T>> {

    /**
     * Добавить элемент в дерево
     *
     * @param value
     */
    void add(T value);

    /**
     * Удалить элемент из дерева
     *
     * @param o
     */
    boolean remove(T o);

    /**
     * Возвращает true, если элемент содержится в дереве
     *
     * @param o
     */
    boolean contains(T o);
}