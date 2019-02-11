package 排列组合;

import java.util.ArrayList;
import java.util.List;

/**
 * created by Ethan-Walker on 2019/2/5
 */
public class KthPermutation {
    // 选中第一个数前，每个数开头的数量是 (n-1)! ，根据k推出结果的开头字母，再继续推接下来的字母
    public String getPermutation(int n, int k) {
        List<Integer> nums = new ArrayList<>(); // 存放数字 1/2/3/4/5...
        int ave = 1;
        for (int i = 0; i < n - 1; i++) {
            nums.add(i + 1);
            ave *= (i + 1);
        }
        // 初始时 ave = (n-1)!     以每个数开头的平均数量
        nums.add(n);
        StringBuilder sb = new StringBuilder();
        recur(nums, nums.size(), 0, ave, sb, k - 1);
        return sb.toString();
    }

    // k 表示在 剩余集合 nums 的全排列的第 k 个( k 是下标，从 0 开始)
    // ave 表示以集合中各个数开始的平均排列个数
    // 确定 index 位置的数，每确定一个数，将集合中该数移除
    public void recur(List<Integer> nums, int n, int index, int ave, StringBuilder sb, int k) {

        // 选到 index 位置的数的 下标
        int i = k / ave;             // 选中num[i] 到 sb[index]
        sb.append(nums.get(i));
        if (index == n - 1) {        // n个数全部确定
            return;
        }
        nums.remove(i);              // 从集合中移除已选定的数
        k = k % ave;                 // 对应在 ave 个数的排列中位置
        ave /= nums.size();          // 剩余数的全部排列中: 以各个数为开始的平均排列个数  (n-1)! -> (n-2)!  -> (n-3)!
        recur(nums, n, index + 1, ave, sb, k);
    }
}
