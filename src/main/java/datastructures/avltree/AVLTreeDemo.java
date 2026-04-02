package datastructures.avltree;

public class AVLTreeDemo {

    public static void main(String[] args) {
        AVLTree<Integer> tree = new AVLTree<>();

        int[] values = {10, 20, 30, 40, 50, 25};

        for (int v : values) {
            tree.insert(v);
        }

        System.out.println("In-order: " + tree.inOrder());
        System.out.println("Contains 25? " + tree.contains(25));
        System.out.println("Height: " + tree.height());
        System.out.println("Size: " + tree.size());
        System.out.println("Valid AVL? " + tree.isValidAVL());

        System.out.println("Traversal using iterator:");
        for (int v : tree) {
            System.out.print(v + " ");
        }
    }
}
