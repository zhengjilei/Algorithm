package leetcode;

import org.junit.Test;

/**
 * created by Ethan-Walker on 2019/2/28
 */
public class Q268_MissingNumber {



    // 无序状态处理
    public int missingNumber2(int[] nums) {
        int n = nums.length;
        int sum = n * (n + 1) / 2;
        int sum2 = 0;
        for (int i = 0; i < n; i++) {
            sum2 += nums[i];
        }
        return sum - sum2;
    }

    @Test
    public void test() {
        System.out.println(missingNumber2(new int[]{3, 0, 1}));

    }
}
