package 剑指offer;

import java.util.ArrayDeque;

/**
 * 二叉搜索树的第k大节点
 * created by Ethan-Walker on 2018/12/14
 */
public class Q054_KthMaxInBST {


    public TreeNode getKthMaxInBST(TreeNode root, int k) {
        if (root == null) return null;

        ArrayDeque<TreeNode> stack = new ArrayDeque<>();
        inOrder(root, stack);
        if (stack.size() < k) return null;// 没有第 k 大，元素少于 k 个

        TreeNode node = null;
        while (k > 0) {
            node = stack.pop();
            k--;
        }

        return node;

    }

    public void inOrder(TreeNode node, ArrayDeque<TreeNode> stack) {
        if (node != null) {
            inOrder(node.left, stack);
            stack.push(node);
            inOrder(node.right, stack);
        }

    }
}
