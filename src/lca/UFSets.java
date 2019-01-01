package lca;

import 程序员代码面试指南.TreeNode;

import java.util.HashMap;

/**
 * created by Ethan-Walker on 2018/12/31
 */
public class UFSets {

    private HashMap<TreeNode, TreeNode> fatherMap; // key 的父节点为 value
    private HashMap<TreeNode, Integer> rankMap;  // 表示节点 key 所在的集合的总层数 (深度)

    public UFSets() {
        fatherMap = new HashMap<>();
        rankMap = new HashMap<>();
    }

    /**
     * 初始化/重新构建并查集
     *
     * @param root
     */
    public void makeSets(TreeNode root) {
        fatherMap.clear();
        rankMap.clear();
        preOrderInit(root);
    }

    /**
     * 前序遍历每个节点，并初始化 fatherMap rankMap
     */
    private void preOrderInit(TreeNode root) {
        if (root == null) return;
        fatherMap.put(root, root);
        rankMap.put(root, 1);
        preOrderInit(root.left);
        preOrderInit(root.right);
    }

    /**
     * 查询节点 node 所在的集合（集合也用节点表示，可以理解为根节点）
     * 递归压缩  比较难理解
     *
     * @param node
     * @return
     */
    public TreeNode findFather2(TreeNode node) {
        TreeNode father = fatherMap.get(node); // 节点 node 的父节点
        if (father != node) {
            // 说明 node 不是集合的根节点
            father = findFather2(father);
        }
        // father 是node集合的根节点

        fatherMap.put(node, father);// 让 node 指向 father

        return father;
    }

    /**
     * 查询节点 node 所在集合
     * 先迭代找到集合顶点，然后重新迭代一次，让路径上的所有节点指向 顶点
     *
     * @return
     */
    public TreeNode findFather(TreeNode node) {
        TreeNode father = node;
        while (father != fatherMap.get(father)) {
            father = fatherMap.get(father);
        }

        // father 为节点 node 所在集合的顶点
        TreeNode tempFather = null;
        while (fatherMap.get(node) != father) {
            tempFather = fatherMap.get(node);
            fatherMap.put(node, father);
            node = tempFather;
        }

        return father;
    }

    public void union(TreeNode a, TreeNode b) {
        if (a == null || b == null) return;

        TreeNode aFather = findFather(a);
        TreeNode bFather = findFather(b);

        if (aFather != bFather) {
            int aRank = rankMap.get(a);
            int bRank = rankMap.get(b);

            if (aRank < bRank) {
                // 将 a 的集合挂到 b 集合上,新集合的 rank 没有变化
                fatherMap.put(aFather, bFather);
            } else if (aRank > bRank) {
                fatherMap.put(bFather, aFather);
            } else {
                // 随便挂
                fatherMap.put(aFather, bFather);
                rankMap.put(bFather, bRank + 1);
            }
        }


    }

}
