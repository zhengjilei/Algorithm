package leetcode2;

import org.junit.Test;

/**
 * created by Ethan-Walker on 2019/2/26
 */
public class Q0215_KthMax {

    public int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0 || k > nums.length) return -1;
        return findKth(nums, k, 0, nums.length - 1);
    }

    public int findKth(int[] nums, int k, int start, int end) {
        int pivotIndex = partition(nums, start, end);
        int leftMidCount = pivotIndex - start + 1;
        if (leftMidCount == k) {
            return nums[pivotIndex];
        } else if (leftMidCount < k) {
            return findKth(nums, k - leftMidCount, pivotIndex + 1, end);
        } else {
            return findKth(nums, k, start, pivotIndex - 1);
        }
    }

    public int partition(int[] nums, int start, int end) {
        int pivotIndex = (int) (Math.random() * (end - start + 1)) + start;
        int pivot = nums[pivotIndex];
        swap(nums, pivotIndex, end);
        int i = start, j = end;
        while (i < j) {
            while (i < j && nums[i] >= pivot) i++;
            while (i < j && nums[j] <= pivot) j--;
            if (i < j) {
                swap(nums, i, j);
            }
        }
        swap(nums, end, i);
        return i;
    }

    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

    @Test
    public void test() {
        int[] a = new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6};
        System.out.println(findKthLargest(a, 4));
    }
}
