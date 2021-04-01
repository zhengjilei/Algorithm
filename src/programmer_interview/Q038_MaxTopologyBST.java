package programmer_interview;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Map;
import java.util.TreeMap;

/**
 * 求二叉树中符合二叉搜索树性质的最大拓扑结构
 * 分析：拓扑结构 不一定是该二叉树的子树
 *
 *
 * <p>
 * created by Ethan-Walker on 2018/12/29
 */
public class Q038_MaxTopologyBST {

    TreeNode maxRoot = null;

    public int getMaxTopologySize(TreeNode root) {
        if (root == null) return 0;
        int curMax = maxTopologyBSTSize(root);
        int leftMax = getMaxTopologySize(root.left);
        int rightMax = getMaxTopologySize(root.right);

        TreeMap<Integer, TreeNode> map = new TreeMap<>();
        map.put(curMax, root);
        map.put(leftMax, root.left);
        map.put(rightMax, root.right);

        Map.Entry<Integer, TreeNode> entry = map.lastEntry();
        maxRoot = entry.getValue();

        // 为了同时得到最大拓扑结构 和 最大拓扑结构的根节点

        return entry.getKey();
    }

    public int maxTopologyBSTSize(TreeNode root) {
        if (root == null) return 0;

        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        TreeNode node = null;
        int count = 0;
        while (!queue.isEmpty()) {
            node = queue.poll();
            if (findValue(node.val, root)) {
                count++;
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
        }
        return count;
    }

    boolean findValue(int val, TreeNode root) {
        if (root == null) return false;
        if (root.val == val) return true;

        if (val > root.val) return findValue(val, root.right);
        else return findValue(val, root.left);
    }

    @Test
    public void test() {
        int[] pre = {6, 1, 0, 3, 12, 10, 4, 2, 5, 14, 11, 15, 13, 20, 16};
        int[] in = {0, 1, 3, 6, 2, 4, 5, 10, 11, 14, 15, 12, 20, 13, 16};
        TreeNode root = TreeUtil.buildTreeByPreAndIn(pre, in);

        int maxSize = getMaxTopologySize(root);
        System.out.println(maxSize);

        System.out.println(maxRoot.val);


    }

}
