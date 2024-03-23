package leetcode;

/**
 * created by Ethan-Walker on 2019/2/19
 */
public class Q0033_SearchRotateMin {
    public int search(int[] nums, int target) {

        if (nums == null || nums.length == 0) return -1;
        int left = 0, right = nums.length - 1, mid = 0;
        while (left <= right) {
            mid = (left + right) >> 1;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > nums[right]) {  // 将 nums[mid] > nums[right] 放在前面,因为 nums[mid]==nums[right] 和 nums[mid]<nums[right] 采取一样的处理
                // [left,mid] 有序，[mid+1,right] 无序
                if (target >= nums[left] && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                // [mid,right] 有序，[left,mid-1]无序
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }
}
