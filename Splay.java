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
        if (e == null) return;
        root = insert(root, e);
        root = splay(root, e); 

    private Node insert(Node node, E e) {
        if (node == null) {
            return new Node(e);
        }
        if (e.compareTo(node.element) < 0) {
            node.left = insert(node.left, e);
        } else if (e.compareTo(node.element) > 0) {
            node.right = insert(node.right, e);
        }
        return node;
    }
    @Override
    public boolean find(E e) {
        if (e == null) return false;
        root = splay(root, e); 
        return root != null && root.element.equals(e);
    }

    @Override
    public void delete(E e) {
        if (e == null || root == null) return;
        root = splay(root, e); 
        if (root != null && root.element.equals(e)) {
            if (root.left == null) {
                root = root.right; 
            } else {
                Node temp = root;
                root.right = temp.right; 
            }
        }
    }

    @Override
    public void printInOrder() {
        printInOrder(root);
        System.out.println();
    }
    private void printInOrder(Node node) {
        if (node != null) {
            printInOrder(node.left);
            System.out.print(node.element + " ");
            printInOrder(node.right);
        }
    }
    public void printRoot() {
        if (root != null) {
            System.out.println("The root contains: " + root.element);
        } else {
            System.out.println("The tree is empty.");
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
