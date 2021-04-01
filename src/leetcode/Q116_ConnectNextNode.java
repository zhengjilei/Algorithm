package leetcode;

/**
 * created by Ethan-Walker on 2019/3/20
 */
public class Q116_ConnectNextNode {
    /**
     * 满二叉树
     * 注意：初始时所有节点的 next 都是 null
     *
     * @param root
     * @return
     */
    public Node connect(Node root) {
        if (root == null) return root;
        if (root.left != null) { // 左子树不为空
            root.left.next = root.right; // 设置左子树的 next 指向右子树（满二叉树）
        }
        if (root.right != null && root.next != null) {
            root.right.next = root.next.left; // 设置右子树的 next 指向 root.next.left
        }
        // root.left root.right 都已经链接好

        // 递归处理 root.left root.right
        connect(root.left);
        connect(root.right);

        return root;
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

    ;
}
