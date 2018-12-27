package 程序员代码面试指南;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * created by Ethan-Walker on 2018/12/26
 */
public class Q034_SerialBT {

    /**
     * 前序序列化
     * 每个节点后添加! 作为结束标志, 例 3!
     * 空节点使用 #!
     *
     * @param root
     * @return
     */
    public String serialByPreOrder(TreeNode root) {
        if (root == null) return "#!";

        String s = root.val + "!";
        s += serialByPreOrder(root.left);
        s += serialByPreOrder(root.right);

        return s;
    }

    /**
     * 反序列化
     * 示例  1!2!#!4!#!#!3!5!#!#!#!
     *
     * @param str
     * @return
     */
    public TreeNode reconByPreOrder(String str) {
        String[] values = str.split("!");
        ArrayDeque<String> queue = new ArrayDeque<>();
        for (int i = 0; i < values.length; i++) {
            queue.offer(values[i]);
        }

        return reconByPreOrder(queue);

    }

    /**
     * 前序反序列化
     *
     * @param queue
     * @return
     */
    private TreeNode reconByPreOrder(ArrayDeque<String> queue) {
        String s = queue.poll();
        if (s.equals("#")) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.valueOf(s));
        root.left = reconByPreOrder(queue);
        root.right = reconByPreOrder(queue);

        return root;
    }


    /**
     * 中序序列化
     *
     * @param root
     * @return
     */
    public String serialByInOrder(TreeNode root) {
        if (root == null) return "#!";

        String s = "";
        s += serialByInOrder(root.left);
        s += (root.val + "!");
        s += serialByInOrder(root.right);

        return s;
    }

    /**
     * 后序序列化
     *
     * @param root
     * @return
     */
    public String serialByPostOrder(TreeNode root) {
        if (root == null) return "#!";
        String s = "";
        s += serialByPostOrder(root.left);
        s += serialByPostOrder(root.right);
        s += (root.val + "!");
        return s;
    }
    // TODO: 2018/12/27  中序、后序反序列化可以实现吗？

    /**
     * 按层遍历序列化
     * 非空节点的左右子树即便为 null 也需要压入-> 队列中不允许压入 null(使用特殊节点代替 null)
     *
     * @param root
     * @return
     */
    public String serialByLevel(TreeNode root) {
        if (root == null) return "#!";

        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        StringBuilder sb = new StringBuilder();
        TreeNode node = null;
        TreeNode nullNode = new TreeNode(-1);

        while (!queue.isEmpty()) {
            node = queue.poll();
            if (node != nullNode) {
                sb.append(node.val).append("!");
            } else {
                sb.append("#!");
            }
            if (node != nullNode) {
                // 非空节点的  左子树右子树 即便为 null 也要压入队列
                queue.offer(node.left == null ? nullNode : node.left);
                queue.offer(node.right == null ? nullNode : node.right);
            }
        }
        return sb.toString();
    }


    public TreeNode deSerialByLevel(String s) {
        String[] values = s.split("!");
        int index = 0; // 指向 values 数组的索引，遍历values 数组，构造节点

        TreeNode head = generateNode(values[index++]);
        if (head == null) return null;

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(head);        // 队列中压的是 构造未完成的节点（还未构造左右子树）

        TreeNode node = null;
        while (!queue.isEmpty()) {
            node = queue.poll();

            node.left = generateNode(values[index++]);
            node.right = generateNode(values[index++]);
            if (node.left != null) queue.offer(node.left); // 将左子树继续进行构造
            if (node.right != null) queue.offer(node.right);
        }
        return head;
    }

    public TreeNode generateNode(String s) {
        if (s.equals("#")) {
            return null;
        } else {
            return new TreeNode(Integer.valueOf(s));
        }
    }


    @Test
    public void test() {
        TreeNode root = TreeUtil.buildTreeByPreOrder("1!2!#!4!#!#!3!5!#!#!#!");
        String levelStr = serialByLevel(root); // 1!2!3!#!4!5!#!#!#!#!#!
        TreeNode root2 = deSerialByLevel(levelStr);

//        TreeUtil.preOrder(root2);
//        TreeUtil.inOrder(root2);
//        TreeUtil.postOrder(root2);
//        TreeUtil.levelOrder(root2);

        System.out.println(serialByInOrder(root2));
        System.out.println(serialByPostOrder(root2));
//        System.out.println(levelStr);
    }
}
