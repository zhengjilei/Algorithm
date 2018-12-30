package 腾讯面试;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * 两个树节点的最近公共祖先
 * 1. 二叉树还是多叉树
 * 2. 叶节点还是任意节点(可能出现一个节点是另外一个节点的祖先)
 * 3. 若是二叉树：是普通的二叉树还是二叉搜索树
 * 二叉搜索树：给定父节点，判断大小、往下递归循环即可
 * 普通二叉树：和多叉树情况下的操作相同
 *
 * 4. 若是多叉树：是否有父节点指针？
 * 有：两个节点追溯到根节点，形成两条链表，等价于求两个链表的第一个公共节点
 * 无：遍历找到从根节点到目标节点的路径，寻找两条路径最后一个公共节点
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
     * 有指向父节点的指针
     *
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

    /**
     * 多叉树，但不知道父节点，给出根节点
     * <p>
     * 从根节点开始，判断输入节点 A、B 是否在节点内
     * 在？遍历所有子节点，看A、B是否在各个子树中（进入到A、B节点所在的子树树根节点中）
     * <p>
     * 缺点：每判断一次输入节点是否在树内，该树下的所有节点被遍历一次。
     * 导致很多节点被重复遍历多次
     *
     * @param root
     * @param node1
     * @param node2
     * @return
     */
    public MultiTreeNode getClosestFather(MultiTreeNode root, MultiTreeNode node1, MultiTreeNode node2) {

        MultiTreeNode node = root;
        if (!inTree(node, node1, node2)) {
            return null;
        }
        int flag = 0;
        while (true) {
            flag = 0;
            ArrayList<MultiTreeNode> children = node.children;
            for (MultiTreeNode child : children) {
                if (inTree(child, node1, node2)) {
                    node = child;
                    flag = 1;
                    break;
                }
            }
            // flag = 0 说明两个输入节点在 以node 为根的树中，但不在以node的子节点为根的树中
            if (flag == 0) {
                return node;
            }
        }
    }

    /**
     * 判断输入节点 A/B 是否在以 root 为根的树
     * 层次遍历所有节点，当出现节点 node1 node2 时标识一下
     * 时间复杂度 :O(n)
     * 空间复杂度 :O(1)
     *
     * @param root
     * @param node1
     * @param node2
     * @return
     */
    public boolean inTree(MultiTreeNode root, MultiTreeNode node1, MultiTreeNode node2) {
        ArrayDeque<MultiTreeNode> queue = new ArrayDeque<>();
        boolean node1Exist = false, node2Exist = false;

        while (!queue.isEmpty() && (!node1Exist || !node2Exist)) {
            MultiTreeNode node = queue.poll();
            if (node == node1) {
                node1Exist = true;
            } else if (node == node2) {
                node2Exist = true;
            }
            ArrayList<MultiTreeNode> children = node.children;
            for (MultiTreeNode n : children) {
                queue.offer(n);
            }
        }
        return node1Exist && node2Exist;

    }

    /**
     * 对上面解法的优化
     * 寻找从根节点到两个目标节点H/G的路径  A->B->C->H   A->B->E->G
     * 然后找到两个路径的第一个分叉点（两个链表的最后一个共同节点）
     * <p>
     * 时间复杂度: O(n)
     * 空间复杂度: O(k) k 为根节点到目标节点的长度
     *
     * @param root
     * @param node1
     * @param node2
     * @return
     */
    public MultiTreeNode getClosestFather2(MultiTreeNode root, MultiTreeNode node1, MultiTreeNode node2) {

        boolean isExist = inTree(root, node1, node2);
        if (!isExist) return null; // 节点 node1 node2 不在以root为根的树中
        int count = getTreeNodeCount(root);
        MultiTreeNode[] node1Path = new MultiTreeNode[count]; // 最大路径长度
        MultiTreeNode[] node2Path = new MultiTreeNode[count]; // 最大路径长度
        node1Path[0] = root;
        node2Path[0] = root;
        int length1 = getPath(root, node1, node1Path, 1);
        int length2 = getPath(root, node2, node2Path, 1);

        int i = 0;
        MultiTreeNode commonFather = root;
        while (i < length1 && i < length2) {
            if (node1Path[i] == node2Path[i]) {
                commonFather = node1Path[i];
                i++;
            } else {
                break;
            }
        }
        return commonFather;
    }

    public int getTreeNodeCount(MultiTreeNode root) {
        if (root == null) return 0;
        int count = 0;
        ArrayDeque<MultiTreeNode> queue = new ArrayDeque<>();
        while (!queue.isEmpty()) {
            MultiTreeNode node = queue.poll();
            count++;
            ArrayList<MultiTreeNode> children = node.children;
            for (MultiTreeNode n : children) {
                queue.offer(n);
            }
        }
        return count;

    }

    /**
     * 获得从 root 到 节点 node 的路径
     * <p>
     * index 探索路径中第 index 位置的节点（从 0 算起）
     *
     * @param root
     * @param node
     * @return
     */
    public MultiTreeNode[] getPath(MultiTreeNode root, MultiTreeNode node) {
        if (root == null || node == null) return null;
        int maxPathLength = getTreeNodeCount(root);
        MultiTreeNode[] path = new MultiTreeNode[maxPathLength];
        int actualLength = getPath(root, node, path, 0);
        if (actualLength == 0) return null;

        return Arrays.copyOf(path, actualLength);
    }

    private int getPath(MultiTreeNode root, MultiTreeNode node, MultiTreeNode[] path, int index) {

        if (root == null) return 0;
        if (root == node) {
            path[index] = root;
            return index + 1; // 找到路径，返回路径长度
        }
        path[index] = root;
        ArrayList<MultiTreeNode> children = root.children;
        int length = 0;
        for (MultiTreeNode n : children) {
            if ((length = getPath(n, node, path, index + 1)) > 0) {
                return length;
            }
        }
        return 0;
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

        MultiTreeNode() {
            this.children = new ArrayList<>();

        }
    }
}
