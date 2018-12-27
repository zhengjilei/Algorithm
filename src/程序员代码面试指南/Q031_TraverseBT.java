package 程序员代码面试指南;

import java.util.ArrayDeque;
import java.util.ArrayList;

/**
 * created by Ethan-Walker on 2018/12/26
 */
public class Q031_TraverseBT {


    public ArrayList<Integer> preOrder(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        if (root == null) return result;
        ArrayDeque<TreeNode> stack = new ArrayDeque<>();

        do {
            while (root != null) {
                result.add(root.val); // 访问
                stack.push(root);
                root = root.left; // 进入到左子树
            }
            // 不需要判断栈是否为空，栈一定不空
            root = stack.pop(); // 父节点弹出
            root = root.right; // 进入右子树
        } while (!stack.isEmpty() || root != null);
        // 栈为空，root！=null ：退到根节点了，开始访问右子树
        // root == null，栈不为空：右子树为空

        return result;
    }

    /**
     * 只压右子树
     *
     * @param root
     * @return
     */
    public ArrayList<Integer> preOrder2(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        if (root == null) return result;
        ArrayDeque<TreeNode> stack = new ArrayDeque<>();

        do {
            while (root != null) {
                result.add(root.val);
                if (root.right != null) stack.push(root.right);
                root = root.left;
            }
            // 需要判断栈是否为空, 可能上一步的 root.right == null
            if (!stack.isEmpty()) {
                root = stack.pop(); // 弹出最近的右子树
            }
        } while (!stack.isEmpty() || root != null);
        return result;
    }

    public ArrayList<Integer> inOrder(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        if (root == null) return result;

        ArrayDeque<TreeNode> stack = new ArrayDeque<>();

        // 方法同前序遍历，但是延迟到退出时访问
        do {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            result.add(root.val);
            root = root.right;
        } while (!stack.isEmpty() || root != null);

        return result;
    }


    public ArrayList<Integer> postOrder(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        if (root == null) return result;
        ArrayDeque<TreeNode> stack = new ArrayDeque<>();

        stack.push(root);
        stack.push(root);

        while (!stack.isEmpty()) {
            root = stack.pop();
            if (!stack.isEmpty() && root == stack.peek()) {
                // 当前节点左右子树未访问

                if (root.right != null) {
                    stack.push(root.right);
                    stack.push(root.right);
                }
                if (root.left != null) {
                    stack.push(root.left);
                    stack.push(root.left);
                }

            } else {
                // 栈为空：当前节点是根节点,栈中已没有元素
                // root!=stack.peek() 当前节点的左右子树已经访问过
                result.add(root.val);
            }
        }
        return result;
    }
}
