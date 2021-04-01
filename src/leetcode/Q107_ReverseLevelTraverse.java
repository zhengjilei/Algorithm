package leetcode;

import programmer_interview.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * created by Ethan-Walker on 2018/12/26
 */
public class Q107_ReverseLevelTraverse {

    public List<List<Integer>> levelOrderBottom(TreeNode root) {

        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        ArrayDeque<TreeNode> queue = new ArrayDeque<>();

        List<Integer> level = new ArrayList<>();

        queue.offer(root);
        int curLevelCount = 1, nextLevelCount = 0;
        TreeNode node = null;
        while (!queue.isEmpty()) {
            node = queue.poll();
            level.add(node.val);
            curLevelCount--;

            if (node.left != null) {
                queue.offer(node.left);
                nextLevelCount++;
            }
            if (node.right != null) {
                queue.offer(node.right);
                nextLevelCount++;
            }

            if (curLevelCount == 0) {
                // 当前行遍历结束
                result.add(level);
                level = new ArrayList<>();

                curLevelCount = nextLevelCount;
                nextLevelCount = 0;
            }

        }
        reverse(result);
        return result;
    }

    private void reverse(List<List<Integer>> result) {
        int length = result.size();
        List<Integer> l = null;
        for (int i = 0; i < length / 2; i++) {
            l = result.get(i);
            result.set(i, result.get(length - i - 1));
            result.set(length - i - 1, l);
        }

    }
}
