package avl;

import java.util.ArrayDeque;

/**
 * created by Ethan-Walker on 2018/11/8
 */
public class AVL {

    Node root;
    int size;

    /**
     * 左单旋转
     * q是 p的子节点， dir=0 表示是左子节点
     *
     * @param p
     * @param q 出现平衡因子失常的节点
     */
    public void rotateL(Node p, Node q) {
        Node r = q.right;
        q.right = r.left;
        r.left = q;
        if (p != null) {
            // 说明 q 没有父节点，q本身是根节点
            if (q.val < p.val) {
                p.left = r;
            } else {
                p.right = r;
            }
        } else {
            root = r;
        }
        q.bf = r.bf = 0;
    }

    /**
     * 右单旋转
     * q 是 p 的子节点
     *
     * @param p
     * @param q 现平衡因子失常的节点
     */
    public void rotateR(Node p, Node q) {
        Node r = q.left;
        q.left = r.right;
        r.right = q;
        if (p != null) {
            if (q.val < p.val) {
                p.left = r;
            } else {
                p.right = r;
            }
        } else {
            root = r;
        }

        q.bf = r.bf = 0;
    }

    /**
     * < 型
     * 先左后右双旋转
     * p 是 a 的父节点
     *
     * @param p
     * @param a
     */
    public void rotateLR(Node p, Node a) {

        Node b = a.left;
        Node c = b.right;
        //左单旋转
        b.right = c.left;
        c.left = b;
        if (c.bf <= 0) b.bf = 0;
        else b.bf = -1;

        //右单旋转
        a.left = c.right;
        c.right = a;
        if (c.bf == -1) a.bf = 1;
        else a.bf = 0;
        c.bf = 0;

        if (p != null) {
            if (a.val < p.val) {
                p.left = c;
            } else {
                p.right = c;
            }
        } else {
            root = c;
        }

    }

    /**
     * 先右后左双旋转
     *
     * @param p
     * @param a
     */
    public void rotateRL(Node p, Node a) {
        Node b = a.right;
        Node c = b.left;
        //右旋转
        b.left = c.right;
        c.right = b;

        if (c.bf >= 0) b.bf = 0;
        else b.bf = 1;

        //左旋转
        a.right = c.left;
        c.left = a;
        if (c.bf == 1) a.bf = -1;
        else a.bf = 0;
        c.bf = 0;

        if (p != null) {
            if (a.val < p.val) {
                p.left = c;
            } else {
                p.right = c;
            }
        } else {
            root = c;
        }

    }

    public boolean insert(int val) {

        ArrayDeque<Node> stack = new ArrayDeque<>();
        Node q = root, p = null;
        while (q != null) {
            p = q;
            if (q.val == val) return false;
            else if (val < q.val) {
                stack.push(q);
                q = q.left;
            } else {
                stack.push(q);
                q = q.right;
            }
        }
        q = new Node(val);
        if (p == null) {
            // 说明二叉树为空
            root = q;
            size++;
            return true;
        }
        if (val < p.val) {
            p.left = q;
        } else {
            p.right = q;
        }
        while (!stack.isEmpty()) {
            p = stack.pop();
            if (q == p.left) {
                p.bf--;
            } else {
                p.bf++;
            }
            if (p.bf == 0) break;
            if (p.bf == 1 || p.bf == -1) {
                q = p;
            } else {
                // p.bf 绝对值等于2(回退导致的)
                int d = (p.bf < 0) ? -1 : 1;
                Node parent = null;
                if (!stack.isEmpty()) {
                    parent = stack.peekFirst();
                }
                if (q.bf == d) {
                    //同号,单旋转
                    // 获取 p 的父节点
                    if (d == -1) {
                        //右单旋转
                        rotateR(parent, p);
                    } else {
                        rotateL(parent, p);
                    }
                } else {
                    // 异号，双旋转
                    if (d == -1) {
                        // < 型，先左后右旋转
                        rotateLR(parent, p);
                    } else {
                        rotateRL(parent, p);
                    }
                }
                break;
            }
        }
        size++;
        return true;
    }


    public void levelOrder() {
        Node p = root, q;
        ArrayDeque<Node> queue = new ArrayDeque<>();
        queue.offer(p);

        while (!queue.isEmpty()) {
            p = queue.poll();
            System.out.printf("%5d,%3d", p.val, p.bf);
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
            System.out.printf("%5d,%3d", p.val, p.bf);
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
            System.out.printf("%5d,%3d", p.val, p.bf);
            inOrder(p.right);
        }
    }

    public static void main(String[] args) {
        AVL avl = new AVL();
        int[] a = new int[]{16, 3, 7, 11, 9, 26, 18, 14, 15};
        for (int i = 0; i < a.length; i++) {
            avl.insert(a[i]);
        }
        avl.levelOrder();
        avl.preOrder();
        avl.inOrder();
    }

}
