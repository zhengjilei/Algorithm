package leetcode;

import programmer_interview.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * created by Ethan-Walker on 2018/12/24
 */
public class Q0145_PostOrder {

    public List<Integer> postorderTraversal(TreeNode root) {

        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        ArrayDeque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        stack.push(root);
        while (!stack.isEmpty()) {
            root = stack.pop();
            if (!stack.isEmpty() && root == stack.peek()) {
                // 如果当前节点等于栈顶节点，说明该节点的左子树、右子树还未遍历
                // 先压右子树，再压左子树
                if (root.right != null) {
                    stack.push(root.right);
                    stack.push(root.right);
                }
                if (root.left != null) {
                    stack.push(root.left);
                    stack.push(root.left);
                }
            } else {
                // 左右子树都已经访问
                result.add(root.val);
            }
        }
        return result;
    }
}
