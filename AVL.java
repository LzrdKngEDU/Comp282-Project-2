public class AVL<E extends Comparable<E>> implements TreeInterface<E> {
    private class Node {
        E element;
        Node left, right;
        int height;

        Node(E element) {
            this.element = element;
            this.height = 1; // New nodes have height 1 by default
        }
    }

    private Node root;

    public AVL() {
        root = null;
    }

    @Override
    public void insert(E e) {
        root = insert(root, e); // Recursive insert
    }

    private Node insert(Node node, E e) {
        if (node == null) {
            return new Node(e); // Insert a new node
        }
        // Standard binary search tree insertion
        if (e.compareTo(node.element) < 0) {
            node.left = insert(node.left, e);
        } else if (e.compareTo(node.element) > 0) {
            node.right = insert(node.right, e);
        } else {
            return node; // No duplicates allowed
        }

        // Update height and balance
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        return balance(node);
    }

    private Node balance(Node node) {
        // Balance the tree if necessary
        // Implement left-right and right-left rotations
        return node; // Placeholder for balancing logic
    }

    @Override
    public boolean find(E e) {
        return find(root, e);
    }

    private boolean find(Node node, E e) {
        if (node == null) return false;
        if (e.compareTo(node.element) < 0) {
            return find(node.left, e);
        } else if (e.compareTo(node.element) > 0) {
            return find(node.right, e);
        } else {
            return true;
        }
    }

    @Override
    public void delete(E e) {
        root = delete(root, e); // Recursive delete
    }

    private Node delete(Node node, E e) {
        if (node == null) return null;

        // Perform standard delete operations
        // Rebalance the tree if necessary
        return node; // Placeholder for delete logic
    }

    @Override
    public void printInOrder() {
        printInOrder(root); // In-order traversal
    }

    private void printInOrder(Node node) {
        if (node != null) {
            printInOrder(node.left);
            System.out.print(node.element + " ");
            printInOrder(node.right);
        }
    }

    // Additional methods

    public void heightAVL() {
        System.out.println("AVL height = " + height(root));
    }

    private int height(Node node) {
        return node == null ? 0 : node.height;
    }

    public void printAVL() {
        printAVL(root);
    }

    private void printAVL(Node node) {
        if (node != null) {
            printAVL(node.left);
            System.out.print("(" + node.element + "," + (height(node.left) - height(node.right)) + ")");
            printAVL(node.right);
        }
    }
}
