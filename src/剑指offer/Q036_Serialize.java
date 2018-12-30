package 剑指offer;

import binary_tree.BinaryTree;
import binary_tree.TreeNode;

/**
 * 二叉树的序列化和反序列化
 * created by Ethan-Walker on 2018/12/8
 */
public class Q036_Serialize {


    public String serializable(TreeNode<Integer> root) {

        StringBuilder sb = new StringBuilder();
        serializable(root, sb);
        return sb.toString();
    }

    /**
     * 序列化二叉树
     *
     * @param node
     * @param sb
     */
    public void serializable(TreeNode<Integer> node, StringBuilder sb) {
        if (node == null) {
            sb.append("$");
            return;
        }
        sb.append(node.val);
        serializable(node.left, sb);
        serializable(node.right, sb);
    }


    private int index = 0;
    private String str;

    /**
     * 反序列化二叉树
     *
     * @return
     */
    public TreeNode<Integer> deSerialize() {
        if (str.charAt(index) == '$') {
            index++;
            return null;
        }
        TreeNode<Integer> node = new TreeNode<>(str.charAt(index++) - '0');
        node.left = deSerialize();
        node.right = deSerialize();
        return node;
    }

    public static void main(String[] args) {
        Q036_Serialize a = new Q036_Serialize();
        BinaryTree<Integer> tree = new BinaryTree<>();

        int[] pre1 = new int[]{1, 2, 4, 5};
        int[] in1 = new int[]{1, 2, 4, 5};
        tree.buildTree(pre1, in1);
        String s = a.serializable(tree.getRoot());
        System.out.println(s);
        a.str = s;

        TreeNode<Integer> newRoot = a.deSerialize();
        BinaryTree<Integer> tree2 = new BinaryTree<>();
        tree2.setRoot(newRoot);
        tree2.preOrderRecur();
        tree2.inOrderRecur();

    }
}
