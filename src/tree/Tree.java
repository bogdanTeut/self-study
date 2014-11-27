package tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by bogdan.teut on 27/11/2014.
 */
public class Tree<T> {
    private TreeNode<T> rootNode;

    public Tree(T value) {
        rootNode = new TreeNode<T>(value);
    }

    public Tree(T value, Tree<T> ...trees) {
        this(value);
        for (Tree<T> tree : trees) {
            this.rootNode.addChild(tree.rootNode);
        }
    }

    public void printDFS(TreeNode<T> node, String space){
        System.out.println(space+node.getValue());
        if (node.getChildren().size() == 0) return;
        for (TreeNode<T> child : node.getChildren()) {
            printDFS(child,"  ");
        }
    }

    public void printBFS(TreeNode<T> node, String space){
        Queue<TreeNode<T>> queue = new LinkedList<TreeNode<T>>();
        queue.add(node);
        while (queue.size() > 0){
            TreeNode<T> remove = queue.remove();
            System.out.println(remove.getValue());
            for (TreeNode<T> treeNode : remove.getChildren()) {
                queue.add(treeNode);
            }
        }
    }

    public static void main(String[] args) {
        Tree<Integer> tree =
                new Tree<Integer>(7,
                        new Tree<Integer>(19,
                                new Tree<Integer>(1),
                                new Tree<Integer>(12),
                                new Tree<Integer>(31)),
                        new Tree<Integer>(21),
                        new Tree<Integer>(14,
                                new Tree<Integer>(23),
                                new Tree<Integer>(6))
                );

        // Traverse and print the tree using Depth-First-Search
        tree.printDFS(tree.rootNode, "");
        System.out.println("====================");
        tree.printBFS(tree.rootNode, "");
    }
}
