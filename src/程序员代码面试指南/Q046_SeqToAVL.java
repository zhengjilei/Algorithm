package 程序员代码面试指南;

import org.junit.Test;

/**
 * 给定一个顺序序列，生成一个AVL 高度平衡的二叉搜索树
 * created by Ethan-Walker on 2018/12/29
 */
public class Q046_SeqToAVL {

    public TreeNode seqToAVL(int[] seq) {
        if (seq == null || seq.length == 0) return null;
        return seqToAVL(seq, 0, seq.length);
    }

    public TreeNode seqToAVL(int[] seq, int start, int length) {
        if (length == 0) return null;
        int mid = start + length / 2;
        int leftSize = mid - start;
        TreeNode root = new TreeNode(seq[mid]);
        root.left = seqToAVL(seq, start, leftSize);
        root.right = seqToAVL(seq, mid + 1, length - leftSize - 1);
        return root;
    }

    @Test
    public void test() {
        int[] seq = {1, 2, 3, 4, 5, 6, 7, 8};
        TreeNode root = seqToAVL(seq);

        TreeUtil.printShapeBT(root);
    }
}
