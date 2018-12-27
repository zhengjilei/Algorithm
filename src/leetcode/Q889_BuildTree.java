package leetcode;

import org.junit.Test;
import 程序员代码面试指南.TreeNode;
import 程序员代码面试指南.TreeUtil;

/**
 * 根据前序和后序构造二叉树
 * <p>
 * 前序和后序不能唯一确定一棵二叉树
 * created by Ethan-Walker on 2018/12/27
 */
public class Q889_BuildTree {

    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        if (pre.length != post.length) {
            throw new RuntimeException("前序和后序不匹配");
        }
        return buildTreeByPreAndPost(pre, post, 0, post.length - 1, pre.length);
    }

    public TreeNode buildTreeByPreAndPost(int[] pre, int[] post, int preStart, int postEnd, int length) {

        if (length == 0) {
            return null;
        }
        if (pre[preStart] != post[postEnd]) {
            throw new RuntimeException("前序和后序不匹配");
        }
        if (length == 1) {
            // 只有一个节点
            return new TreeNode(post[postEnd]);
        }

        TreeNode root = new TreeNode(post[postEnd]);

        int j = 1; // 跳过前序序列第一个元素（根节点）
        while (j < length && pre[j + preStart] != post[postEnd - 1]) j++; //在前序序列中找到 等于后序序列的倒数第二个值（右子树的根节点）
        if (j == length) {
            throw new RuntimeException("前序和后序遍历");
        }

        int leftSize = j - 1;
        int rightSize = length - 1 - leftSize;
        root.left = buildTreeByPreAndPost(pre, post, preStart + 1, postEnd - rightSize - 1, leftSize);
        root.right = buildTreeByPreAndPost(pre, post, preStart + 1 + leftSize, postEnd - 1, rightSize);

        return root;
    }

    @Test
    public void test() {
        int[] pre = {1, 2, 4, 5, 3, 6, 7};
        int[] post = {4, 5, 2, 6, 7, 3, 1};

        TreeNode root = constructFromPrePost(pre, post);
        TreeUtil.printShapeBT(root);
        TreeUtil.levelOrder(root);
        TreeUtil.preOrder(root);

    }
}
