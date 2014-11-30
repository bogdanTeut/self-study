package tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bogdan.teut on 27/11/2014.
 */
public class TreeNode<T> {
    private T value;
    private boolean hasParent;
    private List<TreeNode<T>> children;

    public TreeNode(T value) {
        this.value = value;
        children = new ArrayList<TreeNode<T>>();
    }

    public void addChild(TreeNode<T> child){
        if (child.hasParent == false){
            children.add(child);
        }
    }

    public T getValue() {
        return value;
    }

    public List<TreeNode<T>> getChildren() {
        return children;
    }
}
