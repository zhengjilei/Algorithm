package 剑指offer;

import org.junit.Test;

/**
 * 判断序列 A 是否是某个二叉搜索树的后序遍历序列
 * <p>
 * 确定二叉搜索树的后序遍历序列特征：
 * 1. 最后一个值 a[n-1] 为根节点的值
 * 2. 故a[0]~a[n-2] 中前 a[0]~a[k] 是a[n-1]的左子树，都<a[n-1]
 * 3. a[k+1] ~ a[n-2] 是 a[n-1] 的右子树 都 >a[n-1]
 * <p>
 * created by Ethan-Walker on 2018/12/7
 */
public class Q033_BSTPostOrder {
    public boolean VerifySquenceOfBST(int[] seq) {
        if(seq==null||seq.length==0) return false;

        return judgePostOrder(seq,0, seq.length-1);
    }
    /**
     * 判断序列 seq[0] ~ seq[length-1] 是否满足二叉搜索树的后序遍历特征
     *
     * @param seq
     * @return
     */
    public boolean judgePostOrder(int[] seq, int start, int end) {
        if (seq == null || end < start) return false;
        if (end == start) return true; // 只有  1  个元素

        int root = seq[end];

        int i = start;
        while (seq[i] < root) {
            i++;
        }
        // seq[start]~seq[i-1] 是左子树

        int j = i;
        while (seq[j] > root) {
            j++;
        }

        // 不管有没有右子树，j 都应该指向 end 位置
        if (j != end) return false;

        boolean left = true;
        if (i > start) {
            // 存在左子树
            left = judgePostOrder(seq, start, i - 1);
        }
        if (!left) return false;

        boolean right = true;
        if (j > i) {
            // 说明存在右子树
            right = judgePostOrder(seq, i, end - 1);  //seq[i]~seq[end-1] 是右子树

        }
        return right;
    }

    @Test
    public void test() {
        int[] a = new int[]{5, 7, 6, 9, 11, 10, 8};
        System.out.println(judgePostOrder(a, 0, a.length - 1));

        int[] b = new int[]{7, 4, 6, 5};
        System.out.println(judgePostOrder(b, 0, b.length - 1));
    }


}
