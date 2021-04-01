package leetcode;

import programmer_interview.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * created by Ethan-Walker on 2018/12/24
 */
public class Q094_InOrder {


    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        if (root == null) return result;
        ArrayDeque<TreeNode> stack = new ArrayDeque<>();
        do {
            while (root != null) {
                stack.push(root);
                root = root.left; // 压当前节点，进入左子树
            }

            root = stack.pop();// 退出访问
            result.add(root.val);
            root = root.right; // 进入右子树，循环
        } while (!stack.isEmpty() || root != null);
        return result;
    }
}
