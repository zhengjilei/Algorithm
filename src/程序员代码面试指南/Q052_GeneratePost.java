package 程序员代码面试指南;

import org.junit.Test;

import java.util.Arrays;

/**
 * 根据先序和中序数组生成后序数组，不要重建整棵树
 * <p>
 * created by Ethan-Walker on 2018/12/30
 */
public class Q052_GeneratePost {


    public int[] generatePost(int[] pre, int[] in) {

        int[] post = new int[pre.length];

        generate(pre, in, post, 0, 0, 0, pre.length);
        return post;
    }

    private void generate(int[] pre, int[] in, int[] post, int preStart, int inStart, int postStart, int length) {
        if (length == 0) return;

        post[postStart + length - 1] = pre[preStart];// 后序最后一个等于前序 第一个

        // 在中序中找到 根节点
        int j = 0;
        while (j < length && in[j + inStart] != pre[preStart]) j++;
        if (j == length) throw new RuntimeException("序列有错");

        // in[j+inStart] == preStart
        // 将序列分成左右子树两部分，分别构造
        int leftSize = j, rightSize = length - j - 1;

        generate(pre, in, post, preStart + 1, inStart, postStart, leftSize);
        generate(pre, in, post, preStart + leftSize + 1, inStart + j + 1, postStart + leftSize, rightSize);


    }


    @Test
    public void test() {
        int[] pre = {1, 2, 4, 6, 10, 11, 7, 5, 8, 9, 3};
        int[] in = {10, 6, 11, 4, 7, 2, 8, 5, 9, 1, 3};
        TreeNode root = TreeUtil.buildTreeByPreAndIn(pre, in);
        TreeUtil.postOrder(root);

        int[] post = generatePost(pre, in);
        System.out.println(Arrays.toString(post));


    }

}
