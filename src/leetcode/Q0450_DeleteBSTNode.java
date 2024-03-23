package leetcode;

import programmer_interview.TreeNode;

/**
 * created by Ethan-Walker on 2019/2/25
 */
public class Q0450_DeleteBSTNode {

    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (key < root.val) {

            root.left = deleteNode(root.left, key);
            return root;
        } else if (key > root.val) {
            root.right = deleteNode(root.right, key);
            return root;
        } else {

            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            } else {
                // 左右子树均不为空
                // 删除中序下 root 的后继节点, 即右子树中序下的第一个节点
                TreeNode successor = root.right;
                while (successor.left != null) {
                    successor = successor.left;
                }
                // 递归删除原 successor, 新 successor 作为新的root节点，取代 root 位置
                successor.right = delMin(root.right);
                successor.left = root.left;

                return successor;
            }
        }
    }

    // 递归删除最小值，并返回根节点
    public TreeNode delMin(TreeNode root) {
        if (root.left == null) {
            return root.right;
        }
        root.left = delMin(root.left);
        return root;
    }

}
