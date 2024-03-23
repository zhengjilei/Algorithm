package leetcode;

import org.junit.Test;
import programmer_interview.TreeNode;
import programmer_interview.TreeUtil;

/**
 * 根据前序和后序构造二叉树
 * <p>
 * 前序和后序不能唯一确定一棵二叉树
 * created by Ethan-Walker on 2018/12/27
 */
public class Q0889_BuildTree {

    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        if (pre.length != post.length) {
            throw new RuntimeException("前序和后序不匹配");
        }
        return buildTreeByPreAndPost(pre, post, 0, post.length - 1, pre.length);
    }

    /**
     * 这里构造出来的二叉树是 众多结果中的一种
     * 当某个节点只有一个子节点时，优先将这个节点作为右子节点
     *
     * @param pre
     * @param post
     * @param preStart
     * @param postEnd
     * @param length
     * @return
     */
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
            throw new RuntimeException("前序和后序不匹配");
        }

        int leftSize = j - 1;
        int rightSize = length - 1 - leftSize;
        root.left = buildTreeByPreAndPost(pre, post, preStart + 1, postEnd - rightSize - 1, leftSize);
        root.right = buildTreeByPreAndPost(pre, post, preStart + 1 + leftSize, postEnd - 1, rightSize);

        return root;
    }

    /**
     * 当某个节点只有一个子节点时，优先将这个节点作为左子节点
     * @param pre
     * @param post
     * @param preStart
     * @param postStart
     * @param length
     * @return
     */
    public TreeNode buildTreeByPreAndPost2(int[] pre, int[] post, int preStart, int postStart, int length) {
        if (length == 0) return null;
        if (pre[preStart] != post[postStart + length - 1])
            throw new RuntimeException("前序和后序不匹配");
        if (length == 1) {
            return new TreeNode(pre[preStart]);
        }

        TreeNode root = new TreeNode(pre[preStart]);
        int leftRootIndex = preStart + 1;
        int j = postStart;
        while (j < postStart + length - 1 && post[j] != pre[leftRootIndex]) j++;

        if (j == postStart + length - 1) throw new RuntimeException("前序和后序不匹配");

        int leftLen = j - postStart + 1;
        int rightLen = length - leftLen - 1;

        root.left = buildTreeByPreAndPost2(pre,post,preStart+1, postStart,leftLen);
        root.right = buildTreeByPreAndPost2(pre,post,preStart+leftLen+1, j+1, rightLen);
        return root;
    }

    @Test
    public void test() {
        int[] pre = {1, 2, 4, 5, 3, 6, 7};
        int[] post = {4, 5, 2, 6, 7, 3, 1};

        TreeNode root = constructFromPrePost(pre, post);
        TreeUtil.printShapeBT(root);
        int[] pre2 = {1, 2, 4, 6, 3, 5, 7};
        int[] in2 = {4, 6, 2, 1, 5, 7, 3};
        int[] post2 = {6, 4, 2, 7, 5, 3, 1};

        TreeNode origin = TreeUtil.buildTreeByPreAndIn(pre2, in2);
        System.out.println("原型：");
        TreeUtil.printShapeBT(origin); // 树的原型

        TreeNode result = constructFromPrePost(pre2, post2);
        System.out.println("重建：");
        TreeUtil.printShapeBT(result); // 根据先序后序构造出来的结果

    }
}
