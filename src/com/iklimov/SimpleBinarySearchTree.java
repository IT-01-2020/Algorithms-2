package com.iklimov;

class SimpleBinarySearchTree<T extends Comparable<T>> implements MyTree<T> {
    private Node<T> rootNode;

    @Override
    public void insert(T value) {
        if (rootNode == null) {
            rootNode = new Node<T>(value, null, null, null);
        } else {
            Node<T> currentRoot = rootNode;

            while (true) {
                if (value.compareTo(currentRoot.getValue()) < 0) {
                    // go left
                    if (currentRoot.getLeftNode() == null) {
                        // insert
                        Node<T> newNode = new Node<>(value, null, null, currentRoot);
                        currentRoot.setLeftNode(newNode);
                        break;
                    } else {
                        currentRoot = currentRoot.getLeftNode();
                    }
                } else {
                    // go right
                    if (currentRoot.getRightNode() == null) {
                        // insert
                        Node<T> newNode = new Node<>(value, null, null, currentRoot);
                        currentRoot.setRightNode(newNode);
                        break;
                    } else {
                        currentRoot = currentRoot.getRightNode();
                    }
                }
            }
        }
    }

    @Override
    public void delete(T value) {
        Node<T> currentRoot = rootNode;

        while (true) {
            if (currentRoot == null) {
                return;
            } else if (currentRoot.getValue().equals(value)) {

                // found a needed node
                Node<T> myParent = currentRoot.getParent();
                if (myParent == null) {
                    // was a root node
                    if (currentRoot.getLeftNode() == null && currentRoot.getRightNode() == null) {
                        // had no children
                        rootNode = null;
                    } else if (currentRoot.getLeftNode() == null) {
                        // had no left node
                        rootNode = currentRoot.getRightNode();
                        rootNode.setParent(null);
                    } else if (currentRoot.getRightNode() == null) {
                        // had no right node
                        rootNode = currentRoot.getLeftNode();
                        rootNode.setParent(null);
                    } else if (currentRoot.getLeftNode() != null && currentRoot.getRightNode() != null) {
                        // had both children
                        // make a left node new root
                        rootNode = currentRoot.getLeftNode();
                        rootNode.setParent(null);
                        // place the right node to the very bottom of a new root
                        Node<T> rightNode = currentRoot.getRightNode();
                        getRightMostNode(rootNode).setRightNode(rightNode);
                        rightNode.setParent(getRightMostNode(rootNode));
                    }
                } else if (myParent.getLeftNode() != null && myParent.getLeftNode().getValue().equals(currentRoot.getValue())) {
                    // was a left node
                    if (currentRoot.getRightNode() != null) {
                        myParent.setLeftNode(currentRoot.getRightNode());
                    } else {
                        myParent.setLeftNode(currentRoot.getLeftNode());
                    }
                } else if (myParent.getRightNode() != null && myParent.getRightNode().getValue().equals(currentRoot.getValue())) {
                    // was a right node
                    if (currentRoot.getLeftNode() != null) {
                        myParent.setRightNode(currentRoot.getLeftNode());
                    } else {
                        myParent.setRightNode(currentRoot.getRightNode());
                    }
                }
                return;
            } else if (value.compareTo(currentRoot.getValue()) < 0) {
                // go left
                currentRoot = currentRoot.getLeftNode();
            } else {
                // go right
                currentRoot = currentRoot.getRightNode();
            }
        }
    }

    @Override
    public boolean contains(T value) {
        Node<T> currentRoot = rootNode;

        while (true) {
            if (currentRoot == null) {
                return false;
            } else if (currentRoot.getValue().equals(value)) {
                return true;
            } else if (value.compareTo(currentRoot.getValue()) < 0) {
                // go left
                currentRoot = currentRoot.getLeftNode();
            } else {
                // go right
                currentRoot = currentRoot.getRightNode();
            }
            loopsMade++;
        }
    }

    private Node<T> getRightMostNode(Node<T> node) {
        Node<T> currentNode = node;

        while (true) {
            if (currentNode.getRightNode() == null) {
                return currentNode;
            } else {
                currentNode = currentNode.getRightNode();
            }
        }
    }

    private Node<T> getLeftMost(Node<T> node) {
        Node<T> currentNode = node;

        while (true) {
            if (currentNode.getLeftNode() == null) {
                return currentNode;
            } else {
                currentNode = currentNode.getLeftNode();
            }
        }
    }

}


