package leetcode;

import org.junit.Test;
import programmer_interview.TreeNode;

/**
 * 根据前序和中序构造二叉树
 * created by Ethan-Walker on 2018/12/26
 */
public class Q105_BuildTree {


    public TreeNode buildTree(int[] preorder, int[] inorder) {

        return buildTree(preorder, inorder, 0, 0, inorder.length);
    }

    public TreeNode buildTree(int[] pre, int[] in, int preStart, int inStart, int length) {

        if (length == 0) return null;

        //  构造以 pre[preStart] 为根节点的树
        int j = 0;
        while (j < length && in[j + inStart] != pre[preStart]) { // 中序中找到根节点
            j++;
        }
        if (j >= length) {
            throw new RuntimeException("前序中序不匹配");
        }
        TreeNode root = new TreeNode(pre[preStart]);

        // 左子树的节点数 j , 右子树的节点数 length-j-1
        // 中序中以 索引 j+inStart 划分成左右两部分，分别构造左右子树
        root.left = buildTree(pre, in, preStart + 1, inStart, j);
        root.right = buildTree(pre, in, preStart + j + 1, j + inStart + 1, length - j - 1);

        return root;

    }


    @Test
    public void test() {
        int[] a = {3, 9, 20, 15, 7};
        int[] b = {9, 3, 15, 20, 7};
        buildTree(a, b);
    }


}
