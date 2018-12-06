package 剑指offer;

import binary_tree.BinaryTree;
import org.junit.Test;

import java.util.ArrayDeque;

/**
 * 求二叉树的镜像
 * created by Ethan-Walker on 2018/12/6
 */
public class Q027_MirrorTree {

    public void mirrorTreeRecur(TreeNode root) {
        if (root == null) return;

        TreeNode left = root.left;
        TreeNode right = root.right;
        root.left = right;
        root.right = left;
        mirrorTreeRecur(root.left);
        mirrorTreeRecur(root.right);
    }

    public void mirrorTree(TreeNode root) {
        if (root == null) return;

        ArrayDeque<TreeNode> stack = new ArrayDeque<>();
        TreeNode node = root, temp;
        stack.push(root);
        while (!stack.isEmpty()) {
            node = stack.pop();
            temp = node.left;
            node.left = node.right;
            node.right = temp;

            if (node.left != null) stack.push(node.left);
            if (node.right != null) stack.push(node.right);
        }

    }

    @Test
    public void test() {
        BinaryTree<Integer> binaryTree = new BinaryTree<>();
        try {
            binaryTree.buildTree(new int[]{8, 6, 5, 7}, new int[]{5, 6, 7, 8});
            binaryTree.postOrderRecur();
            binaryTree.mirrorTree();
            binaryTree.postOrderRecur();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}
