package leetcode;

import org.junit.Test;

/**
 * created by Ethan-Walker on 2019/2/28
 */
public class Q0268_MissingNumber {

    /**
     * 异或
     * 数组中所有数的范围介于[0,n] , 下标包括[0,n-1]
     * 数组中各个下标 + 下标对应的数 ，两种情况
     * 1. 不包括 n , 即 2 个[0,n-1] ,loss = n
     * 2. 包括 n， 缺少[0,n-1] 中的其中一个数 loss, 但是其他数都是成对的(除了 n)
     * 设置初始sum = n
     * sum和 数组下标及下标对应的数 进行异或运算，异或的结果就是 缺失的数
     *
     * @param nums
     * @return
     */
    public int missingNumber(int[] nums) {

        int sum = nums.length;
        for (int i = 0; i < nums.length; i++) {
            sum = sum ^ i ^ nums[i];
        }
        return sum;
    }

    /**
     * 无序状态:求和
     */
    public int missingNumber2(int[] nums) {
        int n = nums.length;
        int sum = n * (n + 1) / 2;
        int sum2 = 0;
        for (int i = 0; i < n; i++) {
            sum2 += nums[i];
        }
        return sum - sum2;
    }

    public int missingNumber3(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            // 遇到越界(<数组下标或者 >数组最大下标)的值, 忽略
            // 没有越界的值，如果nums[i]!=i ，则将 nums[i] 位置的数和 当前位置 i 的数交换，将 nums[i] 放到正确的位置上
            while (nums[i] < nums.length && nums[i] != i) { // 直到当前位置 i  的数等于 i，或者当前位置i 的数>= nums.length 无法调整
                swap(nums, i, nums[i]);
            }
        }
        // 遍历完之后，能保证每个位置i 要么等于 i, 要么不等于i 但是无法调整

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i) return i;
        }
        return nums.length;

    }

    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

    @Test
    public void test() {
        System.out.println(missingNumber2(new int[]{3, 0, 1}));
    }
}
