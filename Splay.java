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

        if (root == null) {
            root = new Node(e);
            return;
        }

        root = splay(root, e);

        if (e.compareTo(root.element) < 0) {
            Node newNode = new Node(e);
            newNode.left = root.left;
            newNode.right = root;
            root.left = null;
            root = newNode;
        } else if (e.compareTo(root.element) > 0) {
            Node newNode = new Node(e);
            newNode.right = root.right;
            newNode.left = root;
            root.right = null;
            root = newNode;
        }
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

        if (root.element.equals(e)) {
            if (root.left == null) {
                root = root.right;
            } else {
                Node temp = root.right;
                root = root.left;
                root = splay(root, e);
                root.right = temp;
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
            System.out.print("(" + node.element + ", height) ");
            printSplay(node.right);
        }
    }

    private Node splay(Node node, E e) {
        if (node == null || e == null) return node;

        if (e.compareTo(node.element) < 0) {
            if (node.left == null) return node;

            if (e.compareTo(node.left.element) < 0) {
                node.left.left = splay(node.left.left, e);
                node = rotateRight(node);
            } else if (e.compareTo(node.left.element) > 0) {
                node.left.right = splay(node.left.right, e);
                if (node.left.right != null) {
                    node.left = rotateLeft(node.left);
                }
            }
            return node.left == null ? node : rotateRight(node);

        } else if (e.compareTo(node.element) > 0) {
            if (node.right == null) return node;

            if (e.compareTo(node.right.element) > 0) {
                node.right.right = splay(node.right.right, e);
                node = rotateLeft(node);
            } else if (e.compareTo(node.right.element) < 0) {
                node.right.left = splay(node.right.left, e);
                if (node.right.left != null) {
                    node.right = rotateRight(node.right);
                }
            }
            return node.right == null ? node : rotateLeft(node);
        } else {
            return node;
        }
    }

    private Node rotateLeft(Node node) {
        Node newRoot = node.right;
        node.right = newRoot.left;
        newRoot.left = node;
        return newRoot;
    }

    private Node rotateRight(Node node) {
        Node newRoot = node.left;
        node.left = newRoot.right;
        newRoot.right = node;
        return newRoot;
    }
}
