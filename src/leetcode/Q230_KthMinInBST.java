package leetcode;

import 程序员代码面试指南.TreeNode;

import java.util.ArrayDeque;

/**
 * created by Ethan-Walker on 2019/3/1
 */
public class Q230_KthMinInBST {

    // 非递归中序遍历，找到第 k 个节点
    public int kthSmallest(TreeNode root, int k) {

        TreeNode p = root;
        ArrayDeque<TreeNode> stack = new ArrayDeque<>();
        while (p != null || !stack.isEmpty()) {
            while (p != null) {
                stack.push(p);
                p = p.left;
            }
            if (!stack.isEmpty()) {
                p = stack.pop();
                k--;
                // 访问元素
                if (k == 0) {
                    return p.val;
                }
                p = p.right;
            }
        }
        return -1;
    }

    // 递归实现
    public int kthSmallest2(TreeNode root, int k) {
        int[] ks = new int[]{k};
        int[] res = new int[1];
        recur(root, ks, res);
        return res[0];
    }

    // k[0] 存储 k 的值，用数组使得递归下去 每访问一个节点，k 的值 -1， k==0 时，说明访问的节点就是第 k 小节点
    // res[0] 存储结果
    public void recur(TreeNode root, int[] k, int[] res) {
        if (root == null) return;
        if (root.left != null) {
            recur(root.left, k, res);
        }
        if (k[0] <= 0) return; // 左子树中已经找到
        k[0]--;
        if (k[0] == 0) {
            res[0] = root.val;
            return;
        }
        if (root.right != null) {
            recur(root.right, k, res);
        }
    }

}
