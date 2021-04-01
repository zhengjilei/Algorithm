package programmer_interview;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * created by Ethan-Walker on 2018/12/26
 */
public class Q032_PrintEdgeNode {


    public List<Integer> print1(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        int height = getHeight(root);
        TreeNode[][] edges = new TreeNode[height][2]; // [i][0] 放第i+1 层的最左节点，[i][1] 放第 i+1 层的最右节点

        setEdge(edges, root);

        // 先顺序访问所有的最左节点
        for (int i = 0; i < edges.length; i++) {
            result.add(edges[i][0].val);
        }
        // 然后先序访问所有非最左、非最右的叶节点
        accessLeafNotEdge(edges, 0, root, result);

        // 最后倒序访问所有的最右节点（和同行最左节点相等时不访问）
        for (int i = edges.length - 1; i >= 0; i--) {
            if (edges[i][1] != edges[i][0]) {
                result.add(edges[i][1].val);
            }
        }
        return result;
    }


    public void accessLeafNotEdge(TreeNode[][] edges, int level, TreeNode root, List<Integer> result) {
        if (root == null) return;

        if (root.left == null && root.right == null && edges[level][0] != root && edges[level][1] != root) {
            result.add(root.val);
        }
        accessLeafNotEdge(edges, level + 1, root.left, result);
        accessLeafNotEdge(edges, level + 1, root.right, result);
    }


    /**
     * 设置每一层的最左最右节点
     *
     * @param nodes
     * @param root
     */
    public void setEdge(TreeNode[][] nodes, TreeNode root) {

        ArrayDeque<TreeNode> queue = new ArrayDeque<>();

        int curLevelCount = 1, nextLevelCount = 0, cnt = 0, line = 0;
        TreeNode node = null;
        while (!queue.isEmpty()) {
            node = queue.poll();

            if (cnt == 0) {
                // 这是当前行访问的最左节点
                nodes[line][0] = node;
            }
            if (cnt == curLevelCount - 1) {
                // 这是当前行访问的最右节点
                nodes[line][1] = node;
            }
            cnt++; //

            if (node.left != null) {
                queue.offer(node.left);
                nextLevelCount++;
            }
            if (node.right != null) {
                queue.offer(node.right);
                nextLevelCount++;
            }


            if (cnt == curLevelCount) { // 当前行已经全部访问完
                line++;
                curLevelCount = nextLevelCount;
                nextLevelCount = 0;
                cnt = 0;
            }
        }

    }


    public int getHeight(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(getHeight(root.left), getHeight(root.right));
    }

}
