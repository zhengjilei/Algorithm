package leetcode;

import java.util.ArrayDeque;

/**
 * created by Ethan-Walker on 2019/3/20
 */
public class Q117_ConnectNextTreeNode {
    public Node connect(Node root) {
        if (root == null) {
            return root;
        }

        ArrayDeque<Node> queue = new ArrayDeque<>();
        queue.offer(root);
        Node t;
        int curLevelCount = 1;
        int nextLevelCount = 0;
        Node prev = null;
        while (!queue.isEmpty()) {
            t = queue.poll();
            if (prev != null) {
                prev.next = t;
            }
            prev = t;
            curLevelCount--;
            if (t.left != null) {
                queue.offer(t.left);
                nextLevelCount++;
            }
            if (t.right != null) {
                queue.offer(t.right);
                nextLevelCount++;
            }

            if (curLevelCount == 0) {
                t.next = null; // t 是当前层的最后一个节点

                curLevelCount = nextLevelCount;
                nextLevelCount = 0;
                prev = null; // 下一层 prev 设为 null
            }
        }

        return root;
    }

    public Node connect2(Node root) {
        if (root == null) return null;
        if (root.left != null) {
            if (root.right != null) {
                root.left.next = root.right;
            } else {
                root.left.next = nextNode(root.next);
            }
        }

        if (root.right != null) {
            root.right.next = nextNode(root.next);
        }

        // 先确保 root.right 下的节点的已完全连接，因 root.left 下的节点的连接
        // 需要 root.left.next 下的节点的信息，若 root.right 下的节点未完全连
        // 接（即先对 root.left 递归），则 root.left.next 下的信息链不完整，将
        // 返回错误的信息。可能出现的错误情况如下图所示。此时，底层最左边节点将无
        // 法获得正确的 next 信息：
        //                  o root
        //                 / \
        //     root.left  o —— o  root.right
        //               /    / \
        //              o —— o   o
        //             /        / \
        //            o        o   o
        connect2(root.right);
        connect2(root.left);
        return root;

    }

    /**
     * 从 node 开始找到第一个子节点（左右子节点都为空，迭代找node.next）
     *
     * @param node
     * @return
     */
    public Node nextNode(Node node) {
        while (node != null) {
            if (node.left != null) return node.left;
            if (node.right != null) return node.right;
            node = node.next;
        }
        return null;
    }

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }
}
