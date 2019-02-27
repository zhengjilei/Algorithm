package 剑指offer;

import binary_tree.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 二叉树的序列化和反序列化
 * created by Ethan-Walker on 2018/12/8
 */
public class Q036_Serialize {
    // Encodes a tree to a single string.


    public String serialize(TreeNode root) {
        if (root == null) return "$,";
        StringBuilder sb = new StringBuilder(root.val + ",");
        sb.append(serialize(root.left));
        sb.append(serialize(root.right));
        return sb.toString();
    }


    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.length() == 0) return null;
        String[] split = data.split(",");
        if (split.length == 0) return null;
        Queue<String> queue = new ArrayDeque<>();
        for (String s : split) {
            queue.offer(s);
        }
        return deserial(queue);

    }

    public TreeNode deserial(Queue<String> queue) {

        String s = queue.poll();
        if (s.equals("$")) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(s));
        root.left = deserial(queue);
        root.right = deserial(queue);

        return root;
    }
}
