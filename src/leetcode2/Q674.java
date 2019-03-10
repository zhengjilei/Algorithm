package leetcode2;

/**
 * created by Ethan-Walker on 2019/3/9
 */
public class Q674 {

    public int findLengthOfLCIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int maxLen = 1;
        int[] dp = new int[nums.length];
        dp[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;
            if (nums[i] > nums[i - 1]) {
                dp[i] = dp[i - 1] + 1;
                if (dp[i] > maxLen) {
                    maxLen = dp[i];
                }
            }
        }

        return maxLen;
    }

    public int findLengthOfLCIS1(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int maxLen = 1;
        int dp = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                dp = dp + 1;
            } else {
                dp = 1;
            }
            if (dp > maxLen) {
                maxLen = dp;
            }
        }

        return maxLen;
    }
}
