package leetcode;

import java.util.Arrays;

/**
 * created by Ethan-Walker on 2019/3/12
 */
public class Q0628_MaximumProducr {
    public int maximumProduct(int[] nums) {

        Arrays.sort(nums);
        int t = nums[0] * nums[1];
        int tt = nums[nums.length - 3] * nums[nums.length - 2];

        return t > tt ? t * nums[nums.length - 1] : tt * nums[nums.length - 1];

    }

    /**
     * 全是正数: 三个最大的相乘  nums[len-3] *nums[len-2] * nums[len-1]
     * 全是负数: 三个最大的相乘（结果为负数，绝对值最小，乘积最大）
     * 一负多(>=3)正：三个最大的相乘
     * 两负多(>=3)正: nums[len-1] * Max (nums[0]* nums[1] , nums[len-3]* [len-2])
     * 多(>=3)负多正: 同上
     * 多负两正: nums[0] *nums[1] * nums[len-1]
     * 多负一正: 同上
     * <p>
     * 结论：遍历找到最大的三个数 max1 max2 max3，最小的两个数 min1 min2
     * 结果等于  max1 * Max( max2*max3, min1*min2)
     *
     * @param nums
     * @return
     */
    public int maximumProduct2(int[] nums) {
        int max1 = Integer.MIN_VALUE;  // 最大的数
        int max2 = Integer.MIN_VALUE;  // 第二大的数
        int max3 = Integer.MIN_VALUE;  // 第三大的数
        int min1 = Integer.MAX_VALUE;  // 最小的数
        int min2 = Integer.MAX_VALUE;  // 次小的数

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > max1) {
                max3 = max2;
                max2 = max1;
                max1 = nums[i];
            } else if (nums[i] > max2) {
                max3 = max2;
                max2 = nums[i];
            } else if (nums[i] > max3) {
                max3 = nums[i];
            }
            if (nums[i] < min1) {
                min2 = min1;
                min1 = nums[i];
            } else if (nums[i] < min2) {
                min2 = nums[i];
            }
        }
        return Math.max(max2 * max3, min1 * min2) * max1;
    }
}
