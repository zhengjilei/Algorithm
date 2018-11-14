package binary_tree;

/**
 * 中序线索二叉树
 * created by Ethan-Walker on 2018/11/5
 */
public class ThreadTree {
    ThreadNode root;

    private void visit(ThreadNode p) {
        System.out.print(p.value + " ");
    }

    /**
     * 获取节点node 的后继节点
     *
     * @param node
     * @return
     */
    public ThreadNode next(ThreadNode node) {
        if (node.rTag == 1) {
            // 说明right 存的就是 后继
            return node.right;
        } else {
            // 说明 right 存的是 右孩子
            // 后继为右孩子节点的最左下角节点
            return first(node.right);
        }
    }

    private ThreadNode first(ThreadNode node) {
        ThreadNode q = node;
        while (q.lTag == 0) q = q.left;
        return q;
    }

    /**
     * 获取节点 node 的前趋节点
     *
     * @param node
     * @return
     */
    public ThreadNode prev(ThreadNode node) {
        if (node.lTag == 1) {
            return node.left;
        } else {
            // p 有左孩子
            // p 的前驱节点为左孩子的 最右下角节点
            return last(node.left);
        }
    }

    private ThreadNode last(ThreadNode node) {
        ThreadNode p = node;
        while (p.rTag == 0) p = p.right;
        return p;
    }

    /**
     * 利用线索进行中序遍历
     *
     * @param node
     */
    public void inOrder(ThreadNode node) {
        ThreadNode p = first(node); // 中序遍历下的第一个节点
        while (p != null) {
            visit(p);
            p = next(p);
        }
    }

    /**
     * 对已有的二叉树进行中序线索化
     */
    public void createInThread() {

        ThreadNode pre = null;
        if (root != null) {
            createInThread(root, pre);
            pre.right = null; // 线索化之后，pre 指向中序遍历最后一个节点
            pre.rTag = 1;
        }
    }

    private void createInThread(ThreadNode current, ThreadNode pre) {
        if (current == null) return;
        createInThread(current.left, pre); //对左子树进行递归线索化，左子树节点的前驱可能是 pre，不可能是current（视为中间节点）
        // current 建立前驱
        if (current.left == null) {
            // 左子树为空，current 才能有前驱
            current.left = pre;
            current.lTag = 1;
        }
        //pre 建立后驱
        if (pre != null && pre.right == null) {
            pre.right = current;
            pre.rTag = 1;
        }
        pre = current; // 前驱跟上，中间节点作为右子树的前驱
        createInThread(current.right,pre);
    }
}
