package tree;

/**
 * Created by bogdan.teut on 03/12/2014.
 */
public class BinarySearchNode<T extends Comparable> implements Comparable<T> {
    public T value;
    public BinarySearchNode<T> left;
    public BinarySearchNode<T> right;
    public BinarySearchNode<T> parent;

    public BinarySearchNode(T value) {
        this.value = value;
    }

    @Override
    public int compareTo(T o) {
        return this.compareTo(o);
    }
}
