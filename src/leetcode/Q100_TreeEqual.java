package leetcode;

import programmer_interview.TreeNode;

/**
 * created by Ethan-Walker on 2019/3/12
 */
public class Q100_TreeEqual {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;

        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
