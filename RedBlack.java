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
    root = insertedNode; // Update root reference
    root.isRed = false; // Ensure root is always black
}

private Node insert(Node node, Node parent, E e) {
    if (node == null) {
        Node newNode = new Node(e); // Create a new red node
        newNode.parent = parent;   // Set its parent
        return newNode;
    }

    if (e.compareTo(node.element) < 0) {
        node.left = insert(node.left, node, e); // Recur for left subtree
    } else if (e.compareTo(node.element) > 0) {
        node.right = insert(node.right, node, e); // Recur for right subtree
    } else {
        return node; // Duplicates are not allowed
    }

    // Fix any violations of Red-Black properties
    return fixViolations(node);
}

private Node fixViolations(Node node) {
    // Case 1: Red child on the right (left rotation needed)
    if (isRed(node.right) && !isRed(node.left)) {
        node = rotateLeft(node);
    }

    // Case 2: Red child with a red grandchild on the left (right rotation needed)
    if (isRed(node.left) && isRed(node.left.left)) {
        node = rotateRight(node);
    }

    // Case 3: Both children are red (flip colors)
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
// Jason End
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
<<<<<<< HEAD
        printInOrder(root);
        System.out.println(); // In-order traversal
=======
        printInOrder(root); 
>>>>>>> b4d8faeb1cb2e8d0b04b9cf4f334df9e851fe80f
    }

    private void printInOrder(Node node) {
        if (node != null) {
            printInOrder(node.left);
            System.out.print(node.element + " ");
            printInOrder(node.right);
        }
    }

    
    public void statusRB() {
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

// Aaron End