package leetcode;

import 程序员代码面试指南.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * created by Ethan-Walker on 2018/12/27
 */
public class Q297_SerialBT {

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
