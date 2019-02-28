package leetcode;

/**
 * created by Ethan-Walker on 2019/2/28
 */
public class Q034_SearchRange {
    public int[] searchRange(int[] nums, int target) {

        int[] res = new int[2];
        res[0] = res[1] = -1;
        if (nums == null || nums.length == 0) return res;
        res[0] = getFirst(nums, target);
        res[1] = getLast(nums, target);
        return res;
    }

    public int getFirst(int[] nums, int target) {

        int l = 0, r = nums.length - 1;
        int res = -1;
        int mid;
        while (l <= r) {
            mid = (l + r) >> 1;
            if (target == nums[mid]) {
                res = mid;
                r = mid - 1;
            } else if (target > nums[mid]) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return res;
    }

    public int getLast(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        int mid;
        int res = -1;
        while (l <= r) {
            mid = (l + r) >> 1;
            if (target < nums[mid]) {
                r = mid - 1;
            } else if (target == nums[mid]) {
                res = mid;
                l = mid + 1;
            } else {
                l = mid + 1;
            }
        }
        return res;
    }
}
