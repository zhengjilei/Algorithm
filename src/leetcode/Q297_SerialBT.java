package leetcode;

import org.junit.Test;
import 程序员代码面试指南.TreeUtil;
import 程序员代码面试指南.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * created by Ethan-Walker on 2018/12/27
 */
public class Q297_SerialBT {

    // 前序序列化 和 前序反序列化

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) return "#!";
        StringBuilder sb = new StringBuilder();  // 参数是 容量，不是初始化字符串
        sb.append(root.val).append("!");
        sb.append(serialize(root.left));
        sb.append(serialize(root.right));
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.length() == 0) return null;
        String[] values = data.split("!");
        Queue<String> queue = new ArrayDeque<>();

        for (String val : values) {
            queue.offer(val);
        }
        return deserialize(queue);
    }

    private TreeNode deserialize(Queue<String> queue) {
        String s = queue.poll();
        if (s.equals("#")) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(s));
        root.left = deserialize(queue);
        root.right = deserialize(queue);

        return root;

    }

    @Test
    public void test() {
        TreeNode root = deserialize("1!2!#!4!#!#!3!5!#!#!#!");
        TreeUtil.preOrder(root);
        TreeUtil.inOrder(root);
        System.out.println(serialize(deserialize("1!2!#!4!#!#!3!5!#!#!#!")));
    }
}
