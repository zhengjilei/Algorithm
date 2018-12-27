package 程序员代码面试指南;

import binary_tree.BinaryTree;
import org.junit.Test;
import binary_tree.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * created by Ethan-Walker on 2018/12/26
 */
public class Q033_PrintBT {

    /**
     * 形象地打印二叉树
     * 如果一个节点2的左子树为空,右子树不空 :  该节点表示未 ^2
     * 左右子树均为空 ^2^
     * 1
     * ^2  3^
     * ^4^ ^5^
     *
     * @param root
     * @return
     */
    public List<List<String>> printBT(TreeNode root) {

        List<List<String>> result = new ArrayList<>();
        List<String> list = new ArrayList<>();

        ArrayDeque<TreeNode<Integer>> queue = new ArrayDeque<>();
        int curLevelCount = 1, nextLevelCount = 0;
        queue.offer(root);
        TreeNode<Integer> node = null;
        while (!queue.isEmpty()) {
            node = queue.poll();
            // 访问当前节点
            StringBuilder sb = new StringBuilder();
            if (node.left == null) {
                sb.append("^");
            } else {
                queue.offer(node.left);
                nextLevelCount++;
            }
            sb.append(node.val);
            if (node.right == null) {
                sb.append("^");
            } else {
                queue.offer(node.right);
                nextLevelCount++;
            }

            list.add(sb.toString());

            curLevelCount--;
            if (curLevelCount == 0) {
                result.add(list);
                list = new ArrayList<>();
                curLevelCount = nextLevelCount;
                nextLevelCount = 0;
            }
        }
        return result;
    }

    @Test
    public void test() {
        BinaryTree<Integer> bt = new BinaryTree<>();
        int[] pre = {1, 2, 4, 3, 5};
        int[] in = {2, 4, 1, 5, 3};
        bt.buildTree(pre, in);
        List<List<String>> shape = printBT(bt.getRoot());

        shape.forEach(e -> System.out.println(e));

    }

}
