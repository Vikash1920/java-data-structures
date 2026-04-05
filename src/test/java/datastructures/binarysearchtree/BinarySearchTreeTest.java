package datastructures.binarysearchtree;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTreeTest {

    @Test
    void testEmptyTree() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();

        assertEquals(0, tree.size());
        assertFalse(tree.contains(10));
        assertEquals(List.of(), tree.inOrder());
    }

    @Test
    void testInsertAndContains() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();

        tree.insert(5);
        tree.insert(3);
        tree.insert(7);

        assertTrue(tree.contains(5));
        assertTrue(tree.contains(3));
        assertTrue(tree.contains(7));
        assertFalse(tree.contains(99));
    }

    @Test
    void testDuplicateIgnored() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();

        tree.insert(5);
        tree.insert(5);

        assertEquals(1, tree.size());
        assertEquals(List.of(5), tree.inOrder());
    }

    @Test
    void testInOrderSorted() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();

        List<Integer> values = List.of(5, 2, 8, 1, 3, 7);
        values.forEach(tree::insert);

        List<Integer> sorted = new ArrayList<>(values);
        Collections.sort(sorted);

        assertEquals(sorted, tree.inOrder());
    }

    // ---- DELETE TESTS ----

    @Test
    void testDeleteLeaf() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();

        tree.insert(5);
        tree.insert(3);
        tree.insert(7);

        tree.delete(3);

        assertFalse(tree.contains(3));
        assertEquals(2, tree.size());
        assertEquals(List.of(5, 7), tree.inOrder());
    }

    @Test
    void testDeleteNodeWithOneChild() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();

        tree.insert(5);
        tree.insert(3);
        tree.insert(2);

        tree.delete(3);

        assertFalse(tree.contains(3));
        assertEquals(2, tree.size());
        assertEquals(List.of(2, 5), tree.inOrder());
    }

    @Test
    void testDeleteNodeWithTwoChildren() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();

        tree.insert(5);
        tree.insert(3);
        tree.insert(7);
        tree.insert(6);
        tree.insert(8);

        tree.delete(5);

        // THIS WILL FAIL WITH YOUR CURRENT IMPLEMENTATION
        assertEquals(4, tree.size());

        assertFalse(tree.contains(5));
        assertEquals(List.of(3, 6, 7, 8), tree.inOrder());
    }

    @Test
    void testDeleteNonExisting() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();

        tree.insert(5);
        tree.insert(3);

        tree.delete(99);

        assertEquals(2, tree.size());
        assertEquals(List.of(3, 5), tree.inOrder());
    }

    // ---- STRUCTURE VALIDATION ----

    @Test
    void testBSTInvariantAfterOperations() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        Random rand = new Random(42);

        Set<Integer> inserted = new HashSet<>();

        for (int i = 0; i < 500; i++) {
            int val = rand.nextInt(1000);
            tree.insert(val);
            inserted.add(val);
        }

        for (int i = 0; i < 200; i++) {
            int val = rand.nextInt(1000);
            tree.delete(val);
            inserted.remove(val);
        }

        List<Integer> expected = new ArrayList<>(inserted);
        Collections.sort(expected);

        assertEquals(expected, tree.inOrder());
        assertEquals(inserted.size(), tree.size());
    }

    @Test
    void testNullInsertThrows() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();

        assertThrows(IllegalArgumentException.class, () -> tree.insert(null));
    }
}