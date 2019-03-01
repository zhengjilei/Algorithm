package binary_tree;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * created by Ethan-Walker on 2018/12/24
 */
public class TraverseOrder {


    public void visit(TreeNode node) {
        System.out.print(node.val + ",");
    }

    /**
     * 树的非递归前序遍历
     * 先访问节点，只压节点的右子树，进入到左子树。
     * 直到左子树为空，弹栈。继续循环上面的步骤
     *
     * @param node
     */
    public void preOrder2(TreeNode node) {

        if (node == null) return;
        ArrayDeque<TreeNode> stack = new ArrayDeque<>();
        do {
            while (node != null) {
                visit(node);
                if (node.right != null) {
                    stack.push(node.right);
                }
                node = node.left;
            }
            if (!stack.isEmpty()) {// 这里需要判断，因为节点的右子树可能为空，栈为空
                node = stack.pop();// 弹出进入右子树，循环上面的步骤
            }
        } while (!stack.isEmpty() || node != null);
        System.out.println();
    }

    /**
     * 树的非递归前序遍历
     * 先访问，然后一直压左子节点，直到左子节点为空
     * 弹出，进入到右子树
     *
     * @param root
     */
    public void preOrder(TreeNode root) {
        if (root == null) return;
        ArrayDeque<TreeNode> stack = new ArrayDeque<>();

        TreeNode node = root;

        do {
            while (node != null) {//
                visit(node);
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            node = node.right; // 进入到右子树

        } while (!stack.isEmpty() || node != null);
        System.out.println();
    }

    /**
     * 树的非递归中序遍历
     * 访问推迟到弹出时
     *
     * @param root
     */
    public void inOrder(TreeNode root) {
        if (root == null) return;
        ArrayDeque<TreeNode> stack = new ArrayDeque<>();

        TreeNode node = root;
        do {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            visit(node);
            node = node.right;

        } while (!stack.isEmpty() || node != null);
        System.out.println();
    }

    /**
     * 树的非递归后序遍历
     * 每个节点压两次
     * 弹出时，如果弹出的节点和栈顶节点仍然相等，说明该节点的左右子树都未遍历：先压右子树、再压左子树
     *  如果弹出节点和栈顶节点不等 ：说明该节点的左右子树都已经访问过了，该访问该节点
     *  或者栈为空：说明当前节点是树的根节点，左右子树都已经访问过了，访问该节点
     *
     * @param node
     */
    public void postOrder(TreeNode node) {
        if (node == null) return;
        ArrayDeque<TreeNode> stack = new ArrayDeque<>();

        stack.push(node);
        stack.push(node);
        TreeNode temp = null;
        while (!stack.isEmpty()) {
            temp = stack.pop();
            if (!stack.isEmpty() && temp == stack.peek()) {
                // 如果当前节点等于栈顶节点，说明该节点的左子树、右子树还未遍历
                // 先压右子树，再压左子树
                if (temp.right != null) {
                    stack.push(temp.right);
                    stack.push(temp.right);
                }
                if (temp.left != null) {
                    stack.push(temp.left);
                    stack.push(temp.left);
                }
            } else {
                // 如果栈为空，说明所有节点退出，访问的是根节点
                // temp!=stack.peek() 说明当前节点的左右子树都已经访问过
                visit(temp);
            }
        }
        System.out.println();
    }

    /**
     * 层遍历，不换行
     *
     * @param root
     * @return
     */
    public List<Integer> levelOrder(TreeNode<Integer> root) {
        ArrayList<Integer> result = new ArrayList<>();
        if (root == null) return result;

        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            root = queue.poll();
            result.add(root.val);

            if (root.left != null) {
                queue.offer(root.left);
            }
            if (root.right != null) {
                queue.offer(root.right);
            }
        }
        return result;
    }

    /**
     * 层遍历，换行
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder2(TreeNode<Integer> root) {
        ArrayList<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        ArrayList<Integer> level = new ArrayList<>();

        TreeNode<Integer> node = null;
        int curLevelCount = 1, nextLevelCount = 0;

        while (!queue.isEmpty()) {
            node = queue.poll();
            level.add(node.val);  // 访问当前行元素，curLevelCount-1
            curLevelCount--;

            if (node.left != null) {
                queue.offer(node.left);
                nextLevelCount++;
            }
            if (node.right != null) {
                queue.offer(node.right);
                nextLevelCount++;
            }

            if (curLevelCount == 0) { // 当前层遍历结束

                result.add(level);
                level = new ArrayList<>();

                curLevelCount = nextLevelCount;
                nextLevelCount = 0;
            }
        }
        return result;

    }

    @Test
    public void test() {
        BinaryTree<Integer> binaryTree = new BinaryTree<>();
        int[] pre = new int[]{10, 6, 4, 5, 7, 9, 8, 14, 16};
        int[] mid = new int[]{4, 5, 6, 7, 8, 9, 10, 14, 16};
        binaryTree.buildTree(pre, mid);

        binaryTree.levelOrderLine();

        preOrder(binaryTree.getRoot());
        preOrder2(binaryTree.getRoot());
        inOrder(binaryTree.getRoot());
        postOrder(binaryTree.getRoot());
    }
}
