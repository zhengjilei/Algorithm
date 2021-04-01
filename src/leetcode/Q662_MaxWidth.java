package leetcode;

import programmer_interview.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * created by Ethan-Walker on 2019/3/10
 */
public class Q662_MaxWidth {
    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        int[] res = new int[1];
        width(root, 0, 1, new ArrayList<>(), res);
        return res[0];
    }

    /**
     * 二叉树各个节点编号, 根节点从 0 编号, 左子节点为 2*i+1, 右子节点 2*i+2
     * 层 level 从 1 开始编号
     * List 只保存每一层的最左节点的编号 ( level > list.size() )
     * dfs 遍历，每当遍历一个节点，首先判断是否是该层的最左节点，是的话加入
     * 然后计算当前节点离 最左节点的距离（当前节点编号 - 当前层的最左节点编号 + 1），> maxWidth, 则赋值到 maxWidth
     * 搜索 左右子树
     * @param node
     * @param index
     * @param level
     * @param leftList
     * @param res
     */
    public void width(TreeNode node, int index, int level, List<Integer> leftList, int[] res) {
        if (node == null) return;
        if (level > leftList.size()) leftList.add(index);
        res[0] = Math.max(res[0], index - leftList.get(level - 1) + 1); // node 节点离 当前层最左边的节点的距离

        width(node.left, 2 * index + 1, level + 1, leftList, res);
        width(node.right, 2 * index + 2, level + 1, leftList, res);
    }
}

