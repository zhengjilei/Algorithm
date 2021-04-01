package binary_search_tree;

import java.util.ArrayDeque;

/**
 * created by Ethan-Walker on 2018/11/8
 */
public class BinarySearchTree {

    Node root;
    int size;

    public BinarySearchTree() {
    }

    public Node getRoot() {
        return root;
    }

    /**
     * 返回 false 表明val 已在树中
     *
     * @param val
     * @return
     */
    public boolean insert(int val) {
        Node p = null, q = root;
        while (q != null) {
            p = q;
            if (val > q.val) {
                q = q.right;
            } else if (val < q.val) {
                q = q.left;
            } else {
                return false;
            }
        }
        q = new Node(val);
        if (p == null) {
            root = q;
            size++;
            return true;
        }
        if (val < p.val) {
            p.left = q;
        } else {
            p.right = q;
        }
        size++;
        return true;
    }

    public Node search(int val) {
        Node p = root;
        while (p != null) {
            if (val == p.val) {
                return p;
            } else if (val < p.val) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        return null;
    }

    public void buildBST(int[] val) {
        for (int i = 0; i < val.length; i++) {
            insert(val[i]);
        }
    }


    public void levelOrder() {
        Node p = root, q;
        ArrayDeque<Node> queue = new ArrayDeque<>();
        queue.offer(p);

        while (!queue.isEmpty()) {
            p = queue.poll();
            System.out.printf("%5d", p.val);
            if (p.left != null) {
                queue.offer(p.left);
            }
            if (p.right != null) {
                queue.offer(p.right);
            }
        }
        System.out.println();

    }

    public void preOrder() {
        preOrder(root);
        System.out.println();
    }

    public void preOrder(Node p) {
        if (p != null) {
            System.out.printf("%5d", p.val);
            preOrder(p.left);
            preOrder(p.right);
        }

    }

    public void inOrder() {
        inOrder(root);
        System.out.println();

    }

    public void inOrder(Node p) {
        if (p != null) {
            inOrder(p.left);
            System.out.printf("%5d", p.val);
            inOrder(p.right);
        }
    }

    /**
     * 删除节点
     *
     * @param val
     * @return false 未找到该节点
     */
    public boolean delete(int val) {
        if (root == null) return false;
        Node p = null, q = root;
        while (q != null) {
            p = q;
            if (val < q.val) {
                q = q.left;
            } else if (val > q.val) {
                q = q.right;
            } else {
                if (p == null) { // 只有根节点
                    root = null;
                    size--;
                    return true;
                }
                if (q.left == null) {
                    // 左子树为空，用右子树顶替
                    if (q.val < p.val) {
                        p.left = q.right;
                    } else {
                        p.right = q.right;
                    }
                } else if (q.right == null) {
                    // 右子树为空，用右子树顶替
                    if (q.val < p.val) {
                        p.left = q.left;
                    } else {
                        p.right = q.left;
                    }
                } else {
                    //左右子树都不为空，选择右子树的左下角顶点（右子树中序遍历下的第一个节点）
                    Node a = q, b = q.right;
                    if (b.left == null) {
                        // 右子树的左子树为空
                        q.val = b.val;
                        q.right = b.right;
                    } else {
                        // 右子树的左子树不为空
                        while (b.left != null) {
                            a = b;
                            b = b.left;
                        }
                        q.val = b.val;
                        a.left = b.right;
                    }
                }
                size--;
                return true;
            }
        }
        return false;

    }

    /**
     * 寻找该二叉搜索树上最小的节点
     * 返回中序下第一个节点（最左下角）
     *
     * @param p
     * @return
     */
    public Node firstInOrder(Node p) {
        if (p == null) return null;
        while (p.left != null) {
            p = p.left;
        }
        return p;
    }

    /**
     * 寻找该二叉搜索树的最大节点
     * 返回中序下的最后一个节点（最右下角）
     *
     * @param p
     * @return
     */
    public Node lastInOrder(Node p) {
        if (p == null) return p;
        while (p.right != null) p = p.right;
        return p;
    }

    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        bst.buildBST(new int[]{53, 78, 65, 17, 87, 9, 81, 45, 23});
        bst.levelOrder();
        bst.preOrder();
        bst.inOrder();

        System.out.println(bst.delete(77));
        System.out.println(bst.delete(45));
        bst.levelOrder();
        bst.preOrder();
        bst.inOrder();

    }
}
