package 程序员代码面试指南;


import org.junit.Test;

import java.util.ArrayDeque;

/**
 * 二叉搜索树中找到两个错误节点
 * created by Ethan-Walker on 2018/12/28
 */
public class Q040_FindErrorTwoNodeInBST {


    /**
     * 找到搜索二叉树的两个错误节点
     * <p>
     * 1 2 3 4 5 6 7 8
     * <p>
     * 错误中序序列只有两种情况:
     * 1. 有两次逆序 例 3 7 是错误节点交换位置
     * 得到 1 2 7 4 5 6 3 8
     * 出现两次逆序: 7->4 6->3
     * 第一次逆序的两个节点 较大的（第一个）是错误节点
     * 第二次逆序的两个节点 较小的（第二个）是错误节点
     * <p>
     * 2. 只有一次逆序 ：当有两个错误节点相邻
     * 例： 2 和 3 逆序
     * 1 3 2 4 5 6 7 8
     * 出现逆序的两个节点均是错误节点
     *
     * @param bstRoot
     * @return
     */
    public TreeNode[] findErrorNodesInBST(TreeNode bstRoot) {
        if (bstRoot == null) return null;
        TreeNode[] nodes = new TreeNode[2];

        // 中序遍历二叉树
        ArrayDeque<TreeNode> stack = new ArrayDeque<>();
        TreeNode node = bstRoot;
        int prev = Integer.MIN_VALUE;

        boolean isFirst = true;
        TreeNode prevNode = null;
        do {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            if (!stack.isEmpty()) {
                node = stack.pop();
                //访问
                if (node.val < prev) {// 降序
                    if (isFirst) {
                        nodes[0] = prevNode;
                        nodes[1] = node;  // 第一次降序的第二个节点也要存，防止只有一次降 序
                        isFirst = false;
                    } else {
                        // 第二次降序
                        nodes[1] = node;
                        break;
                    }
                }
                prev = node.val;
                prevNode = node;
                node = node.right;
            }
        } while (!stack.isEmpty() || node != null);

        return nodes;
    }

    @Test
    public void test() {
//        int[] pre = {5, 3, 4, 8, 6, 7};
//        int[] in = {3, 4, 5, 6, 8, 7};

//        int[] pre = {5, 7, 4, 3, 6, 8};
//        int[] in = {7, 4, 5, 6, 3, 8};


        int[] pre = {5, 4, 3, 7, 6, 8};
        int[] in = {4, 3, 5, 6, 7, 8};
        TreeNode root = TreeUtil.buildTreeByPreAndIn(pre, in);

        TreeNode[] error1 = findErrorNodesInBST(root);
        System.out.println(error1[0].val + "," + error1[1].val);

    }
}
