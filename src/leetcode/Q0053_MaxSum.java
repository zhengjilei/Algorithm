package leetcode;

/**
 * created by Ethan-Walker on 2019/3/12
 */
public class Q0053_MaxSum {
    public int maxSubArray(int[] nums) {
        int max = nums[0];
        int sum = nums[0]; // sum[i] 为以 nums[i] 结尾的连续子数组的最大和
        for (int i = 1; i < nums.length; i++) {
            if (sum > 0) {
                sum += nums[i];
            } else {
                sum = nums[i];
            }
            if (sum > max) {
                max = sum;
            }
        }
        return max;
    }
}
