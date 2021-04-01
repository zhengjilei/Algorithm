package 剑指offer;

import org.junit.Test;
import programmer_interview.TreeNode;
import programmer_interview.TreeUtil;

import java.util.ArrayList;

/**
 * created by Ethan-Walker on 2018/12/7
 */
public class Q034_TreeSumPath {

    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        int[] seq = new int[getHeight(root)];
        findPath(res, seq, 0, root, target);

        return res;
    }

    public int getHeight(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(getHeight(root.left), getHeight(root.right));
    }

    public void findPath(ArrayList<ArrayList<Integer>> res, int[] seq, int index, TreeNode root, int target) {
        if (root.val == target) {
            if (root.left == null && root.right == null) {
                seq[index++] = root.val;
                ArrayList<Integer> list = new ArrayList<>();
                for (int i = 0; i < index; i++) {
                    list.add(seq[i]);
                }
                res.add(list);
                return;
            }
            //注意这里不返回, 可能后面 到达叶节点路径上经过的节点 之和  = 0
        }
        seq[index] = root.val;
        if (root.left != null) {
            findPath(res, seq, index + 1, root.left, target - root.val);
        }
        if (root.right != null) {
            findPath(res, seq, index + 1, root.right, target - root.val);
        }
    }

    @Test
    public void test() {
        TreeNode treeNode = TreeUtil.buildTreeByPreAndIn(new int[]{10, 5, 7, -6, 6, 12}, new int[]{6, -6, 7, 5, 10, 12});
        System.out.println(FindPath(treeNode, 22));
    }
}
