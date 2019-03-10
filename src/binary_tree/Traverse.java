package binary_tree;

import 程序员代码面试指南.TreeNode;

import java.util.*;

/**
 * created by Ethan-Walker on 2019/3/9
 */
public class Traverse {


    //前序：只压右子树
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;

        ArrayDeque<TreeNode> stack = new ArrayDeque<>();

        TreeNode node = root;
        while (true) {
            res.add(node.val);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                node = node.left;
            } else {
                if (stack.isEmpty()) {
                    break;
                }
                node = stack.pop();
            }
        }
        return res;
    }

    // 中序,一直压左子树，左子树为空，弹出栈中节点，访问，然后进入右子树，循环
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        ArrayDeque<TreeNode> stack = new ArrayDeque<>();
        TreeNode node = root;
        while (!stack.isEmpty() || node != null) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }

            node = stack.pop();
            res.add(node.val);
            node = node.right;
        }
        return res;
    }

    // 根节点压两次， 然后弹出，判断当前节点和 栈顶节点是否相等
    // 先压右子节点，再压左子节点
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        ArrayDeque<TreeNode> stack = new ArrayDeque<>();

        stack.push(root);
        stack.push(root);

        TreeNode node;
        while (!stack.isEmpty()) {
            node = stack.pop();
            if (!stack.isEmpty() && node == stack.peek()) {
                if (node.right != null) {
                    stack.push(node.right);
                    stack.push(node.right);
                }
                // node 节点的左右子节点还没访问
                if (node.left != null) {
                    stack.push(node.left);
                    stack.push(node.left);
                }

            } else {
                // 栈为空，访问的是根节点
                // 栈不为空，node != 栈顶节点, 左右子节点都已经访问过了
                res.add(node.val);
            }
        }
        return res;
    }

    public List<List<Integer>> levelOrder(TreeNode root) {

        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;


        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        int curLevelCount = 1;
        int nextLevelCount = 0;
        TreeNode node;
        List<Integer> list = new ArrayList<>();
        while (!queue.isEmpty()) {
            node = queue.poll();
            list.add(node.val);
            curLevelCount--;

            if (node.left != null) {
                queue.offer(node.left);
                nextLevelCount++;
            }
            if (node.right != null) {
                queue.offer(node.right);
                nextLevelCount++;
            }

            if (curLevelCount == 0) {
                res.add(list);
                list = new ArrayList<>();
                curLevelCount = nextLevelCount;
                nextLevelCount = 0;
            }
        }
        return res;

    }

    public List<List<Integer>> zigzag(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        int curLevelCount = 1;
        int nextLevelCount = 0;
        List<Integer> levelList = new ArrayList<>();
        int level = 1;
        TreeNode node;

        while (!queue.isEmpty()) {
            node = queue.poll();
            levelList.add(node.val);

            curLevelCount--;
            if (node.left != null) {
                queue.offer(node.left);
                nextLevelCount++;
            }
            if (node.right != null) {
                queue.offer(node.right);
                nextLevelCount++;
            }
            if (curLevelCount == 0) {
                if ((level & 1) == 0) {
                    Collections.reverse(levelList);
                }
                res.add(levelList);
                levelList = new ArrayList<>();
                curLevelCount = nextLevelCount;
                nextLevelCount = 0;
                level++;
            }
        }
        return res;
    }
}