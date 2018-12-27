package 程序员代码面试指南;

/**
 * 未排序正数数组中，累加和等于给定值的最长子数组长度
 * created by Ethan-Walker on 2018/12/27
 */
public class Q116_SubPositiveArrayMaxLength {

    /**
     * 简单思路，对所有的子数组 n(n+1)/2 进行求和 最好时间复杂度:O(n^2) 求和利用之前的结果
     * 时间复杂度: O(n^2)
     * 空间复杂度: O(1)
     */
    public int maxLengthSimple(int[] arr, int k) {
        int maxLength = 0, sum;

        for (int left = 0; left < arr.length; left++) {
            sum = 0;
            for (int right = left; right < arr.length; right++) {
                //a[left,right] 作为子树组
                sum += arr[right];
                if (sum == k && right - left + 1 > maxLength) {
                    maxLength = sum;
                }
            }
        }
        return maxLength;
    }

    /**
     * 时间复杂度: O(n) 每趟循环left 或者right 中至少有一个指针移动一次，循环最多指向 2n 次
     * 空间复杂度: O(1)
     *
     * @param arr
     * @param k
     * @return
     */
    public int maxLength(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k <= 0) return 0;

        int left = 0, right = 0;
        int sum = arr[0], maxLength = 0;

        // left==right 且a[left]>= k 的情况， 也可以处理（left 右移），不需要单独考虑
        while (right < arr.length) {
            if (sum == k) {
                if (right - left + 1 > maxLength) {
                    maxLength = right - left + 1;
                }
                sum -= arr[left++];
            } else if (sum > k) {
                sum -= arr[left++];
            } else {
                //sum<k, right 右移，sum 加上新元素 a[right+1] 之前要考虑了 right+1 是否超过数组范围了
                ++right;
                if (right < arr.length) {
                    sum += arr[right];
                }
            }
        }
        return maxLength;
    }


}
