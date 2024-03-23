package leetcode;

import programmer_interview.TreeNode;

/**
 * created by Ethan-Walker on 2019/3/1
 */
public class Q0111_MinDepth {
    /**
     * 二叉树的最小深度：根节点到叶节点的最小长度
     * 注意是：到叶节点 (节点本身不为空，左右子树都为空的节点)
     *
     * @param root
     * @return
     */
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) // root 是叶节点，深度为 1
            return 1;

        if (root.left == null) { // 左子树为空的话，root 到左子树没有路径
            return minDepth(root.right) + 1;
        } else if (root.right == null) {
            return minDepth(root.left) + 1;
        } else {
            return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
        }
    }
}
