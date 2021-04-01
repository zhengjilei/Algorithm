package bytedance;

import programmer_interview.TreeNode;

/**
 * created by Ethan-Walker on 2019/2/15
 */
public class Q236_LowestCommonAncestor {
    // 没有父节点指针
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == null || q == null) return null;
        int height = getHeight(root);
        TreeNode[] path1 = new TreeNode[height];
        TreeNode[] path2 = new TreeNode[height];
        // dfs 分别找到从根节点root 到 p /q 的路径
        int len1 = getPath(root, p, path1, 0);
        int len2 = getPath(root, q, path2, 0);

        int index = 0;
        int min = Math.min(len1, len2);
        while (index < min && path1[index] == path2[index]) {
            index++;
        }
        // index - 1 指向path1 path2 最后一个公共的节点
        return path1[index - 1];
    }

    // index 指向当前 path 中节点的下标
    public int getPath(TreeNode now, TreeNode target, TreeNode[] path, int index) {
        if (now == null) {
            return 0;
        }
        path[index] = now;
        if (now == target) {
            return index + 1;
        }

        int len = 0;
        if ((len = getPath(now.left, target, path, index + 1)) > 0) {
            return len;
        }
        return getPath(now.right, target, path, index + 1);
    }

    public int getHeight(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(getHeight(root.left), getHeight(root.right));
    }


    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) {
            return root;
        }
        return left != null ? left : right;
    }
}
