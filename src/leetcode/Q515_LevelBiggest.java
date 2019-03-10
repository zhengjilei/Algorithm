package leetcode;

import 程序员代码面试指南.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * created by Ethan-Walker on 2019/3/10
 */
public class Q515_LevelBiggest {
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        TreeNode node = null;
        int curLevelCount = 1, nextLevelCount = 0;
        int levelMax = Integer.MIN_VALUE;

        while (!queue.isEmpty()) {
            node = queue.poll();
            if (node.val > levelMax) {
                levelMax = node.val;
            }
            curLevelCount--;

            if (node.left != null) {
                queue.offer(node.left);
                nextLevelCount++;
            }
            if (node.right != null) {
                queue.offer(node.right);
                nextLevelCount++;
            }

            if (curLevelCount == 0) { // 当前层遍历结束
                result.add(levelMax);
                levelMax = Integer.MIN_VALUE;
                curLevelCount = nextLevelCount;
                nextLevelCount = 0;
            }
        }
        return result;

    }
}
