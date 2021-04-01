package programmer_interview;

import org.junit.Test;

import java.util.ArrayDeque;

/**
 * 判断一棵树是否是完全二叉树
 * 辅助变量：shouldBeLeaf
 * 层次遍历二叉树
 * 1. 如果节点既有左子树 也有右子树 ， 压入左右子树，继续遍历
 * 2. 如果节点有右子树没有左子树 返回 false
 * 3. 如果节点有左子树没有右子树(要压入左子树)，或者左右子树都没有， 那么之后所有的节点必须是叶节点 showBeLeaf = true
 *
 * <p>
 * <p>
 * created by Ethan-Walker on 2018/12/29
 */
public class Q045A_IsCST {


    public boolean isCST(TreeNode root) {
        if (root == null) return true;
        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        boolean shouldBeLeave = false;
        queue.offer(root);
        TreeNode left, right;

        while (!queue.isEmpty()) {
            root = queue.poll();

            left = root.left;
            right = root.right;

            if (!shouldBeLeave) {
                if (left == null && right != null) {
                    return false;
                } else if (left != null && right == null) {
                    shouldBeLeave = true;
                    queue.offer(left);
                } else if (left == null && right == null) {
                    shouldBeLeave = true;
                } else {
                    // 左右子树都不为空
                    queue.offer(left);
                    queue.offer(right);
                }
            } else {
                // 当前节点需要是叶节点
                if (left != null || right != null) return false;
            }

        }
        return true;
    }


    int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;

    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;

        if (root.left == null && root.right == null) {
            min = max = root.val;
            return true;
        }

        int minLeft = root.val;
        int maxRight = root.val;

        if (root.left != null) {
            if (!isValidBST(root.left)) {
                return false;
            } else {
                // 左子树的最大值
                if (max >= root.val) {
                    return false;
                }
                minLeft = min;
            }
        }

        if (root.right != null) {
            if (!isValidBST(root.right)) {
                return false;
            } else {
                if (min <= root.val) {
                    return false;
                }
                maxRight = max;

            }
        }

        min = minLeft;
        max = maxRight;
        return true;
    }


    @Test
    public void test() {
        TreeNode root = TreeUtil.buildTreeByPreAndIn(new int[]{0, -1}, new int[]{-1, 0});
        System.out.println(isValidBST(root));

    }


}
