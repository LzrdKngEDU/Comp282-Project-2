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

        // Update height of the current node
        node.height = Math.max(height(node.left), height(node.right)) + 1;

        // Balance the node
        return balance(node);
    }

    private Node balance(Node node) {
        int balanceFactor = height(node.left) - height(node.right);

        // Left heavy
        if (balanceFactor > 1) {
            if (height(node.left.left) >= height(node.left.right)) {
                return rotateRight(node); // Left-Left case
            } else {
                node.left = rotateLeft(node.left); // Left-Right case
                return rotateRight(node);
            }
        }

        // Right heavy
        if (balanceFactor < -1) {
            if (height(node.right.right) >= height(node.right.left)) {
                return rotateLeft(node); // Right-Right case
            } else {
                node.right = rotateRight(node.right); // Right-Left case
                return rotateLeft(node);
            }
        }

        return node; // Node is already balanced
    }

    private Node rotateRight(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        // Perform rotation
        x.right = y;
        y.left = T2;

        // Update heights
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x; // Return new root
    }

    private Node rotateLeft(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        // Perform rotation
        y.left = x;
        x.right = T2;

        // Update heights
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y; // Return new root
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

        if (e.compareTo(node.element) < 0) {
            node.left = delete(node.left, e); // Go left
        } else if (e.compareTo(node.element) > 0) {
            node.right = delete(node.right, e); // Go right
        } else {
            // Node with only one child or no child
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;

            // Node with two children: replace with in-order successor
            Node minNode = findMin(node.right);
            node.element = minNode.element;
            node.right = delete(node.right, minNode.element);
        }

        // Update height of the current node
        node.height = Math.max(height(node.left), height(node.right)) + 1;

        // Balance the node
        return balance(node);
    }

    private Node findMin(Node node) {
        while (node.left != null) node = node.left;
        return node;
    }

    @Override
    public void printInOrder() {
        printInOrder(root);
        System.out.println(); // New line after printing
    }

    private void printInOrder(Node node) {
        if (node != null) {
            printInOrder(node.left);
            System.out.print(node.element + " ");
            printInOrder(node.right);
        }
    }

    // Additional AVL-Specific Methods

    public void heightAVL() {
        System.out.println("AVL height = " + height(root));
    }

    private int height(Node node) {
        return node == null ? 0 : node.height;
    }

    public void printAVL() {
        printAVL(root);
        System.out.println(); // New line after printing
    }

    private void printAVL(Node node) {
        if (node != null) {
            printAVL(node.left);
            int balanceFactor = height(node.left) - height(node.right);
            System.out.print("(" + node.element + "," + balanceFactor + ") ");
            printAVL(node.right);
        }
    }
}
