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
    private Node pre = null; // 当前节点的前趋节点,执行结束后就是双向链表尾节点

    public void inOrder(Node node) {
        if (node == null) return;
        inOrder(node.left);

        node.left = pre;
        if (pre != null) {
            pre.right = node;
        }
        // 递归到最左下节点，设置为链表头结点
        if (head == null) {
            head = node;
        }
        pre = node;

        inOrder(node.right);
    }


    public void print() {
        Node node = head;
        while (node != null) {
            System.out.print(node.val + ",");
            node = node.right;
        }
        System.out.println();
    }

    public void reversePrint() {
        Node node = pre;
        while (node != null) {
            System.out.print(node.val + ",");
            node = node.left;
        }
        System.out.println();
    }


    @Test
    public void test() {
        BinarySearchTree tree = new BinarySearchTree();
        tree.buildBST(new int[]{10, 6, 14, 4, 8, 16});

        inOrder(tree.getRoot());

        print();
        reversePrint();
    }

}