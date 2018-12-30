package leetcode;

import org.junit.Test;
import 程序员代码面试指南.TreeNode;
import 程序员代码面试指南.TreeUtil;

/**
 * 判断一棵树是否是BST
 * <p>
 * 后序遍历
 * 左子树是BST，右子树是 BST的前提下，判断以当前节点为根的树
 * 当前节点要判断比 左BST 的最大值大，比 右BST的最小值小
 * 故每棵子树被判定为 BST 返回时，需要携带树的最小值，最大值，以及是否是BST
 * <p>
 * created by Ethan-Walker on 2018/12/29
 */
public class Q098_ValidateBST {

    public boolean isValidBST(TreeNode root) {
        int[] minmax = new int[2];
        return isBST(root, minmax);
    }

    /**
     * 时间复杂度: O(n)
     *
     * @param root
     * @param minmax
     * @return
     */
    public boolean isBST(TreeNode root, int[] minmax) {
        if (root == null) { // 空树也算是 BST
            return true;
        }
        if (root.left == null && root.right == null) { // 没有左右子树
            minmax[0] = root.val;
            minmax[1] = root.val;
            return true;
        }

        // 当左子树为空时,以root为根的树的 minLeft maxLeft 的值
        int minLeft = root.val;
        int maxLeft = root.val;

        if (root.left != null) {
            if (!isBST(root.left, minmax))
                return false;        // 左子树都不是 BST了，直接返回
            minLeft = minmax[0];
            maxLeft = minmax[1];
            // 比左子树最大值小
            if (root.val <= maxLeft) {
                return false;
            }
        }

        // 当右子树为空时，minRight、maxRight的值
        int minRight = root.val;
        int maxRight = root.val;

        if (root.right != null) {
            if (!isBST(root.right, minmax))
                return false;    // 右子树都不是 BST了，直接返回

            minRight = minmax[0];
            maxRight = minmax[1];
            if (root.val >= minRight) {
                return false;
            }
        }

        // 还能到达者说明是 BST 了
        minmax[0] = minLeft;
        minmax[1] = maxRight; // 更改以节点 root 为BST的最小值、最大值
        return true;
    }

    @Test
    public void test() {
        int[] pre = {5, 1, 4, 3, 6};
        int[] in = {1, 5, 3, 4, 6};
        TreeNode root = TreeUtil.buildTreeByPreAndIn(pre, in);
        System.out.println(isValidBST(root));
    }
}
