package leetcode;

/**
 * 最长连续递增子序列
 * longest successive increment sequence
 * <p>
 * created by Ethan-Walker on 2019/1/3
 */
public class Q674_LSIS {

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
}
