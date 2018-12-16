package binary_tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * 多叉树
 * created by Ethan-Walker on 2018/12/16
 */
public class MultiTree<T> {


    MultiTreeNode<T> root;


    /**
     * 在树中寻找从根节点到 node
     *
     * @param node
     * @return
     */
    public MultiTreeNode[] getPath(MultiTreeNode node) {
        if (node == null) return null;
        int maxPathLength = getSize();
        MultiTreeNode[] path = new MultiTreeNode[maxPathLength];
        int actualLength = getPath(root, node, path, 0);
        if (actualLength == 0) return null;

        return Arrays.copyOf(path, actualLength);
    }

    public int getPath(MultiTreeNode root, MultiTreeNode node, MultiTreeNode[] path, int index) {
        if (root == null) return 0; // 说明找不到路径
        if (root == node) {
            path[index] = root;
            return index + 1;
        }
        path[index] = root; // 假定 root 在当前路径中，探索下一个节点
        ArrayList<MultiTreeNode> children = root.children;
        int length = 0;
        for (MultiTreeNode n : children) {
            if ((length = getPath(n, node, path, index + 1)) > 0) {
                return length;
            }
        }
        return 0;
    }

    /**
     * 获取树中的节点总数
     *
     * @return
     */
    public int getSize() {
        return getSize(root);
    }

    public int getSize(MultiTreeNode root) {
        if (root == null) return 0;
        ArrayList<MultiTreeNode> list = root.children;

        int count = 1;
        for (MultiTreeNode node : list) {
            count += getSize(node);
        }
        return count;
    }

    public MultiTreeNode getNodeByValue(T val) {
        // 可以按照二叉树的层次遍历进行，但是意义上不同
        if (root != null) {
            ArrayDeque<MultiTreeNode> queue = new ArrayDeque<>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                MultiTreeNode node = queue.poll();
                if (node.val.equals(val)) {
                    return node;
                }
                ArrayList<MultiTreeNode> children = node.children;
                for (MultiTreeNode n : children) {
                    queue.offer(n);
                }
            }
        }
        return null;
    }

    public MultiTreeNode getFirstCommonFather(MultiTreeNode node1, MultiTreeNode node2) {
        MultiTreeNode[] path1 = getPath(node1);
        MultiTreeNode[] path2 = getPath(node2);

        if (path1 == null || path2 == null) return null;
        int i = 0;
        MultiTreeNode father = root;  // 两个都找到路径，说明至少有一个共同的 root 节点
        while (i < path1.length && i < path2.length) {
            if (path1[i] == path2[i]) {
                father = path1[i];
                i++;
            } else {
                break;
            }
        }
        return father;

    }


}
