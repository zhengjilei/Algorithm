package leetcode;


import programmer_interview.TreeNode;

/**
 * created by Ethan-Walker on 2019/3/20
 */
public class Q0112_HasPathSum {
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;
        return find(root, sum);
    }

    public boolean find(TreeNode node, int target) {
        if (node.left == null && node.right == null) {
            if (target == node.val) {
                return true;
            }
            return false;
        }
        target -= node.val;
        boolean flag = false;
        if (node.left != null)
            flag = find(node.left, target);
        if (flag)
            return true;

        if (node.right != null) {
            return find(node.right, target);
        }
        return false;
    }
}
