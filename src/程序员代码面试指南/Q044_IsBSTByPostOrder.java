package 程序员代码面试指南;

import org.junit.Test;

/**
 * 给定一个序列，判断该序列是否可能是BST后序遍历的结果
 * <p>
 * 假设是 BST 后序遍历的结果，由于将序列排序后的升序结果就是中序
 * 中序和后序序列能确定一棵二叉树，故该后序序列能唯一确定一棵BST
 * <p>
 * 分析 BST 的后序序列的特点
 * 1. 最后一个节点root是 根节点，故前面 n-1 个节点满足 左半部分全部 < root, 右半部分全部> root
 * 2. 上面分出的左右子树 也满足条件 1
 * created by Ethan-Walker on 2018/12/29
 */
public class Q044_IsBSTByPostOrder {

    public boolean isBSTPostOrder(int[] postOrder) {
        return isBSTPostOrder(postOrder, 0, postOrder.length);
    }

    private boolean isBSTPostOrder(int[] postOrder, int start, int length) {
        if (length <= 2) return true;// 0、1、2 个节点都返回 true （不管怎么排列，都是对的）
        int root = postOrder[start + length - 1];

        int j = 0;
        while (j < length - 1 && postOrder[j + start] < root) {
            j++;
        }

        // j+start 指向第一个大于 root 的节点（指向右子树）
        // j== length-1 ，说明只有左子树
        // j== 0 说明只有右子树

        // 判断右子树是否每个节点都大于 root
        for (int k = j + start + 1; k < start + length - 1; k++) {
            if (postOrder[k] < root) return false;
        }

        // 递归判断左子树、右子树是否满足二叉搜索树的限定条件
        boolean result = true;
        result = isBSTPostOrder(postOrder, start, j);//左子树为空也可以执行（因为开头当 length = 0 时返回 true）
        if (result) {
            result = isBSTPostOrder(postOrder, j, length - j - 1);
        }

        return result;
    }


    @Test
    public void test() {
//        int[] pre = {5, 3, 2, 4, 7, 6, 8};
//        int[] in = {2, 3, 4, 5, 6, 7, 8};
//        TreeUtil.buildTreeByPreAndIn(pre, in);
//        int[] post = {2, 4, 3, 6, 8, 7, 5};
        int[] post = {2, 4, 3, 6, 7, 8, 5};
        System.out.println(isBSTPostOrder(post));

    }

}
