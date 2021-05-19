package com.group1;

interface MyTree<T extends Comparable<T>> {

  /**
   * Добавить элемент в дерево
   *
   * @param value
   */
    void insert(T value);

    /**
     * Удалить элемент из дерева
     *
     * @param value
     */
    void delete(T value);

    /**
     * Возвращает true, если элемент содержится в дереве
     *
     * @param value
     */
    boolean contains(T value);

}
