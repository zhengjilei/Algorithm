package leetcode2;

import programmer_interview.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * created by Ethan-Walker on 2019/3/1
 */
public class Q094_InOrder {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();

        if (root == null) return res;
        TreeNode p = root;

        ArrayDeque<TreeNode> stack = new ArrayDeque<>();
        while (p != null || !stack.isEmpty()) { //   p==null 可能是右子树为空，但栈不为空（下一次需要再次弹出）
                                                 // 栈为空，左子树和根节点访问完毕，可能 p 不为空（访问右子树）
            while (p != null) { // 递进压入 p 的左子树节点,直到左子树节点为空
                stack.push(p);
                p = p.left;
            }
            if (!stack.isEmpty()) { // 弹出栈顶元素，进行访问，并进入到其右子树
                p = stack.pop();
                res.add(p.val);
                p = p.right;
            }
        }
        return res;
    }
}
