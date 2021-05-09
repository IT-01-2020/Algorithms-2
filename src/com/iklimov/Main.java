package com.iklimov;

import java.util.Random;

public class Main {

    private static final Random random = new Random();

    public static void main(String[] args) {
        checkInsertionPerformance();
        checkSearchPerformance();
        checkDeletionPerformance();

    }

    private static void checkDeletionPerformance() {
        SimpleBinarySearchTree<Integer> simpleTree = new SimpleBinarySearchTree<>();

        for (int i = 0; i < 100_000; i++) {
            simpleTree.insert(random.nextInt(1_000_000));
        }
        long start = System.nanoTime();
        simpleTree.delete(random.nextInt(1_000_000));
        System.out.println("Simple delete took ms: " + (System.nanoTime() - start));


        RedBlackTree<Integer> betterTree = new RedBlackTree<>();

        for (int i = 0; i < 100_000; i++) {
            betterTree.insert(random.nextInt(1_000_000));
        }
        start = System.nanoTime();
        betterTree.deleteNode(random.nextInt(1_000_000));
        System.out.println("Better delete took ms: " + (System.nanoTime() - start));
    }

    private static void checkSearchPerformance() {
        SimpleBinarySearchTree<Integer> simpleTree = new SimpleBinarySearchTree<>();

        for (int i = 0; i < 100_000; i++) {
            simpleTree.insert(random.nextInt(1_000_000));
        }
        long start = System.nanoTime();
        boolean contains = simpleTree.contains(5);
        System.out.println("Simple contains took ms: " + (System.nanoTime() - start));


        RedBlackTree<Integer> betterTree = new RedBlackTree<>();

        for (int i = 0; i < 100_000; i++) {
            betterTree.insert(random.nextInt(1_000_000));
        }
        start = System.nanoTime();
        contains = betterTree.searchTree(random.nextInt(1_000_000)) != null;
        System.out.println("Better contains took ms: " + (System.nanoTime() - start));
    }

    private static void checkInsertionPerformance() {
        long start = System.nanoTime();
        SimpleBinarySearchTree<Integer> simpleTree = new SimpleBinarySearchTree<>();

        for (int i = 0; i < 100_000; i++) {
            simpleTree.insert(random.nextInt(1_000_000));
        }
        System.out.println("Simple insertion took ms: " + (System.nanoTime() - start));


        start = System.nanoTime();
        RedBlackTree<Integer> betterTree = new RedBlackTree<>();

        for (int i = 0; i < 100_000; i++) {
            betterTree.insert(random.nextInt(1_000_000));
        }
        System.out.println("Better insertion took ms: " + (System.nanoTime() - start));
    }
}
