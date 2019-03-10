package 程序员代码面试指南2;

import 程序员代码面试指南.TreeNode;

/**
 * created by Ethan-Walker on 2019/3/10
 */
public class Q050_MaxDis {

    public int maxDis(TreeNode root) {
        if (root == null) return 0;
        int[] res = new int[1];
        return max(root, res);
    }

    /**
     * 求以 root 为根的树中两个节点的最大距离（从 A->B 经过的节点总数）
     * <p>
     * 最大距离来源：左子树/右子树/ 跨根节点
     * 1. root.left 的最大距离
     * 2. root.right 的最大距离
     * 3. root.left 的树中离 root.left 的最远距离（高度），+1（经过根节点）, + root.right 的树中离 root.right 的最远距离（高度）
     * record[0] 表示从叶节点 离root 最远的距离
     * 后序遍历
     *
     * @param root
     * @return 最大距离
     */
    public int max(TreeNode root, int[] record) {

        int leftMax = max(root.left, record);
        int maxToLeft = record[0];

        int rightMax = max(root.right, record);
        int maxToRight = record[0];

        record[0] = Math.max(maxToLeft, maxToRight) + 1;

        int maxRes = leftMax;
        if (leftMax < rightMax) {
            maxRes = rightMax;
        }
        return Math.max(maxRes, 1 + maxToLeft + maxToRight);
    }


}
