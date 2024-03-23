package leetcode;

import programmer_interview.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * created by Ethan-Walker on 2019/3/20
 */
public class Q0113_PathSum {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {

        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        find(new ArrayList<>(), root, sum, res);
        return res;
    }

    public void find(List<Integer> path, TreeNode node, int target, List<List<Integer>> res) {
        if (node.left == null && node.right == null) {
            if (node.val == target) {
                path.add(target);
                ArrayList<Integer> list = new ArrayList<>(path);
                res.add(list);
            }
            return;
        }

        path.add(node.val);
        target -= node.val;

        if (node.left != null) {
            // 深拷贝
            find(new ArrayList<>(path), node.left, target, res);
        }
        // 这里不需要拷贝，因为左子节点 深拷贝不影响 path的值，这里直接使用 path 即可
        if (node.right != null) {
            find(path, node.right, target, res);
        }
    }
}
