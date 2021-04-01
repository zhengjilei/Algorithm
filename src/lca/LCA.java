package lca;

import programmer_interview.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * created by Ethan-Walker on 2018/12/31
 */
public class LCA {

    /**
     * 1. 二叉搜索树的最近公共祖先
     * 当 node ==p 或者 q 时，说明 p 或者 q 是对方的祖先节点
     * 当 node.val 介于 p q 之间时，说明 node 是 p q 的最低祖先节点
     * p q > node.val 时，node 进入右子节点
     * p q < node.val 时，node 进入左子节点
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lca(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        TreeNode node = root;
        while (node != null) {
            if (node.val == p.val || node.val == q.val) return node;
            if (p.val > node.val && q.val > node.val) {
                node = node.right;
            } else if (p.val < node.val && q.val < node.val) {
                node = node.left;
            } else {
                // node.val 介于 p q 之间
                return node;
            }
        }
        return null; // 说明该二叉搜索树中没有 p q 的公共祖先节点
    }

    /**
     * 2. 普通二叉树的公共祖先
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lca2(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;

        TreeNode left = lca2(root.left, p, q);
        TreeNode right = lca2(root.right, p, q);

        if (left != null && right != null)  // 左子树中找到 p q 中的其中一个，右子树找到 p q 其中的一个
            return root;
        // 如果 left==null ,那么 right!=null, 说明右子树中找到 p q其中一个就返回了，左子树一个都没找到
        return left != null ? left : right;
    }

    /**
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lca3(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> path1 = new ArrayList<>();
        List<TreeNode> path2 = new ArrayList<>();
        int len1 = getPath(path1, 0, root, p);
        int len2 = getPath(path2, 0, root, q);

        int i = 0;
        TreeNode res = null;
        while (i < len1 && i < len2) {
            if (path1.get(i) != path2.get(i)) break;
            res = path1.get(i);
            i++;
        }
        return res;
    }


    public int getPath(List<TreeNode> path, int index, TreeNode node, TreeNode target) {
        if (node == null) return 0;

        if (path.size() > index) {
            path.set(index, node);
        } else {
            path.add(node);
        }

        if (node == target) {
            return index + 1;
        }
        int len = 0;
        if ((len = getPath(path, index + 1, node.left, target)) > 0) {
            return len;
        }

        return getPath(path, index + 1, node.right, target);
    }


    public TreeNode lca33(TreeNode root, TreeNode p, TreeNode q) {
        int height = getHeight(root);
        TreeNode[] path1 = new TreeNode[height];
        TreeNode[] path2 = new TreeNode[height];

        int len1 = getPath(path1, 0, root, p);
        int len2 = getPath(path2, 0, root, q);
        int i = 0;
        TreeNode res = null;
        while (i < len1 && i < len2) {
            if (path1[i] != path2[i]) break;
            res = path1[i];
            i++;
        }
        return res;
    }

    public int getPath(TreeNode[] path, int index, TreeNode node, TreeNode target) {
        if (node == null) return 0;
        path[index] = node;
        if (node == target) {
            return index + 1;
        }

        int len = 0;
        if ((len = getPath(path, index + 1, node.left, target)) > 0) {
            return len;
        }
        return getPath(path, index + 1, node.right, target);

    }

    public int getHeight(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(getHeight(root.left), getHeight(root.right));
    }


    HashMap<TreeNode, TreeNode> nodeParent;

    /**
     * 频繁查询
     * 构建每个节点和其父节点的关联关系 HashMap: < node,parent>
     * 以后每次查询时：就转换成两个链表的第一个公共节点的问题了
     * O(m+n)  m/n 分别是两个节点到根节点的路径长度
     *
     * @param root
     * @return
     */
    public void lca33(TreeNode root) {
        nodeParent = new HashMap<>();
        initParent(root);

    }

    public TreeNode queryLca(TreeNode p, TreeNode q) {
        int len1 = 0;
        TreeNode a = p, b = q;
        while (a != null) {
            len1++;
            a = nodeParent.get(a);
        }

        int len2 = 0;
        while (b != null) {
            len2++;
            b = nodeParent.get(b);
        }
        int step = Math.abs(len1 - len2);
        TreeNode fast = p, slow = q;
        if (len1 < len2) {
            fast = q;
            slow = p;
        }
        while (step > 0) {
            fast = nodeParent.get(fast);
            step--;
        }

        while (fast != slow) {
            fast = nodeParent.get(fast);
            slow = nodeParent.get(slow);
        }
        return fast;
    }

    public void initParent(TreeNode node) {
        if (node == null) return;
        if (node.left != null) {
            nodeParent.put(node.left, node);
            initParent(node.left);
        }
        if (node.right != null) {
            nodeParent.put(node.right, node);
            initParent(node.right);
        }
    }


}
