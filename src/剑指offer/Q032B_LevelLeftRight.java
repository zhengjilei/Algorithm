package 剑指offer;

import binary_tree.BinaryTree;
import org.junit.Test;

import java.util.ArrayDeque;

/**
 * 之字形逐层打印
 * 第一层从左到右  第二层从右到左 第三层从左到右
 * created by Ethan-Walker on 2018/12/7
 */
public class Q032B_LevelLeftRight {

    public void print(TreeNode root) {
        if (root == null) return;

        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        ArrayDeque<Double> stack = new ArrayDeque<>();
        int beToPrint = 1, nextLineNodeCount = 0;
        queue.push(root);
        TreeNode node = null;
        boolean leftToRight = true; // 0 表示打印顺序从左到右 , 1表示打印顺序从右到左，先正行压栈
        while (!queue.isEmpty()) {
            node = queue.poll();
            if (leftToRight) {
                System.out.printf("%5.1f", node.val);
            } else {
                stack.push(node.val);
            }
            beToPrint--;

            if (node.left != null) {
                nextLineNodeCount++;
                queue.offer(node.left);
            }
            if (node.right != null) {
                nextLineNodeCount++;
                queue.offer(node.right);
            }

            if (beToPrint == 0) {
                //一行结束
                if (!leftToRight) {
                    // 是从右到左的一行
                    // 积攒的一行，从栈中退出
                    while (!stack.isEmpty()) {
                        System.out.printf("%5.1f", stack.pop());
                    }
                    // 切换 flag
                    leftToRight = true;
                } else {
                    leftToRight = false;
                }
                beToPrint = nextLineNodeCount;
                nextLineNodeCount = 0;
                System.out.println();
            }
        }
    }

    @Test
    public void test() {
        BinaryTree<Integer> tree = new BinaryTree<>();
        tree.buildTree(new int[]{1, 2, 4, 8, 9, 5, 10, 11, 3, 6, 12, 13, 7, 14, 15},
                new int[]{8, 4, 9, 2, 10, 5, 11, 1, 12, 6, 13, 3, 14, 7, 15});
        tree.levelOrderLine();
        tree.printZhiLevel();

    }
}
