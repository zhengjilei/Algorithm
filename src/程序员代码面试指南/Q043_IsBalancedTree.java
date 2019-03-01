package 程序员代码面试指南;

/**
 * created by Ethan-Walker on 2018/12/29
 */
public class Q043_IsBalancedTree {


    /**
     * 判断树是否是平衡二叉树
     * 方法一:
     * 首先判断当前节点是否平衡(计算左右子树的高度)，然后前序遍历所有的节点，判断每个节点是否平衡
     * <p>
     * 缺点：计算当前节点的高度时，左右子树都全部被遍历了一遍。再判断左子节点是否平衡，计算高度，又会将左子节点的左子树右子树遍历一遍
     * 导致大量节点重复遍历
     *
     * @param root
     * @return
     */
    public boolean isAVL(TreeNode root) {

        if (root == null) return true;

        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);

        if (Math.abs(leftHeight - rightHeight) > 1) {
            return false;
        }
        // 当前节点是 AVL ，判断左子树、右子树是否是AVL
        return isAVL(root.left) && isAVL(root.right);
    }


    /**
     * 获得以root 为根节点的树的高
     * 计算左子树的高和右子树的高
     * 再计算当前节点的高
     * 后序计算
     *
     * @param root
     * @return
     */
    public int getHeight(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(getHeight(root.left), getHeight(root.right));
    }


    /**
     * 方法二：在判断根节点是否平衡时，计算左右子树高度时
     * 后序遍历到每个节点时都要判断当前节点是否平衡，只要有一个不平衡，就直接返回false
     * <p>
     * 只要遍历一次树
     *
     * @param root
     * @return
     */
    public boolean isBalance(TreeNode root) {

        boolean[] isBalance = new boolean[1];
        isBalance[0] = true;

        getHeight(root, isBalance);

        return isBalance[0];

    }

    /**
     * 既计算节点 root 的高度，同时也要判断节点 root 是否平衡
     *
     * @param root
     * @param isBalance
     * @return
     */
    public int getHeight(TreeNode root, boolean[] isBalance) {
        if (root == null) return 0;

        int leftHeight = getHeight(root.left, isBalance);
        if (!isBalance[0]) return -1; // 左子树不平衡，直接返回，不用计算当前节点的高度了

        int rightHeight = getHeight(root.right, isBalance);
        if (!isBalance[0]) return -1; // 右子树不平衡，直接返回，不用计算当前节点的高度了

        if (Math.abs(leftHeight - rightHeight) > 1) {
            isBalance[0] = false;
        }
        // 只有左右子树平衡了，才返回高度
        return 1 + Math.max(leftHeight, rightHeight);
    }

}
