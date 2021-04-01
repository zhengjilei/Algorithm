package leetcode;

import programmer_interview.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * 二叉树的右视图
 * created by Ethan-Walker on 2019/3/15
 */
public class Q199_BTRightSideView {
    /**
     * 思路：二叉树的层次遍历，当遍历的节点是当前层最后一个节点时，保存下来
     *
     * @param root
     * @return
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        int curLevelCount = 1;
        int nextLevelCount = 0;
        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        TreeNode tmp;
        while (!queue.isEmpty()) {
            tmp = queue.poll();

            curLevelCount--;

            if (tmp.left != null) {
                queue.offer(tmp.left);
                nextLevelCount++;
            }
            if (tmp.right != null) {
                queue.offer(tmp.right);
                nextLevelCount++;
            }

            if (curLevelCount == 0) {
                // tmp 是当前层的最后一个节点
                res.add(tmp.val);

                curLevelCount = nextLevelCount;
                nextLevelCount = 0;
            }
        }
        return res;
    }

    /**
     * 二叉树左视图:每一层第一个节点
     *
     * @param root
     * @return
     */
    public List<Integer> leftSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        int curLevelCount = 1, nextLevelCount = 0;
        res.add(root.val);                      // 先将 root.val 添加进去, 左视图第一个节点
        TreeNode t;
        while (!queue.isEmpty()) {
            t = queue.poll();

            curLevelCount--;
            if (t.left != null) {
                queue.offer(t.left);
                if (nextLevelCount == 0) {  // 如果nextLevelCount==0 说明t.left 节点是下一层首节点
                    res.add(t.left.val);
                }
                nextLevelCount++;
            }
            if (t.right != null) {
                queue.offer(t.right);
                if (nextLevelCount == 0) {  // 如果nextLevelCount==0 说明t.right 节点是下一层首节点
                    res.add(t.right.val);
                }
                nextLevelCount++;
            }
            if (curLevelCount == 0) {
                curLevelCount = nextLevelCount;
                nextLevelCount = 0;
            }
        }
        return res;
    }

    public List<Integer> rightSideView2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        dfsRightView(1, res, root);

        return res;
    }

    public void dfsRightView(int level, List<Integer> res, TreeNode node) {
        if (node == null) return;

        if (level > res.size()) {
            res.add(node.val);
        }
        if (node.right != null) {
            dfsRightView(level + 1, res, node.right);
        }
        if (node.left != null) {
            dfsRightView(level + 1, res, node.left);
        }

    }

    public List<Integer> leftSideView2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        dfsLeftView(1, res, root);
        return res;
    }

    public void dfsLeftView(int level, List<Integer> res, TreeNode node) {
        if (node == null) return;

        if (level > res.size()) {
            res.add(node.val);
        }

        if (node.left != null) {
            dfsLeftView(level + 1, res, node.left);
        }
        if (node.right != null) {
            dfsLeftView(level + 1, res, node.right);
        }

    }
}
