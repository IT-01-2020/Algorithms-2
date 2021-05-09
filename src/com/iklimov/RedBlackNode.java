package com.iklimov;

public class RedBlackNode<T> {
    private T value;
    private RedBlackNode<T> parent;
    private RedBlackNode<T> left;
    private RedBlackNode<T> right;
    private int color; // 1 . Red, 0 . Black

    public RedBlackNode() {

    }

    public RedBlackNode(T value, RedBlackNode<T> parent, RedBlackNode<T> left, RedBlackNode<T> right, int color) {
        this.value = value;
        this.parent = parent;
        this.left = left;
        this.right = right;
        this.color = color;
    }

    public T getValue() {
        return value;
    }

    public RedBlackNode<T> setValue(T value) {
        this.value = value;
        return this;
    }

    public RedBlackNode<T> getParent() {
        return parent;
    }

    public RedBlackNode<T> setParent(RedBlackNode<T> parent) {
        this.parent = parent;
        return this;
    }

    public RedBlackNode<T> getLeft() {
        return left;
    }

    public RedBlackNode<T> setLeft(RedBlackNode<T> left) {
        this.left = left;
        return this;
    }

    public RedBlackNode<T> getRight() {
        return right;
    }

    public RedBlackNode<T> setRight(RedBlackNode<T> right) {
        this.right = right;
        return this;
    }

    public int getColor() {
        return color;
    }

    public RedBlackNode<T> setColor(int color) {
        this.color = color;
        return this;
    }
}