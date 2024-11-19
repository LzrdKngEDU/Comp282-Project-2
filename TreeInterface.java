public interface TreeInterface<E extends Comparable<E>> {
    /** Insert an element into the tree */
    public void insert(E e);

    /** Return true if the element is in the tree */
    public boolean find(E e);

    /** Delete the specified element from the tree */
    public void delete(E e);

    /** Print the items of the tree in ascending order */
    public void printInOrder();
}
