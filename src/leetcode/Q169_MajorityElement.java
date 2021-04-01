package leetcode;

/**
 * created by Ethan-Walker on 2019/2/28
 */
public class Q169_MajorityElement {

    public int majorityElement(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        return kthSmallNum(nums, nums.length / 2 + 1, 0, nums.length - 1);
    }

    int kthSmallNum(int[] nums, int k, int left, int right) {
        int pivotIndex = partition(nums, left, right);
        int leftMidCount = pivotIndex - left + 1;
        if (leftMidCount == k) {
            return nums[pivotIndex];
        } else if (leftMidCount < k) {
            return kthSmallNum(nums, k - leftMidCount, pivotIndex + 1, right);
        } else {
            return kthSmallNum(nums, k, left, pivotIndex - 1);
        }

    }

    private int partition(int[] nums, int left, int right) {
        int pivotIndex = (int) (Math.random() * (right - left + 1)) + left;
        swap(nums, pivotIndex, right);
        int i = left, j = right;
        while (i < j) {
            while (i < j && nums[i] <= nums[right]) i++;
            while (i < j && nums[j] >= nums[right]) j--;
            if (i < j) {
                swap(nums, i, j);
            }
        }
        swap(nums, i, right);
        return i;
    }

    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

    public boolean checkMoreThanHalf(int[] nums, int key) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (key == nums[i]) count++;
        }
        return count > nums.length / 2;
    }

    public int majorityElement2(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        int count = 0, num = 0;
        for (int i = 0; i < nums.length; i++) {
            if (num == nums[i]) {
                count++;
            } else if (count == 0) {
                count = 1;
                num = nums[i];
            } else {
                count--;
            }
        }
        if (checkMoreThanHalf(nums, num)) {
            return num;
        }
        return -1;

    }
}
