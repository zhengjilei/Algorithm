package programmer_interview;

import java.util.HashMap;

/**
 * 在二叉树中找到累加和为指定值的最长路径长度
 * <p>
 * 之前分析过在数组中找到累加和为指定值的最长子数组长度，这里是二叉树，可将二叉树的每一条从 根节点到叶节点的路径视为一个数组
 * 每一层类比为数组的索引，最长路径长度即为 层之间的差值
 * <p>
 * 可以通过前序遍历，访问每一个"数组"（从根节点到叶节点的路径）
 * 但是每个数组处理完之后，会导致 map 中出现一些之前数组多余的 key 项，故前序遍历每退出一层时，就要删除map中该层对应的 key value 项
 * <p>
 * created by Ethan-Walker on 2018/12/27
 */
public class Q036_SumOfBTMaxLength {


    public int getMaxLength(TreeNode root, int sum) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1); //
        return preOrder(0, sum, 0, 0, root, map);
    }


    public int preOrder(int level, int targetSum, int preSum, int maxLen, TreeNode node, HashMap<Integer, Integer> map) {
        if (node == null) return maxLen;

        int curSum = preSum + node.val;         // sum
        if (map.containsKey(curSum - targetSum)) {  // curSum-targetSum 即为 数组处理中的 sum-k (当前路径中的 a[0]~a[j] 之和)
            int len = level - map.get(curSum - targetSum);  //  a[j+1]~a[i] 之和等于 k(target) 找到子路径，计算子路径长度
            if (len > maxLen) {
                maxLen = len;
            }
        }

        map.putIfAbsent(curSum, level);  // 若 map 中不存在 <curNum,XX> 项才放入，否则会覆盖等于 curSum 的最低层位置

        maxLen = preOrder(level + 1, targetSum, curSum, maxLen, node.left, map);
        maxLen = preOrder(level + 1, targetSum, curSum, maxLen, node.right, map);

        // 回退，删除 map 中包括 key = curSum, value = level 的项, 为处理其他数组（从根节点到叶节点的路径）
        if (map.get(curSum) == level) {
            map.remove(curSum);
        }

        return maxLen;
    }
}
