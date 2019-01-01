package 程序员代码面试指南;

import lca.Query;
import lca.Tarjan;
import org.junit.Test;

/**
 * created by Ethan-Walker on 2018/12/30
 */
public class Q049_LCATarjan {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        Query[] queries = new Query[1];
        queries[0] = new Query(p, q);

        Tarjan tarjan = new Tarjan();

        TreeNode[] result = tarjan.query(root, queries);

        return result[0];


    }

    public TreeNode[] lca(TreeNode root, Query[] queries) {

        return new Tarjan().query(root, queries);
    }


    @Test
    public void test2() {
        int[] pre = {1, 2, 4, 5, 7, 9, 8, 3, 6};
        int[] in = {4, 2, 9, 7, 5, 8, 1, 3, 6};

        TreeNode root = TreeUtil.buildTreeByPreAndIn(pre, in);
        TreeUtil.printShapeBT(root);

        TreeNode node1 = TreeUtil.findNodeByVal(root, 1);
        TreeNode node2 = TreeUtil.findNodeByVal(root, 2);
        TreeNode node3 = TreeUtil.findNodeByVal(root, 3);
        TreeNode node4 = TreeUtil.findNodeByVal(root, 4);
        TreeNode node5 = TreeUtil.findNodeByVal(root, 5);
        TreeNode node6 = TreeUtil.findNodeByVal(root, 6);
        TreeNode node7 = TreeUtil.findNodeByVal(root, 7);
        TreeNode node8 = TreeUtil.findNodeByVal(root, 8);
        TreeNode node9 = TreeUtil.findNodeByVal(root, 9);

        Query[] queries = new Query[4];
        queries[0] = new Query(node9, node8);
        queries[1] = new Query(node4, node6);
        queries[2] = new Query(node7, node5);
        queries[3] = new Query(node5, node3);

        TreeNode[] lcaResult = lca(root, queries);

        for (TreeNode t : lcaResult) {
            System.out.println(t.val);
        }
    }

    @Test
    public void test() {

        int[] pre = {1, 2, 4, 5, 7, 9, 8, 3, 6};
        int[] in = {4, 2, 9, 7, 5, 8, 1, 3, 6};

        TreeNode root = TreeUtil.buildTreeByPreAndIn(pre, in);
        TreeUtil.printShapeBT(root);

        TreeNode node1 = TreeUtil.findNodeByVal(root, 9);
        TreeNode node2 = TreeUtil.findNodeByVal(root, 4);

        System.out.println(node1.val);
        System.out.println(node2.val);

        TreeNode result = lowestCommonAncestor(root, node1, node2);

        System.out.println(result.val);
    }
}
