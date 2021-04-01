package lca;

import programmer_interview.TreeNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * created by Ethan-Walker on 2018/12/31
 */
public class Tarjan {


    private HashMap<TreeNode, List<TreeNode>> queryMap;
    private HashMap<TreeNode, List<Integer>> indexMap;
    private UFSets ufSets;
    private HashMap<TreeNode, TreeNode> ancestorMap;

    public Tarjan() {
        queryMap = new HashMap<>();
        indexMap = new HashMap<>();
        ancestorMap = new HashMap<>();
        ufSets = new UFSets();


    }

    public TreeNode[] query(TreeNode root, Query[] queries) {
        ufSets.makeSets(root);
        TreeNode[] ans = new TreeNode[queries.length];
        buildQueries(queries, ans);
        calAnswers(root, ans);
        return ans;
    }

    /**
     * 初始化 queryMap 和 indexMap
     *
     * @param queries
     */
    public void buildQueries(Query[] queries, TreeNode[] ans) {
        TreeNode o1, o2;
        for (int i = 0; i < queries.length; i++) {
            o1 = queries[i].o1;
            o2 = queries[i].o2;

            if (o1 == o2 || o1 == null || o2 == null) {
                ans[i] = o1 != null ? o1 : o2;
            }
            if (!queryMap.containsKey(o1)) {
                queryMap.put(o1, new LinkedList<>());
                indexMap.put(o1, new LinkedList<>());
            }
            if (!queryMap.containsKey(o2)) {
                queryMap.put(o2, new LinkedList<>());
                indexMap.put(o2, new LinkedList<>());
            }
            queryMap.get(o1).add(o2);
            indexMap.get(o1).add(i);
            queryMap.get(o2).add(o1);
            indexMap.get(o2).add(i);
        }
    }


    public void calAnswers(TreeNode root, TreeNode[] ans) {
        if (root == null) {
            return;
        }

        calAnswers(root.left, ans);
        ufSets.union(root.left, root);  // 当前节点和左子树集合 合并，合并之后集合根节点不确定是哪个
        ancestorMap.put(ufSets.findFather(root), root); // 将上一步合并得到的集合的祖先设置为 当前节点root

        // 右子树执行同样的操作
        calAnswers(root.right, ans);
        ufSets.union(root.right, root);
        ancestorMap.put(ufSets.findFather(root), root); // 祖先节点映射中，只存放集合根节点-> 当前节点

        //左右子树处理完，处理当前节点
        List<TreeNode> queries = queryMap.get(root);
        List<Integer> indexes = indexMap.get(root);

        TreeNode node = null, nodeFather;
        int index = 0;

        if (queries != null) {
            for (int i = 0; i < queries.size(); i++) {
                node = queries.get(i);
                index = indexes.get(i);

                nodeFather = ufSets.findFather(node);// 集合根节点

                if (ancestorMap.containsKey(nodeFather)) {// 说明节点 node 已经被处理过（加到某个集合中）
                    ans[index] = ancestorMap.get(nodeFather);
                }

            }
        }


    }
}
