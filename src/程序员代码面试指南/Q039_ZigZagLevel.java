package 程序员代码面试指南;

import java.util.*;

/**
 * created by Ethan-Walker on 2018/12/28
 */
public class Q039_ZigZagLevel {

    /**
     * 按层遍历
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        int curLevelCount = 1, nextLevelCount = 0;
        TreeNode node = null;
        List<Integer> levelList = new ArrayList<>();
        while (!queue.isEmpty()) {
            node = queue.poll();
            levelList.add(node.val);

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
                result.add(levelList);
                levelList = new ArrayList<>();
                curLevelCount = nextLevelCount;
                nextLevelCount = 0;
            }
        }
        return result;
    }

    /**
     * 之字形打印
     * level&1)==1 奇数：从左向右遍历
     * level&0)==0 偶数：从右向左遍历，压入前  调用 Collections.reverse()
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelZOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        List<Integer> levelList = new ArrayList<>();
        ArrayDeque<Integer> stack = new ArrayDeque<>();

        int curLevelCount = 1, nextLevelCount = 0;
        TreeNode node = null;

        int level = 1;
        while (!queue.isEmpty()) {
            node = queue.poll();
            levelList.add(node.val);
            stack.push(node.val);

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

                if ((level & 1) == 0) { // 偶数
                    Collections.reverse(levelList);
                }
                result.add(levelList);
                levelList = new ArrayList<>();
                curLevelCount = nextLevelCount;
                nextLevelCount = 0;
                level++;

            }
        }
        return result;


    }
}
