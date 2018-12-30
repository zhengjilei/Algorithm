package 程序员代码面试指南;


import java.util.HashMap;

/**
 * 频繁查询任意两个节点的最近公共祖先
 * <p>
 * created by Ethan-Walker on 2018/12/30
 */
public class Q048B_LCAFrequentQuery {
    // TODO: 2018/12/30 一次构建，使得以后查询的时间为 O(1)

    private HashMap<TreeNode, HashMap<TreeNode, TreeNode>> map;
}
