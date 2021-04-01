package 剑指offer;

import java.util.ArrayDeque;

/**
 * 按层遍历二叉树
 * created by Ethan-Walker on 2018/12/7
 */
public class Q032_TreeLevelOrder {

    public void levelOrder(TreeNode root) {
        if (root == null) return;
        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        queue.push(root);
        TreeNode node = null;
        while (!queue.isEmpty()) {
            node = queue.poll();
            System.out.printf("%5.2f", node.val);
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }
    }
}
