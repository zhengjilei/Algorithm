package binary_tree;


import java.util.ArrayDeque;

/**
 * Created by Ethan-Walker on 2018/4/21.
 */
public class BinaryTree<T> {

    private TreeNode root = null;



    public BinaryTree() {

    }

    public BinaryTree(T value) {
        root = new TreeNode(value);
        root.left = null;
        root.right = null;
        root.parent = null;
    }

    public void buildTree(int[] a, int[] b) throws Exception {
        root = buildTree(a, b, 0, 0, b.length - 1);
    }

    /**
     * 根据前序和中序序列构造二叉树
     *
     * @param a
     * @param b
     * @param aStart
     * @param bStart
     * @param bEnd
     * @return
     * @throws Exception
     */
    public TreeNode<Integer> buildTree(int[] a, int[] b, int aStart, int bStart, int bEnd) throws Exception {

        if (bEnd - bStart < 0) return null;
        int k = 0;
        int j = bStart;
        while (j <= bEnd && a[aStart] != b[j]) {
            j++;
            k++;
        }
        if (j > bEnd) {
            throw new Exception("前序和中序不匹配"); // 前序中序不一致，必须得抛出异常，如果返回 Null 会误以为可以正确构建二叉树
        }
        // k 表示 左子树节点个数

        TreeNode<Integer> root = new TreeNode<>(a[aStart]);

        TreeNode p = buildTree(a, b, aStart + 1, bStart, bStart + k - 1); // [bStart,bStart+k-1] 构造左子树
        TreeNode q = buildTree(a, b, aStart + k + 1, bStart + k + 1, bEnd); // [bStart+k+1, bEnd] 构造右子树

        root.left = p;
        root.right = q;
        if (p != null) {
            p.parent = root;
        }
        if (q != null) {
            q.parent = root;
        }
        return root;
    }

    /**
     * 获取中序遍历下的下一个节点
     *
     * @param node
     * @return
     */
    public TreeNode<Integer> getNext(TreeNode<Integer> node) {

        if (node == null) return null;
        TreeNode<Integer> p = node;

        if (p.right != null) {
            // 右子节点不为空，中序下一个节点为右子树的最左子节点
            p = p.right;
            while (p.left != null) {
                p = p.left;
            }
            return p;
        } else {
            // 右子节点为空
            TreeNode<Integer> parent;
            while (p != null) {
                parent = p.parent;
                if (parent != null) {
                    if (parent.left == p) {
                        // p 是 父节点的左子节点, 父节点即为中序下一个节点
                        return parent;
                    } else {
                        p = parent;
                    }
                } else {
                    break;
                }
            }


        }
        return null;
    }

    public void inOrderByGetNext() {
        System.out.print("inOrderByGetNext: ");
        TreeNode p = getFirstInOrder();
        while (p != null) {
            visit(p);
            p = getNext(p);
        }
        System.out.println();
    }

    /**
     * 获取中序遍历下的第一个节点
     *
     * @return
     */
    public TreeNode<Integer> getFirstInOrder() {
        TreeNode<Integer> p = root;
        while (p.left != null) {
            p = p.left;
        }
        return p;
    }

    private void visit(TreeNode n) {
        System.out.print(n.val + "  ");
    }

    public void setRoot(TreeNode root) {
        if (root == null) return;
        this.root = root;
    }

    public TreeNode getRoot() {
        return root;
    }

    public void preOrderRecur() {
        if (root == null) return;
        preOrderRecur(root);
        System.out.println();
    }

    public void inOrderRecur() {
        if (root == null) return;

        inOrderRecur(root);
        System.out.println();
    }

    public void postOrderRecur() {
        if (root == null) return;

        postOrderRecur(root);
        System.out.println();
    }

    private void preOrderRecur(TreeNode p) {
        if (p != null) {
            visit(p);
            preOrderRecur(p.left);
            preOrderRecur(p.right);
        }
    }

    private void inOrderRecur(TreeNode p) {
        if (p != null) {
            inOrderRecur(p.left);
            visit(p);
            inOrderRecur(p.right);
        }
    }

    private void postOrderRecur(TreeNode p) {
        if (p != null) {
            postOrderRecur(p.left);
            postOrderRecur(p.right);
            visit(p);
        }
    }

    /**
     * 前序遍历，非递归
     * 先压根节点，然后出栈访问，有右子树节点 先压右子树节点,然后压左子树节点
     * 出栈访问，循环第一步
     */
    public void preOrder() {
        ArrayDeque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            root = stack.pop();
            visit(root);
            if (root.right != null) stack.push(root.right);
            if (root.left != null) stack.push(root.left);
        }
    }

    /**
     * 前序遍历，非递归
     * 只压右节点
     */
    public void preOrderAno() {
        ArrayDeque<TreeNode> stack = new ArrayDeque<>();
        TreeNode p = root;
        stack.push(null); // 防止栈为空，弹出出错
        while (p != null) {
            visit(p);
            if (p.right != null) stack.push(p.right);
            if (p.left != null) p = p.left;
            else p = stack.pop();  // 左子树为空，得访问右子树了（中间节点最先已经访问过了）
        }
    }

    /**
     * 层次序遍历：队列
     */
    public void levelOrder() {
        if (root == null) return;
        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        queue.push(root);
        TreeNode q;
        while (!queue.isEmpty()) {
            q = queue.poll();
            visit(q);
            if (q.left != null) queue.offer(q.left);
            if (q.right != null) queue.offer(q.right);
        }
        System.out.println();
    }

    /**
     * 中序遍历
     * 非递归
     */
    public void midOrder() {
        ArrayDeque<TreeNode> stack = new ArrayDeque<>();
        TreeNode p = root;
        do {
            // 递进压入 p 的左子树节点,直到左子树节点为空
            while (p != null) {
                stack.push(p);
                p = p.left;
            }

            // 弹出栈顶元素，进行访问，并进入到其右子树，重复该操作
            if (!stack.isEmpty()) {
                p = stack.pop();
                visit(p);
                p = p.right;
            }
        } while (p != null || !stack.isEmpty());
        // p==null 可能是右子树为空，但栈不为空（下一次需要再次弹出）
        // 栈为空，可能 p 不为空
    }

    /**
     * 后序遍历，节点内部设置 L、R标记 略复杂
     */


    public void mirrorTree() {
        if (root == null) return;
        ArrayDeque<TreeNode> stack = new ArrayDeque<>();
        TreeNode node = root, temp;
        stack.push(root);
        while (!stack.isEmpty()) {
            node = stack.pop();
            temp = node.left;
            node.left = node.right;
            node.right = temp;

            if (node.left != null) stack.push(node.left);
            if (node.right != null) stack.push(node.right);
        }
    }

    public void mirrorTreeRecur() {
        mirrorTreeRecur(root);
    }

    /**
     * 求二叉树的镜像
     *
     * @param root
     * @return
     */
    private void mirrorTreeRecur(TreeNode root) {
        if (root == null) return;

        TreeNode left = root.left;
        TreeNode right = root.right;
        root.left = right;
        root.right = left;
        mirrorTreeRecur(root.left);
        mirrorTreeRecur(root.right);
    }


    /**
     * 判断一个二叉树是否是对称结构
     * @param root
     * @return
     */
    public boolean isSymmetrical(TreeNode<Integer> root) {
        if (root == null) return true;
        if (root.left == null && root.right == null) return true;

        if (root.left != null && root.right != null) {
            return judge(root.left, root.right);
        }
        return false;
    }

    public boolean judge(TreeNode<Integer> root1, TreeNode<Integer> root2) {
        if (root1 == null && root2 == null) return true;
        if (root1 == null || root2 == null) return false; // 只有一个为空
        if (!root1.val.equals(root2.val)) {
            return false;
        }
        return judge(root1.left, root2.right) && judge(root1.right, root2.left);
    }

}
