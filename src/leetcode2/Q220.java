package leetcode2;

/**
 * created by Ethan-Walker on 2019/3/21
 */
public class Q220 {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length && j <= i + k; j++) {
                if (Math.abs((long) nums[i] - nums[j]) <= t && j - i <= k) return true;
            }
        }
        return false;
    }
}
