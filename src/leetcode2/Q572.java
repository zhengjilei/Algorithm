package leetcode2;

import programmer_interview.TreeNode;

/**
 * created by Ethan-Walker on 2019/3/17
 */
public class Q572 {

    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (t == null) return true;
        if (s == null) return false;
        return isEqual(s, t) || isSubtree(s.left, t) || isSubtree(s.right, t);
    }

    public boolean isEqual(TreeNode s, TreeNode t) {
        if (s == null && t == null) return true;
        if (s == null || t == null) return false;
        return s.val == t.val && isEqual(s.left, t.left) && isEqual(s.right, t.right);
    }

}
