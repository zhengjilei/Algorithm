package 程序员代码面试指南;

import org.junit.Test;

/**
 * 二叉树中两个节点的最近公共祖先
 * 节点没有 parent 指针，如果有，就转换成两个链表的第一个公共节点的问题
 * <p>
 * 条件：给定树的头结点和两个目标节点
 * <p>
 * created by Ethan-Walker on 2018/12/30
 */
public class Q048_LCA1 {


    /**
     * 方法一：遍历，找到到目标节点的路径
     * <p>
     * 时间复杂度: O(n)   3*O(n)+2*O(h)
     *
     * @param node1
     * @param node2
     * @return
     */
    public TreeNode closestFather(TreeNode root, TreeNode node1, TreeNode node2) {
        if (root == null || node1 == null || node2 == null) return null;

        int height = getHeight(root);
        TreeNode[] path1 = new TreeNode[height];// 路径的最大长度
        TreeNode[] path2 = new TreeNode[height];

        int length1 = getPath(root, node1, path1, 0);
        int length2 = getPath(root, node2, path2, 0);


        // 找到第一个 路径不等的两个节点的 上一个节点
        TreeNode prev = root;
        int i = 1; // 跳过根节点
        while (i < length1 && i < length2) {
            if (path1[i] != path2[i]) {
                break;
            }
            prev = path1[i];
            i++;
        }
        // 当某个节点是另一个节点的父节点时，也能处理,因为 prev 恰指向较短路径的最后一个节点
        return prev;
    }

    // 只有当找到路径时，才返回 > 1
    public int getPath(TreeNode root, TreeNode target, TreeNode[] path, int level) {
        if (root == null) return 0;

        path[level] = root;     // 存当前节点
        if (root == target) {
            return level + 1;
        }

        int length = 0;
        if ((length = getPath(root.left, target, path, level + 1)) > 0) {
            return length;
        }
        return getPath(root.right, target, path, level + 1); // 不管是否是 0 ，都要返回

    }

    int getHeight(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(getHeight(root.left), getHeight(root.right));
    }

    @Test
    public void test() {
        TreeNode a = new TreeNode(3);
        TreeNode b = new TreeNode(5);
        TreeNode c = new TreeNode(1);
        TreeNode d = new TreeNode(6);
        TreeNode e = new TreeNode(2);
        TreeNode f = new TreeNode(0);
        TreeNode g = new TreeNode(8);
        TreeNode h = new TreeNode(7);
        TreeNode i = new TreeNode(4);

        a.left = b;
        a.right = c;
        b.left = d;
        b.right = e;
        c.left = f;
        c.right = g;
        e.left = h;
        e.right = i;

        TreeNode father = closestFather(a, b, i);
        System.out.println(father.val);

    }

}
