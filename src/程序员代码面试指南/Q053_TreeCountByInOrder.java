package 程序员代码面试指南;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * created by Ethan-Walker on 2018/12/30
 */
public class Q053_TreeCountByInOrder {


    /**
     * 根据中序遍历的结果构建二叉树，问这样的二叉树有多少种
     * 时间复杂度: 1+2+3...+n  O(n^2)
     *
     * @param n
     * @return
     */
    public int count(int n) {
        int[] num = new int[n + 1];
        num[0] = 1;

        for (int i = 1; i <= n; i++) {
            // 计算 num[i] =   num[0]*num[i-1]+ num[1]*num[i-2] ... + num[i-1]*num[0]
            for (int j = 0; j <= i; j++) {
                num[i] += num[j] * num[i - j - 1];
            }
        }
        return num[n];
    }


    /**
     * 根据递增的中序遍历构建所有可能的BST
     *
     * @param in
     * @return
     */

    public List<TreeNode> buildBSTByInOrder(int[] in) {

        List<TreeNode> result = new ArrayList<>();
        result = buildBST(in, 0, in.length);
        return result;
    }


    public List<TreeNode> buildBST(int[] in, int start, int length) {

        List<TreeNode> rootList = new ArrayList<>(); // 存放所有不同构造树的根节点
        if (length == 0) {
            rootList.add(null); // 需要存放 null ,当某个节点的左/右子树为空时需要使用
            return rootList;
        }

        for (int i = start; i < start + length; i++) {
            // 选定一个节点作为根节点，其他作为左右子树
            TreeNode root = new TreeNode(in[i]);

            int leftSize = i - start;
            int rightSize = length - leftSize - 1;

            // 左右子树 生成的所有可能的二叉树 ，返回 二叉树根节点
            List<TreeNode> lRootList = buildBST(in, start, leftSize);
            List<TreeNode> rRootList = buildBST(in, i + 1, rightSize);

            for (TreeNode left : lRootList) {
                for (TreeNode right : rRootList) {
                    root.left = left;
                    root.right = right;
                    rootList.add(cloneTree(root)); // 必须进行克隆，否则循环导致 已添加到列表的树被更改
                }
            }

        }
        return rootList;
    }

    public TreeNode cloneTree(TreeNode root) {
        if (root == null) return null;
        TreeNode targetRoot = new TreeNode(root.val);
        targetRoot.left = cloneTree(root.left);
        targetRoot.right = cloneTree(root.right);
        return targetRoot;
    }

    @Test
    public void test() {
        int[] in = {1, 2, 3};
        List<TreeNode> result = buildBSTByInOrder(in);
        System.out.println(result.size() + "\n---------------------------\n");
        for (TreeNode n : result) {
            TreeUtil.printShapeBT(n);
        }
    }
}
