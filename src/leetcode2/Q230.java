package leetcode2;

import programmer_interview.TreeNode;

import java.util.ArrayDeque;

/**
 * created by Ethan-Walker on 2019/3/10
 */
public class Q230 {

    public int kthSmallest(TreeNode root, int k) {

        ArrayDeque<TreeNode> stack = new ArrayDeque<>();
        TreeNode node = root;

        while (!stack.isEmpty() || node != null) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            k--;
            if (k == 0) {
                return node.val;
            }
            node = node.right;
        }

        return -1;
    }

    public int kthSmallest2(TreeNode root, int k) {
        int[] ks = new int[]{k};
        int[] res = new int[]{0};
        midOrder(root, ks, res);
        return res[0];
    }

    public void midOrder(TreeNode root, int[] k, int[] res) {
        if (root == null) return;
        midOrder(root.left, k, res);

        k[0]--;
        if (k[0] == 0) {
            res[0] = root.val;
            return;
        }

        midOrder(root.right, k, res);

    }
}
