package leetcode2;

import org.junit.Test;

/**
 * created by Ethan-Walker on 2019/3/16
 */
public class Q041 {
    public int firstMissingPositive(int[] nums) {
        int len = nums.length;
        //将所有可以调整的数[1,n]，调整到正确的位置 [0,n-1]
        for (int i = 0; i < len; i++) {
            if (nums[i] != i + 1) { // 当前位置的数值不匹配下标
                // 这里是: nums[i]!=nums[nums[i]-1] , 不是 nums[i]!= i+1 , 是为了避免 nums[i]-1 位置上已经放置的是 nums[i] 值了，导致死循环
                while (nums[i] > 0 && nums[i] <= len && nums[i] != nums[nums[i] - 1]) {
                    swap(nums, i, nums[i] - 1); // 将位置 i的数 nums[i] 调整到正确的位置 nums[i] - 1
                }
            }

        }
        for (int i = 0; i < len; i++) {
            if (nums[i] != i + 1) return i + 1;
        }
        return len + 1;
    }

    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }


    @Test
    public void test() {
        System.out.println(firstMissingPositive(new int[]{1, 1}));
    }
}
