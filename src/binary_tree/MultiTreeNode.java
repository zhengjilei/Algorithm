package binary_tree;

import java.util.ArrayList;

/**
 * 多叉树节点
 * 每个节点只有所有的儿子节点列表
 * created by Ethan-Walker on 2018/12/16
 */
public class MultiTreeNode<T> {

    T val;
    ArrayList<MultiTreeNode> children;

    public MultiTreeNode(T val) {
        this.val = val;
        children = new ArrayList<>();
    }

    @Override
    public String toString() {
        String s = val + "";
        return s;
    }
}
