package leetcode2;

import programmer_interview.TreeNode;

/**
 * created by Ethan-Walker on 2019/3/1
 */
public class Q0110_IsBalanced {
    public boolean isBalanced(TreeNode root) {

        boolean[] res = new boolean[]{true};
        getHeight(root, res);
        return res[0];
    }

    public int getHeight(TreeNode root, boolean[] res) {
        if (root == null) return 0;
        int leftHeight = getHeight(root.left, res);
        if (res[0] == false) return -1; // 左子树不平衡

        int rightHeight = getHeight(root.right, res);
        if (res[0] == false) return -1; // 右子树不平衡

        if (Math.abs(leftHeight - rightHeight) > 1) {
            res[0] = false;
        }
        return 1 + Math.max(leftHeight, rightHeight);

    }


    public boolean isBalanced2(TreeNode root) {
        getHeight(root);
        return isBalanced;
    }

    boolean isBalanced = true;

    public int getHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftHeight = getHeight(root.left);
        if (!isBalanced) return -1;

        int rightHeight = getHeight(root.right);
        if (!isBalanced) return -1;

        if (Math.abs(leftHeight - rightHeight) > 1) {
            isBalanced = false;
            return -1;
        }

        return Math.max(leftHeight, rightHeight) + 1;
    }


}
