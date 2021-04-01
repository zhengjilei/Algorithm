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

        if (node.right != null) {
            // 右子节点不为空，中序下一个节点为右子树的最左子节点
            node = node.right;
            while (node.left != null) {
                node = node.left;
            }
            return node;
        } else {
            // 右子节点为空, 寻找某个节点是父节点的左子节点
            while (node.parent != null && node == node.parent.right) {
                node = node.parent;
            }
            return node.parent; // node.parent==null 没找到 , node.parent!=null ,node 是父节点的左子节点
        }
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
