package 剑指offer;

import binary_tree.BinaryTree;
import binary_tree.TreeNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * created by Ethan-Walker on 2018/12/7
 */
public class Q034_TreeSumPath {


    /**
     * 在以 node 为根的树上，寻找和为 val 的路径, 当前正在寻找第 index 个节点
     * <p>
     * 数组 path + 索引起到记录栈的作用
     */
    public void find(TreeNode<Integer> node, int val, int[] path, int index) {

        if (node == null) return;
        if (node.val == val) {
            if (node.left == null && node.right == null) {
                path[index] = node.val;
                // 只有是叶节点，才算是路径
                print(path, index + 1);
            }

            return;
        } else if (node.val < val) {
            path[index] = node.val;
            if (node.left != null) {
                find(node.left, val - node.val, path, index + 1);
            }
            if (node.right != null) {
                find(node.right, val - node.val, path, index + 1);
            }
        } else {
            return;
        }

    }

    public void print(int[] path, int len) {
        for (int i = 0; i < len; i++) {
            System.out.printf("%3d", path[i]);
        }
        System.out.println();
    }

    @Test
    public void test() {
        BinaryTree<Integer> tree = new BinaryTree<>();
//        tree.buildTree(new int[]{10, 5, 4, 7, 12}, new int[]{4, 5, 7, 10, 12});
        tree.buildTree(new int[]{10, 5, 8, 9, 6, 11, 18, 4}, new int[]{8, 9, 5, 6, 11, 10, 4, 18});
        int[] path = new int[1000];
        find(tree.getRoot(), 15, path, 0);
    }
}
