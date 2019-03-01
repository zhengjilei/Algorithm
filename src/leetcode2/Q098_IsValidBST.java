package leetcode2;

import 程序员代码面试指南.TreeNode;

/**
 * created by Ethan-Walker on 2019/3/1
 */
public class Q098_IsValidBST {
    // 错误，这种判断：当root 左子树的右子节点大于 root 值的时候，也会认为是 BST，实际上不是
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        boolean res = true;
        if (root.left != null) {
            if (root.val > root.left.val) {
                res = isValidBST(root.left);
            } else {
                return false;
            }
        }
        if (!res) return false;
        if (root.right != null) {
            if (root.val < root.right.val) {
                return isValidBST(root.right);
            } else {
                return false;
            }
        }
        return true;
    }

    public boolean isValidBST2(TreeNode root) {
        int[] max = new int[1];
        int[] min = new int[1];
        return isValidBST(root, max, min);
    }


    /**
     * 深度优先遍历，记录左子树的  最大值，右子树的最小值，然后判断当前节点是否是 BST
     */
    public boolean isValidBST(TreeNode root, int[] max, int[] min) {
        if (root == null)
            return true;
        if (root.left == null && root.right == null) {
            max[0] = root.val;
            min[0] = root.val;
            return true;
        }

        if (root.right == null) {
            boolean flag = isValidBST(root.left, max, min);
            if (!flag) return false;
            if (root.val > max[0]) {
                max[0] = root.val;
                return true;
            }
            return false;
        } else if (root.left == null) {
            boolean flag = isValidBST(root.right, max, min);
            if (!flag) return false;
            if (root.val < min[0]) {
                min[0] = root.val;
                return true;
            }
            return false;

        } else {
            boolean flag = isValidBST(root.left, max, min);
            if (!flag) return false;
            int minx = min[0];
            int maxx = max[0];
            flag = isValidBST(root.right, max, min);
            if (!flag) return false;

            if (root.val > maxx && root.val < min[0]) {
                min[0] = minx;
                // max 保持不变
                return true;
            }

            return false;
        }


    }
}
