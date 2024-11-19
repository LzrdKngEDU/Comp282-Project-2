public class RedBlack<E extends Comparable<E>> implements TreeInterface<E> {
    private class Node {
        E element;
        Node left, right, parent;
        boolean isRed; // True for red, false for black

        Node(E element) {
            this.element = element;
            this.isRed = true; // New nodes are initially red
        }
    }

    private Node root;

    public RedBlack() {
        root = null;
    }
    // Jason
    @Override
    public void insert(E e) {
        root = insert(root, e); // Insert and balance the tree
    }

    private Node insert(Node node, E e) {
        if (node == null) {
            return new Node(e);
        }
        // Insert logic for red-black tree
        return node; // Placeholder
    }
// jason end
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
// Aaron
    @Override
    public void delete(E e) {
        root = delete(root, e); 
    }
    private Node delete(Node node, E e) {
        if (node == null) return null;
        if (e.compareTo(node.element) < 0) {
            node.left = delete(node.left, e); // Left Tree
        } else if (e.compareTo(node.element) > 0) {
            node.right = delete(node.right, e); // Right Tree
        } else {
            if (node.left == null || node.right == null) {
                Node replace = (node.left != null) ? node.left : node.right;

                if (replace != null) {
                    replace.parent = node.parent; 
                }
                return replace; 
            } else {//case 3
                Node successor = findMin(node.right); // Small val
                node.element = successor.element; //new element
                node.right = delete(node.right, successor.element); //deletion
            }
        }
// Aaron Start
    @Override
    public void printInOrder() {
        printInOrder(root);
        System.out.println(); // In-order traversal
    }

    private void printInOrder(Node node) {
        if (node != null) {
            printInOrder(node.left);
            System.out.print(node.element + " ");
            printInOrder(node.right);
        }
    }

// Aaron End

    public void statusRB() {
        // Count red and black nodes, and compute black height
        System.out.println("Red = ?, Black = ?, BlackHeight = ?");
    }

    public void printRedBlack() {
        printRedBlack(root);
    }

    private void printRedBlack(Node node) {
        if (node != null) {
            printRedBlack(node.left);
            System.out.print("(" + node.element + (node.isRed ? ",R" : ",B") + ") ");
            printRedBlack(node.right);
        }
    }
}
