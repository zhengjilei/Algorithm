package leetcode;

import 程序员代码面试指南.TreeNode;

/**
 * created by Ethan-Walker on 2018/12/26
 */
public class Q105_BuildTree {


    public TreeNode buildTree(int[] preorder, int[] inorder) {

        return buildTree(preorder, inorder, 0, 0, inorder.length - 1);
    }

    public TreeNode buildTree(int[] pre, int[] in, int preStart, int inStart, int inEnd) {

        if (inStart > inEnd) return null;

        // inStart==inEnd 也要进行下面的步骤，防止pre 和 in 序列不匹配

        //  构造以 pre[preStart] 为根节点的树
        int j = inStart; // 中序中找到根节点
        while (j <= inEnd && in[j] != pre[preStart]) {
            j++;
        }
        if (j > inEnd) {
            throw new RuntimeException("前序中序不匹配");
        }

        // 中序中以 索引 j 划分成左右两部分，分别构造左右子树
        TreeNode root = new TreeNode(pre[preStart]);
        int leftChildCount = j - inStart + 1; // 左子树的节点数目
        root.left = buildTree(pre, in, preStart + 1, inStart, j - 1);
        root.right = buildTree(pre, in, preStart + leftChildCount, j + 1, inEnd);

        return root;

    }


}
