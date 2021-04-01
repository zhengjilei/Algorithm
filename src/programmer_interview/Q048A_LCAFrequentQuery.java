package programmer_interview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 频繁查询两个节点的最低公共祖先，减少单个的查询时间
 * 遍历一遍：将各节点的父节点信息，通过hash表建立链接
 * 建立hash表：时间复杂度O(n) 空间复杂度:O(n)
 * <p>
 * 以后每次查询时：就是两个链表的第一个公共节点问题
 * 时间复杂度: O(a+b) a/b 分别是链表长度< h
 * <p>
 * created by Ethan-Walker on 2018/12/30
 */
public class Q048A_LCAFrequentQuery {

    private HashMap<TreeNode, TreeNode> nodeParent;

    private void buildParent(TreeNode root) {
        if (root == null) return;

        if (root.left != null) {
            nodeParent.put(root.left, root);
            buildParent(root.left);
        }
        if (root.right != null) {
            nodeParent.put(root.right, root);
            buildParent(root.right);
        }
    }

    public void initParent(TreeNode root) {
        nodeParent = new HashMap<>();
        nodeParent.put(root, null);
        buildParent(root);
    }

    /**
     * 时间复杂度: O(m+n) m、n 分别是node1 node2 到root节点的路径长度
     *
     * @param node1
     * @param node2
     * @return
     */
    public TreeNode lca(TreeNode node1, TreeNode node2) {
        List<TreeNode> path1 = new ArrayList<>();
        List<TreeNode> path2 = new ArrayList<>();
        TreeNode tempNode1 = node1, tempNode2 = node2;
        while (tempNode1 != null) {
            path1.add(tempNode1);
            tempNode1 = nodeParent.get(tempNode1);
        }

        while (tempNode2 != null) {
            path2.add(tempNode2);
            tempNode2 = nodeParent.get(tempNode2);
        }
        int step = Math.abs(path1.size() - path2.size());

        tempNode1 = node1;
        tempNode2 = node2;

        if (path1.size() < path2.size()) {
            tempNode1 = node2;
            tempNode2 = node1;
        }
        // tempNode1 指向长度更长的path 的首节点

        while (step > 0) {
            tempNode1 = nodeParent.get(tempNode1);
            step--;
        }

        while (tempNode1 != tempNode2) {
            tempNode1 = nodeParent.get(tempNode1);
            tempNode2 = nodeParent.get(tempNode2);
        }

        return tempNode1;

    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        initParent(root);
        return lca(p, q);
    }
}
