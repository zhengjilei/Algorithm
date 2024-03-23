package leetcode;

import programmer_interview.TreeNode;

import java.util.ArrayDeque;

/**
 * created by Ethan-Walker on 2019/3/21
 */
public class Q0114_BTToList {


    public void flatten(TreeNode root) {
        if (root == null) return;
        ArrayDeque<TreeNode> stack = new ArrayDeque<>();
        TreeNode prev = null;
        TreeNode node = root;
        TreeNode tmp;
        while (true) {
            // 访问当前节点
            if (prev != null) {
                prev.right = node;
            }
            prev = node;

            if (node.right != null) {
                stack.push(node.right);
            }

            if (node.left != null) {
                // 进入左子树
                tmp = node.left;
                node.left = null; // 将 node 节点的左子树设置为 null
                node = tmp;
            } else {
                if (stack.isEmpty()) {
                    break;
                }
                node = stack.pop();
            }
        }
    }
}
