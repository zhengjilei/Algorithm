package a_review.interview.grab;

import org.junit.Test;
import programmer_interview.TreeNode;
import programmer_interview.TreeUtil;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * created by Ethan-Walker on 2019/3/18
 */
public class BTLevelOrder {


    public void levelOrder(TreeNode root) {
        if (root == null) return;
        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        int curLevelCount = 1;
        int nextLevelCount = 0;
        queue.offer(root);
        TreeNode t;

        List<Integer> level = new ArrayList<>();
        while (!queue.isEmpty()) {
            t = queue.poll();
            level.add(t.val);

            if (t.left != null) {
                nextLevelCount++;
                queue.offer(t.left);
            }
            if (t.right != null) {
                nextLevelCount++;
                queue.offer(t.right);
            }
            curLevelCount--;
            if (curLevelCount == 0) {
                System.out.println(level); // 该层全部访问，输出
                level = new ArrayList<>();
                curLevelCount = nextLevelCount;
                nextLevelCount = 0;
            }
        }
        return;
    }


    @Test
    public void test() {
        // 左右子节点都有
        TreeNode root = TreeUtil.buildTreeByPreAndIn(new int[]{1, 2, 3}, new int[]{2, 1, 3});
        levelOrder(root);
        System.out.println("----------------");

        // 所有节点只有左子节点
        TreeNode root2 = TreeUtil.buildTreeByPreAndIn(new int[]{1, 2, 3, 4}, new int[]{4, 3, 2, 1});
        levelOrder(root2);
        System.out.println("----------------");

        // 所有节点只有右子节点
        TreeNode root3 = TreeUtil.buildTreeByPreAndIn(new int[]{1, 2, 3, 4}, new int[]{1, 2, 3, 4});
        levelOrder(root3);
    }

}
