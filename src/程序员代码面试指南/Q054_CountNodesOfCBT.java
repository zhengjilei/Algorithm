package 程序员代码面试指南;

import org.junit.Test;

/**
 * 统计完全二叉树的节点数
 * <p>
 * <p>
 * created by Ethan-Walker on 2018/12/30
 */
public class Q054_CountNodesOfCBT {

    /**
     * 最简单的方法，和普通二叉树一样处理，遍历一遍
     * 时间复杂度 O(n)
     *
     * @param root
     * @return
     */
    public int simpleSolve(TreeNode root) {
        if (root == null) return 0;
        return 1 + simpleSolve(root.left) + simpleSolve(root.right);
    }


    public int countNodes(TreeNode root) {

        int height = getHeight(root);
        return getCount(root, 1, height);

    }

    /**
     * 时间复杂度: O(h^2) < O(n)
     * log2(n) ^2 < n
     * <p>
     * 每一层都只选左右子树中的一棵进行递归 ，要执行 h 次递归
     * 每个递归中都要获取右子树的高度: 各趟分别执行  h-1 h-2 h-3 ... 1 次
     * 故一共为 n(n+1)/2
     * 时间复杂度: O(h^2)
     *
     * @param root
     * @param curLevel
     * @param height
     * @return
     */

    public int getCount(TreeNode root, int curLevel, int height) {
        if (root == null) return 0;
        if (curLevel == height) return 1;

        int leftHeight = height - curLevel; // 左子树的高
        int rightHeight = getHeight(root.right); // 右子树的高

        if (leftHeight == rightHeight) {
            // 说明左子树是满二叉树, 节点数为 2^(height-curLevel) -1  + 1 （当前节点 root）
            // 右子树是完全二叉树，不确定是否是满二叉树
            return (1 << leftHeight) + getCount(root.right, curLevel + 1, height);  // 注意 << 优先级低于 +
        } else {
            // rightHeight == leftHeight-1
            // 左子树不是满二叉树,  右子树是少一层的 满二叉树
            return (1 << rightHeight) + getCount(root.left, curLevel + 1, height);
        }
    }


    /**
     * 完全二叉树求高度，最左下角的节点的高度一定是完全二叉树的高度
     *
     * @param root
     * @return
     */
    public int getHeight(TreeNode root) {
        int height = 0;
        while (root != null) {
            height++;
            root = root.left;
        }
        return height;
    }


    @Test
    public void test() {
        int[] pre = {1, 2, 4, 5, 3, 6};
        int[] in = {4, 2, 5, 1, 6, 3};

        TreeNode root = TreeUtil.buildTreeByPreAndIn(pre, in);
        TreeUtil.printShapeBT(root);
        int i = countNodes(root);

        System.out.println(i);

    }
}
