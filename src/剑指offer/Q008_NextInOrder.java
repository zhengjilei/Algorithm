package 剑指offer;

import binary_tree.BinaryTree;
import binary_tree.TreeNode;
import org.junit.Test;

/**
 * 二叉树中序遍历下的下一个节点
 * created by Ethan-Walker on 2018/12/2
 */
public class Q008_NextInOrder {

    public TreeNode<Integer> getNext(TreeNode<Integer> node) {

        if (node == null) return null;
        TreeNode<Integer> p = node;

        if (p.right != null) {
            // 右子节点不为空，中序下一个节点为右子树的最左子节点
            p = p.right;
            while (p.left != null) {
                p = p.left;
            }
            return p;
        } else {
            // 右子节点为空, 寻找某个节点是父节点的左子节点
            TreeNode<Integer> parent;
            while (p != null) {
                parent = p.parent;
                if (parent != null) {
                    if (parent.left == p) {
                        // p 是 父节点的左子节点, 父节点即为中序下一个节点
                        return parent;
                    } else {
                        p = parent;
                    }
                } else {
                    break;
                }
            }
        }
        return null;
    }

    @Test
    public void testNextInOrder() {
        BinaryTree<Integer> tree = new BinaryTree<>();
//        int[] preOrder = new int[]{1, 2, 4, 5, 8, 9, 3, 6, 7};
//        int[] inOrder = new int[]{4, 2, 8, 5, 9, 1, 6, 3, 7};
        int[] preOrder = new int[]{1};
        int[] inOrder = new int[]{1};
        try {
            tree.buildTree(preOrder, inOrder);
            tree.inOrderByGetNext();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

    }
}
