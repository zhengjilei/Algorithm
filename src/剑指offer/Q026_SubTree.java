package 剑指offer;

/**
 * created by Ethan-Walker on 2018/12/6
 */
public class Q026_SubTree {

    public boolean isSubTree(TreeNode pRoot, TreeNode qRoot) {
        boolean result = false;
        if (pRoot != null && qRoot != null) {
            if (Math.abs(pRoot.val - qRoot.val) < 10e-8) {
                result = judge(pRoot, qRoot);
            }
        }
        return result || isSubTree(pRoot.left, qRoot) || isSubTree(pRoot.right, qRoot);
    }

    /**
     * pRoot.val == qRoot.val
     * 判断qRoot 是否是 pRoot 中 以pRoot为根的子结构
     */
    boolean judge(TreeNode pRoot, TreeNode qRoot) {
        if (qRoot == null) {
            return true; // 说明子树 qRoot 比较成功
        }
        if (pRoot == null) {
            return false;// 说明 qRoot 还不为空，但是 pRoot 已经为空了
        }

        return Math.abs(pRoot.val - qRoot.val) < 10e-8
                && judge(pRoot.left, qRoot.left)
                && judge(pRoot.right, qRoot.right);
    }
}
