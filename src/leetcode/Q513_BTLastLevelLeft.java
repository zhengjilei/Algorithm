package leetcode;

import programmer_interview.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * created by Ethan-Walker on 2019/3/15
 */
public class Q513_BTLastLevelLeft {
    public int findBottomLeftValue(TreeNode root) {
        if (root == null) return -1;
        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        int curLevelCount = 1, nextLevelCount = 0;
        int res = root.val;

        TreeNode t;
        while (!queue.isEmpty()) {
            t = queue.poll();

            curLevelCount--;
            if (t.left != null) {
                queue.offer(t.left);
                if (nextLevelCount == 0) {  // 如果nextLevelCount==0 说明t.left 节点是下一层首节点
                    res = t.left.val;
                }
                nextLevelCount++;
            }
            if (t.right != null) {
                queue.offer(t.right);
                if (nextLevelCount == 0) {  // 如果nextLevelCount==0 说明t.right 节点是下一层首节点
                    res = t.right.val;
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


    public int findBottomLeftValue2(TreeNode root) {
        if (root == null) return -1;
        List<Integer> res = new ArrayList<>();
        dfsLeftView(1, res, root);
        return res.get(res.size() - 1);
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


    public int findBottomLeftValue3(TreeNode root) {
        if (root == null) return -1;
        accessLevel = 0;
        dfs(1, root);
        return res;
    }

    int accessLevel; // 当前已经访问的层的最大序号，初始为 0
    int res; // 最终结果

    public void dfs(int level, TreeNode node) {
        if (node == null) return;
        if (level > accessLevel) { // 访问到更深的层
            res = node.val; //保存该层第一个节点
            accessLevel = level; // 更新当前访问的最深的层
        }
        // 优先访问左子树，让优先到达下一层的节点是左节点
        if (node.left != null) {
            dfs(level + 1, node.left);
        }
        if (node.right != null) {
            dfs(level + 1, node.right);
        }
    }
}
