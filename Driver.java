public class Driver {
    public static void main(String[] args) {
        System.out.println("Testing AVL Tree:");
        TreeInterface<Integer> avlTree = new AVL<>();
        avlTree.insert(10);
        avlTree.insert(20);
        avlTree.insert(5);
        avlTree.printInOrder(); // Should print the AVL tree in ascending order
        System.out.println("Finding 10 in AVL Tree: " + avlTree.find(10));
        avlTree.delete(10);
        avlTree.printInOrder(); // Should print the AVL tree after deletion

        System.out.println("\nTesting Splay Tree:");
        TreeInterface<Integer> splayTree = new Splay<>();
        splayTree.insert(15);
        splayTree.insert(25);
        splayTree.insert(10);
        splayTree.printInOrder(); // Should print the Splay tree in ascending order
        System.out.println("Finding 25 in Splay Tree: " + splayTree.find(25));
        splayTree.delete(25);
        splayTree.printInOrder(); // Should print the Splay tree after deletion

        System.out.println("\nTesting Red-Black Tree:");
        TreeInterface<Integer> rbTree = new RedBlack<>();
        rbTree.insert(30);
        rbTree.insert(40);
        rbTree.insert(20);
        rbTree.printInOrder(); // Should print the Red-Black tree in ascending order
        System.out.println("Finding 40 in Red-Black Tree: " + rbTree.find(40));
        rbTree.delete(40);
        rbTree.printInOrder(); // Should print the Red-Black tree after deletion
    }
}
