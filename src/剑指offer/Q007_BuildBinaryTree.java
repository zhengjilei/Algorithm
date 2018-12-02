package 剑指offer;

import binary_tree.BinaryTree;
import binary_tree.Node;
import org.junit.Test;

/**
 * 根据前序和中序构建二叉树
 * created by Ethan-Walker on 2018/12/2
 */
public class Q007_BuildBinaryTree {

    public Node<Integer> buildTree(int[] a, int[] b, int aStart, int bStart, int bEnd) throws Exception {

        if (bEnd - bStart < 0) return null;
        int k = 0;
        int j = bStart;
        while (j <= bEnd && a[aStart] != b[j]) {
            j++;
            k++;
        }
        if (j > bEnd) {
            throw new Exception("前序和中序不匹配"); // 前序中序不一致，必须得抛出异常，如果返回 Null 会误以为可以正确构建二叉树
        }
        // k 表示 左子树节点个数

        Node<Integer> root = new Node<>(a[aStart]);

        root.leftChild = buildTree(a, b, aStart + 1, bStart, bStart + k - 1); // [bStart,bStart+k-1] 构造左子树
        root.rightChild = buildTree(a, b, aStart + k + 1, bStart + k + 1, bEnd); // [bStart+k+1, bEnd] 构造右子树

        return root;
    }

    @Test
    public void testBuildTree() {
        int[] a = new int[]{1, 2, 4, 7, 3, 5, 6, 8,};
        int[] b = new int[]{4, 7, 2, 1, 5, 3, 8, 6};

        Node<Integer> root = null;
        try {
            root = buildTree(a, b, 0, 0, 7);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
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

        Node<Integer> root = null;
        try {
            root = buildTree(a, b, 0, 0, 0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
        BinaryTree<Integer> tree = new BinaryTree<>();
        tree.setRoot(root);
        tree.preOrderRecur();
        tree.inOrderRecur();
        tree.postOrderRecur();
        tree.levelOrder();
    }

}
