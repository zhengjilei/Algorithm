package binary_tree;


import java.util.ArrayDeque;

/**
 * Created by Ethan-Walker on 2018/4/21.
 */
public class BinaryTree<T> {

    private Node root = null;

    public BinaryTree(T value) {
        root = new Node(value);
        root.leftChild = null;
        root.rightChild = null;
        root.parentNode = null;
    }

    private void visit(Node n) {
        System.out.print(n.value + "  ");
    }


    public void preOrderRecur(Node p) {
        if (p != null) {
            visit(p);
            preOrderRecur(p.leftChild);
            preOrderRecur(p.rightChild);
        }
    }

    public void inOrderRecur(Node p) {
        if (p != null) {
            inOrderRecur(p.leftChild);
            visit(p);
            inOrderRecur(p.rightChild);
        }
    }

    public void postOrder(Node p) {
        if (p != null) {
            postOrder(p.leftChild);
            postOrder(p.rightChild);
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
     *
     * @param p
     */
    public void levelOrder(Node p) {
        ArrayDeque<Node> queue = new ArrayDeque<>();
        queue.push(p);
        Node q;
        while (!queue.isEmpty()) {
            q = queue.poll();
            visit(q);
            if (q.leftChild != null) queue.offer(q.leftChild);
            if (q.rightChild != null) queue.offer(q.rightChild);
        }
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
