package leetcode2;

/**
 * created by Ethan-Walker on 2019/3/16
 */
public class Q0268 {

    public int missingNumber(int[] nums) {

        int len = nums.length;
        for (int i = 0; i < len; i++) {
            while (nums[i] < len && nums[i] != nums[nums[i]]) {
                swap(nums, i, nums[i]);
            }
        }
        for (int i = 0; i < len; i++) {
            if (nums[i] != i) {
                return i;
            }
        }
        return len;

    }

    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
