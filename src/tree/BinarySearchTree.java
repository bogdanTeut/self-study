package tree;

import binarysearch.BinarySearch;

/**
 * Created by bogdan.teut on 03/12/2014.
 */
public class BinarySearchTree<T extends Comparable> {
    public BinarySearchNode<T> root;

    public void insert(T value) {
        if (root == null){
            root = new BinarySearchNode<T>(value);
        }else{
            insertValue(value, root, null);
        }
    }

    private BinarySearchNode<T> insertValue(T value, BinarySearchNode<T> node, BinarySearchNode<T> parentNode){
        if (node == null){
            node = new BinarySearchNode<T>(value);
            node.parent = parentNode;
        }else{
            if (value.compareTo(node.value) < 0){
                node.left = insertValue(value, node.left, node);
            }else{
                node.right = insertValue(value, node.right, node);
            }
        }
        return node;
    }

    public BinarySearchNode<T> find(T value) {
        if (root == null){
            return null;
        }
        final int compareTo = value.compareTo(root.value);
        if (compareTo == 0){
            return root;
        }else if (compareTo < 0){
            return getElement(value, root.left);
        }else {
            return getElement(value, root.right);
        }

    }

    private BinarySearchNode<T> getElement(T value, BinarySearchNode<T> node) {
        if (node == null) return null;
        int compareTo = value.compareTo(node.value);
        if (compareTo == 0){
            return node;
        }else if (compareTo < 0){
            return getElement(value, node.left);
        }else{
            return getElement(value, node.right);
        }

    }

}
