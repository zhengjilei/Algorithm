package leetcode;

import java.util.Arrays;

/**
 * created by Ethan-Walker on 2019/2/3
 */
public class Q031_NextPermutation {

    public void nextPermutation(int[] nums) {

        int j = nums.length - 2;
        while (j >= 0 && nums[j] >= nums[j + 1]) j--;

        if (j == -1) {
            Arrays.sort(nums);
            return;
        }
        int minIndex = j + 1;
        for (int k = minIndex + 1; k < nums.length; k++) {
            if (nums[k] > nums[j] && nums[k] <= nums[minIndex]) {
                minIndex = k;
            }
        }
        // minIndex 指向[j+1,...) 大于nums[j] 的最小元素
        swap(nums, j, minIndex);

        // [j...之后的元素全为逆序，反转即得到升序
        reverse(nums, j + 1, nums.length - 1);
    }

    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }

    public void swap(int[] nums, int a, int b) {
        int t = nums[a];
        nums[a] = nums[b];
        nums[b] = t;
    }
}
