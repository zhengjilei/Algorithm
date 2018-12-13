package 剑指offer;

/**
 * 求二叉树的深度
 * created by Ethan-Walker on 2018/12/14
 */
public class Q055_BTDepth {

    /**
     * 求二叉树的高度，即根节点的深度
     *
     * @param node
     * @return
     */
    public int depth(TreeNode node) {
        if (node == null) {
            return 0;
        }

        return Math.max(depth(node.left), depth(node.right)) + 1;
    }

    /**
     * 判断一个二叉树是否是平衡二叉树，即任意节点的高度差不得超过 1
     * 计算根节点的左右子节点的高度，得到根节点是否平衡，若平衡，递归比较左右子节点是否平衡
     * 缺点：大量节点被重复遍历
     *
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        int leftDepth = depth(root.left);
        int rightDepth = depth(root.right);

        if (Math.abs(leftDepth - rightDepth) > 1) {
            return false;
        }

        // 节点 root 平衡

        // 递归比较节点的左右子节点是否平衡
        return isBalanced(root.left) && isBalanced(root.right);
    }


    /**
     * 每个节点至多只遍历一遍，返回 -1 表示不平衡
     *
     * @param root
     * @return
     */
    public int isBalanced2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = isBalanced2(root.left);
        if (leftDepth == -1) return -1; // 左子树不平衡，不需要再计算右子树

        int rightDepth = isBalanced2(root.right);

        // 右子树不平衡或者当前节点不平衡
        if (rightDepth == -1 || Math.abs(leftDepth - rightDepth) > 1) {
            return -1;
        } else {

            return 1 + (leftDepth >= rightDepth ? leftDepth : rightDepth);
        }
    }


}
