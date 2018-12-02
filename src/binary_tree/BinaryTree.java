package binary_tree;


import java.util.ArrayDeque;

/**
 * Created by Ethan-Walker on 2018/4/21.
 */
public class BinaryTree<T> {

    private Node root = null;

    public BinaryTree() {

    }

    public BinaryTree(T value) {
        root = new Node(value);
        root.leftChild = null;
        root.rightChild = null;
        root.parentNode = null;
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
    public Node<Integer> buildTree(int[] a, int[] b, int aStart, int bStart, int bEnd) throws Exception {

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

        Node<Integer> root = new Node<>(a[aStart]);

        Node p = buildTree(a, b, aStart + 1, bStart, bStart + k - 1); // [bStart,bStart+k-1] 构造左子树
        Node q = buildTree(a, b, aStart + k + 1, bStart + k + 1, bEnd); // [bStart+k+1, bEnd] 构造右子树

        root.leftChild = p;
        root.rightChild = q;
        if (p != null) {
            p.parentNode = root;
        }
        if (q != null) {
            q.parentNode = root;
        }
        return root;
    }

    /**
     * 获取中序遍历下的下一个节点
     *
     * @param node
     * @return
     */
    public Node<Integer> getNext(Node<Integer> node) {

        if (node == null) return null;
        Node<Integer> p = node;

        if (p.rightChild != null) {
            // 右子节点不为空，中序下一个节点为右子树的最左子节点
            p = p.rightChild;
            while (p.leftChild != null) {
                p = p.leftChild;
            }
            return p;
        } else {
            // 右子节点为空
            Node<Integer> parent;
            while (p != null) {
                parent = p.parentNode;
                if (parent != null) {
                    if (parent.leftChild == p) {
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
        Node p = getFirstInOrder();
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
    public Node<Integer> getFirstInOrder() {
        Node<Integer> p = root;
        while (p.leftChild != null) {
            p = p.leftChild;
        }
        return p;
    }

    private void visit(Node n) {
        System.out.print(n.value + "  ");
    }

    public void setRoot(Node root) {
        if (root == null) return;
        this.root = root;
    }

    public Node getRoot() {
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

    private void preOrderRecur(Node p) {
        if (p != null) {
            visit(p);
            preOrderRecur(p.leftChild);
            preOrderRecur(p.rightChild);
        }
    }

    private void inOrderRecur(Node p) {
        if (p != null) {
            inOrderRecur(p.leftChild);
            visit(p);
            inOrderRecur(p.rightChild);
        }
    }

    private void postOrderRecur(Node p) {
        if (p != null) {
            postOrderRecur(p.leftChild);
            postOrderRecur(p.rightChild);
            visit(p);
        }
    }

    /**
     * 前序遍历，非递归
     * 先压根节点，然后出栈访问，有右子树节点 先压右子树节点,然后压左子树节点
     * 出栈访问，循环第一步
     */
    public void preOrder() {
        ArrayDeque<Node> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            root = stack.pop();
            visit(root);
            if (root.rightChild != null) stack.push(root.rightChild);
            if (root.leftChild != null) stack.push(root.leftChild);
        }
    }

    /**
     * 前序遍历，非递归
     * 只压右节点
     */
    public void preOrderAno() {
        ArrayDeque<Node> stack = new ArrayDeque<>();
        Node p = root;
        stack.push(null); // 防止栈为空，弹出出错
        while (p != null) {
            visit(p);
            if (p.rightChild != null) stack.push(p.rightChild);
            if (p.leftChild != null) p = p.leftChild;
            else p = stack.pop();  // 左子树为空，得访问右子树了（中间节点最先已经访问过了）
        }
    }

    /**
     * 层次序遍历：队列
     */
    public void levelOrder() {
        if (root == null) return;
        ArrayDeque<Node> queue = new ArrayDeque<>();
        queue.push(root);
        Node q;
        while (!queue.isEmpty()) {
            q = queue.poll();
            visit(q);
            if (q.leftChild != null) queue.offer(q.leftChild);
            if (q.rightChild != null) queue.offer(q.rightChild);
        }
        System.out.println();
    }

    /**
     * 中序遍历
     * 非递归
     */
    public void midOrder() {
        ArrayDeque<Node> stack = new ArrayDeque<>();
        Node p = root;
        do {
            // 递进压入 p 的左子树节点,直到左子树节点为空
            while (p != null) {
                stack.push(p);
                p = p.leftChild;
            }

            // 弹出栈顶元素，进行访问，并进入到其右子树，重复该操作
            if (!stack.isEmpty()) {
                p = stack.pop();
                visit(p);
                p = p.rightChild;
            }
        } while (p != null || !stack.isEmpty());
        // p==null 可能是右子树为空，但栈不为空（下一次需要再次弹出）
        // 栈为空，可能 p 不为空
    }

    /**
     * 后序遍历，节点内部设置 L、R标记 略复杂
     */
}
