package leetcode2;

import programmer_interview.TreeNode;

/**
 * created by Ethan-Walker on 2019/3/12
 */
public class Q0572_IsSubTree {

    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null) return false;
        return isSame(s, t) || isSubtree(s.left, t) || isSubtree(s.right, t);
    }

    public boolean isSame(TreeNode s, TreeNode t) {
        if (s == null && t == null) return true;
        if (s == null || t == null) return false;
        return s.val == t.val && isSame(s.left, t.left) && isSame(s.right, t.right);
    }
}
