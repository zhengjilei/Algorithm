package 程序员代码面试指南;

/**
 * created by Ethan-Walker on 2018/12/30
 */
public class Q048_LCA2 {


    /**
     * 方法二：
     * 后序遍历判断左子树是否包含 node1/node2 右子树是否包含 node1/node2
     * 然后决定当前节点是否是最低公共祖先，或者是某个节点的祖先
     * 时间复杂度: O(n)
     *
     * @param root
     * @param node1
     * @param node2
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode node1, TreeNode node2) {
        if (root == null || root == node1 || root == node2) return root;
        // root ==node1|node2  当前节点可能是某个节点的祖先（或者可能是该节点是另一个目标节点的父节点）

        TreeNode left = lowestCommonAncestor(root.left, node1, node2);
        TreeNode right = lowestCommonAncestor(root.right, node1, node2);
        // left==null 说明左子树不包含 node1或node2
        // right==null 说明右子树不包含node1或node2

        // 左右子树都不为空，说明左子树中找到了node1（node2）右子树中找到了 node2(node1)
        if (left != null && right != null) {
            return root;
        }
        return left != null ? left : right;
    }



}
