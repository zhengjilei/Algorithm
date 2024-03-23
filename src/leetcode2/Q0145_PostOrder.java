package leetcode2;

import programmer_interview.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * created by Ethan-Walker on 2019/3/1
 */
public class Q0145_PostOrder {

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        ArrayDeque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        stack.push(root);
        TreeNode p;
        while (!stack.isEmpty()) {
            p = stack.pop();
            if (!stack.isEmpty() && stack.peek() == p) {
                // 如果当前节点等于栈顶节点，说明该节点的左子树、右子树还未遍历
                // 先压右子树，再压左子树
                if (p.right != null) {
                    stack.push(p.right);
                    stack.push(p.right);
                }
                if (p.left != null) {
                    stack.push(p.left);
                    stack.push(p.left);
                }
            } else {
                // 栈为空，说明当前节点是根节点
                // 弹出的节点和 栈顶不同，说明其左右子树都访问了
                res.add(p.val);
            }
        }
        return res;
    }
}
