package leetcode;

/**
 * 最长连续递增子序列
 * longest successive increment sequence
 * <p>
 * created by Ethan-Walker on 2019/1/3
 */
public class Q0674_LSIS {

    /**
     * 用一个变量prev存储最近连续递增序列的末尾元素
     * 用 start 、end 作为最近连续递增序列的开始、结束位置
     *
     * @param nums
     * @return
     */
    public int findLengthOfLSIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return 1;
        int start = 0, end = 0;
        int prev = nums[start];
        int maxLength = 1;
        while (end < nums.length) {
            prev = nums[start];
            while (end < nums.length && nums[end] > prev) {
                prev = nums[end];
                end++;
            }
            if (end - start > maxLength) {
                maxLength = end - start;
            }
            start = end;
            end++;
        }
        return maxLength;
    }

    /**
     * 动态规划
     * dp[i]=k 表示以 nums[i] 结尾的最长连续递增序列长度为k
     * 初始化 dp[i] = 1
     * 如果 nums[i]>nums[i-1] dp[i] = dp[i-1] + 1
     * <p>
     * 时间复杂度:O(n)
     * 空间复杂度:O(1)
     *
     * @param nums
     * @return
     */
    public int findLengthOfLCIS2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int[] dp = new int[nums.length]; //
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

    /**
     * 动态规划，空间复杂度降为O(1)
     *
     * @param nums
     * @return
     */
    public int findLengthOfLCIS3(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int dp = 1; //
        int max = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                dp = dp + 1;
            } else {
                dp = 1;             // dp = 1 不能省略
            }
            if (dp > max) {
                max = dp;
            }
        }
        return max;
    }
}
