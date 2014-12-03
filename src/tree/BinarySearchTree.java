package tree;

/**
 * Created by bogdan on 02/12/14.
 */
public class BinarySearchTree<T extends Comparable> {
    private BinarySearchNode<T> root;

    public BinarySearchNode<T> insert(T value, BinarySearchNode<T> node){
        if (node == null){
            node = new BinarySearchNode<T>(value);
        }else{
            if (value.compareTo(node.value) < 0){
                node.left = insert(value, node.left);
            }else if (value.compareTo(node.value) > 0){
                node.right = insert(value, node.right);
            }
        }
        return  node;
    }

    public BinarySearchNode<T> find(T value){
        return getBinarySearchNode(value, root);
    }

    private BinarySearchNode<T> getBinarySearchNode(T value, BinarySearchNode<T> node) {
        int comparison = value.compareTo(node.value);
        if (comparison == 0){
            return node;
        }else if (comparison < 0){
            return getBinarySearchNode(value, node.left);
        }else{
            return getBinarySearchNode(value, node.right);
        }
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer> binarySearchTree = new BinarySearchTree<Integer>();
        binarySearchTree.root = binarySearchTree.insert(19, binarySearchTree.root);
        binarySearchTree.insert(11, binarySearchTree.root);
        binarySearchTree.insert(7, binarySearchTree.root);
        binarySearchTree.insert(16, binarySearchTree.root);
        binarySearchTree.insert(13, binarySearchTree.root);
        binarySearchTree.insert(17, binarySearchTree.root);

        binarySearchTree.insert(35, binarySearchTree.root);
        binarySearchTree.insert(23, binarySearchTree.root);

        System.out.println(binarySearchTree.find(11).value);
        System.out.println(binarySearchTree.find(13).value);

        System.out.println("");
    }
}
