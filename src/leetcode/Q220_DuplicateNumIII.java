package leetcode;

/**
 * created by Ethan-Walker on 2019/2/18
 */
public class Q220_DuplicateNumIII {

    /**
     * 时间复杂度:O(nk)
     * 空间复杂度:O(1)
     * @param nums
     * @param k
     * @param t
     * @return
     */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (nums == null || nums.length <= 1) return false;
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length && j <= i + k; j++) {
                if (Math.abs((long) nums[i] - nums[j]) <= t) return true; //注意转换成 Long ，否则可能会溢出导致结果错误
            }
        }
        return false;
    }

}
