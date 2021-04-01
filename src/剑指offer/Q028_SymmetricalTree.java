package 剑指offer;

import binary_tree.BinaryTree;
import org.junit.Test;
import binary_tree.TreeNode;

/**
 * 判断一个二叉树是否是对称的
 * created by Ethan-Walker on 2018/12/7
 */
public class Q028_SymmetricalTree {
    public boolean isSymmetrical(TreeNode<Integer> root) {
        if (root == null) return true;
        if (root.left == null && root.right == null) return true;

        if (root.left != null && root.right != null) {
            return judge(root.left, root.right);
        }
        return false;
    }

    public boolean judge(TreeNode<Integer> root1, TreeNode<Integer> root2) {
        if (root1 == null && root2 == null) return true;
        if (root1 == null || root2 == null) return false; // 只有一个为空
        if (!root1.val.equals(root2.val)) {
            return false;
        }
        return judge(root1.left, root2.right) && judge(root1.right, root2.left);
    }

    @Test
    public void test() {
        BinaryTree<Integer> binaryTree = new BinaryTree<>();
        try {
            binaryTree.buildTree(new int[]{7, 7, 7, 7, 7}, new int[]{7, 7, 7, 7, 7});
            binaryTree.levelOrder();

            System.out.println(isSymmetrical(binaryTree.getRoot()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
