package leetcode2;

import java.util.Arrays;

/**
 * created by Ethan-Walker on 2019/3/2
 */
public class Q016 {

    public int threeSumClosest(int[] nums, int target) {
        if (nums == null || nums.length <= 2) return -1;
        Arrays.sort(nums);
        int left, right, sum;
        int minDiff = Integer.MAX_VALUE;
        int diff;
        int resultSum = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            left = i + 1;
            right = nums.length - 1;
            while (left < right) {
                sum = nums[i] + nums[left] + nums[right];
                diff = Math.abs(sum - target);
                if (diff == 0) {
                    return target;
                } else if (diff < minDiff) {
                    minDiff = diff;
                    resultSum = sum;
                }
                // 这里不会出现相等的情况了，相等的话 diff == 0 已经处理了
                if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return resultSum;
    }
}
