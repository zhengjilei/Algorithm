package binary_tree;

/**
 * Created by Ethan-Walker on 2018/4/21.
 */
public class TreeNode<T> {

    public T val;

    public TreeNode(T value) {
        this.val = value;
    }

    public TreeNode left;
    public TreeNode right;
    public TreeNode parent;
}
