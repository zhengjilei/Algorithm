package 剑指offer;

/**
 * 输入一个整数序列，判断是否是二叉搜索树的前序遍历结果
 * created by Ethan-Walker on 2018/12/7
 */
public class Q033A_BSTPreOrder {

    public boolean isBSTPreOrder(int[] seq, int start, int end) {
        if (seq == null || end < start) return false;
        if (start == end) return true;

        int root = seq[start];
        int i = start + 1;

        while (i <= end && seq[i] < root) {
            i++;
        }
        // i=end+1 说明只有左子树
        int j = i;
        while (j <= end && seq[i] > root) {
            j++;
        }

        // 如果 j<=end 说明右子树后面还有 空余,错误
        if (j <= end) return false;

        boolean left = true;
        if (i > start) { // 有左子树
            left = isBSTPreOrder(seq, start + 1, i - 1);
        }
        if (!left) return false;

        boolean right = true;
        if (j > i) { // 有右子树
            right = isBSTPreOrder(seq, j, end);
        }
        return right;
    }
}
