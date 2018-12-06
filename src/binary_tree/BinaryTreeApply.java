package binary_tree;

/**
 * created by Ethan-Walker on 2018/11/4
 */
public class BinaryTreeApply {

    /**
     * 递归计算二叉树的节点数
     *
     * @param p
     * @return
     */
    public static int size(TreeNode p) {
        if (p == null) return 0;
        return 1 + size(p.left) + size(p.right);
    }

    /**
     * 递归计算二叉树的深度
     */
    public static int height(TreeNode p) {
        if (p == null) return 0;
        else {
            int i = height(p.left);
            int j = height(p.right);
            return (i > j) ? i + 1 : j + 1;
        }
    }

    /**
     * 递归判断两颗二叉树是否相等
     */
    public boolean equal(TreeNode a, TreeNode b) {
        if (a == null && b == null) return true;// 空树
        if (a != null && b != null && a.left.val == b.right.val && equal(a.left, b.left) && equal(a.right, b.right)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 复制创建二叉树
     */
    public TreeNode copy(TreeNode p) {
        if (p == null) {
            return null;
        }
        TreeNode t = new TreeNode(p.val);
        t.left = copy(p.left);
        t.right = copy(p.right);
        return t;
    }
}
