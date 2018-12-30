package 程序员代码面试指南;

import org.junit.Test;

/**
 * created by Ethan-Walker on 2018/12/29
 */
public class Q042_IsSubTree {


    /**
     * 方法一：参照上题的拓扑结构判断，匹配时，添加限制条件：要完全匹配
     * 时间复杂度: O(n*m)
     *
     * @param root1
     * @param root2
     * @return
     */
    public boolean contain(TreeNode root1, TreeNode root2) {
        if (root2 == null) return true;

        if (root1 != null) // root2!=null
            return match(root1, root2) || contain(root1.left, root2) || contain(root1.right, root2);

        return false;
    }

    public boolean match(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) return true; // root1完全匹配root2，root1 不能有多余的节点

        if (root1 != null && root2 != null && root1.val == root2.val) {
            return match(root1.left, root2.left) && match(root1.right, root2.right);
        }
        return false;
    }

    /**
     * 方法二：将两棵树序列化，然后比较字符串是否 包含另一个字符串
     *
     * @param root
     * @param sub
     * @return
     */
    public boolean isSubtree(TreeNode root, TreeNode sub) {
        if (sub == null) return true;
        if (root == null) return false;

        String str1 = serial(root);
        String str2 = serial(sub);

//        if(str1.contains(str2))
// 错：例如 12,#,#,  和 2,#,#, 表示两棵不同的二叉树，但序列 1包含序列2
// 错误原因在于没有将数值作为一个整体来比较

        String[] str = str1.split(",");
        String[] pattern = str2.split(",");

        return kmp(str,pattern);

    }

    public boolean kmp(String[] str, String[] pattern) {
        int[] next = getNext(pattern);
        int i = 0, j = 0;
        while (i < str.length && j < pattern.length) {
            if (j == -1 || str[i].equals(pattern[j])) {
                i++;
                j++;
            } else {
                j = next[j];// 下一次该匹配的位置
            }
        }
        if (j == pattern.length) return true;
        return false;
    }


    public int[] getNext(String[] pattern) {

        int[] next = new int[pattern.length];
        next[0] = -1;
        int j = 0, k = -1;
        while (j < pattern.length - 1) {
            // 计算 next[j+1]
            if (k == -1 || pattern[k].equals(pattern[j])) {
                next[++j] = ++k;
            } else {
                k = next[k];
            }
        }

        return next;
    }


    public String serial(TreeNode root) {
        if (root == null) return "#,";
        StringBuilder sb= new StringBuilder().append(root.val).append(",");

        String a = serial(root.left);
        String b = serial(root.right);

        sb.append(a).append(b);
        return sb.toString();
    }


    @Test
    public void test() {
        int[] pre1 = {1, 2, 4, 8, 9, 5, 10, 3, 6, 7};
        int[] in1 = {8, 4, 9, 2, 10, 5, 1, 6, 3, 7};
        int[] pre2 = {2, 4, 8, 5};
        int[] in2 = {8, 4, 2, 5};
        TreeNode root1 = TreeUtil.buildTreeByPreAndIn(pre1, in1);
        TreeNode root2 = TreeUtil.buildTreeByPreAndIn(pre2, in2);

        System.out.println(isSubtree(root1, root2));
    }


    @Test
    public void test2() {
        int[] pre1 = {1, 2, 4, 8, 5, 9, 3, 6, 7};
        int[] in1 = {4, 8, 2, 9, 5, 1, 6, 3, 7};
//        int[] pre2 = {2,4,8,5,9};
//        int[] in2 = {4,8,2,9,5};
        int[] pre2 = {2, 4, 8, 5};
        int[] in2 = {4, 8, 2, 5};
        TreeNode root1 = TreeUtil.buildTreeByPreAndIn(pre1, in1);
        TreeNode root2 = TreeUtil.buildTreeByPreAndIn(pre2, in2);

        System.out.println(isSubtree(root1, root2));
    }
}
