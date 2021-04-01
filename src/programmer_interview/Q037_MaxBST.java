package programmer_interview;

import org.junit.Test;

/**
 * 求二叉树的最大二叉搜索树，返回根节点
 * created by Ethan-Walker on 2018/12/27
 */
public class Q037_MaxBST {

    public TreeNode getMaxBST(TreeNode root) {
        int[] record = new int[3];
        return getMaxBST(root, record);
    }

    /**
     * record[0] 节点总数： 表示以root为根的树中最大二叉搜索子树的节点总数
     * record[1] 最小值：表示以root为根的树 且 该树是BST 时，所有节点的最小值（当该树不是 BST 时该值没有意义）
     * record[2] 最大值：表示以root为根的树 且 该树是BST 时，所有节点的最大值（当该树不是 BST 时该值没有意义）
     *
     * @param record
     * @return
     */

    public TreeNode getMaxBST(TreeNode root, int[] record) {
        if (root == null) {
            record[0] = 0;
            record[1] = Integer.MAX_VALUE;// 因为要和 父节点比较，让父节点满足二叉搜索树的判定条件，故设为最大数
            record[2] = Integer.MIN_VALUE;
            return null;
        }

        TreeNode lBSTRoot = getMaxBST(root.left, record); // 左子树中的最大二叉搜索树信息

        int lSize = record[0];
        int lMin = record[1];
        int lMax = record[2];

        TreeNode rBSTRoot = getMaxBST(root.right, record);// 不用担心 左子树的record 值对右子树的 record 计算有影响，因为 record 值都是自下往上推导的
        int rSize = record[0];
        int rMin = record[1];
        int rMax = record[2];

        // 判断当前root为根的二叉树是否是 BST
        if (root.left == lBSTRoot && root.right == rBSTRoot && root.val > lMax && root.val < rMin) {
            record[0] = lSize + rSize + 1;
            record[1] = lMin;
            record[2] = rMax;
            return root;
        } else {
            record[0] = Math.max(lSize, rSize);
            // 不存储 record[1] [2] , 当前以 root 为节点的树不能构成BST，存储 min  max 没有意义了
            return lSize > rSize ? lBSTRoot : rBSTRoot;
        }
    }

    @Test
    public void test(){


    }
}
