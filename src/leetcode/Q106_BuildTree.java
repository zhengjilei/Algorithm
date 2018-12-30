package leetcode;

import 程序员代码面试指南.TreeNode;

/**
 * 中序和后序构建二叉树
 * 思路和 中序和前序一样，细节处理方面注意一下
 * created by Ethan-Walker on 2018/12/27
 */
public class Q106_BuildTree {


    public TreeNode buildTreeByInAndPost(int[] in, int[] post) {
        if (in == null || post == null || in.length != post.length) {
            throw new RuntimeException("中序和后序不匹配");
        }
        return buildTreeByInAndPost(in, post, 0, post.length - 1, post.length);
    }


    private TreeNode buildTreeByInAndPost(int[] in, int[] post, int inStart, int postEnd, int length) {
        if (length == 0) return null;
        // 构造以 post[postEnd]为根节点的树
        int j = 0;
        while (j < length && in[j + inStart] != post[postEnd]) {
            j++;
        }
        if (j >= length) {
            throw new RuntimeException("中序和后序不匹配");
        }
        // in[j] 等于 post[postEnd]

        // 左子树的数目 j , 右子树 length-j-1
        int rightSize = length - j - 1;
        TreeNode root = new TreeNode(post[postEnd]);
        root.left = buildTreeByInAndPost(in, post, inStart, postEnd - rightSize - 1, j); // 后序序列的前一部分是左子树节点
        root.right = buildTreeByInAndPost(in, post, inStart + j + 1, postEnd - 1, rightSize);  // 后序序列的后半部分是 右子树节点

        return root;

    }
}
