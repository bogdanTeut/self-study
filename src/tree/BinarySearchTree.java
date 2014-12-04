package tree;

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

    public void delete(T value) {
        BinarySearchNode<T> elementToDelete = find(value);

        //no children
        if (elementToDelete.left == null && elementToDelete.right == null){
            if (elementToDelete.parent == null){
                root = null;
            }else{
                if (elementToDelete.equals(elementToDelete.parent.left)){
                    elementToDelete.parent.left = null;
                }else{
                    elementToDelete.parent.right = null;
                }
            }
        //only one child
        }else if (elementToDelete.left == null || elementToDelete.right == null){
            if (elementToDelete.left != null){
                deleteMiddleNode(elementToDelete, elementToDelete.left);
            }else{
                deleteMiddleNode(elementToDelete, elementToDelete.right);
            }
        //two children
        }else{
            BinarySearchNode<T> replacement = elementToDelete.right;
            while (replacement.left != null){
                replacement = replacement.left;
            }
            T temp = null;
            temp = elementToDelete.value;
            elementToDelete.value = replacement.value;
            replacement.value = temp;
            replacement.parent.left = null;
        }

    }

    private void deleteMiddleNode(BinarySearchNode<T> elementToDelete, BinarySearchNode<T> child) {
        if (elementToDelete.parent == null) root = child;
        else{
            child.parent = elementToDelete.parent;

            if (elementToDelete.equals(elementToDelete.parent.right)){
                elementToDelete.parent.right = child;
            }else{
                elementToDelete.parent.left = child;
            }
        }
    }

    public void dfs(BinarySearchNode<T> node){
        if (node != null){
            dfs(node.left);
            System.out.println(node.value);
            dfs(node.right);
        }
    }

    public static void main(String[] args) {

            BinarySearchTree<String> tree =
                    new BinarySearchTree<String>();
            tree.insert("Telerik");
            tree.insert("Google");
            tree.insert("Microsoft");
            tree.dfs(tree.root); // Google Microsoft Telerik

            tree.delete("Telerik");
            tree.dfs(tree.root); // Google Microsoft
    }
}
