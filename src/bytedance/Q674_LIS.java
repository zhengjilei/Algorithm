package bytedance;

import org.junit.Test;

/**
 * created by Ethan-Walker on 2019/2/3
 */
public class Q674_LIS {
    public int findLengthOfLCIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int[] dp = new int[nums.length]; // dp[i]以 nums[i] 结尾的最长连续递增序列
        dp[0] = 1;
        int max = 1;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;
            if (nums[i] > nums[i - 1]) {
                dp[i] = dp[i - 1] + 1;
            }
            if (dp[i] > max) {
                max = dp[i];
            }
        }
        return max;
    }

    @Test
    public void test() {
        System.out.println(findLengthOfLCIS(new int[]{1, 3, 5, 4, 7}));
        System.out.println(findLengthOfLCIS(new int[]{2, 2, 2, 2, 2}));
    }
}
