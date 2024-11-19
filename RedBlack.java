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
    Node insertedNode = insert(root, null, e); // Perform insertion
    root = insertedNode; 
    root.isRed = false; 
}

private Node insert(Node node, Node parent, E e) {
    if (node == null) {
        Node newNode = new Node(e);
        newNode.parent = parent;  
        return newNode;
    }

    if (e.compareTo(node.element) < 0) {
        node.left = insert(node.left, node, e); 
    } else if (e.compareTo(node.element) > 0) {
        node.right = insert(node.right, node, e); 
    } else {
        return node; // Duplicates are not allowed
    }
    return fixViolations(node);
}

private Node fixViolations(Node node) {
    // Case 1: Red child on the right
    if (isRed(node.right) && !isRed(node.left)) {
        node = rotateLeft(node);
    }

    // Case 2: Red child with a red grandchild on the left
    if (isRed(node.left) && isRed(node.left.left)) {
        node = rotateRight(node);
    }

    // Case 3: Both children are red
    if (isRed(node.left) && isRed(node.right)) {
        flipColors(node);
    }

    return node;
}

private boolean isRed(Node node) {
    return node != null && node.isRed;
}

private Node rotateLeft(Node node) {
    Node newParent = node.right;
    node.right = newParent.left;
    if (newParent.left != null) {
        newParent.left.parent = node;
    }
    newParent.left = node;
    newParent.isRed = node.isRed;
    node.isRed = true;
    return newParent;
}

private Node rotateRight(Node node) {
    Node newParent = node.left;
    node.left = newParent.right;
    if (newParent.right != null) {
        newParent.right.parent = node;
    }
    newParent.right = node;
    newParent.isRed = node.isRed;
    node.isRed = true;
    return newParent;
}

private void flipColors(Node node) {
    node.isRed = true;
    if (node.left != null) node.left.isRed = false;
    if (node.right != null) node.right.isRed = false;
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
// Jason End
// Aaron Start
@Override
public void delete(E e) {
    if (e == null || root == null) return;
    root = delete(root, e);
    if (root != null) root.isRed = false; // Ensure the root is always black
}

private Node delete(Node node, E e) {
    if (node == null) return null;

    if (e.compareTo(node.element) < 0) {
        if (node.left != null) {
            if (!isRed(node.left) && !isRed(node.left.left)) {
                node = moveRedLeft(node);
            }
            node.left = delete(node.left, e);
        }
    } else {
        if (isRed(node.left)) {
            node = rotateRight(node);
        }
        if (e.compareTo(node.element) == 0 && (node.right == null)) {
            return null;
        }
        if (node.right != null) {
            if (!isRed(node.right) && !isRed(node.right.left)) {
                node = moveRedRight(node);
            }
            if (e.compareTo(node.element) == 0) {
                Node successor = findMin(node.right);
                node.element = successor.element;
                node.right = deleteMin(node.right);
            } else {
                node.right = delete(node.right, e);
            }
        }
    }

    return fixViolations(node);
}

private Node deleteMin(Node node) {
    if (node.left == null) return null;

    if (!isRed(node.left) && !isRed(node.left.left)) {
        node = moveRedLeft(node);
    }

    node.left = deleteMin(node.left);
    return fixViolations(node);
}

private Node findMin(Node node) {
    while (node.left != null) {
        node = node.left;
    }
    return node;
}

private Node moveRedLeft(Node node) {
    flipColors(node);
    if (isRed(node.right.left)) {
        node.right = rotateRight(node.right);
        node = rotateLeft(node);
        flipColors(node);
    }
    return node;
}

private Node moveRedRight(Node node) {
    flipColors(node);
    if (isRed(node.left.left)) {
        node = rotateRight(node);
        flipColors(node);
    }
    return node;
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

public void statusRB() {
    System.out.println("Red = " + countRedNodes(root) +
            ", Black = " + countBlackNodes(root) +
            ", BlackHeight = " + calculateBlackHeight(root));
}

private int countRedNodes(Node node) {
    if (node == null) return 0;
    return (node.isRed ? 1 : 0) + countRedNodes(node.left) + countRedNodes(node.right);
}

private int countBlackNodes(Node node) {
    if (node == null) return 0;
    return (!node.isRed ? 1 : 0) + countBlackNodes(node.left) + countBlackNodes(node.right);
}

private int calculateBlackHeight(Node node) {
    if (node == null) return 0;
    int leftHeight = calculateBlackHeight(node.left);
    int rightHeight = calculateBlackHeight(node.right);
    return (!node.isRed ? 1 : 0) + Math.max(leftHeight, rightHeight);
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

// Aaron End