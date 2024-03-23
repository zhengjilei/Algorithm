package leetcode2;

import programmer_interview.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * created by Ethan-Walker on 2019/3/1
 */
public class Q0144_PreOrder {

    public List<Integer> preorderTraversal(TreeNode root) {
        ArrayDeque<TreeNode> stack = new ArrayDeque<>();
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;

        TreeNode p = root;
        while (true) { // 当所有元素遍历完，从栈中弹出的是  null
            res.add(p.val);
            if (p.right != null) stack.push(p.right);
            if (p.left != null) p = p.left;
            else {
                if (!stack.isEmpty()) {
                    p = stack.pop();
                } else break;
            }
        }
        return res;
    }
}
