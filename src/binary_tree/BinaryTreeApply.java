package binary_tree;

/**
 * created by Ethan-Walker on 2018/11/4
 */
public class BinaryTreeApply {

    /**
     * 递归计算二叉树的节点数
     *
     * @param p
     * @return
     */
    public static int size(Node p) {
        if (p == null) return 0;
        return 1 + size(p.leftChild) + size(p.rightChild);
    }

    /**
     * 递归计算二叉树的深度
     */
    public static int height(Node p) {
        if (p == null) return 0;
        else {
            int i = height(p.leftChild);
            int j = height(p.rightChild);
            return (i > j) ? i + 1 : j + 1;
        }
    }

    /**
     * 递归判断两颗二叉树是否相等
     */
    public boolean equal(Node a, Node b) {
        if (a == null && b == null) return true;// 空树
        if (a != null && b != null && a.leftChild.value == b.rightChild.value && equal(a.leftChild, b.leftChild) && equal(a.rightChild, b.rightChild)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 复制创建二叉树
     */
    public Node copy(Node p) {
        if (p == null) {
            return null;
        }
        Node t = new Node(p.value);
        t.leftChild = copy(p.leftChild);
        t.rightChild = copy(p.rightChild);
        return t;
    }
}
