package com.group1;

class Node<T extends Comparable<T>> {
    private T value;
    private Node<T> parent;
    private Node<T> leftNode;
    private Node<T> rightNode;

    public Node(T value, Node<T> leftNode, Node<T> rightNode, Node<T> parent) {
        this.value = value;
        this.leftNode = leftNode;
        this.rightNode = rightNode;
        this.parent = parent;
    }

    public T getValue() {
        return value;
    }

    public Node<T> setValue(T value) {
        this.value = value;
        return this;
    }

    public Node<T> getLeftNode() {
        return leftNode;
    }

    public Node<T> setLeftNode(Node<T> leftNode) { //Установка ссылки на левого потомка
        this.leftNode = leftNode;
        return this;
    }

    public Node<T> getRightNode() { //Получение ссылки на правого потомка
        return rightNode;
    }

    public Node<T> setRightNode(Node<T> rightNode) { //Установка ссылки на правого потомка
        this.rightNode = rightNode;
        return this;
    }

    public Node<T> getParent() { //Получение ссылки на предка
        return parent;
    }

    public Node<T> setParent(Node<T> parent) { // Установка ссылки на предка
        this.parent = parent;
        return this;
    }
}
