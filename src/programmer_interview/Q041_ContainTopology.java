package programmer_interview;

import org.junit.Test;

/**
 * 判断一颗树是否包含另一棵树的全部拓扑结构
 * created by Ethan-Walker on 2018/12/29
 */
public class Q041_ContainTopology {

    public boolean contain(TreeNode root1, TreeNode root2) {

        return match(root1, root2) || contain(root1.left, root2) || contain(root1.right, root2);
    }

    public boolean match(TreeNode root1, TreeNode root2) {

        if (root2 == null) return true;

        // root2 !=null
        if (root1 != null && root1.val == root2.val) {
            // 当前节点匹配，匹配左子树、右子树

            return match(root1.left, root2.left) && match(root1.right, root2.right);
        }
        return false;
    }

    @Test
    public void test() {
        int[] pre1 = {1, 2, 4, 8, 9, 5, 10, 3, 6, 7};
        int[] in1 = {8, 4, 9, 2, 10, 5, 1, 6, 3, 7};
        int[] pre2 = {2, 4, 8, 5};
        int[] in2 = {8, 4, 2, 5};
        TreeNode root1 = TreeUtil.buildTreeByPreAndIn(pre1, in1);
        TreeNode root2 = TreeUtil.buildTreeByPreAndIn(pre2, in2);

        System.out.println(contain(root1, root2));
    }


}
