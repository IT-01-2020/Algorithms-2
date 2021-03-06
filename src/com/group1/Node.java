package com.group1;

class Node<T extends Comparable<T>> {
    private T value;
    private Node<T> parent; // Родитель
    private Node<T> leftNode; // Левый потомок
    private Node<T> rightNode; //Правый потомок

    public Node(T value, Node<T> leftNode, Node<T> rightNode, Node<T> parent) {
        this.value = value; // Выбранное Значение
        this.leftNode = leftNode; //Выбранный левый потомок
        this.rightNode = rightNode; // Выбранный правый потомок
        this.parent = parent;// Выбранный родитель
    }

    public T getValue() {
        return value;
    } // Получить значение этого элемента

    public Node<T> setValue(T value) { //Установка значения данного элемента
        this.value = value;
        return this;
    }

    public Node<T> getLeftNode() { // Получение ссылки на левого потомка
        return leftNode;
    }

    public Node<T> setLeftNode(Node<T> leftNode) { // Установка ссылки на левого потомка
        this.leftNode = leftNode;
        return this;
    }

    public Node<T> getRightNode() { // Получение ссылки на правого потомка
        return rightNode;
    }

    public Node<T> setRightNode(Node<T> rightNode) { // Установка ссылки на правого потомка
        this.rightNode = rightNode;
        return this;
    }

    public Node<T> getParent() { // Получение ссылки на предка
        return parent;
    }

    public Node<T> setParent(Node<T> parent) { // Установка ссылки на предка
        this.parent = parent;
        return this;
    }
}
