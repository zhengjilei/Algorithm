package 剑指offer;

import binary_tree.BinaryTree;
import binary_tree.TreeNode;
import org.junit.Test;

/**
 * 根据前序和中序构建二叉树
 * created by Ethan-Walker on 2018/12/2
 */
public class Q007_BuildBinaryTree {

    public TreeNode<Integer> buildTree(int[] a, int[] b, int aStart, int bStart, int bEnd)  {

        if (bEnd - bStart < 0) return null;
        int k = 0;
        int j = bStart;
        while (j <= bEnd && a[aStart] != b[j]) {
            j++;
            k++;
        }
        if (j > bEnd) {
            throw new RuntimeException("前序和中序不匹配"); // 前序中序不一致，必须得抛出异常，如果返回 Null 会误以为可以正确构建二叉树
        }
        // k 表示 左子树节点个数

        TreeNode<Integer> root = new TreeNode<>(a[aStart]);

        root.left = buildTree(a, b, aStart + 1, bStart, bStart + k - 1); // [bStart,bStart+k-1] 构造左子树
        root.right = buildTree(a, b, aStart + k + 1, bStart + k + 1, bEnd); // [bStart+k+1, bEnd] 构造右子树

        return root;
    }

    @Test
    public void testBuildTree() {
        int[] a = new int[]{1, 2, 4, 7, 3, 5, 6, 8,};
        int[] b = new int[]{4, 7, 2, 1, 5, 3, 8, 6};

        TreeNode<Integer> root = null;
        root = buildTree(a, b, 0, 0, 7);
        BinaryTree<Integer> tree = new BinaryTree<>();
        tree.setRoot(root);
        tree.preOrderRecur();
        tree.inOrderRecur();
        tree.postOrderRecur();
        tree.levelOrder();
    }

    @Test
    public void testBuildTree2() {
        int[] a = new int[]{0};
        int[] b = new int[]{2};

        TreeNode<Integer> root = null;
        root = buildTree(a, b, 0, 0, 0);
        BinaryTree<Integer> tree = new BinaryTree<>();
        tree.setRoot(root);
        tree.preOrderRecur();
        tree.inOrderRecur();
        tree.postOrderRecur();
        tree.levelOrder();
    }

}
