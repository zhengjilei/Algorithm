package programmer_interview2;

import programmer_interview.TreeNode;

import java.util.ArrayDeque;

/**
 * created by Ethan-Walker on 2019/3/20
 */
public class Q045 {

    public boolean isCST(TreeNode root) {
        if (root == null) return true;
        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        boolean shouldBeLeaf = false;
        TreeNode left, right;
        while (!queue.isEmpty()) {
            root = queue.poll();

            left = root.left;
            right = root.right;

            if (shouldBeLeaf) {
                if (left != null || right != null)
                    return false;
            } else {
                if (left == null && right != null) // 左子树为空，右子树不为空
                    return false;
                else if (left != null && right != null) {
                    queue.offer(left);
                    queue.offer(right);
                } else {
                    // left!=null && right==null || left==null && right == null
                    shouldBeLeaf = true;
                    if (left != null && right == null) {
                        queue.offer(left);
                    }
                }

            }
        }

        return true;
    }

}
