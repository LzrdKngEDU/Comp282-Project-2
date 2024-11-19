public class Splay<E extends Comparable<E>> implements TreeInterface<E> {
    private class Node {
        E element;
        Node left, right;

        Node(E element) {
            this.element = element;
        }
    }

    private Node root;

    public Splay() {
        root = null;
    }

    @Override
    public void insert(E e) {
        root = insert(root, e); // Insert into the splay tree
        // Splay the tree after insertion
    }

    private Node insert(Node node, E e) {
        if (node == null) {
            return new Node(e);
        }
        // Perform standard insert operation for splay tree
        return node; // Placeholder
    }

    @Override
    public boolean find(E e) {
        return find(root, e);
    }

    private boolean find(Node node, E e) {
        if (node == null) return false;
        if (e.compareTo(node.element) == 0) return true;
        // Perform splay operation
        return false; // Placeholder
    }

    @Override
    public void delete(E e) {
        root = delete(root, e); // Perform delete operation for splay tree
    }

    private Node delete(Node node, E e) {
        if (node == null) return null;
        // Perform delete operation and splay the tree
        return node; // Placeholder
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

    public void printRoot() {
        if (root != null) {
            System.out.println("The root contains: " + root.element);
        }
    }

    public void printSplay() {
        printSplay(root);
    }

    private void printSplay(Node node) {
        if (node != null) {
            printSplay(node.left);
            System.out.print("(" + node.element + ",height) "); // Add height info later
            printSplay(node.right);
        }
    }
}
