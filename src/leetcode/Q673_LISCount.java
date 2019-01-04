package leetcode;

import org.junit.Test;

/**
 * 求最长递增子序列的个数
 * 思路: 计算出 dp ,然后 dp 逆序统计
 * created by Ethan-Walker on 2019/1/3
 */
public class Q673_LISCount {


    /**
     * dp[i] 表示以 nums[i] 结尾的最长递增子序列 序列长度
     * cnt[i] 表示以 nums[i]结尾的最长递增子序列 不同序列种类总数
     *
     * @param nums
     * @return
     */
    public int findNumberOfLIS(int[] nums) {
        int maxLength = 1, res = 0, n = nums.length;
        int[] dp = new int[n];
        int[] cnt = new int[n];

        int maxLengthIndex = 0;
        for (int i = 0; i < n; ++i) {
            dp[i] = cnt[i] = 1;  // i= 0,初始化为 1 很重要
            for (int j = 0; j < i; ++j) {
                if (nums[j] < nums[i]) {
                    if (dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1; // 更新当前以 nums[i] 结尾的最长递增子序列长度
                        cnt[i] = cnt[j]; // 以 nums[i] 结尾的最长递增子序列个数 等于以 nums[j] 结尾的最长递增子序列种类个数
                        // 因为仅仅将 nums[i] 添加到原来以  nums[i] 结尾的字符串
                    } else if (dp[j] + 1 == dp[i]) {
                        // 这里不需要更新 dp[i], 因为已经是目标值了
                        // 但是以 nums[j] 结尾的最长递增子序列 可以替代以 nums[i]结尾的最长递增子序列除了 nums[i]的所有元素
                        // 故增加的种类即为以nums[j]结尾的最长递增子序列的种类个数
                        cnt[i] += cnt[j];
                    }
                }

            }
            maxLength = dp[i] > maxLength ? dp[i] : maxLength;
        }
        for (int i = 0; i < n; ++i)
            if (dp[i] == maxLength) res += cnt[i]; // 可能有多个 dp[i] == maxLength, 全部加上{1,1,1,1,1}

        return res;
    }

    /**
     * ends[0..right] 有效区
     * ends[a] = b 遍历到当前节点时，最长递增子序列长度为 a+1 时，最小的结尾数为 b
     *
     * @param arr
     * @return
     */
    public int[] getDp(int[] arr) {

        int[] ends = new int[arr.length];
        int[] dp = new int[arr.length];
        ends[0] = arr[0];
        dp[0] = 1;
        int right = 0;
        for (int i = 1; i < arr.length; i++) {
            // 在 ends[0...right] 中找第一个大于等于 arr[i] 的数
            int firstNotLess = -1;
            int l = 0, r = right, mid = 0;
            while (l <= r) {
                mid = (l + r) >> 1;
                if (arr[mid] < arr[i]) {
                    l = mid + 1;
                } else {
                    firstNotLess = mid;
                    r = mid - 1; // 继续往左找
                }
            }

            if (firstNotLess == -1) {
                // 没找到比 arr[i] 小的数
                ends[++right] = arr[i];
            } else {
                ends[firstNotLess] = arr[i];
            }
            dp[i] = right + 1;
        }
        return dp;
    }

    @Test
    public void test() {
        int[] a = {2, 1, 5, 3, 6, 4, 8, 9, 7};
        System.out.println(findNumberOfLIS(a));
    }

}
