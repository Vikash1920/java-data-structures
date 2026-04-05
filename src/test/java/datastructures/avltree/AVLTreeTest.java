package datastructures.avltree;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class AVLTreeTest {

    @Test
    void testEmptyTree() {
        AVLTree<Integer> tree = new AVLTree<>();

        assertEquals(0, tree.size());
        assertEquals(-1, tree.height());
        assertFalse(tree.contains(10));
        assertTrue(tree.isValidAVL());
        assertTrue(tree.inOrder().isEmpty());
    }

    @Test
    void testSingleInsert() {
        AVLTree<Integer> tree = new AVLTree<>();

        tree.insert(10);

        assertEquals(1, tree.size());
        assertTrue(tree.contains(10));
        assertEquals(0, tree.height());
        assertTrue(tree.isValidAVL());
    }

    @Test
    void testDuplicateInsertIgnored() {
        AVLTree<Integer> tree = new AVLTree<>();

        tree.insert(10);
        tree.insert(10);

        assertEquals(1, tree.size());
        assertEquals(List.of(10), tree.inOrder());
    }

    @Test
    void testInOrderSorted() {
        AVLTree<Integer> tree = new AVLTree<>();

        List<Integer> values = List.of(5, 3, 8, 1, 4, 7, 10);
        values.forEach(tree::insert);

        List<Integer> sorted = new ArrayList<>(values);
        Collections.sort(sorted);

        assertEquals(sorted, tree.inOrder());
    }

    @Test
    void testContains() {
        AVLTree<Integer> tree = new AVLTree<>();

        tree.insert(5);
        tree.insert(3);
        tree.insert(8);

        assertTrue(tree.contains(5));
        assertTrue(tree.contains(3));
        assertTrue(tree.contains(8));
        assertFalse(tree.contains(99));
    }

    // ---- ROTATION TESTS ----

    @Test
    void testLLRotation() {
        AVLTree<Integer> tree = new AVLTree<>();

        tree.insert(30);
        tree.insert(20);
        tree.insert(10); // triggers LL

        assertTrue(tree.isValidAVL());
        assertEquals(List.of(10, 20, 30), tree.inOrder());
    }

    @Test
    void testRRRotation() {
        AVLTree<Integer> tree = new AVLTree<>();

        tree.insert(10);
        tree.insert(20);
        tree.insert(30); // triggers RR

        assertTrue(tree.isValidAVL());
        assertEquals(List.of(10, 20, 30), tree.inOrder());
    }

    @Test
    void testLRRotation() {
        AVLTree<Integer> tree = new AVLTree<>();

        tree.insert(30);
        tree.insert(10);
        tree.insert(20); // triggers LR

        assertTrue(tree.isValidAVL());
        assertEquals(List.of(10, 20, 30), tree.inOrder());
    }

    @Test
    void testRLRotation() {
        AVLTree<Integer> tree = new AVLTree<>();

        tree.insert(10);
        tree.insert(30);
        tree.insert(20); // triggers RL

        assertTrue(tree.isValidAVL());
        assertEquals(List.of(10, 20, 30), tree.inOrder());
    }

    // ---- STRESS TEST ----

    @Test
    void testLargeRandomInsertions() {
        AVLTree<Integer> tree = new AVLTree<>();
        Random rand = new Random(42);

        Set<Integer> inserted = new HashSet<>();

        for (int i = 0; i < 1000; i++) {
            int value = rand.nextInt(10000);
            tree.insert(value);
            inserted.add(value);
        }

        List<Integer> expected = new ArrayList<>(inserted);
        Collections.sort(expected);

        assertEquals(expected, tree.inOrder());
        assertEquals(inserted.size(), tree.size());
        assertTrue(tree.isValidAVL());

        // AVL height should be O(log n)
        assertTrue(tree.height() < 2 * (Math.log(tree.size()) / Math.log(2)));
    }

    // ---- EDGE CASE ----

    @Test
    void testNullInsertThrows() {
        AVLTree<Integer> tree = new AVLTree<>();

        assertThrows(IllegalArgumentException.class, () -> tree.insert(null));
    }

    @Test
    void testIterator() {
        AVLTree<Integer> tree = new AVLTree<>();

        tree.insert(2);
        tree.insert(1);
        tree.insert(3);

        Iterator<Integer> it = tree.iterator();

        assertTrue(it.hasNext());
        assertEquals(1, it.next());
        assertEquals(2, it.next());
        assertEquals(3, it.next());
        assertFalse(it.hasNext());
    }
}