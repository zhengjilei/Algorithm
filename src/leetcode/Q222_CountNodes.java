package leetcode;

import programmer_interview.TreeNode;

/**
 * created by Ethan-Walker on 2019/3/20
 */
public class Q222_CountNodes {

    public int countNodes(TreeNode root) {
        return count(root, 1);
    }

    public int count(TreeNode root, int index) {
        if (root == null) return 0;
        if (root.left == null)
            return (index << 1) - 1; // 左子树为空，直接返回 2*index-1
        else {
            if (root.right == null) { // 左子树不空，右子树为空，返回 2*index
                return index << 1;
            } else {
                // 左右子树均不为空
                if (root.left.right != null) { // 判断左子树的右子节点是否为空，不为空说明左子树是满二叉树
                    // 进入当前节点的右子树递归计算
                    return count(root.right, (index << 1) + 1);
                } else {
                    // 左子树的右子节点为空，左子树不是满二叉树
                    // 递归计算左子节点
                    return count(root.left, index << 1);
                }
            }
        }

    }


}
