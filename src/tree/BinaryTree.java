package tree;

/**
 * Created by bogdan on 01/12/14.
 */
public class BinaryTree<T> {
    private T value;
    private BinaryTree<T> leftChild;
    private BinaryTree<T> rightChild;

    public BinaryTree(T value, BinaryTree<T> leftChild, BinaryTree<T> rightChild) {
        this.value = value;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    public void printInOrder(){
        if (leftChild != null){
            leftChild.printInOrder();
        }
        System.out.println(value);
        if (rightChild != null){
            rightChild.printInOrder();
        }
    }

    public static void main(String[] args) {
        BinaryTree<Integer> binaryTree = new BinaryTree<Integer>(14,
                                                                    new BinaryTree<Integer>(19,
                                                                            new BinaryTree<Integer>(23, null, null),
                                                                                    new BinaryTree<Integer>(6,
                                                                                            new BinaryTree<Integer>(10, null, null),
                                                                                            new BinaryTree<Integer>(21, null, null)
                                                                                            )
                                                                                    ),
                                                                    new BinaryTree<Integer>(15,
                                                                            new BinaryTree<Integer>(3,
                                                                                    null, null), null
                                                                            ));
        binaryTree.printInOrder();
    }
}
