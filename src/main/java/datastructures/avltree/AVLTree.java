package datastructures.avltree;

import java.util.*;

/**
 * A generic AVL Tree implementation.
 *
 * <p>An AVL Tree is a self-balancing Binary Search Tree where the height
 * difference (balance factor) between left and right subtrees is at most 1.
 *
 * <p>This ensures O(log n) time complexity for insert, delete, and search.
 *
 * @param <T> the type of elements stored in the tree
 */
public class AVLTree<T extends Comparable<T>> implements Iterable<T> {

    private class Node {
        T value;
        Node left, right;
        int height;

        Node(T value) {
            this.value = value;
            this.height = 0;
        }
    }

    private Node root;
    private int size;

    /**
     * Inserts a value into the AVL tree.
     *
     * @param value the value to insert
     */
    public void insert(T value) {
        if (value == null) {
            throw new IllegalArgumentException("Value cannot be null");
        }
        root = insert(root, value);
    }

    private Node insert(Node node, T value) {
        if (node == null) {
            size++;
            return new Node(value);
        }

        int cmp = value.compareTo(node.value);

        if (cmp < 0) {
            node.left = insert(node.left, value);
        } else if (cmp > 0) {
            node.right = insert(node.right, value);
        } else {
            return node; // ignore duplicates
        }

        updateHeight(node);
        return rebalance(node);
    }

    /**
     * Checks whether a value exists in the tree.
     */
    public boolean contains(T value) {
        Node current = root;

        while (current != null) {
            int cmp = value.compareTo(current.value);
            if (cmp < 0) current = current.left;
            else if (cmp > 0) current = current.right;
            else return true;
        }
        return false;
    }

    /**
     * Returns the height of the tree.
     */
    public int height() {
        return height(root);
    }

    private int height(Node node) {
        return node == null ? -1 : node.height;
    }

    /**
     * Returns the number of elements in the tree.
     */
    public int size() {
        return size;
    }

    /**
     * In-order traversal (sorted).
     */
    public List<T> inOrder() {
        List<T> result = new ArrayList<>();
        inOrder(root, result);
        return result;
    }

    private void inOrder(Node node, List<T> result) {
        if (node == null) return;
        inOrder(node.left, result);
        result.add(node.value);
        inOrder(node.right, result);
    }

    /**
     * Rebalances the tree at a given node.
     */
    private Node rebalance(Node node) {
        int balance = balanceFactor(node);

        // Left heavy
        if (balance > 1) {
            if (balanceFactor(node.left) < 0) {
                node.left = rotateLeft(node.left); // LR case
            }
            return rotateRight(node); // LL case
        }

        // Right heavy
        if (balance < -1) {
            if (balanceFactor(node.right) > 0) {
                node.right = rotateRight(node.right); // RL case
            }
            return rotateLeft(node); // RR case
        }

        return node;
    }

    /**
     * Left rotation.
     */
    private Node rotateLeft(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        updateHeight(x);
        updateHeight(y);

        return y;
    }

    /**
     * Right rotation.
     */
    private Node rotateRight(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        updateHeight(y);
        updateHeight(x);

        return x;
    }

    /**
     * Updates node height.
     */
    private void updateHeight(Node node) {
        node.height = 1 + Math.max(height(node.left), height(node.right));
    }

    /**
     * Computes balance factor.
     */
    private int balanceFactor(Node node) {
        return height(node.left) - height(node.right);
    }

    /**
     * Validates AVL Tree properties.
     */
    public boolean isValidAVL() {
        return validate(root) != -2;
    }

    private int validate(Node node) {
        if (node == null) return -1;

        int left = validate(node.left);
        int right = validate(node.right);

        if (left == -2 || right == -2) return -2;

        if (Math.abs(left - right) > 1) return -2;

        return 1 + Math.max(left, right);
    }

    /**
     * Returns an iterator (in-order).
     */
    @Override
    public Iterator<T> iterator() {
        return inOrder().iterator();
    }
}
