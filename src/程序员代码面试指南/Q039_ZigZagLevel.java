package 程序员代码面试指南;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

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
     * 方法1：按照上面存入到 List< List< Integer>> 中，每隔一行反转List< Integer>
     * 方法2: 创建一个栈，添加辅助变量 level
     * level&1)==1 奇数：从左向右遍历，故不需要处理，正常访问即可
     * level&0)==0 偶数：从右向左遍历，弹出时先不着急访问，先压到栈中，等换行时从栈中退出
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
            if ((level & 1) == 1) { // 奇数
                levelList.add(node.val);
            } else {
                stack.push(node.val);
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

            if (curLevelCount == 0) {

                if ((level & 1) == 0) { // 偶数
                    // 从右向左访问
                    while (!stack.isEmpty()) {
                        levelList.add(stack.pop());
                    }

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
