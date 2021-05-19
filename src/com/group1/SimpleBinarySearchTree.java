package com.group1;

import java.util.ArrayList;

class SimpleBinarySearchTree<T extends Comparable<T>> implements ITree<T> {
    private Node<T> rootNode;

    @Override
    public void add(T value) {
        if (rootNode == null) {
            rootNode = new Node<T>(value, null, null, null);
        } else {
            Node<T> currentRoot = rootNode;

            while (true) {
                if (value.compareTo(currentRoot.getValue()) == 0) {
                    // заменить значение
                    currentRoot.setValue(value);
                    break;
                } else if (value.compareTo(currentRoot.getValue()) < 0) {
                    // идем налово
                    if (currentRoot.getLeftNode() == null) {
                        // вставить
                        Node<T> newNode = new Node<>(value, null, null, currentRoot);
                        currentRoot.setLeftNode(newNode);
                        break;
                    } else {
                        currentRoot = currentRoot.getLeftNode();
                    }
                } else {
                    // идем направо
                    if (currentRoot.getRightNode() == null) {
                        // вставить
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

    // Хиббард удаление
    @Override
    public boolean remove(T value) {
        Node<T> currentRoot = rootNode;

        while (true) {
            if (currentRoot == null) {
                return false;
            } else if (currentRoot.getValue().equals(value)) {
                // нашел нужную ячейку
                Node<T> myParent = currentRoot.getParent();
                if (currentRoot.getLeftNode() == null && currentRoot.getRightNode() == null) {
                    // не было детей
                    replaceNodeFrom(myParent, currentRoot, null);
                } else if (currentRoot.getLeftNode() == null) {
                    // не было левого ребенка. Заменить себя с правым ребенком
                    replaceNodeFrom(myParent, currentRoot, currentRoot.getRightNode());
                } else if (currentRoot.getRightNode() == null) {
                    // не было правого ребенка. Заменить себя с левым ребенком
                    replaceNodeFrom(myParent, currentRoot, currentRoot.getLeftNode());
                } else {
                    // имел оба ребенка. Заменяем сeбя с самой маленькой ячейкой с правых детей
                    Node<T> toReplaceWith = getLeftMost(currentRoot.getRightNode());
                    remove(toReplaceWith.getValue());
                    replaceNodeFrom(myParent, currentRoot, toReplaceWith);
                    // обновить ссылки
                    transferChildren(currentRoot, toReplaceWith);
                }

                return true;
            } else if (value.compareTo(currentRoot.getValue()) < 0) {
                // идем налево
                currentRoot = currentRoot.getLeftNode();
            } else {
                // идем направо
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
                // идем налево
                currentRoot = currentRoot.getLeftNode();
            } else {
                // идем направо
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
     *
     * Заменяем nodeToReplace с nodeToReplaceWith, сохраняем nodeToReplaceWith родителя
     */
    private void replaceNodeFrom(Node<T> parent, Node<T> nodeToReplace, Node<T> nodeToReplaceWith) {
        if (nodeToReplace.getValue() == rootNode.getValue()) {
            // был корень
            rootNode = nodeToReplaceWith;
        } else if (parent.getLeftNode() != null && parent.getLeftNode().getValue() == nodeToReplace.getValue()) {
            parent.setLeftNode(nodeToReplaceWith);
        } else if (parent.getRightNode() != null && parent.getRightNode().getValue() == nodeToReplace.getValue()) {
            parent.setRightNode(nodeToReplaceWith);
        }
        // обновить родителькую ссылку
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
