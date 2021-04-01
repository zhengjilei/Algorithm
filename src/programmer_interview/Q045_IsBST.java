package programmer_interview;

/**
 * 判断一棵树是否为二叉搜索树
 * created by Ethan-Walker on 2018/12/29
 */
public class Q045_IsBST {


    public boolean isBST(TreeNode root) {
        int[] minmax = new int[2];
        return isBST(root, minmax);
    }

    public boolean isBST(TreeNode root, int[] minmax) {
        if (root == null) return true;
        if (root.left == null && root.right == null) {
            minmax[0] = minmax[1] = root.val;
            return true;
        }

        int leftMin = root.val, leftMax = root.val;

        if (root.left != null) {
            if (!isBST(root.left, minmax)) return false;
            leftMin = minmax[0];
            leftMax = minmax[1];
            if (root.val <= leftMax) return false;
        }

        int rightMin = root.val, rightMax = root.val;
        if (root.right != null) {
            if (!isBST(root, minmax)) return false;
            rightMin = minmax[0];
            rightMax = minmax[1];
            if (root.val >= rightMin) return false;
        }


        minmax[0] = leftMin;
        minmax[1] = rightMax;

        return true;


    }
}
