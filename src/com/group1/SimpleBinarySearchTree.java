package com.group1;

import java.util.ArrayList;

class SimpleBinarySearchTree<T extends Comparable<T>> implements MyTree<T> {
    private Node<T> rootNode;

    @Override
    public void insert(T value) {
        if (rootNode == null) {
            rootNode = new Node<T>(value, null, null, null);
        } else {
            Node<T> currentRoot = rootNode;

            while (true) {
                if (value.compareTo(currentRoot.getValue()) == 0) {
                    // replace value
                    currentRoot.setValue(value);
                    break;
                } else if (value.compareTo(currentRoot.getValue()) < 0) {
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

    // Hibbard deletion
    @Override
    public void delete(T value) {
        Node<T> currentRoot = rootNode;

        while (true) {
            if (currentRoot == null) {
                return;
            } else if (currentRoot.getValue().equals(value)) {
                // found a needed node
                Node<T> myParent = currentRoot.getParent();
                if (currentRoot.getLeftNode() == null && currentRoot.getRightNode() == null) {
                    // had no children
                    replaceNodeFrom(myParent, currentRoot, null);
                } else if (currentRoot.getLeftNode() == null) {
                    // had no left child. Replace itself with its right child
                    replaceNodeFrom(myParent, currentRoot, currentRoot.getRightNode());
                } else if (currentRoot.getRightNode() == null) {
                    // had no right child. Replace itself with its left child
                    replaceNodeFrom(myParent, currentRoot, currentRoot.getLeftNode());
                } else {
                    // had both children. Replace itself with the the smallest node of its right children
                    Node<T> toReplaceWith = getLeftMost(currentRoot.getRightNode());
                    delete(toReplaceWith.getValue());
                    replaceNodeFrom(myParent, currentRoot, toReplaceWith);
                    // update links
                    transferChildren(currentRoot, toReplaceWith);
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
        }
    }

    public Iterable<T> getKeys() {
        ArrayList<T> list = new ArrayList<>();
        inOrder(rootNode, list);
        return list;
    }

    private void inOrder(Node<T> node, ArrayList<T> queue) {
        if (node == null) {
            return;
        }

        inOrder(node.getLeftNode(), queue);
        queue.add(node.getValue());
        inOrder(node.getRightNode(), queue);
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

    /**
     * Replace a nodeToReplace with nodeToReplaceWith, while keeping nodeToReplaceWith's children
     */
    private void replaceNodeFrom(Node<T> parent, Node<T> nodeToReplace, Node<T> nodeToReplaceWith) {
        if (nodeToReplace.getValue() == rootNode.getValue()) {
            // was a root
            rootNode = nodeToReplaceWith;
        } else if (parent.getLeftNode() != null && parent.getLeftNode().getValue() == nodeToReplace.getValue()) {
            parent.setLeftNode(nodeToReplaceWith);
        } else if (parent.getRightNode() != null && parent.getRightNode().getValue() == nodeToReplace.getValue()) {
            parent.setRightNode(nodeToReplaceWith);
        }
        // update parent link
        if (nodeToReplaceWith != null) {
            nodeToReplace.setParent(parent);
        }
    }

    private void transferChildren(Node<T> from, Node<T> to) {
        if (from.getLeftNode() != null) {
            to.setLeftNode(from.getLeftNode());
            from.getLeftNode().setParent(to);
        }
        if (from.getRightNode() != null) {
            to.setRightNode(from.getRightNode());
            from.getRightNode().setParent(to);
        }
    }
}