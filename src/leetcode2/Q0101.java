package leetcode2;

import programmer_interview.TreeNode;

/**
 * created by Ethan-Walker on 2019/3/17
 */
public class Q0101 {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isSymetric(root.left, root.right);

    }

    public boolean isSymetric(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) return true;
        else if (root1 == null || root2 == null) return false;
        return root1.val == root2.val
                && isSymetric(root1.left, root2.right)
                && isSymetric(root2.left, root1.right);
    }


    public boolean isSymmetric2(TreeNode root) {
        if (root == null) return true;
        return isSymmetric2(root.left, root.right);
    }

    public boolean isSymmetric2(TreeNode node1, TreeNode node2) {
        if (node1 == null && node2 == null) return true;
        if (node1 == null || node2 == null) return false;

        return node1.val == node2.val && isSymmetric2(node1.right, node2.left) && isSymmetric2(node1.left, node2.right);
    }

}
