package leetcode;

import programmer_interview.TreeNode;

/**
 * created by Ethan-Walker on 2019/3/10
 */
public class Q783_MinDiffInBST {
    public int minDiffInBST2(TreeNode root) {
        if (root == null) return Integer.MAX_VALUE;
        int leftMinDiff = Integer.MAX_VALUE;
        int rightMinDiff = Integer.MAX_VALUE;
        int curMinDiff = Integer.MAX_VALUE;

        if (root.left != null) {
            leftMinDiff = minDiffInBST(root.left);
            curMinDiff = Math.abs(root.val - root.left.val);
        }
        if (root.right != null) {
            rightMinDiff = minDiffInBST(root.right);
            int t = Math.abs(root.val - root.right.val);
            if (t < curMinDiff) {
                curMinDiff = t;
            }
        }

        return Math.min(Math.min(leftMinDiff, rightMinDiff), curMinDiff);
    }

    int prev = -1000;
    int min = Integer.MAX_VALUE;

    public int minDiffInBST(TreeNode root) {
        if (root == null) return Integer.MAX_VALUE;
        int[] prev = new int[]{-1000};
        int[] res = new int[]{1000};

        recur(root, prev, res);
        return (int) res[0];
    }

    public void recur1(TreeNode node) {
        if (node == null) return;

        recur1(node.left);

        int t = node.val - prev;
        if (t < min) {
            min = t;
        }
        prev = node.val;
        recur1(node.right);
    }

    public void recur(TreeNode node, int[] prev, int[] res) {
        if (node == null) return;
        recur(node.left, prev, res);
        int t = node.val - prev[0];
        if (t < res[0]) {
            res[0] = t;
        }

        prev[0] = node.val;
        recur(node.right, prev, res);
    }


}
