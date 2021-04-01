package leetcode;

import programmer_interview.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * created by Ethan-Walker on 2018/12/24
 */
public class Q144_PreOrder {

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        if (root == null) return result;

        ArrayDeque<TreeNode> stack = new ArrayDeque<>();
        do {
            while (root != null) {
                result.add(root.val); // 访问
                if (root.right != null) {
                    stack.push(root.right);//只压右子树
                }
                root = root.left; // 进入左子树
            }

            if (!stack.isEmpty()) {
                root = stack.pop(); // 进入右子树
            }

        } while (!stack.isEmpty() || root != null);
        return result;

    }


    public List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        if (root == null) return result;

        ArrayDeque<TreeNode> stack = new ArrayDeque<>();

        do {
            while (root != null) {
                //一直压当前节点
                result.add(root.val);
                stack.push(root);
                root = root.left;// 进入左子树
            }

            root = stack.pop(); // 弹出父节点
            root = root.right; // 进入右子树
        } while (!stack.isEmpty() || root != null);

        return result;

    }

}
