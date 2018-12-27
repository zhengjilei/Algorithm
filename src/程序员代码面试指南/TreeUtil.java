package 程序员代码面试指南;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * created by Ethan-Walker on 2018/12/26
 */
public class TreeUtil {

    public static TreeNode buildTreeByPreOrder(String str) {

        String[] values = str.split("!");
        Queue<String> queue = new ArrayDeque<>();

        for (String value : values) {
            queue.offer(value);
        }

        return buildTreeByPreOrder(queue);
    }

    private static TreeNode buildTreeByPreOrder(Queue<String> queue) {
        String s = queue.poll();
        if (s.equals("#")) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.valueOf(s));
        root.left = buildTreeByPreOrder(queue);
        root.right = buildTreeByPreOrder(queue);
        return root;
    }

    public static String serialByPreOrder(TreeNode root) {
        if (root == null) return "#!";
        StringBuilder sb = new StringBuilder(root.val).append("!");

        sb.append(serialByPreOrder(root.left));
        sb.append(serialByPreOrder(root.right));
        return sb.toString();
    }

    public static void preOrder(TreeNode root) {
        System.out.print("前序: ");
        preOrderCur(root);
        System.out.println();
    }

    private static void preOrderCur(TreeNode root) {
        if (root == null) return;
        visit(root);

        preOrderCur(root.left);
        preOrderCur(root.right);
    }

    public static void inOrder(TreeNode root) {
        System.out.print("中序: ");

        inOrderRecur(root);
        System.out.println();
    }

    private static void inOrderRecur(TreeNode root) {
        if (root == null) return;
        inOrderRecur(root.left);
        visit(root);
        inOrderRecur(root.right);
    }


    public static void postOrder(TreeNode root) {
        System.out.print("后序: ");
        postOrderCur(root);
        System.out.println();
    }

    private static void postOrderCur(TreeNode root) {
        if (root == null) return;
        postOrderCur(root.left);
        postOrderCur(root.right);
        visit(root);
    }

    public static void levelOrder(TreeNode root) {
        System.out.print("按层: ");

        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            root = queue.poll();
            visit(root);
            if (root.left != null) queue.offer(root.left);
            if (root.right != null) queue.offer(root.right);
        }
        System.out.println();
    }

    private static void visit(TreeNode node) {
        if (node != null) {
            System.out.print(node.val + ",");
        }
    }


}
