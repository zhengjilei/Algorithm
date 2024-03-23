package leetcode;

import org.junit.Test;

/**
 * created by Ethan-Walker on 2019/3/14
 */
public class Q0041_FirstLostNum {

    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }


    /**
     * 由于要找第一个未出现的正数，故考虑的数从 1开始，将数组中 1/2/3... 调整到数组中正确的位置  => 位置为 i ，应该放置 i+1
     * 再次遍历: a[i] !=i+1，说明 i+1 是缺少的最小正数
     *
     * @param nums
     * @return
     */
    public int firstMissingPositive(int[] nums) {


        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) { // i位置没有放正确的数，尝试将 nums[i] 调整到正确的位置
                // 如果i位置上的数 nums[i] <=0, 或者>n, 忽略，因为这个数无法调到正确的位置
                // 直到数 nums[i] 调整到其应该在的位置: nums[i]-1  即 nums[nums[i]-1] = nums[i]
                while (nums[i] > 0 && nums[i] <= nums.length && nums[nums[i] - 1] != nums[i]) {
                    // 将位置 i的值 nums[i] 交换到正确的位置: nums[i]-1
                    swap(nums, i, nums[i] - 1);
                }
            }

        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) return i + 1;
        }
        // [0,nums.length-1] 位置上的数都不缺
        return nums.length + 1;

    }

    @Test
    public void test() {
        int[] a = new int[]{-2, 1, 4, -1, 2};
        System.out.println(firstMissingPositive(a));
    }

}
