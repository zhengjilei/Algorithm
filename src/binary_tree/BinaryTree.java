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

    /**
     * 根据字符串反序列化构建二叉树
     * @param str
     */
    public void buildTree(String str) {

    }

    public void buildTree(int[] a, int[] b) {
        if (a.length != b.length) throw new RuntimeException("前序和中序不匹配");
        root = buildTree(a, b, 0, 0, b.length - 1);
    }

    /**
     * 根据前序和中序序列构造二叉树
     * 返回根节点
     *
     * @param a
     * @param b
     * @param aStart
     * @param bStart
     * @param bEnd
     * @return
     * @throws Exception
     */
    public TreeNode<Integer> buildTree(int[] a, int[] b, int aStart, int bStart, int bEnd) {

        // 构造以 a[aStart] 为根节点的子树返回

        if (bEnd - bStart < 0) return null;
        int j = bStart;
        // 在中序序列中找到第一个等于 a[aStart] 的数 b[j]
        while (j <= bEnd && a[aStart] != b[j]) {
            j++;
        }
        if (j > bEnd) {
            throw new RuntimeException("前序和中序不匹配"); // 前序中序不一致，必须得抛出异常，如果返回 Null 会误以为可以正确构建二叉树
        }
        TreeNode<Integer> root = new TreeNode<>(a[aStart]);

        int leftCount = j - bStart + 1;  // 左子树的节点数目, 以便确定先序序列中的右子树起始位置
        TreeNode p = buildTree(a, b, aStart + 1, bStart, j - 1); //   [aStart,aStart+leftCount-1] [bStart,j-1] 构造左子树
        TreeNode q = buildTree(a, b, aStart + leftCount, j + 1, bEnd); //[aleft+leftCount,aEnd] [j+1, bEnd] 构造右子树

        root.left = p;
        root.right = q;

        // 如果需要父节点指针的话
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

    public TreeNode<T> getRoot() {
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
     * 逐层换行打印
     * 双队列实现，每个队列轮流放置一行
     */
    public void levelOrderLine() {
        if (root == null) return;
        ArrayDeque<TreeNode> queue1 = new ArrayDeque<>();
        ArrayDeque<TreeNode> queue2 = new ArrayDeque<>();
        TreeNode node = null;
        queue1.push(root);
        while (!queue1.isEmpty() || !queue2.isEmpty()) {
            while (!queue1.isEmpty()) {
                node = queue1.poll();
                System.out.printf("%3d", node.val);
                if (node.left != null) queue2.offer(node.left);
                if (node.right != null) queue2.offer(node.right);
            }
            System.out.println();
            while (!queue2.isEmpty()) {
                node = queue2.poll();
                System.out.printf("%3d", node.val);
                if (node.left != null) queue1.offer(node.left);
                if (node.right != null) queue1.offer(node.right);
            }
            System.out.println();
        }
    }

    /**
     * 逐层换行打印
     * 辅助变量：
     * toBePrint: 当前行还需要打印的节点个数
     * nextLineNodeCount: 下一行节点的总数
     */
    public void printLine() {
        if (root == null) return;
        ArrayDeque<TreeNode> queue = new ArrayDeque<>();

        TreeNode node = null;
        queue.push(root);

        int toBePrint = 1;          // 当前行还没有打印的节点个数
        int nextLineNodeCount = 0;      // 下一行节点的总数
        while (!queue.isEmpty()) {
            node = queue.poll();
            System.out.printf("%4d", node.val);
            toBePrint--;

            if (node.left != null) {
                ++nextLineNodeCount;
                queue.push(node.left);
            }
            if (node.right != null) {
                ++nextLineNodeCount;
                queue.push(node.right);
            }

            if (toBePrint == 0) {
                System.out.println();
                toBePrint = nextLineNodeCount;
                nextLineNodeCount = 0;
            }
        }
    }

    /**
     * 之字形逐层换行打印
     *
     * @param root
     */
    public void printZhiLevel() {
        if (root == null) return;

        ArrayDeque<TreeNode<Integer>> queue = new ArrayDeque<>();
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int beToPrint = 1, nextLineNodeCount = 0;
        queue.push(root);
        TreeNode node = null;
        int flag = 0; // 0 表示打印顺序从左到右 , 1表示打印顺序从右到左，先正行压栈
        while (!queue.isEmpty()) {
            node = queue.poll();
            if (flag == 0) {
                System.out.printf("%5d", node.val);
            } else {
                stack.push((Integer) node.val);
            }
            beToPrint--;

            if (node.left != null) {
                nextLineNodeCount++;
                queue.offer(node.left);
            }
            if (node.right != null) {
                nextLineNodeCount++;
                queue.offer(node.right);
            }

            if (beToPrint == 0) {
                //一行结束
                if (flag == 1) {
                    // 从右到左的一行
                    // 积攒的一行，从栈中退出
                    while (!stack.isEmpty()) {
                        System.out.printf("%5d", stack.pop());
                    }
                    flag = 0;
                } else {
                    flag = 1;
                }
                beToPrint = nextLineNodeCount;
                nextLineNodeCount = 0;
                System.out.println();
            }
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
            p = stack.pop();
            visit(p);
            p = p.right;
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
     *
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

    /**
     * 在以 root 为根节点的树中查找 target 节点，路径保存在 path中，返回最终路径长度（返回0表示target不在 该树中）
     * 探索 index 位置的节点
     *
     * @param root
     * @param target
     * @param path
     * @param index
     * @return
     */
    public int getPath(TreeNode root, TreeNode target, TreeNode[] path, int index) {

        if (root == null) return 0;
        if (root == target) {
            path[index] = target;
            return index + 1;
        }

        path[index] = root; // 保存当前节点到路径中
        int length = 0;
        if ((length = getPath(root.left, target, path, index + 1)) > 0) {
            return length;
        }
        if ((length = getPath(root.right, target, path, index + 1)) > 0) {
            return length;
        }
        return 0;
    }


    /**
     * 在二叉树中查找节点
     * 层次遍历，队列
     *
     * @param t
     * @return
     */
    public TreeNode getNodeByValue(T t) {
        if (root != null) {
            ArrayDeque<TreeNode> queue = new ArrayDeque<>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                if (node.val.equals(t)) {
                    return node;
                }
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
        }
        return null;
    }

}
