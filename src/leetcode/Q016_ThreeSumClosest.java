package leetcode;

import java.util.Arrays;

/**
 * Created by Ethan-Walker on 2018/6/12.
 */
public class Q016_ThreeSumClosest {

    public static void main(String[] args) {
        int i = threeSumClosest(new int[]{-1, 2, 1, -4}, 1);
        System.out.println(i);
    }

    /**
     * 思路：先排序，然后按照 2Sum 计算
     *
     * @param nums
     * @param target
     * @return
     */
    public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int result = 0;
        int minDiffValue = Integer.MAX_VALUE;

        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i - 1] == nums[i]) continue;

            int start = i + 1;
            int end = nums.length - 1;

            while (start < end) {
                int sum = nums[i] + nums[start] + nums[end];

                // 计算结果和 target 的差值
                int diffValue = Math.abs(sum - target);
                if (diffValue < minDiffValue) {
                    // 判断当前结果是否最接近 target
                    minDiffValue = diffValue;
                    result = sum;
                }

                if (sum == target) {
                    return target;
                } else if (sum < target) {
                    // 计算结果小于 target
                    start++;
                } else {
                    // 计算结果大于 target
                    end--;
                }
            }
        }
        return result;
    }
}
