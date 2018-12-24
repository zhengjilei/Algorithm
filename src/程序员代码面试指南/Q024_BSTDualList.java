package 程序员代码面试指南;

import binary_search_tree.BinarySearchTree;
import binary_search_tree.Node;
import org.junit.Test;

import java.util.ArrayDeque;

/**
 * created by Ethan-Walker on 2018/12/24
 */
public class Q024_BSTDualList {


    private Node head = null;
    private Node prev = null;

    /**
     * 递归中序构建
     * 时间复杂度:O(n)
     * 空间复杂度:O(h) h 为二叉树高度
     *
     * @param node
     */
    public void inOrderBuildDualLL(Node node) {
        if (node == null) return;
        inOrderBuildDualLL(node.left);

        node.left = prev;
        if (prev != null) {
            prev.right = node;
        }
        if (head == null) {
            head = node;
        }
        prev = node;

        inOrderBuildDualLL(node.right);
    }

    /**
     * 栈实现中序遍历，依次访问每个节点，并连接
     * 时间复杂度: O(n)
     * 空间复杂度: 最坏 O(n)
     *
     * @return
     */
    public Node buildDual(Node root) {
        if (root == null) return null;
        ArrayDeque<Node> stack = new ArrayDeque<>();
        Node head = null, prev = null, node;

        do {
            while (root != null) {
                stack.push(root);
                root = root.left; // 保存，进入左子树
            }

            root = stack.pop();
            // 访问
            if (head == null) {
                head = root;
                prev = head;
            } else {
                prev.right = root;
                root.left = prev; // 前驱后继设置好

                prev = root;
            }
            root = root.right; // 进入右子树

        } while (!stack.isEmpty() || root != null);

        // 中序最后一个节点的右子树肯定为空 第一个节点的左子树也为空，故不需要再考虑将 第一个节点的left置为null, 最后一个节点的 right 置为null
        return head;
    }


    /**
     * 方法一 打印双链表
     */
    void print1() {
        System.out.print("顺序：");
        Node node = head;
        while (node != null) {
            System.out.print(node.val + ",");
            node = node.right;
        }
        System.out.println();


        System.out.print("逆序：");
        node = prev;
        while (node != null) {
            System.out.print(node.val + ",");
            node = node.left;
        }
        System.out.println();

    }

    /**
     * 方法二打印
     *
     * @param node
     */
    public void print2(Node node) {
        System.out.print("顺序：");
        Node tail = null;
        while (node != null) {
            System.out.print(node.val + ",");
            tail = node;
            node = node.right;
        }
        System.out.println();

        System.out.print("逆序：");
        while (tail != null) {
            System.out.print(tail.val + ",");
            tail = tail.left;
        }
        System.out.println();
    }

    public void reversePrint() {

    }

    @Test
    public void test() {
        BinarySearchTree tree = new BinarySearchTree();
        tree.buildBST(new int[]{6, 4, 2, 5, 1, 3, 7, 9, 8});

//        inOrderBuildDualLL(tree.getRoot());
        Node head = buildDual(tree.getRoot());

        print2(head);
//        reversePrint();

    }
}
