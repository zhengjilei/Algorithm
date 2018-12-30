package 程序员代码面试指南;

import org.junit.Test;

/**
 * 二叉树节点间的最大距离
 * 以root为根的树的节点间最大距离的取值：
 * 最大距离不经过root：
 * 1. 左子树节点间的最大距离
 * 2. 右子树节点间的最大距离
 * 最大距离经过 root
 * 3. 左子树的高度+右子树的高度+1
 * 后序遍历
 * <p>
 * 为了不重复计算高度，从每个节点返回时需携带两个信息：当前节点的最大距离，以当前节点为根的树的高度
 * 由于返回值只能存储一个信息，将高度存入参数数组中
 * created by Ethan-Walker on 2018/12/30
 */
public class Q050_MaxDistanceInBT {


    public int getMaxDistance(TreeNode root) {
        int[] height = new int[1];
        return getMaxDistance(root, height);
    }

    private int getMaxDistance(TreeNode root, int[] height) {
        if (root == null) {
            height[0] = 0;
            return 0;
        }

        int leftMax = getMaxDistance(root.left, height);
        int leftHeight = height[0];

        int rightMax = getMaxDistance(root.right, height);
        int rightHeight = height[0];


        //重点：确定当前节点的最大距离、树高度
        height[0] = 1 + Math.max(leftHeight, rightHeight);

        int max = Math.max(leftMax, rightMax);
        max = Math.max(max, 1 + leftHeight + rightHeight);
        return max;

    }


    @Test
    public void test() {
        int[] pre = {1, 2, 4, 6, 10, 11, 7, 5, 8, 9, 3};
        int[] in = {10, 6, 11, 4, 7, 2, 8, 5, 9, 1, 3};
        TreeNode root = TreeUtil.buildTreeByPreAndIn(pre, in);

        int maxDistance = getMaxDistance(root);


        System.out.println(maxDistance);
    }

}
