package 腾讯面试;

import java.util.ArrayDeque;
import java.util.ArrayList;

/**
 * 两个树节点的最近公共祖先
 * 1. 二叉树还是多叉树
 * 2. 叶节点还是任意节点(可能出现一个节点是另外一个节点的祖先)
 * 3. 若是二叉树：是普通的二叉树还是二叉搜索树,若是普通的二叉树是否有指向父节点的指针？
 * 4. 若是多叉树：两个节点追溯到根节点，形成两条链表，等价于求两个链表的第一个公共节点
 * <p>
 * created by Ethan-Walker on 2018/12/13
 */
public class ClosestFather {

    /**
     * 普通二叉树(有指向父节点的指针)
     * <p>
     * 双栈找到到根节点的路径（是左节点压0，是右节点压1），然后从根路径下沉（知道栈弹出数据不同）
     * 适用于任意两节点（不管是不是叶节点均适用）
     *
     * @param node1
     * @param node2
     * @return
     */
    public TreeNode getClosestFather(TreeNode node1, TreeNode node2) {
        if (node1 == null || node2 == null) return null;
        TreeNode pNode = node1;
        TreeNode qNode = node2;
        ArrayDeque<Integer> stack1 = new ArrayDeque<>();
        ArrayDeque<Integer> stack2 = new ArrayDeque<>();

        while (pNode.parent != null) {
            if (pNode.parent.left == node1) {
                stack1.push(0);
            } else {
                stack1.push(1);
            }
            pNode = pNode.parent;
        }
        while (qNode.parent != null) {
            if (qNode.parent.left == node1) {
                stack2.push(0);
            } else {
                stack2.push(1);
            }
            qNode = qNode.parent;
        }

        if (qNode != pNode) { // 不在同一颗树上
            return null;
        }

        TreeNode result = pNode;
        int a, b;
        while (!stack1.isEmpty() && !stack2.isEmpty()) {
            a = stack1.pop();
            b = stack2.pop();
            if (a != b) break;

            if (a == 0) {
                pNode = pNode.left;
            } else {
                pNode = pNode.right;
            }
            result = pNode;

        }
        return result;

    }

    /**
     * 二叉搜索树（可以不知道父节点,但要给出根节点）
     * 适用于任意两节点（不管是不是叶节点均适用）
     *
     * @param node1
     * @param node2
     * @return
     */
    public TreeNode getClosestFather(TreeNode root, TreeNode node1, TreeNode node2) {
        if (root == null || node1 == null || node2 == null) return null;
        if (node1 == node2) return node1;
        TreeNode pNode = root;
        while (pNode != null) {
            if (pNode.val > node1.val && pNode.val < node2.val
                    || pNode.val > node2.val && pNode.val < node1.val
                    || pNode.val == node1.val
                    || pNode.val == node2.val) { // 后面两个条件是 考虑其中一个节点是另外一个节点的祖先
                return pNode;
            }

            if (pNode.val > node1.val) {// 大于两个节点
                pNode = pNode.left;
            } else {
                pNode = pNode.right;
            }
        }
        return null;
    }


    /**
     * 任意树（可能是二叉树，可能是多叉树）
     * 两个链表的第一个公共节点
     * 计算两个链表的长度，双指针法
     * 适用于任意两节点（不管是不是叶节点均适用）
     *
     * @param node1
     * @param node2
     * @return
     */
    public MultiTreeNode getClosestFather(MultiTreeNode node1, MultiTreeNode node2) {
        if (node1 == null || node2 == null) return null;

        int pLength = 0, qLength = 0;
        MultiTreeNode pNode = node1;
        MultiTreeNode qNode = node2;
        while (pNode != null) {
            pLength++;
            pNode = pNode.parent;
        }

        while (qNode != null) {
            qLength++;
            qNode = qNode.parent;
        }

        MultiTreeNode fastNode = pNode, slowNode = qNode;
        if (qLength - pLength > 0) {
            fastNode = qNode;
            slowNode = pNode;
        }
        int l = Math.abs(pLength - qLength);
        while (l > 0) {
            fastNode = fastNode.parent;
            l--;
        }
        while (fastNode != null && slowNode != null) {
            if (fastNode == slowNode) {
                return fastNode;
            }
            fastNode = fastNode.parent;
            slowNode = slowNode.parent;
        }
        return null;
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode parent;

        TreeNode(int val) {
            this.val = val;
        }
    }

    class MultiTreeNode {
        int val;
        ArrayList<MultiTreeNode> children;
        MultiTreeNode parent;
    }
}
