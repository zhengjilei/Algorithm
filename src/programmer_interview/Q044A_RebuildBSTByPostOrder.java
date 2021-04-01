package programmer_interview;

import org.junit.Test;

/**
 * 给定一个搜索二叉树的后序序列，重建搜索二叉树
 * created by Ethan-Walker on 2018/12/29
 */
public class Q044A_RebuildBSTByPostOrder {


    public TreeNode rebuildBSTByPostOrder(int[] postOrder) {
        if (postOrder == null || postOrder.length == 0) return null;
        return rebuildBSTByPostOrder(postOrder, postOrder.length - 1, postOrder.length);
    }

    public TreeNode rebuildBSTByPostOrder(int[] postOrder, int postEnd, int length) {
        if (length == 0) return null;

        int start = postEnd - length + 1;
        int j = 0;
        while (j + start < postEnd && postOrder[j + start] < postOrder[postEnd]) j++;
        // j+start 指向第一个比 root 大的节点
        // j+start  = start 只有右子树
        // j+start =postEnd 只有左子树

        int leftSize = j, rightSize = length - j - 1;

        TreeNode root = new TreeNode(postOrder[postEnd]);
        root.left = rebuildBSTByPostOrder(postOrder, j + start - 1, leftSize);
        root.right = rebuildBSTByPostOrder(postOrder, postEnd - 1, rightSize);

        return root;

    }

    @Test
    public void test() {
        int[] postOrder = {2, 3, 8, 7, 5};
        TreeNode root = rebuildBSTByPostOrder(postOrder);
        TreeUtil.printShapeBT(root);
    }
}
