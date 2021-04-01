package leetcode;

/**
 * created by Ethan-Walker on 2019/3/12
 */
public class Q152_MaxProduct {
    public int maxProduct(int[] nums) {
        int imax = 1, imin = 1, max = Integer.MIN_VALUE; // imax imin表示 以nums[i] 结尾的乘积的最大值和最小值

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0) {
                int temp = imax;
                imax = imin;
                imin = temp;
            }

            imax = Math.max(imax * nums[i], nums[i]);
            imin = Math.min(imin * nums[i], nums[i]);
            if (imax > max) max = imax;
        }
        return max;
    }
}
