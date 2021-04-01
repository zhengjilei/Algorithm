package 剑指offer;

import binary_tree.BinaryTree;
import org.junit.Test;

import java.util.ArrayDeque;

/**
 * 分行打印二叉树
 * created by Ethan-Walker on 2018/12/7
 */
public class Q032A_TreeLevelOrderLine {

    public void print(TreeNode root) {
        if (root == null) return;
        ArrayDeque<TreeNode> queue1 = new ArrayDeque<>();
        ArrayDeque<TreeNode> queue2 = new ArrayDeque<>();
        TreeNode node = null;
        queue1.push(root);
        while (!queue1.isEmpty() || !queue2.isEmpty()) {
            while (!queue1.isEmpty()) {
                node = queue1.poll();
                System.out.printf("%5.2f", node.val);
                if (node.left != null) queue2.offer(node.left);
                if (node.right != null) queue2.offer(node.right);
            }
            System.out.println();
            while (!queue2.isEmpty()) {
                node = queue2.poll();
                System.out.printf("%5.2f", node.val);
                if (node.left != null) queue1.offer(node.left);
                if (node.right != null) queue1.offer(node.right);
            }
            System.out.println();
        }
    }

    public void printLine(TreeNode root) {
        if (root == null) return;
        ArrayDeque<TreeNode> queue = new ArrayDeque<>();

        TreeNode node = null;
        queue.push(root);

        int toBePrint = 1;          // 当前行还需要打印的节点个数，
        int nextLineNodeCount = 0;      // 下一行节点的总数
        while (!queue.isEmpty()) {
            node = queue.poll();
            System.out.printf("%5.1f", node.val);
            toBePrint--;

            if (node.left != null) {
                ++nextLineNodeCount;
                queue.offer(node.left);
            }
            if (node.right != null) {
                ++nextLineNodeCount;
                queue.offer(node.right);
            }

            if (toBePrint == 0) {
                System.out.println();
                toBePrint = nextLineNodeCount;
                nextLineNodeCount = 0;
            }
        }
    }

    @Test
    public void test() {
        BinaryTree<Integer> tree = new BinaryTree<>();
        tree.buildTree(new int[]{8, 6, 5, 7, 10, 9, 11}, new int[]{5, 6, 7, 8, 9, 10, 11});
        tree.levelOrder();
        tree.levelOrderLine();
    }

}
