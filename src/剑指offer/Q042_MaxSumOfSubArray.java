package 剑指offer;

/**
 * 连续子数组的最大和
 * created by Ethan-Walker on 2018/12/10
 */
public class Q042_MaxSumOfSubArray {


    /**
     * 数组数值分析
     * <p>
     * 时间复杂度： O(n)
     * 空间复杂度:  O(1)
     *
     * @param array
     * @return
     */
    public int getMaxSumOfSubArray(int[] array) {
        int maxSum = Integer.MIN_VALUE; // 设定为最小负值，因为可能会出现最大和为负数的情况
        int sum = 0;
        for (int i = 0; i < array.length; i++) {

            if (sum <= 0) {  // 如果之前的累和 < 0 ，再进行累加会使得后面的值减小，故sum应重置成 0
                sum = array[i];
            } else {
                sum += array[i];
            }
            // 每次累加之后，都要比较是否超过最大值
            if (sum > maxSum) {
                maxSum = sum;
            }
        }
        return maxSum;
    }

    /**
     * 时间复杂度: O(n)
     * 空间复杂度: O(n)
     * <p>
     * 动态规划
     * f[i] 表示以 a[i] 结尾的子数组的最大值
     * <p>
     * f[i]=
     * 1. a[i]
     * i=0|| f[i-1]<=0
     * 2. f[i-1]+a[i]
     *
     * @param array
     * @return
     */
    public int dp(int[] array) {
        int[] f = new int[array.length];
        int maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < array.length; i++) {
            if (i == 0 || f[i - 1] <= 0) {
                f[i] = array[i];
            } else {
                f[i] = f[i - 1] + array[i];
            }
            if (f[i] > maxSum) {
                maxSum = f[i];
            }
        }
        return maxSum;
    }
}
