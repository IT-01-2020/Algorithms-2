package com.group1;


public class RedBlackTree<T extends Comparable<T>> {
    private RedBlackNode<T> root;
    private RedBlackNode<T> tNull;

    private void preOrderHelper(RedBlackNode<T> node) {
        if (node != tNull) {
            System.out.print(node.getValue() + " ");
            preOrderHelper(node.getLeft());
            preOrderHelper(node.getRight());
        }
    }

    private void inOrderHelper(RedBlackNode<T> node) {
        if (node != tNull) {
            inOrderHelper(node.getLeft());
            System.out.print(node.getValue() + " ");
            inOrderHelper(node.getRight());
        }
    }

    private void postOrderHelper(RedBlackNode<T> node) {
        if (node != tNull) {
            postOrderHelper(node.getLeft());
            postOrderHelper(node.getRight());
            System.out.print(node.getValue() + " ");
        }
    }

    private RedBlackNode<T> searchTreeHelper(RedBlackNode<T> node, T key) {
        if (node == tNull || key == node.getValue()) {
            return node;
        }

        if (key.compareTo(node.getValue()) < 0) {
            return searchTreeHelper(node.getLeft(), key);
        }
        return searchTreeHelper(node.getRight(), key);
    }

    // fix the rb tree modified by the delete operation
    private void fixDelete(RedBlackNode<T> x) {
        RedBlackNode<T> s;
        while (x != root && x.getColor() == 0) {
            if (x == x.getParent().getLeft()) {
                s = x.getParent().getRight();
                if (s.getColor() == 1) {
                    // case 3.1
                    s.setColor(0);
                    x.getParent().setColor(1);
                    leftRotate(x.getParent());
                    s = x.getParent().getRight();
                }

                if (s.getLeft().getColor() == 0 && s.getRight().getColor() == 0) {
                    // case 3.2
                    s.setColor(1);
                    x = x.getParent();
                } else {
                    if (s.getRight().getColor() == 0) {
                        // case 3.3
                        s.getLeft().setColor(0);
                        s.setColor(1);
                        rightRotate(s);
                        s = x.getParent().getRight();
                    }

                    // case 3.4
                    s.setColor(x.getParent().getColor());
                    x.getParent().setColor(0);
                    s.getRight().setColor(0);
                    leftRotate(x.getParent());
                    x = root;
                }
            } else {
                s = x.getParent().getLeft();
                if (s.getColor() == 1) {
                    // case 3.1
                    s.setColor(0);
                    x.getParent().setColor(1);
                    rightRotate(x.getParent());
                    s = x.getParent().getLeft();
                }

                if (s.getRight().getColor() == 0 && s.getRight().getColor() == 0) {
                    // case 3.2
                    s.setColor(1);
                    x = x.getParent();
                } else {
                    if (s.getLeft().getColor() == 0) {
                        // case 3.3
                        s.getRight().setColor(0);
                        s.setColor(1);
                        leftRotate(s);
                        s = x.getParent().getLeft();
                    }

                    // case 3.4
                    s.setColor(x.getParent().getColor());
                    x.getParent().setColor(0);
                    s.getLeft().setColor(0);
                    rightRotate(x.getParent());
                    x = root;
                }
            }
        }
        x.setColor(0);
    }


    private void rbTransplant(RedBlackNode<T> u, RedBlackNode<T> v) {
        if (u.getParent() == null) {
            root = v;
        } else if (u == u.getParent().getLeft()) {
            u.getParent().setLeft(v);
        } else {
            u.getParent().setRight(v);
        }
        v.setParent(u.getParent());
    }

    private void deleteNodeHelper(RedBlackNode<T> node, T key) {
        // find the node containing key
        RedBlackNode<T> z = tNull;
        RedBlackNode<T> x, y;
        while (node != tNull) {
            if (node.getValue() == key) {
                z = node;
            }

            if (node.getValue().compareTo(key) <= 0) {
                node = node.getRight();
            } else {
                node = node.getLeft();
            }
        }

        if (z == tNull) {
            System.out.println("Couldn't find key in the tree");
            return;
        }

        y = z;
        int yOriginalColor = y.getColor();
        if (z.getLeft() == tNull) {
            x = z.getRight();
            rbTransplant(z, z.getRight());
        } else if (z.getRight() == tNull) {
            x = z.getLeft();
            rbTransplant(z, z.getLeft());
        } else {
            y = minimum(z.getRight());
            yOriginalColor = y.getColor();
            x = y.getRight();
            if (y.getParent() == z) {
                x.setParent(y);
            } else {
                rbTransplant(y, y.getRight());
                y.setRight(z.getRight());
                y.getRight().setParent(y);
            }

            rbTransplant(z, y);
            y.setLeft(z.getLeft());
            y.getLeft().setParent(y);
            y.setColor(z.getColor());
        }
        if (yOriginalColor == 0) {
            fixDelete(x);
        }
    }

    // fix the red-black tree
    private void fixInsert(RedBlackNode<T> k) {
        RedBlackNode<T> u;
        while (k.getParent().getColor() == 1) {
            if (k.getParent() == k.getParent().getParent().getRight()) {
                u = k.getParent().getParent().getLeft(); // uncle
                if (u.getColor() == 1) {
                    // case 3.1
                    u.setColor(0);
                    k.getParent().setColor(0);
                    k.getParent().getParent().setColor(1);
                    k = k.getParent().getParent();
                } else {
                    if (k == k.getParent().getLeft()) {
                        // case 3.2.2
                        k = k.getParent();
                        rightRotate(k);
                    }
                    // case 3.2.1
                    k.getParent().setColor(0);
                    k.getParent().getParent().setColor(1);
                    leftRotate(k.getParent().getParent());
                }
            } else {
                u = k.getParent().getParent().getRight(); // uncle

                if (u.getColor() == 1) {
                    // mirror case 3.1
                    u.setColor(0);
                    k.getParent().setColor(0);
                    k.getParent().getParent().setColor(1);
                    k = k.getParent().getParent();
                } else {
                    if (k == k.getParent().getRight()) {
                        // mirror case 3.2.2
                        k = k.getParent();
                        leftRotate(k);
                    }
                    // mirror case 3.2.1
                    k.getParent().setColor(0);
                    k.getParent().getParent().setColor(1);
                    rightRotate(k.getParent().getParent());
                }
            }
            if (k == root) {
                break;
            }
        }
        root.setColor(0);
    }

    private void printHelper(RedBlackNode<T> root, String indent, boolean last) {
        // print the tree structure on the screen
        if (root != tNull) {
            System.out.print(indent);
            if (last) {
                System.out.print("R----");
                indent += "     ";
            } else {
                System.out.print("L----");
                indent += "|    ";
            }

            String sColor = root.getColor() == 1 ? "RED" : "BLACK";
            System.out.println(root.getValue() + "(" + sColor + ")");
            printHelper(root.getLeft(), indent, false);
            printHelper(root.getRight(), indent, true);
        }
    }

    public RedBlackTree() {
        tNull = new RedBlackNode<T>();
        tNull.setColor(0);
        tNull.setLeft(null);
        tNull.setRight(null);
        root = tNull;
    }

    // Pre-Order traversal
    // Node<T>.Left Subtree.Right Subtree
    public void preorder() {
        preOrderHelper(this.root);
    }

    // In-Order traversal
    // Left Subtree . Node<T> . Right Subtree
    public void inorder() {
        inOrderHelper(this.root);
    }

    // Post-Order traversal
    // Left Subtree . Right Subtree . Node<T>
    public void postorder() {
        postOrderHelper(this.root);
    }

    // search the tree for the key k
    // and return the corresponding node
    public RedBlackNode<T> searchTree(T k) {
        return searchTreeHelper(this.root, k);
    }

    // find the node with the minimum key
    public RedBlackNode<T> minimum(RedBlackNode<T> node) {
        while (node.getLeft() != tNull) {
            node = node.getLeft();
        }
        return node;
    }

    // find the node with the maximum key
    public RedBlackNode<T> maximum(RedBlackNode<T> node) {
        while (node.getRight() != tNull) {
            node = node.getRight();
        }
        return node;
    }

    // find the successor of a given node
    public RedBlackNode<T> successor(RedBlackNode<T> x) {
        // if the right subtree is not null,
        // the successor is the leftmost node in the
        // right subtree
        if (x.getRight() != tNull) {
            return minimum(x.getRight());
        }

        // else it is the lowest ancestor of x whose
        // left child is also an ancestor of x.
        RedBlackNode<T> y = x.getParent();
        while (y != tNull && x == y.getRight()) {
            x = y;
            y = y.getParent();
        }
        return y;
    }

    // find the predecessor of a given node
    public RedBlackNode<T> predecessor(RedBlackNode<T> x) {
        // if the left subtree is not null,
        // the predecessor is the rightmost node in the
        // left subtree
        if (x.getLeft() != tNull) {
            return maximum(x.getLeft());
        }

        RedBlackNode<T> y = x.getParent();
        while (y != tNull && x == y.getLeft()) {
            x = y;
            y = y.getParent();
        }

        return y;
    }

    // rotate left at node x
    public void leftRotate(RedBlackNode<T> x) {
        RedBlackNode<T> y = x.getRight();
        x.setRight(y.getLeft());
        if (y.getLeft() != tNull) {
            y.getLeft().setParent(x);
        }
        y.setParent(x.getParent());
        if (x.getParent() == null) {
            this.root = y;
        } else if (x == x.getParent().getLeft()) {
            x.getParent().setLeft(y);
        } else {
            x.getParent().setRight(y);
        }
        y.setLeft(x);
        x.setParent(y);
    }

    // rotate right at node x
    public void rightRotate(RedBlackNode<T> x) {
        RedBlackNode<T> y = x.getLeft();
        x.setLeft(y.getRight());
        if (y.getRight() != tNull) {
            y.getRight().setParent(x);
        }
        y.setParent(x.getParent());
        if (x.getParent() == null) {
            this.root = y;
        } else if (x == x.getParent().getRight()) {
            x.getParent().setRight(y);
        } else {
            x.getParent().setLeft(y);
        }
        y.setRight(x);
        x.setParent(y);
    }

    // insert the key to the tree in its appropriate position
    // and fix the tree
    public void insert(T key) {
        // Ordinary Binary Search Insertion
        RedBlackNode<T> node = new RedBlackNode<T>();
        node.setParent(null);
        node.setValue(key);
        node.setLeft(tNull);
        node.setRight(tNull);
        node.setColor(1); // new node must be red

        RedBlackNode<T> y = null;
        RedBlackNode<T> x = this.root;

        while (x != tNull) {
            y = x;
            if (node.getValue().compareTo(x.getValue()) < 0) {
                x = x.getLeft();
            } else {
                x = x.getRight();
            }
        }

        // y is parent of x
        node.setParent(y);
        if (y == null) {
            root = node;
        } else if (node.getValue().compareTo(y.getValue()) < 0) {
            y.setLeft(node);
        } else {
            y.setRight(node);
        }

        // if new node is a root node, simply return
        if (node.getParent() == null) {
            node.setColor(0);
            return;
        }

        // if the grandparent is null, simply return
        if (node.getParent().getParent() == null) {
            return;
        }

        // Fix the tree
        fixInsert(node);
    }

    public RedBlackNode<T> getRoot() {
        return this.root;
    }

    // delete the node from the tree
    public void deleteNode(T data) {
        deleteNodeHelper(this.root, data);
    }

    // print the tree structure on the screen
    public void prettyPrint() {
        printHelper(this.root, "", true);
    }

}