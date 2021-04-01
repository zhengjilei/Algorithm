package leetcode;

import programmer_interview.TreeNode;

import java.util.LinkedList;

/**
 * created by Ethan-Walker on 2019/3/21
 */
public class Q449_SerialBT {
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
        String[] split = data.split(",");
        if (split == null || split.length == 0) return null;
        LinkedList<String> queue = new LinkedList<>();
        for (String s : split) {
            queue.offer(s);
        }
        return deserial(queue);
    }

    public TreeNode deserial(LinkedList<String> queue) {
        if (queue.isEmpty()) return null;
        String s = queue.poll();
        if ("$".equals(s)) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(s));
        root.left = deserial(queue);
        root.right = deserial(queue);
        return root;
    }
}
