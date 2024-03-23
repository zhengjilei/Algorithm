package leetcode;

/**
 * created by Ethan-Walker on 2019/3/12
 */
public class Q0035_SearchInsetIndex {
    public int searchInsert(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        int mid;
        while (l < r) {
            mid = l + r >> 1;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }
}
