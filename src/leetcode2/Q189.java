package leetcode2;

/**
 * created by Ethan-Walker on 2019/3/2
 */
public class Q189 {
    public void rotate(int[] nums, int k) {
        k = k % nums.length;
        if (k == 0) return;

        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    public void reverse(int[] chs, int start, int end) {
        while (start < end) {
            swap(chs, start, end);
            start++;
            end--;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
