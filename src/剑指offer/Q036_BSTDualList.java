package 剑指offer;

import binary_search_tree.BinarySearchTree;
import binary_search_tree.Node;
import org.junit.Test;

/**
 * 二叉搜索树转换成从小到大排序的双向链表
 * created by Ethan-Walker on 2018/12/8
 */
public class Q036_BSTDualList {

    private Node head = null;
    private Node pre = null;

    public void inOrder(Node node) {
        if (node == null) return;
        inOrder(node.left);

        node.left = pre;
        if (pre != null) {
            pre.right = node;
        }
        pre = node;
        if (head == null) {
            head = pre;
        }
        inOrder(node.right);
    }


    @Test
    public void test() {
        BinarySearchTree tree = new BinarySearchTree();
        tree.buildBST(new int[]{10, 6, 14, 4, 8, 16});

        inOrder(tree.getRoot());

        System.out.println();
    }

}